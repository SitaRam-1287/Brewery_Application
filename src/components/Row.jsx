import React from 'react';
import {View, Text, FlatList, StyleSheet} from 'react-native';
import ItemCard from './ItemCard';

function Row({title, data}) {
  const renderRowItem = ({item}) => (
    <View style={{marginRight: 15,marginLeft:3}}>
      <ItemCard
        name={item.name}
        description={item.description}
        price={item.price}
        image={item.image}
      />
    </View>
  );

  return (
    <View style={styles.rowContainer}>
      <Text style={styles.rowTitle}>{title}</Text>
      <FlatList
        horizontal
        showsHorizontalScrollIndicator={false}
        data={data}
        keyExtractor={item => item.id.toString()}
        renderItem={renderRowItem}
        contentContainerStyle={styles.rowItemsContainer}
      />
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
