import React from 'react';
import { View, Text, TouchableOpacity, ScrollView, StyleSheet, Alert } from 'react-native';
import { styles, colors } from '../theme';
import BottomNav from '../components/BottomNav';

const options = [
  { key: 'mis_subastas', label: 'MIS SUBASTAS' },
  { key: 'estadisticas', label: 'ESTADÍSTICAS' },
  { key: 'medios_pago', label: 'MEDIOS DE PAGO' },
  { key: 'articulos_publicados', label: 'ARTÍCULOS PUBLICADOS' },
];

export default function ProfileScreen({ navigation }) {
  const handleLogout = () => {
    Alert.alert('Cerrar sesión', '¿Estás seguro de que deseas cerrar sesión?', [
      { text: 'No', style: 'cancel' },
      {
        text: 'Sí',
        onPress: () => navigation.reset({ index: 0, routes: [{ name: 'Login' }] }),
      },
    ]);
  };

  return (
    <View style={styles.page}>
      <ScrollView contentContainerStyle={styles.container}>
        <Text style={styles.title}>USUARIO</Text>
        <View style={localStyles.badge}>
          <Text style={localStyles.badgeText}>CATEGORÍA: ORO</Text>
        </View>

        <View style={localStyles.statusRow}>
          <View style={localStyles.statusCard}>
            <Text style={localStyles.statusLabel}>VERIFICADO</Text>
            <Text style={localStyles.statusValue}>✓</Text>
          </View>
          <View style={localStyles.statusCard}>
            <Text style={localStyles.statusLabel}>ACTIVO</Text>
            <Text style={localStyles.statusValue}>✔</Text>
          </View>
        </View>

        {options.map((option) => (
          <TouchableOpacity key={option.key} style={localStyles.optionItem} onPress={() => Alert.alert(option.label, 'Opción seleccionada')}>
            <Text style={localStyles.optionText}>{option.label}</Text>
            <Text style={localStyles.optionArrow}>{'>'}</Text>
          </TouchableOpacity>
        ))}

        <TouchableOpacity style={[styles.button, { marginTop: 24 }]} onPress={handleLogout}>
          <Text style={styles.buttonText}>CERRAR SESIÓN</Text>
        </TouchableOpacity>
      </ScrollView>
      <BottomNav navigation={navigation} activeRoute="Profile" />
    </View>
  );
}

const localStyles = StyleSheet.create({
  badge: {
    marginTop: 16,
    paddingHorizontal: 16,
    paddingVertical: 10,
    borderRadius: 20,
    backgroundColor: colors.black,
    alignSelf: 'flex-start',
  },
  badgeText: {
    color: colors.background,
    fontWeight: '700',
  },
  statusRow: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginTop: 24,
  },
  statusCard: {
    flex: 1,
    marginRight: 10,
    padding: 16,
    borderRadius: 16,
    backgroundColor: colors.lightGray,
    justifyContent: 'center',
    alignItems: 'center',
  },
  statusLabel: {
    color: colors.gray,
    fontSize: 12,
    fontWeight: '700',
  },
  statusValue: {
    marginTop: 8,
    fontSize: 24,
    color: colors.black,
    fontWeight: '700',
  },
  optionItem: {
    marginTop: 20,
    padding: 18,
    borderRadius: 16,
    backgroundColor: colors.lightGray,
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  optionText: {
    color: colors.black,
    fontSize: 14,
    fontWeight: '700',
  },
  optionArrow: {
    color: colors.black,
    fontSize: 18,
    fontWeight: '700',
  },
});
