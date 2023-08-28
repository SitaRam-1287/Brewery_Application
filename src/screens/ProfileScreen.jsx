import React from 'react';
import {View, Text, Image, TouchableOpacity, StyleSheet} from 'react-native';
import {useNavigation} from '@react-navigation/native';

export default function ProfileScreen() {
  const navigation = useNavigation();

  const handleOrderHistory = () => {
    // Navigate to Order History screen
    navigation.navigate('OrderHistory');
  };

  const handleUserInfo = () => {
    // Navigate to User Information screen
    navigation.navigate('UserInfo');
  };

  const handleHelp = () => {
    // Navigate to Help screen
    console.log(navigation.navigate('Help'));
  };

  const handlePrivacyPolicy = () => {
    // Navigate to Privacy Policy screen
    navigation.navigate('PrivacyPolicy');
  };

  return (
    <View style={styles.container}>
      <Image
        source={require('../assets/images/deliveryGuy.png')} // Replace with your image source
        style={styles.profileImage}
      />
      <TouchableOpacity
        style={styles.optionButton}
        onPress={handleOrderHistory}>
        <Image
          source={require('../assets/icons/shopping-bag.png')}
          style={styles.icon}
        />
        <Text style={styles.optionText}>Order History</Text>
      </TouchableOpacity>
      <TouchableOpacity style={styles.optionButton} onPress={handleUserInfo}>
        <Image
          source={require('../assets/icons/information.png')}
          style={styles.icon}
        />
        <Text style={styles.optionText}>User Information</Text>
      </TouchableOpacity>
      <TouchableOpacity style={styles.optionButton} onPress={handleHelp}>
        <Image
          source={require('../assets/icons/question.png')}
          style={styles.icon}
        />
        <Text style={styles.optionText}>Help</Text>
      </TouchableOpacity>
      <TouchableOpacity
        style={styles.optionButton}
        onPress={handlePrivacyPolicy}>
        <Text style={styles.optionText}>Privacy Policy</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    padding: 20,
  },
  profileImage: {
    width: 120,
    height: 120,
    borderWidth: 6,
    borderColor: '#fdab00',
    borderRadius: 60,
    marginBottom: 20,
  },
  optionButton: {
    width: '100%',
    paddingVertical: 10,
    borderWidth: 4,
    borderColor: '#fdab00',
    borderRadius: 10,
    marginTop: 20,
    backgroundColor: '#f5f5f5',
  },
  optionText: {
    fontSize: 18,
    color: '#fdba00',
    textAlign: 'center',
  },
  icon: {
    height: 20,
    width: 20,
  },
});
