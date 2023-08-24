import React, { useState, useEffect } from 'react';
import { View, StyleSheet, SafeAreaView, FlatList, ActivityIndicator } from 'react-native';
import SkeletonPlaceholder from 'react-native-skeleton-placeholder'; // Import SkeletonPlaceholder
import { get } from '../api';
import ItemCard from './ItemCard';

const MenuList = ({ fetchURL }) => {
  const [data, setData] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    async function fetchData() {
      try {
        const response = await get(fetchURL);
        setData(response);
        setIsLoading(false);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    }
    fetchData();
  }, [fetchURL]);

  const renderItem = ({ item }) => (
    <View style={{ marginLeft: 10, marginRight: 10, marginStart: 10 }}>
      <ItemCard
        name={item.name}
        description={item.details}
        price={item.price}
        image={item.image}
      />
    </View>
  );

  return (
    <SafeAreaView style={styles.container}>
      {isLoading ? (
        <SkeletonPlaceholder>
          <View style={{ flexDirection: 'row' }}>
            <View style={{ width: 100, height: 100, borderRadius: 10 }} />
            <View style={{ marginLeft: 10, flex: 1 }}>
              <View style={{ height: 20, width: '80%' }} />
              <View style={{ marginTop: 6, height: 18, width: '60%' }} />
              <View style={{ marginTop: 6, height: 18, width: '40%' }} />
              <View
                style={{
                  marginTop: 10,
                  flexDirection: 'row',
                  justifyContent: 'space-between',
                }}>
                <View style={{ height: 20, width: '25%' }} />
                <View style={{ height: 30, width: '30%' }} />
              </View>
            </View>
          </View>
        </SkeletonPlaceholder>
      ) : (
        <FlatList
          style={{ marginTop: 10 }}
          data={data}
          renderItem={renderItem}
          keyExtractor={item => item.id.toString()}
        />
      )}
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
