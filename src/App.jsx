import React, {useEffect, useState} from 'react';
import {Image, StyleSheet} from 'react-native';
import {NavigationContainer} from '@react-navigation/native';
import {createMaterialBottomTabNavigator} from '@react-navigation/material-bottom-tabs';
import {createStackNavigator} from '@react-navigation/stack';
import HomeScreen from './screens/HomeScreen';
import MenuScreen from './screens/MenuScreen';
import ProfileScreen from './screens/ProfileScreen';
import CartScreen from './screens/CartScreen';
import ItemDetailsScreen from './screens/ItemDetailsScreen';
import {Provider as ReduxProvider} from 'react-redux';
import OrdersScreen from './screens/OrdersScreen';
import PrivacyPolicy from './screens/PrivacyPolicy';
import MyInfoScreen from './screens/MyInfoScreen';
import configureStore from './redux/store';

import SelectAddressModal from './screens/SelectAddressModal';
import LoginScreen from './screens/LoginScreen';
import SignupScreen from './screens/SignupScreen';
import AsyncStorage from '@react-native-async-storage/async-storage';
import SearchResultsScreen from './screens/SearchResultsScreen';

const Tab = createMaterialBottomTabNavigator();
const Stack = createStackNavigator();
const store = configureStore();

function HomeStack() {
  return (
    <Stack.Navigator
      initialRouteName="Home"
      screenOptions={{headerShown: false}}>
      <Stack.Screen name="Home" component={HomeScreen} />
      <Stack.Screen name="Search" component={SearchResultsScreen} />
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
      <Stack.Screen name="Search" component={SearchResultsScreen} />
      <Stack.Screen name="OrderHistory" component={OrdersScreen} />
      <Stack.Screen name="UserInfo" component={MyInfoScreen} />
      <Stack.Screen name="PrivacyPolicy" component={PrivacyPolicy} />
    </Stack.Navigator>
  );
}

function CartStack() {
  return (
    <Stack.Navigator
      initialRouteName="Cart"
      screenOptions={{headerShown: false}}>
      <Stack.Screen name="Cart" component={CartScreen} />
      <Stack.Screen name="Search" component={SearchResultsScreen} />
    </Stack.Navigator>
  );
}

function TabNavigator() {
  return (
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
            <Image source={require('./assets/icons/menu.png')} />
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
  );
}

function App() {
  const [isLogged, setIsLogged] = useState(false);

  useEffect(() => {
    const retrieveLoginData = async () => {
      try {
        const loginData = await AsyncStorage.getItem('loginData');
        if (loginData) {
          setIsLogged(true);
        }
      } catch (error) {
        console.error('Error retrieving login data:', error);
      }
    };

    retrieveLoginData();
  }, []);

  return (
    <ReduxProvider store={store}>
      <NavigationContainer>
        {isLogged ? (
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
                  <Image source={require('./assets/icons/menu.png')} />
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
        ) : (
          <Stack.Navigator
            initialRouteName="Login"
            screenOptions={{headerShown: false}}>
            <Stack.Screen name="Login" component={LoginScreen} />
            <Stack.Screen name="Signup" component={SignupScreen} />
            <Stack.Screen name="TabNavigator" component={TabNavigator} />
          </Stack.Navigator>
        )}
      </NavigationContainer>
    </ReduxProvider>
  );
}

const styles = StyleSheet.create({
  bottomTabContainer: {
    marginBottom: 10,
  },
});

export default App;

{
  /*  */
}
//  ) : (
//               <Stack.Screen
//                 name="Login"
//                 component={LoginScreen}
//                 options={{headerShown: false}}
//               />
//             )}
