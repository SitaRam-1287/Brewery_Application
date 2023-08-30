import AsyncStorage from '@react-native-async-storage/async-storage';

export const BASE_URL = 'https://orange-colts-say.loca.lt';

const setHeaders = async () => {
  const loginDataString = await AsyncStorage.getItem('loginData');
  const loginData = JSON.parse(loginDataString);
  if (loginData) {
    return {
      userId: loginData.userId,
      accessToken: loginData.accessToken,
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

    if (!response.ok) {
      console.error('Error making GET request:', response.status);
      throw new Error('Network response was not ok');
    }

    const contentType = response.headers.get('content-type');
    if (contentType && contentType.includes('application/json')) {
      const data = await response.json();
      console.log(data);
      return data;
    } else {
      console.error('Error making GET request: Unexpected content type');
      throw new Error('Response was not JSON');
    }
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
