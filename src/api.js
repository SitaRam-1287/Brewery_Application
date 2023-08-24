const BASE_URL = 'https://witty-berries-say.loca.lt';

export async function get(endpoint) {
  try {
    const response = await fetch(`${BASE_URL}${endpoint}`);
    const data = await response.json();
    console.log('get function' + data);
    return data;
  } catch (error) {
    console.error('Error making GET request:', error);
    throw error;
  }
}

export async function post(endpoint, body) {
  try {
    const response = await fetch(`${BASE_URL}${endpoint}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(body),
    });
    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Error making POST request:', error);
    throw error;
  }
}
