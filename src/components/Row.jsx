import React, { useState, useEffect } from 'react';
import { View, Text, FlatList, StyleSheet } from 'react-native';
import SkeletonPlaceholder from 'react-native-skeleton-placeholder'; // Import SkeletonPlaceholder
import { get } from '../api';
import ItemCard from './ItemCard';

function Row({ title, fetchURL }) {
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
  }, []);

  const renderRowItem = ({ item }) => (
    <View style={{ marginRight: 15, marginLeft: 3 }}>
      <ItemCard
        name={item.name}
        description={item.details}
        price={item.price}
        image={item.image}
      />
    </View>
  );

  return (
    <View style={styles.rowContainer}>
      <Text style={styles.rowTitle}>{title}</Text>
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
          horizontal
          showsHorizontalScrollIndicator={false}
          data={data}
          keyExtractor={item => item.id.toString()}
          renderItem={renderRowItem}
          contentContainerStyle={styles.rowItemsContainer}
        />
      )}
    </View>
  );
}

const styles = StyleSheet.create({
  rowContainer: {
    marginLeft: 10,
    marginBottom: 20,
  },
  rowTitle: {
    fontSize: 18,
    fontWeight: 'bold',
    marginBottom: 10,
    color: '#808080',
  },
  rowItemsContainer: {
    flexDirection: 'row',
    paddingVertical: 10,
    marginRight: 10,
  },
});

export default Row;
