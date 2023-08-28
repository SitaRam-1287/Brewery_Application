import AsyncStorage from '@react-native-async-storage/async-storage';

export const BASE_URL = 'https://slick-groups-know.loca.lt';

const setHeaders = async () => {
  const loginData = await AsyncStorage.getItem('loginData');
  if (loginData) {
    const {userId, accessToken} = JSON.parse(loginData);
    return {
      userId: userId,
      accessToken: accessToken,
      'Content-Type': 'application/json',
    };
  }
  return {
    'Content-Type': 'application/json',
  };
};

export async function get(endpoint) {
  try {
    const headers = await setHeaders();
    const response = await fetch(`${BASE_URL}${endpoint}`, {
      headers,
    });
    const data = await response.json();
    console.log(data);
    return data;
  } catch (error) {
    console.error('Error making GET request:', error);
    throw error;
  }
}

export async function post(endpoint, body) {
  try {
    const headers = await setHeaders();
    const response = await fetch(`${BASE_URL}${endpoint}`, {
      method: 'POST',
      headers,
      body: JSON.stringify(body),
    });
    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Error making POST request:', error);
    throw error;
  }
}
