import React, {useState} from 'react';
import {View, Text, TouchableOpacity, Image, StyleSheet} from 'react-native';
import FastImage from 'react-native-fast-image';
import {useNavigation} from '@react-navigation/native';

// Truncate function
function truncate(str, n) {
  return str?.length > n ? str.substr(0, n - 1) + '...' : str;
}

export default function ItemCard({name, description, price, image}) {
  const [quantity, setQuantity] = useState(0);
  const navigation = useNavigation(); // Initialize navigation using the hook

  const handleSeeDetails = () => {
    navigation.navigate('ItemDetails', {
      name,
      description,
      price,
      image,
    });
  };

  const handleIncrease = () => {
    setQuantity(prevQuantity => prevQuantity + 1);
  };

  const handleDecrease = () => {
    if (quantity > 0) {
      setQuantity(prevQuantity => prevQuantity - 1);
    }
  };

  const truncatedDescription = truncate(description, 50); // Adjust the length as needed

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
          <View>
            <View style={styles.quantityContainer}>
              {quantity === 0 ? (
                <TouchableOpacity
                  onPress={handleIncrease}
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
                  <Text style={styles.quantity}>{quantity}</Text>
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
    elevation: 4,
  },
  image: {
    width: 100,
    height: 100,
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
    backgroundColor: '#fdba00', // Active color
    paddingVertical: 5,
    paddingHorizontal: 12,
  },
  quantityButton: {
    borderRadius: 20,
  },
  addButton: {
    borderRadius: 20,
    backgroundColor: '#fdba00', // Active color
    paddingHorizontal: 12,
    paddingVertical: 5,
  },
  addButtonLabel: {
    color: 'white',
    fontSize: 14,
    fontWeight: 'bold',
    marginTop: -3.2,
    marginBottom: -2.1,
  },
  disabledButton: {
    display: 'none',
    margin: 10,
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
    color: 'gray',
  },
});
