import React, {useState} from 'react';
import {
  View,
  Text,
  TextInput,
  TouchableOpacity,
  StyleSheet,
} from 'react-native';
import {post} from '../api';

const SignupScreen = ({navigation}) => {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [phoneNum, setPhone] = useState('');

  const handleSignup = async () => {
    console.log(phoneNum);
    try {
      const signupData = await post('/user', {
        firstName,
        lastName,
        email,
        password,
        phoneNum,
      });

      console.log('Signup Data:', signupData);
      // Handle signupData response and navigation or other actions
    } catch (error) {
      console.error('Error during signup:', error);
    }
  };

  return (
    <View style={styles.container}>
      <Text style={styles.header}>Signup</Text>
      <TextInput
        style={styles.input}
        placeholderTextColor={'#a8a8a8'}
        placeholder="First Name"
        value={firstName}
        onChangeText={setFirstName}
      />
      <TextInput
        style={styles.input}
        placeholderTextColor={'#a8a8a8'}
        placeholder="Last Name"
        value={lastName}
        onChangeText={setLastName}
      />
      <TextInput
        style={styles.input}
        placeholderTextColor={'#a8a8a8'}
        placeholder="Email"
        value={email}
        onChangeText={setEmail}
      />
      <TextInput
        style={styles.input}
        placeholderTextColor={'#a8a8a8'}
        placeholder="Password"
        secureTextEntry
        value={password}
        onChangeText={setPassword}
      />
      <TextInput
        style={styles.input}
        placeholderTextColor={'#a8a8a8'}
        placeholder="Phone Number"
        keyboardType="phone-pad"
        value={phoneNum}
        onChangeText={setPhone}
      />

      <TouchableOpacity style={styles.signupButton} onPress={handleSignup}>
        <Text style={styles.signupButtonText}>Signup</Text>
      </TouchableOpacity>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  header: {
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 20,
    color: 'gray',
  },
  input: {
    width: '80%',
    color: 'gray',
    height: 40,
    borderWidth: 1,
    borderRadius: 8,
    paddingHorizontal: 10,
    marginBottom: 10,
  },
  signupButton: {
    backgroundColor: '#fdab00',
    padding: 10,
    borderRadius: 8,
  },
  signupButtonText: {
    color: 'white',
    fontWeight: 'bold',
    fontSize: 16,
    textAlign: 'center',
  },
});

export default SignupScreen;
