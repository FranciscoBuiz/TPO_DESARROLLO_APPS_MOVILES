import React from 'react';
import { Alert, View, Text, FlatList, TouchableOpacity, StyleSheet } from 'react-native';
import { styles, colors } from '../theme';
import BottomNav from '../components/BottomNav';

const items = [
  { id: '1', title: 'Busto clásico de mármol', price: '$4,500', description: 'Detalles en base de granito del siglo XIX.' },
  { id: '2', title: 'Silla estilo Bauhaus', price: '$7,800', description: 'Estructura de madera y tapizado auténtico.' },
  { id: '3', title: 'Reloj antiguo', price: '$2,400', description: 'Mecanismo funcionando, detalles dorados.' },
  { id: '4', title: 'Jarrón veneciano', price: '$3,900', description: 'Cristal soplado con acabado brillante.' },
  { id: '5', title: 'Estatua de bronce', price: '$5,600', description: 'Figura de caballero con detalles tallados.' },
];

export default function AuctionDetailScreen({ navigation }) {
  return (
    <View style={styles.page}>
      <View style={styles.container}>
        <Text style={styles.label}>CATÁLOGO DE VERANO</Text>
        <Text style={styles.title}>SUBASTA 1</Text>
      </View>

      <FlatList
        data={items}
        keyExtractor={(item) => item.id}
        contentContainerStyle={styles.container}
        renderItem={({ item }) => (
          <View style={styles.card}>
            <View style={styles.cardContent}>
              <Text style={styles.cardTitle}>{item.title}</Text>
              <Text style={styles.cardSubtitle}>{item.description}</Text>
              <View style={localStyles.row}> 
                <Text style={localStyles.price}>{item.price}</Text>
                <TouchableOpacity style={styles.button} onPress={() => Alert.alert('Detalle', 'Abrir detalle de la pieza.')}> 
                  <Text style={styles.buttonText}>VER DETALLE</Text>
                </TouchableOpacity>
              </View>
            </View>
          </View>
        )}
      />

      <View style={localStyles.paginationContainer}>
        <TouchableOpacity style={localStyles.paginationButton} onPress={() => {}}>
          <Text style={localStyles.paginationText}>{'< ANTERIOR'}</Text>
        </TouchableOpacity>
        <View style={localStyles.pageNumbers}>
          <Text style={localStyles.pageNumberActive}>01</Text>
          <Text style={localStyles.pageNumber}>02</Text>
          <Text style={localStyles.pageNumber}>03</Text>
        </View>
        <TouchableOpacity style={localStyles.paginationButton} onPress={() => {}}>
          <Text style={localStyles.paginationText}>{'SIGUIENTE >'}</Text>
        </TouchableOpacity>
      </View>

      <BottomNav navigation={navigation} activeRoute="AuctionDetail" />
    </View>
  );
}

const localStyles = StyleSheet.create({
  row: {
    marginTop: 16,
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
  },
  price: {
    fontSize: 16,
    fontWeight: '700',
    color: colors.black,
  },
  paginationContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    paddingHorizontal: 20,
    paddingBottom: 10,
  },
  paginationButton: {
    flex: 1,
    padding: 10,
    marginHorizontal: 4,
    borderRadius: 12,
    backgroundColor: colors.lightGray,
    alignItems: 'center',
  },
  paginationText: {
    color: colors.black,
    fontWeight: '700',
  },
  pageNumbers: {
    flex: 1,
    flexDirection: 'row',
    justifyContent: 'center',
  },
  pageNumber: {
    marginHorizontal: 6,
    color: colors.gray,
    fontWeight: '700',
  },
  pageNumberActive: {
    marginHorizontal: 6,
    color: colors.black,
    fontWeight: '700',
  },
});
