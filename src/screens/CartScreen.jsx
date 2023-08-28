import React, {useState} from 'react';
import {
  View,
  Text,
  ScrollView,
  FlatList,
  TouchableOpacity,
  StyleSheet,
  Image,
} from 'react-native';
import {useSelector} from 'react-redux';
import ItemCard from '../components/ItemCard';
import Navbar from '../components/Navbar';

const CartScreen = ({navigation}) => {
  const [selectedTab, setSelectedTab] = useState('pickup');
  const items = useSelector(state => state.cartReducer.items);

  const handleSelectAddress = () => {
    navigation.navigate('SelectAddress');
  };

  const calculateTotal = () => {
    let total = 0;
    items.forEach(item => {
      total += item.price * item.quantity;
    });
    return total;
  };

  const renderItem = ({item}) => (
    <ItemCard
      id={item.id}
      name={item.name}
      description={item.description}
      price={item.price}
      image={item.image}
    />
  );

  return (
    <View style={styles.container}>
      <Navbar />
      <ScrollView>
        {items.length === 0 ? (
          <View style={styles.emptyCartContainer}>
            <Image
              source={require('../assets/images/delivery.gif')}
              style={styles.emptyCartImage}
            />
            <Text style={styles.emptyCartText}>Your cart is empty.</Text>
          </View>
        ) : (
          <>
            <FlatList
              data={items}
              keyExtractor={item => item.id.toString()}
              renderItem={renderItem}
            />
            <View style={styles.tabContainer}>
              <TouchableOpacity
                style={[
                  styles.tabButton,
                  selectedTab === 'pickup' && styles.activeTabButton,
                ]}
                onPress={() => setSelectedTab('pickup')}>
                <Text
                  style={[
                    styles.tabButtonText,
                    selectedTab === 'pickup' && styles.activeTabButtonText,
                  ]}>
                  Pickup
                </Text>
              </TouchableOpacity>
              <TouchableOpacity
                style={[
                  styles.tabButton,
                  selectedTab === 'delivery' && styles.activeTabButton,
                ]}
                onPress={() => setSelectedTab('delivery')}>
                <Text
                  style={[
                    styles.tabButtonText,
                    selectedTab === 'delivery' && styles.activeTabButtonText,
                  ]}>
                  Delivery
                </Text>
              </TouchableOpacity>
            </View>
            {selectedTab === 'delivery' && (
              <View style={styles.selectAddressContainer}>
                <TouchableOpacity
                  style={styles.selectAddressButton}
                  onPress={handleSelectAddress}>
                  <Text style={styles.selectAddressButtonText}>
                    Select Address
                  </Text>
                </TouchableOpacity>
              </View>
            )}
            <View style={styles.totalContainer}>
              <Text style={styles.totalText}>
                Total: Rs. {calculateTotal()}
              </Text>
            </View>
          </>
        )}
      </ScrollView>
      {items.length > 0 && (
        <TouchableOpacity style={styles.placeOrderButton}>
          <Text style={styles.placeOrderButtonText}>Place Order</Text>
        </TouchableOpacity>
      )}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  emptyCartContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  emptyCartText: {
    fontSize: 18,
    fontWeight: 'bold',
    color: 'gray',
  },
  emptyCartImage: {
    height: 300,
    width: 300,
  },
  tabContainer: {
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'white',
    paddingVertical: 10,
  },
  tabButton: {
    flex: 1,
    alignItems: 'center',
    paddingVertical: 10,
    borderWidth: 1,
    borderColor: '#fdab00',
    paddingLeft: 10,
  },
  activeTabButton: {
    backgroundColor: '#fdab00',
    borderColor: '#fdab00',
    borderRadius: 15,
  },
  tabButtonText: {
    color: '#fdab00',
  },
  activeTabButtonText: {
    color: 'white',
  },
  selectAddressContainer: {
    alignItems: 'center',
    borderBottomWidth: 1,
    borderColor: '#fdab00',
    paddingVertical: 10,
  },
  selectAddressButton: {
    marginTop: 10,
  },
  selectAddressButtonText: {
    color: '#fdab00',
  },
  totalContainer: {
    borderTopWidth: 1,
    marginTop: 20,
    paddingTop: 10,
    alignItems: 'center',
  },
  totalText: {
    color: '#fdab00',
    fontWeight: 'bold',
    fontSize: 18,
  },
  placeOrderButton: {
    fontWeight: 'bold',
    backgroundColor: '#fdba00',
    padding: 15,
  },
  placeOrderButtonText: {
    color: 'white',
    textAlign: 'center',
    fontWeight: 'bold',
  },
});

export default CartScreen;
