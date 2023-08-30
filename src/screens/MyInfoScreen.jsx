import React, {useState, useEffect} from 'react';
import {
  View,
  Text,
  StyleSheet,
  ScrollView,
  ActivityIndicator,
} from 'react-native';
import {get} from '../api';

const MyInfoScreen = () => {
  const [userInfo, setUserInfo] = useState({});
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchUserInfo();
  }, []);

  const fetchUserInfo = async () => {
    try {
      const userData = await get('/user');
      setUserInfo(userData);
      setLoading(false);
    } catch (error) {
      console.error('Error fetching user info:', error);
      setLoading(false);
    }
  };

  return (
    <ScrollView contentContainerStyle={styles.container}>
      {loading ? (
        <ActivityIndicator size="large" color="#fdab00" />
      ) : (
        <>
          <Text style={styles.header}>My Info</Text>
          <Text style={styles.label}>Name:</Text>
          <Text style={styles.value}>
            {userInfo.firstName} {userInfo.lastName}
          </Text>
          <Text style={styles.label}>Email:</Text>
          <Text style={styles.value}>{userInfo.email}</Text>
          <Text style={styles.label}>Phone:</Text>
          <Text style={styles.value}>{userInfo.phoneNum}</Text>
        </>
      )}
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  container: {
    flexGrow: 1,
    justifyContent: 'center',
    alignItems: 'center',
    paddingVertical: 20,
    paddingHorizontal: 20,
  },
  header: {
    fontSize: 28,
    fontWeight: 'bold',
    marginBottom: 20,
    color: '#fdab00',
  },
  label: {
    fontSize: 20,
    fontWeight: 'bold',
    marginTop: 15,
    color: '#808080',
  },
  value: {
    fontSize: 18,
    marginBottom: 20,
    color: '#333',
  },
});

export default MyInfoScreen;
