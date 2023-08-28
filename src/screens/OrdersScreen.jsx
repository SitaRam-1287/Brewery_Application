import React, {useState, useEffect} from 'react';
import {
  View,
  Text,
  FlatList,
  TouchableOpacity,
  StyleSheet,
  Modal,
} from 'react-native';
import {get} from '../api';
import Navbar from '../components/Navbar';

const OrdersScreen = () => {
  const [orders, setOrders] = useState([]);
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [selectedOrder, setSelectedOrder] = useState(null);

  useEffect(() => {
    const fetchOrders = async () => {
      try {
        const ordersData = await get('/order/getAll');
        setOrders(ordersData);
      } catch (error) {
        console.error('Error fetching orders:', error);
      }
    };
    fetchOrders();
    console.log(orders);
  }, []);

  const openModal = order => {
    setSelectedOrder(order);
    setIsModalVisible(true);
  };

  const closeModal = () => {
    setSelectedOrder(null);
    setIsModalVisible(false);
  };

  const renderOrderItem = ({item}) => {
    console.log(
      'food Items....................................................................................',
      item.foodItems,
    );
    return (
      <View style={styles.orderItem}>
        <Text style={{color: 'gray'}}>Ordered Date: {item.orderedTime}</Text>
        <Text style={{color: 'gray'}}>Total Price: ${item.totalPrice}</Text>
        <TouchableOpacity
          style={styles.reviewButton}
          onPress={() => openModal(item)}>
          <Text style={styles.reviewButtonText}>Review</Text>
        </TouchableOpacity>
      </View>
    );
  };

  return (
    <View>
      <Navbar />
      <View style={styles.container}>
        <FlatList
          data={orders}
          keyExtractor={item => item.id.toString()}
          renderItem={renderOrderItem}
        />
        <Modal visible={isModalVisible} transparent={true}>
          <View style={styles.modalContainer}>
            <View style={styles.modalContent}>
              <Text>Review Item: {selectedOrder?.name}</Text>
              {/* TODO: Add review input and star rating here */}
              <TouchableOpacity style={styles.closeButton} onPress={closeModal}>
                <Text style={styles.closeButtonText}>Close</Text>
              </TouchableOpacity>
            </View>
          </View>
        </Modal>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 10,
  },
  orderItem: {
    marginBottom: 20,
    padding: 10,
    borderWidth: 1,
    borderColor: '#ddd',
    borderRadius: 8,
  },
  orderItemRow: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginTop: 8,
  },
  reviewButton: {
    backgroundColor: 'transparent',
    borderWidth: 1,
    borderColor: '#fdab00',
    borderRadius: 5,
    paddingVertical: 4,
    paddingHorizontal: 10,
  },
  reviewButtonText: {
    color: '#fdab00',
  },
  modalContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'rgba(0, 0, 0, 0.5)',
  },
  modalContent: {
    backgroundColor: 'white',
    padding: 20,
    borderRadius: 8,
  },
  closeButton: {
    backgroundColor: 'gray',
    padding: 10,
    borderRadius: 8,
    marginTop: 10,
  },
  closeButtonText: {
    color: 'white',
    textAlign: 'center',
  },
});

export default OrdersScreen;
