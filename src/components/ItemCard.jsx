import FastImage from 'react-native-fast-image';
import React, {useState, useEffect} from 'react';
import {View, Text, StyleSheet, TouchableOpacity} from 'react-native';
import {useNavigation} from '@react-navigation/native';
import {useSelector, useDispatch} from 'react-redux';

function truncate(str, n) {
  return str?.length > n ? str.substr(0, n - 1) + '...' : str;
}

export default function ItemCard({id, name, description, price, image}) {
  const [localQuantity, setLocalQuantity] = useState(0);
  const selectedItems = useSelector(state => state.cartReducer.items);

  useEffect(() => {
    const selectedItem = selectedItems.find(item => item.id === id);
    if (selectedItem) {
      setLocalQuantity(selectedItem.quantity);
    }
  }, [selectedItems, id]);
  const navigation = useNavigation();
  const dispatch = useDispatch();
  const handleSeeDetails = () => {
    navigation.navigate('ItemDetails', {
      name,
      description,
      price,
      image,
    });
  };

  const handleIncrease = () => {
    setLocalQuantity(prevQuantity => prevQuantity + 1);
    const item = {id};

    dispatch({type: 'INCREASE_QUANTITY', payload: item});
  };

  const handleDecrease = () => {
    if (localQuantity - 1 > 0) {
      setLocalQuantity(prevQuantity => prevQuantity - 1);
      const item = {id};

      dispatch({type: 'DECREASE_QUANTITY', payload: item});
    } else {
      setLocalQuantity(prevQuantity => prevQuantity - 1);
      const item = {id};
      dispatch({type: 'REMOVE_ITEM_FROM_CART', payload: item});
    }
  };

  const handleAddToCart = () => {
    setLocalQuantity(prevQuantity => prevQuantity + 1);
    const item = {
      id,
      name,
      description,
      image,
      price,
      quantity: localQuantity + 1,
    };
    dispatch({type: 'ADD_TO_CART', payload: item});
  };

  const truncatedDescription = truncate(description, 50);

  return (
    <View style={styles.card}>
      <FastImage
        style={styles.image}
        source={{
          uri: `data:image/png;base64,${image}`,
          priority: FastImage.priority.normal,
        }}
        resizeMode={FastImage.resizeMode.cover}
      />
      <View style={styles.info}>
        <Text style={styles.name}>{name}</Text>
        <Text numberOfLines={2} style={styles.description} ellipsizeMode="tail">
          {truncatedDescription}
        </Text>
        <View style={styles.priceContainer}>
          <Text style={styles.price}>Rs. {price}</Text>
          <View style={styles.buttonContainer}>
            <View style={styles.quantityContainer}>
              {localQuantity === 0 ? (
                <TouchableOpacity
                  onPress={handleAddToCart}
                  style={styles.addButton}>
                  <Text style={styles.addButtonLabel}>Add</Text>
                </TouchableOpacity>
              ) : (
                <>
                  <TouchableOpacity
                    onPress={handleDecrease}
                    style={styles.quantityButton}>
                    <Text style={styles.quantityText}>-</Text>
                  </TouchableOpacity>
                  <Text style={styles.quantity}>{localQuantity}</Text>
                  <TouchableOpacity
                    onPress={handleIncrease}
                    style={styles.quantityButton}>
                    <Text style={styles.quantityText}>+</Text>
                  </TouchableOpacity>
                </>
              )}
            </View>
            <TouchableOpacity onPress={handleSeeDetails}>
              <Text style={styles.detailsButtonText}>See Details</Text>
            </TouchableOpacity>
          </View>
        </View>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  card: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: 'white',
    borderRadius: 20,
    padding: 15,
    marginBottom: 10,
    marginTop: 5,
    elevation: 5,
  },
  image: {
    width: 120,
    height: 120,
    borderRadius: 10,
  },
  info: {
    flex: 1,
    paddingLeft: 15,
  },
  name: {
    fontSize: 18,
    fontWeight: 'bold',
    color: 'black',
  },
  description: {
    color: 'gray',
    width: 190,
  },
  priceContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginTop: 10,
  },
  price: {
    fontSize: 18,
    color: '#fdab00',
    fontWeight: 'bold',
  },
  quantityContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    borderRadius: 20,
    backgroundColor: '#fdba00',
    paddingVertical: 5,
    paddingHorizontal: 12,
  },
  quantityButton: {
    borderRadius: 20,
    padding: 3,
  },
  addButton: {
    borderRadius: 20,
    backgroundColor: '#fdba00',
    paddingHorizontal: 16,
    paddingVertical: 8.5,
  },
  addButtonLabel: {
    color: 'white',
    fontSize: 14,
    fontWeight: 'bold',
    marginTop: -3.2,
    marginBottom: -2.1,
  },
  quantity: {
    paddingHorizontal: 10,
    fontWeight: 'bold',
    color: 'white',
  },
  quantityText: {
    color: 'white',
    fontSize: 18,
    fontWeight: 'bold',
  },
  detailsButtonText: {
    marginTop: 3,
    color: 'gray',
    fontSize: 13,
    marginLeft: 6,
  },
});
