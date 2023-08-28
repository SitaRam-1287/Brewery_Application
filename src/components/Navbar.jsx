import React from 'react';
import {View, Image, StyleSheet, TouchableOpacity} from 'react-native';
import {useNavigation} from '@react-navigation/native';

const Navbar = () => {
  const navigation = useNavigation();

  const handleSearch = () => {
    navigation.navigate('Search');
  };

  const handleLogoPress = () => {
    navigation.navigate('Home');
  };

  return (
    <View style={styles.navbar}>
      <TouchableOpacity onPress={handleLogoPress}>
        <View style={styles.logoContainer}>
          <Image
            source={require('../assets/images/download.png')}
            style={styles.logo}
          />
          <Image
            source={require('../assets/images/companyName.png')}
            style={styles.name}
          />
        </View>
      </TouchableOpacity>
      <View style={styles.searchContainer}>
        <TouchableOpacity style={styles.searchButton} onPress={handleSearch}>
          <Image
            source={require('../assets/icons/search.png')}
            style={styles.searchBtn}
          />
        </TouchableOpacity>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  navbar: {
    flexDirection: 'row',
    backgroundColor: '#fff',
    padding: 10,
    alignItems: 'center',
    justifyContent: 'space-between',
    borderBottomColor: '#f5f5f5',
    borderBottomWidth: 3,
  },
  logoContainer: {
    flexDirection: 'row', // Align logo and name horizontally
    alignItems: 'center', // Align items vertically within the container
  },
  logo: {
    width: 40,
    height: 40,
    margin: 10,
    resizeMode: 'contain',
    backgroundColor: 'black',
    borderWidth: 2,
    borderBlockColor: 'black',
    borderRadius: 35,
  },
  searchButton: {
    paddingVertical: 5,
    paddingHorizontal: 10,
    borderRadius: 5,
  },
  name: {
    width: 220,
    height: 350,
    marginTop: -130,
    marginBottom: -200,
    resizeMode: 'contain',
    alignSelf: 'center', // Align the image horizontally in the center
  },
});

export default Navbar;
