import React, { useState } from 'react';
import { SafeAreaView, View, Text, TextInput, TouchableOpacity, ScrollView, StyleSheet, Alert, Image, KeyboardAvoidingView, Platform } from 'react-native';
import * as ImagePicker from 'expo-image-picker';
import { styles, colors } from '../theme';

export default function RegisterStep1Screen({ navigation }) {
  const [nombre, setNombre] = useState('');
  const [apellido, setApellido] = useState('');
  const [documento, setDocumento] = useState('');
  const [domicilio, setDomicilio] = useState('');
  const [frenteUri, setFrenteUri] = useState(null);
  const [dorsoUri, setDorsoUri] = useState(null);

  const handleTakePhoto = async (target) => {
    const permission = await ImagePicker.requestCameraPermissionsAsync();
    if (!permission.granted) {
      Alert.alert('Permiso denegado', 'Se necesita acceso a la cámara para tomar la foto.');
      return;
    }

    const result = await ImagePicker.launchCameraAsync({ allowsEditing: true, quality: 0.7 });
    if (!result.canceled) {
      if (target === 'frente') {
        setFrenteUri(result.assets[0].uri);
      } else {
        setDorsoUri(result.assets[0].uri);
      }
    }
  };

  const handleContinue = () => {
    if (!nombre.trim() || !apellido.trim() || !documento.trim() || !domicilio.trim()) {
      Alert.alert('Validación', 'Completa todos los campos obligatorios.');
      return;
    }

    if (!frenteUri || !dorsoUri) {
      Alert.alert('Validación', 'Debes tomar las fotos del frente y dorso del DNI.');
      return;
    }

    navigation.navigate('RegisterStep2');
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

        <Text style={styles.label}>NOMBRE</Text>
        <TextInput value={nombre} onChangeText={setNombre} placeholder="Ej. Juan" placeholderTextColor={colors.gray} style={styles.input} />

        <Text style={styles.label}>APELLIDO</Text>
        <TextInput value={apellido} onChangeText={setApellido} placeholder="Ej. Pérez" placeholderTextColor={colors.gray} style={styles.input} />

        <Text style={styles.label}>DOCUMENTO</Text>
        <TextInput value={documento} onChangeText={setDocumento} placeholder="DNI" placeholderTextColor={colors.gray} style={styles.input} />

        <Text style={styles.label}>DOMICILIO</Text>
        <TextInput value={domicilio} onChangeText={setDomicilio} placeholder="Calle" placeholderTextColor={colors.gray} style={styles.input} />

        <Text style={styles.label}>VERIFICACIÓN DE IDENTIDAD (DNI)</Text>
        <TouchableOpacity style={localStyles.uploadBox} onPress={() => handleTakePhoto('frente')}>
          {frenteUri ? <Image source={{ uri: frenteUri }} style={localStyles.uploadImage} /> : <Text style={localStyles.uploadText}>Subir DNI frente</Text>}
        </TouchableOpacity>

        <TouchableOpacity style={localStyles.uploadBox} onPress={() => handleTakePhoto('dorso')}>
          {dorsoUri ? <Image source={{ uri: dorsoUri }} style={localStyles.uploadImage} /> : <Text style={localStyles.uploadText}>Subir DNI dorso</Text>}
        </TouchableOpacity>

        <TouchableOpacity style={styles.button} onPress={handleContinue}>
          <Text style={styles.buttonText}>ENVIAR DATOS</Text>
        </TouchableOpacity>

        <Text style={localStyles.noteText}>Al enviar tus datos, aceptas nuestros términos y condiciones de verificación.</Text>
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
  uploadBox: {
    width: '100%',
    height: 120,
    marginTop: 12,
    borderRadius: 16,
    backgroundColor: colors.lightGray,
    justifyContent: 'center',
    alignItems: 'center',
  },
  uploadText: {
    color: colors.black,
    fontWeight: '700',
  },
  uploadImage: {
    width: '100%',
    height: '100%',
    borderRadius: 16,
  },
  noteText: {
    marginTop: 24,
    color: colors.gray,
    fontSize: 11,
  },
});
