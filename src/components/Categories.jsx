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
import {categories} from '../constants/index';

const Categories = () => {
  const [activeCategory, setActiveCategory] = useState(null);

  return (
    <SafeAreaView>
      <Text style={styles.title}>Categoies</Text>
      <ScrollView
        horizontal
        showsHorizontalScrollIndicator={false}
        contentContainerStyle={styles.container}>
        {categories.map(category => {
          const isActive = category.id === activeCategory;
          const categoryContainerStyle = isActive
            ? [styles.categoryContainer, styles.activeCategoryContainer]
            : styles.categoryContainer;

          return (
            <View key={category.id} style={categoryContainerStyle}>
              <TouchableOpacity
                onPress={() => setActiveCategory(category.id)}
                style={styles.categoryButton}>
                <Image style={styles.categoryImage} source={category.image} />
              </TouchableOpacity>
              <Text style={styles.categoryName}>{category.name}</Text>
            </View>
          );
        })}
      </ScrollView>
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
    width: 50,
    height: 50,
    resizeMode: 'contain',
  },
  categoryName: {
    color: '#333',
    marginTop: -10,
    fontWeight: 'bold',
  },
  title: {
    marginLeft:10,
    fontSize: 18,
    fontWeight: 'bold',
    marginBottom: 10,
    color: '#808080',
  },
});

export default Categories;
