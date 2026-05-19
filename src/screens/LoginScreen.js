import React, { useState } from 'react';
import { View, Text, TextInput, TouchableOpacity, StyleSheet, Alert, ScrollView } from 'react-native';
import { styles, colors } from '../theme';

export default function LoginScreen({ navigation }) {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = () => {
    const trimmedEmail = email.trim();
    const trimmedPassword = password.trim();

    if (!trimmedEmail || !trimmedPassword) {
      Alert.alert('Validación', 'Debes completar el email y la contraseña.');
      return;
    }

    if (!trimmedEmail.includes('@gmail.com')) {
      Alert.alert('Validación', 'El email debe contener @gmail.com.');
      return;
    }

    navigation.reset({ index: 0, routes: [{ name: 'Home' }] });
  };

  return (
    <ScrollView contentContainerStyle={styles.container}> 
      <Text style={styles.title}>Iniciar Sesión</Text>
      <Text style={styles.subtitle}>Accede a la plataforma de subastas.</Text>

      <Text style={styles.label}>EMAIL</Text>
      <TextInput
        value={email}
        onChangeText={setEmail}
        keyboardType="email-address"
        autoCapitalize="none"
        placeholder="ejemplo@gmail.com"
        placeholderTextColor={colors.gray}
        style={styles.input}
      />

      <Text style={styles.label}>CONTRASEÑA</Text>
      <TextInput
        value={password}
        onChangeText={setPassword}
        secureTextEntry
        placeholder="••••••••"
        placeholderTextColor={colors.gray}
        style={styles.input}
      />

      <TouchableOpacity style={styles.button} onPress={handleLogin}>
        <Text style={styles.buttonText}>INICIAR SESIÓN</Text>
      </TouchableOpacity>

      <View style={localStyles.dividerContainer}>
        <View style={localStyles.dividerLine} />
        <Text style={localStyles.dividerLabel}>o</Text>
        <View style={localStyles.dividerLine} />
      </View>

      <TouchableOpacity onPress={() => Alert.alert('Recuperar contraseña', 'Funcionalidad no implementada aún.')}> 
        <Text style={localStyles.linkText}>RECUPERAR CONTRASEÑA</Text>
      </TouchableOpacity>

      <TouchableOpacity onPress={() => navigation.navigate('RegisterStep1')}>
        <Text style={localStyles.linkText}>CREAR CUENTA</Text>
      </TouchableOpacity>
    </ScrollView>
  );
}

const localStyles = StyleSheet.create({
  dividerContainer: {
    width: '100%',
    marginTop: 24,
    flexDirection: 'row',
    alignItems: 'center',
  },
  dividerLine: {
    flex: 1,
    height: 1,
    backgroundColor: colors.border,
  },
  dividerLabel: {
    marginHorizontal: 12,
    color: colors.gray,
    fontWeight: '700',
  },
  linkText: {
    marginTop: 24,
    textAlign: 'center',
    color: colors.black,
    fontWeight: '700',
  },
});
