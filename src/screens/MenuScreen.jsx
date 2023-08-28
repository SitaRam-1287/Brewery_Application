import React, {useState, useEffect} from 'react';
import {View, SafeAreaView, ScrollView} from 'react-native';
import Categories from '../components/Categories';
import MenuList from '../components/MenuList';
import Navbar from '../components/Navbar';
import {categories} from '../constants/index';

const MenuScreen = () => {
  const [selectedCategory, setSelectedCategory] = useState('');

  const handleCategorySelect = categoryId => {
    setSelectedCategory(categoryId);
  };

  useEffect(() => {
    if (selectedCategory === '' && categories.length > 0) {
      setSelectedCategory(categories[0].name);
    }
  }, []);

  return (
    <SafeAreaView style={{flex: 1}}>
      <View style={styles.container}>
        <Navbar />
        <ScrollView
          showsVerticalScrollIndicator={false}
          contentContainerStyle={styles.scrollContainer}>
          <Categories
            onSelectCategory={handleCategorySelect}
            activeCategory={selectedCategory}
          />
          <MenuList
            fetchURL={`/item/foodType?foodType=${selectedCategory.toUpperCase()}`}
          />
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
