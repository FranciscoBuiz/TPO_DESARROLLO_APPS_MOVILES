import React, { useState } from 'react';
import { SafeAreaView, View, Text, TextInput, TouchableOpacity, ScrollView, StyleSheet, Alert, KeyboardAvoidingView, Platform } from 'react-native';
import { styles, colors } from '../theme';

export default function RegisterStep2Screen({ navigation }) {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [termsAccepted, setTermsAccepted] = useState(false);

  const handleComplete = () => {
    const trimmedEmail = email.trim();
    const trimmedPassword = password.trim();
    const trimmedConfirm = confirmPassword.trim();

    if (!trimmedEmail || !trimmedPassword || !trimmedConfirm) {
      return Alert.alert('Validación', 'Completa todos los campos obligatorios.');
    }

    if (!trimmedEmail.includes('@')) {
      return Alert.alert('Validación', 'El email debe contener @.');
    }

    if (trimmedPassword.length < 8) {
      return Alert.alert('Validación', 'La contraseña debe tener mínimo 8 caracteres.');
    }

    if (!/[0-9]/.test(trimmedPassword)) {
      return Alert.alert('Validación', 'La contraseña debe incluir al menos un número.');
    }

    if (trimmedPassword !== trimmedConfirm) {
      return Alert.alert('Validación', 'Las contraseñas no coinciden.');
    }

    if (!termsAccepted) {
      return Alert.alert('Validación', 'Debes aceptar los términos y condiciones.');
    }

    navigation.reset({ index: 0, routes: [{ name: 'Home' }] });
  };

  return (
    <SafeAreaView style={styles.page}>
      <KeyboardAvoidingView
        style={styles.page}
        behavior={Platform.OS === 'ios' ? 'padding' : 'height'}
        keyboardVerticalOffset={Platform.OS === 'ios' ? 20 : 0}
      >
        <ScrollView
          style={styles.page}
          contentContainerStyle={localStyles.scrollContent}
          keyboardShouldPersistTaps="handled"
          showsVerticalScrollIndicator={true}
          nestedScrollEnabled
        >
        <Text style={styles.title}>{'Crear\nCuenta'}</Text>
        <View style={localStyles.bar} />

        <Text style={styles.label}>EMAIL</Text>
        <TextInput
          value={email}
          onChangeText={setEmail}
          keyboardType="email-address"
          autoCapitalize="none"
          placeholder="user@gmail.com"
          placeholderTextColor={colors.gray}
          style={styles.input}
        />

        <Text style={styles.label}>CREAR CONTRASEÑA</Text>
        <TextInput
          value={password}
          onChangeText={setPassword}
          secureTextEntry
          placeholder="••••••••"
          placeholderTextColor={colors.gray}
          style={styles.input}
        />
        <Text style={localStyles.hintText}>Mínimo 8 caracteres, incluye un número.</Text>

        <Text style={styles.label}>CONFIRMAR CONTRASEÑA</Text>
        <TextInput
          value={confirmPassword}
          onChangeText={setConfirmPassword}
          secureTextEntry
          placeholder="••••••••"
          placeholderTextColor={colors.gray}
          style={styles.input}
        />

        <View style={localStyles.checkboxRow}>
          <TouchableOpacity onPress={() => setTermsAccepted((prev) => !prev)} style={localStyles.checkbox}>
            <Text style={localStyles.checkboxText}>{termsAccepted ? '✓' : ''}</Text>
          </TouchableOpacity>
          <Text style={localStyles.termsText}>Acepto los términos y condiciones y la política de datos.</Text>
        </View>

        <TouchableOpacity style={styles.button} onPress={handleComplete}>
          <Text style={styles.buttonText}>COMPLETAR REGISTRO</Text>
        </TouchableOpacity>

        <View style={localStyles.infoBox}>
          <Text style={localStyles.infoTitle}>SEGURIDAD DE CUENTA</Text>
          <Text style={localStyles.infoText}>Tu información está protegida mediante encriptación de grado bancario. Nunca compartiremos tus credenciales.</Text>
          <Text style={localStyles.infoStatus}>ESTADO DEL SERVIDOR: ■ ONLINE</Text>
        </View>
      </ScrollView>
      </KeyboardAvoidingView>
    </SafeAreaView>
  );
}

const localStyles = StyleSheet.create({
  scrollContent: {
    paddingHorizontal: 20,
    paddingTop: 24,
    paddingBottom: 80,
  },

  bar: {
    width: 40,
    height: 4,
    backgroundColor: colors.black,
    marginTop: 8,
    marginBottom: 24,
  },
  hintText: {
    marginTop: 8,
    color: colors.gray,
    fontSize: 11,
  },
  checkboxRow: {
    flexDirection: 'row',
    alignItems: 'center',
    marginTop: 24,
  },
  checkbox: {
    width: 22,
    height: 22,
    borderRadius: 6,
    borderWidth: 1,
    borderColor: colors.border,
    justifyContent: 'center',
    alignItems: 'center',
    marginRight: 10,
  },
  checkboxText: {
    color: colors.black,
    fontWeight: '700',
  },
  termsText: {
    flex: 1,
    color: colors.black,
    fontSize: 12,
  },
  infoBox: {
    marginTop: 32,
    padding: 16,
    borderRadius: 16,
    backgroundColor: colors.lightGray,
  },
  infoTitle: {
    fontWeight: '700',
    color: colors.black,
    marginBottom: 12,
  },
  infoText: {
    color: colors.gray,
    fontSize: 12,
    marginBottom: 12,
  },
  infoStatus: {
    color: colors.black,
    fontWeight: '700',
    fontSize: 12,
  },
});
