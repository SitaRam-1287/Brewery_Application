import {View, Text, SafeAreaView, ScrollView} from 'react-native';
import React from 'react';
import MenuList from '../components/MenuList';
import Categories from '../components/Categories';
import Navbar from '../components/Navbar';

const MenuScreen = () => {
  return (
    <SafeAreaView style={{flex: 1}}>
      <View style={styles.container}>
        <Navbar />
        <ScrollView
          showsVerticalScrollIndicator={false}
          contentContainerStyle={styles.scrollContainer}>
          <Categories />
          <MenuList />
        </ScrollView>
      </View>
    </SafeAreaView>
  );
};

const styles = {
  container: {
    flex: 1,
    backgroundColor: 'white',
  },
};

export default MenuScreen;
