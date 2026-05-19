import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import SplashScreen from './src/screens/SplashScreen';
import LoginScreen from './src/screens/LoginScreen';
import RegisterStep1Screen from './src/screens/RegisterStep1Screen';
import RegisterStep2Screen from './src/screens/RegisterStep2Screen';
import HomeScreen from './src/screens/HomeScreen';
import AuctionDetailScreen from './src/screens/AuctionDetailScreen';
import SellScreen from './src/screens/SellScreen';
import ProfileScreen from './src/screens/ProfileScreen';

const Stack = createNativeStackNavigator();

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator screenOptions={{ headerShown: false }} initialRouteName="Splash">
        <Stack.Screen name="Splash" component={SplashScreen} />
        <Stack.Screen name="Login" component={LoginScreen} />
        <Stack.Screen name="RegisterStep1" component={RegisterStep1Screen} />
        <Stack.Screen name="RegisterStep2" component={RegisterStep2Screen} />
        <Stack.Screen name="Home" component={HomeScreen} />
        <Stack.Screen name="AuctionDetail" component={AuctionDetailScreen} />
        <Stack.Screen name="Sell" component={SellScreen} />
        <Stack.Screen name="Profile" component={ProfileScreen} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
