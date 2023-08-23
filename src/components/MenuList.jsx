import React, {useState} from 'react';
import {
  View,
  Text,
  ScrollView,
  TouchableOpacity,
  Image,
  StyleSheet,
  SafeAreaView,
} from 'react-native';
import {dummyDishes} from '../constants';
import ItemCard from './ItemCard';

const MenuList = () => {
  return (
    <SafeAreaView style={styles.container}>
      <ScrollView style={{marginTop: 10}}>
        {dummyDishes.map(dish => (
          <View style={{marginLeft: 10, marginRight: 10, marginStart: 10}}>
            <ItemCard
              key={dish.id}
              name={dish.name}
              description={dish.description}
              price={dish.price}
              image={dish.image}
            />
          </View>
        ))}
      </ScrollView>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
  },
});

export default MenuList;
