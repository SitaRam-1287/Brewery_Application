/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import {StyleSheet, Image} from 'react-native';
import React from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import {createMaterialBottomTabNavigator} from '@react-navigation/material-bottom-tabs';
import HomeScreen from './screens/HomeScreen';
import MenuScreen from './screens/MenuScreen';
import ProfileScreen from './screens/ProfileScreen';
import CartScreen from './screens/CartScreen';
import ItemDetailsScreen from './screens/ItemDetailsScreen';

const Stack = createNativeStackNavigator();
const Tab = createMaterialBottomTabNavigator();

function HomeStack() {
  return (
    <Stack.Navigator
      initialRouteName="Home"
      screenOptions={{headerShown: false}}>
      <Stack.Screen name="Home" component={HomeScreen} />
      <Stack.Screen
        name="ItemDetails"
        options={{presentation: 'fullScreenModal'}}
        component={ItemDetailsScreen}
      />
    </Stack.Navigator>
  );
}

function ProfileStack() {
  return (
    <Stack.Navigator
      initialRouteName="Profile"
      screenOptions={{headerShown: false}}>
      <Stack.Screen name="Profile" component={ProfileScreen} />
      <Stack.Screen name="Orders" component={ProfileScreen} />
    </Stack.Navigator>
  );
}

function CartStack() {
  return (
    <Stack.Navigator
      initialRouteName="Cart"
      screenOptions={{headerShown: false}}>
      <Stack.Screen name="Cart" component={CartScreen} />
    </Stack.Navigator>
  );
}

function App() {
  return (
    <NavigationContainer>
      <Tab.Navigator
        activeColor="#fdab00"
        inactiveColor="gray"
        barStyle={{backgroundColor: '#ffff'}}>
        <Tab.Screen
          name="Home"
          component={HomeStack}
          options={{
            tabBarIcon: () => (
              <Image source={require('./assets/icons/home-button.png')} />
            ),
          }}
        />
        <Tab.Screen
          name="Menu"
          component={MenuScreen}
          options={{
            tabBarIcon: () => (
              <Image source={require('./assets/icons/home-button.png')} />
            ),
          }}
        />
        <Tab.Screen
          name="Cart"
          component={CartStack}
          options={{
            tabBarIcon: () => (
              <Image source={require('./assets/icons/trolley.png')} />
            ),
          }}
        />
        <Tab.Screen
          name="TabProfile"
          component={ProfileStack}
          options={{
            tabBarIcon: () => (
              <Image source={require('./assets/icons/profile.png')} />
            ),
          }}
        />
      </Tab.Navigator>
    </NavigationContainer>
  );
}
const styles = StyleSheet.create({
  bottomTabContainer: {
    marginBottom: 20,
  },
});

export default App;
