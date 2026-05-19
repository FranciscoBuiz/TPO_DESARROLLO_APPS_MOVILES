import React, { useState } from 'react';
import { View, Text, TextInput, TouchableOpacity, ScrollView, StyleSheet, Alert, Image } from 'react-native';
import * as ImagePicker from 'expo-image-picker';
import { styles, colors } from '../theme';
import BottomNav from '../components/BottomNav';

export default function SellScreen({ navigation }) {
  const [photoUri, setPhotoUri] = useState(null);
  const [nombreObra, setNombreObra] = useState('');
  const [artista, setArtista] = useState('');
  const [descripcion, setDescripcion] = useState('');
  const [procedencia, setProcedencia] = useState('');

  const handleTakePhoto = async () => {
    const permission = await ImagePicker.requestCameraPermissionsAsync();
    if (!permission.granted) {
      Alert.alert('Permiso denegado', 'Se necesita acceso a la cámara.');
      return;
    }

    const result = await ImagePicker.launchCameraAsync({ allowsEditing: true, quality: 0.7 });
    if (!result.canceled) {
      setPhotoUri(result.assets[0].uri);
    }
  };

  const handleSubmit = () => {
    if (!photoUri) {
      Alert.alert('Validación', 'Debes tomar una foto de la pieza antes de enviar.');
      return;
    }

    if (!nombreObra.trim() || !artista.trim() || !descripcion.trim() || !procedencia.trim()) {
      Alert.alert('Validación', 'Completa todos los campos del formulario.');
      return;
    }

    Alert.alert('Éxito', 'Solicitud enviada correctamente.');
  };

  return (
    <View style={styles.page}>
      <ScrollView contentContainerStyle={styles.container}>
        <Text style={styles.title}>Vender</Text>
        <Text style={[styles.subtitle, { marginTop: 8 }]}>Complete el formulario técnico para la próxima subasta.</Text>

        <Text style={[styles.label, { marginTop: 24 }]}>DOCUMENTACIÓN VISUAL</Text>
        <TouchableOpacity style={localStyles.photoBox} onPress={handleTakePhoto}>
          {photoUri ? (
            <Image source={{ uri: photoUri }} style={localStyles.photoImage} />
          ) : (
            <View style={localStyles.photoPlaceholder}>
              <Text style={localStyles.photoPlus}>+</Text>
              <Text style={localStyles.photoText}>MIN 6 FOTOS</Text>
              <Text style={localStyles.photoSubtext}>FORMATO JPG/PNG</Text>
            </View>
          )}
        </TouchableOpacity>

        <Text style={styles.label}>NOMBRE DE LA OBRA</Text>
        <TextInput value={nombreObra} onChangeText={setNombreObra} placeholder="P. Ej. Silla Barrera" placeholderTextColor={colors.gray} style={styles.input} />

        <Text style={styles.label}>ARTISTA / DISEÑADOR</Text>
        <TextInput value={artista} onChangeText={setArtista} placeholder="Nombre completo" placeholderTextColor={colors.gray} style={styles.input} />

        <Text style={styles.label}>DESCRIPCIÓN TÉCNICA</Text>
        <TextInput
          value={descripcion}
          onChangeText={setDescripcion}
          multiline
          placeholder="Materiales, dimensiones, estado..."
          placeholderTextColor={colors.gray}
          style={[styles.input, { height: 110, textAlignVertical: 'top' }]}
        />

        <Text style={styles.label}>PROCEDENCIA E HISTORIA</Text>
        <TextInput
          value={procedencia}
          onChangeText={setProcedencia}
          multiline
          placeholder="Historia de propiedad, exposiciones..."
          placeholderTextColor={colors.gray}
          style={[styles.input, { height: 110, textAlignVertical: 'top' }]}
        />

        <TouchableOpacity style={styles.button} onPress={handleSubmit}>
          <Text style={styles.buttonText}>ENVIAR SOLICITUD</Text>
        </TouchableOpacity>
      </ScrollView>
      <BottomNav navigation={navigation} activeRoute="Sell" />
    </View>
  );
}

const localStyles = StyleSheet.create({
  photoBox: {
    width: '100%',
    height: 150,
    borderRadius: 16,
    backgroundColor: colors.lightGray,
    justifyContent: 'center',
    alignItems: 'center',
    marginTop: 12,
  },
  photoPlaceholder: {
    justifyContent: 'center',
    alignItems: 'center',
  },
  photoPlus: {
    fontSize: 32,
    fontWeight: '700',
    color: colors.black,
  },
  photoText: {
    marginTop: 8,
    fontWeight: '700',
    color: colors.black,
  },
  photoSubtext: {
    marginTop: 4,
    color: colors.gray,
  },
  photoImage: {
    width: '100%',
    height: '100%',
    borderRadius: 16,
  },
});
