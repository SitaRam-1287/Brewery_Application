import React, {useState, useEffect} from 'react';
import {View, Text, TextInput, FlatList, StyleSheet} from 'react-native';
import {get} from '../api';
import ItemCard from '../components/ItemCard';
import Navbar from '../components/Navbar';
import SkeletonPlaceholder from 'react-native-skeleton-placeholder';

const SearchResultsScreen = () => {
  const [items, setItems] = useState([]);
  const [searchQuery, setSearchQuery] = useState('');
  const [isLoading, setIsLoading] = useState(true); // Add isLoading state

  useEffect(() => {
    const fetchItems = async () => {
      try {
        const itemsData = await get('/item');
        setItems(itemsData);
        setIsLoading(false); // Mark loading as false once data is fetched
      } catch (error) {
        console.error('Error fetching items:', error);
      }
    };
    fetchItems();
  }, []);

  const filteredItems = items.filter(item =>
    item.name.toLowerCase().includes(searchQuery.toLowerCase()),
  );

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
    <View style={{flex: 1}}>
      <Navbar />
      <View style={styles.container}>
        <TextInput
          style={styles.searchInput}
          placeholder="Search items"
          placeholderTextColor={'gray'}
          value={searchQuery}
          onChangeText={setSearchQuery}
        />
        {isLoading ? (
          <SkeletonPlaceholder>
            <View style={{flexDirection: 'row', marginBottom: 10}}>
              <View style={{width: 100, height: 100, borderRadius: 10}} />
              <View style={{marginLeft: 10, flex: 1}}>
                <View style={{height: 20, width: '80%'}} />
                <View style={{marginTop: 6, height: 18, width: '60%'}} />
                <View style={{marginTop: 6, height: 18, width: '40%'}} />
                <View
                  style={{
                    marginTop: 10,
                    flexDirection: 'row',
                    justifyContent: 'space-between',
                  }}>
                  <View style={{height: 20, width: '25%'}} />
                  <View style={{height: 30, width: '30%'}} />
                </View>
              </View>
            </View>
            
          </SkeletonPlaceholder>
        ) : (
          <FlatList
            data={filteredItems}
            keyExtractor={item => item.id.toString()}
            renderItem={renderItem}
          />
        )}
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    marginLeft: 10,
    marginRight: 10,
  },
  searchInput: {
    borderWidth: 1,
    borderColor: '#ddd',
    borderRadius: 18,
    padding: 8,
    marginBottom: 10,
    marginTop: 10,
    color: 'gray',
    marginLeft: 5,
    marginRight: 5,
  },
});

export default SearchResultsScreen;
