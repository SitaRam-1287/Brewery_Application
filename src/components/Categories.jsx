import React from 'react';
import {
  View,
  Text,
  ScrollView,
  TouchableOpacity,
  Image,
  StyleSheet,
  SafeAreaView,
  FlatList,
} from 'react-native';
import {categories} from '../constants/index';

const Categories = ({onSelectCategory, activeCategory}) => {
  const renderItem = ({item}) => {
    const isActive = item.name === activeCategory;
    const categoryContainerStyle = isActive
      ? [styles.categoryContainer, styles.activeCategoryContainer]
      : styles.categoryContainer;

    return (
      <View key={item.id} style={categoryContainerStyle}>
        <TouchableOpacity
          onPress={() => onSelectCategory(item.name)}
          style={styles.categoryButton}>
          <Image style={styles.categoryImage} source={item.image} />
        </TouchableOpacity>
        <Text style={styles.categoryName}>{item.name}</Text>
      </View>
    );
  };

  return (
    <SafeAreaView>
      <Text style={styles.title}>Categories</Text>
      <FlatList
        data={categories}
        renderItem={renderItem}
        keyExtractor={item => item.id.toString()}
        horizontal
        showsHorizontalScrollIndicator={false}
        contentContainerStyle={styles.container}
      />
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    overflow: 'hidden',
    marginBottom: 25,
    paddingHorizontal: 15,
    paddingBottom: 25,
    paddingTop: 20,
  },
  categoryContainer: {
    marginRight: 12,
    alignItems: 'center',
    justifyContent: 'center',
    borderRadius: 10,
    borderWidth: 1,
    borderColor: '#dcdcdc',
    backgroundColor: 'transparent',
    paddingBottom: 10,
  },
  activeCategoryContainer: {
    backgroundColor: '#fdba00',
  },
  categoryButton: {
    padding: 8,
    borderRadius: 20,
    backgroundColor: 'transparent',
  },
  categoryImage: {
    width: 70,
    height: 70,
    resizeMode: 'contain',
  },
  categoryName: {
    color: '#333',
    marginTop: -10,
    fontWeight: 'bold',
  },
  title: {
    marginLeft: 10,
    fontSize: 18,
    fontWeight: 'bold',
    marginBottom: 10,
    color: '#808080',
  },
});

export default Categories;
