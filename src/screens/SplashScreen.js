import React, { useEffect, useState } from 'react';
import { View, Text, StyleSheet, Animated } from 'react-native';
import { StatusBar } from 'expo-status-bar';
import { colors } from '../theme';

export default function SplashScreen({ navigation }) {
  const [progress] = useState(new Animated.Value(0));

  useEffect(() => {
    Animated.timing(progress, {
      toValue: 100,
      duration: 2000,
      useNativeDriver: false,
    }).start(() => {
      navigation.replace('Login');
    });
  }, []);

  const widthInterpolation = progress.interpolate({
    inputRange: [0, 100],
    outputRange: ['0%', '100%'],
  });

  return (
    <View style={styles.container}>
      <Text style={styles.title}>SUBASTAPP</Text>
      <Text style={styles.subtitle}>Carga inicial en progreso...</Text>
      <View style={styles.progressBarBackground}>
        <Animated.View style={[styles.progressBarFill, { width: widthInterpolation }]} />
      </View>
      <StatusBar style="dark" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: colors.background,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 24,
  },
  title: {
    fontSize: 36,
    fontWeight: '800',
    color: colors.black,
    textAlign: 'center',
  },
  subtitle: {
    marginTop: 12,
    fontSize: 14,
    color: colors.gray,
    textAlign: 'center',
  },
  progressBarBackground: {
    width: '100%',
    height: 10,
    marginTop: 32,
    backgroundColor: colors.lightGray,
    borderRadius: 8,
    overflow: 'hidden',
  },
  progressBarFill: {
    height: '100%',
    backgroundColor: colors.yellow,
  },
});
