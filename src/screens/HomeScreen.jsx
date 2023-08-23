import {View, Text, StatusBar, TextInput, ScrollView} from 'react-native';
import React from 'react';
import {SafeAreaView} from 'react-native-safe-area-context';
import Categories from '../components/Categories';
import Row from '../components/Row';
import {bannerImages, dummyDishes} from '../constants';
import Navbar from '../components/Navbar';
import Banner from '../components/Banner';

const HomeScreen = () => {
  return (
    <SafeAreaView style={{flex: 1}}>
      <View style={styles.container}>
        <Navbar />
        <ScrollView
          showsVerticalScrollIndicator={false}
          contentContainerStyle={styles.scrollContainer}>
          <Banner images={bannerImages} />
          <Categories />
          <Row title={'Most ordered'} data={dummyDishes} />
          <Row title={'Top rated'} data={dummyDishes} />
          <Row title={'Must try'} data={dummyDishes} />
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
  rowContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingHorizontal: 4,
    paddingVertical: 2,
  },
  inputContainer: {
    flexDirection: 'row',
    flex: 1,
    alignItems: 'center',
    padding: 3,
    borderRadius: 9999,
    borderColor: 'gray',
    borderWidth: 1,
  },
  input: {
    marginLeft: 8,
    flex: 1,
    color: 'black',
  },
  locationContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    borderLeftWidth: 2,
    borderLeftColor: 'gray',
    paddingLeft: 2,
  },
  locationText: {
    color: 'gray',
  },
  scrollContainer: {
    paddingBottom: 20,
  },
};

export default HomeScreen;