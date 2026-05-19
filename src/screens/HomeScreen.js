import React from 'react';
import { View, Text, ScrollView, TouchableOpacity, Image, StyleSheet } from 'react-native';
import { styles, colors } from '../theme';
import BottomNav from '../components/BottomNav';

const auctions = [
  { id: '1', title: 'Subasta de Plata', category: 'PLATA', date: '15 OCT - 18:00HS', currency: 'USD' },
  { id: '2', title: 'Subasta Moderna', category: 'ORO', date: '18 OCT - 12:00HS', currency: 'EUR' },
  { id: '3', title: 'Subasta de Arte', category: 'BRONCE', date: '22 OCT - 10:00HS', currency: 'USD' },
];

export default function HomeScreen({ navigation }) {
  return (
    <View style={styles.page}>
      <ScrollView contentContainerStyle={styles.container}>
        <Text style={styles.title}>Subastas</Text>
        <Text style={styles.subtitle}>Disponibles ahora</Text>

        {auctions.map((auction) => (
          <View key={auction.id} style={styles.card}>
            <View style={styles.cardContent}>
              <View style={localStyles.cardHeader}>
                <Text style={styles.cardTitle}>{auction.title}</Text>
                <View style={localStyles.currencyTag}>
                  <Text style={localStyles.currencyText}>{auction.currency}</Text>
                </View>
              </View>
              <Text style={styles.cardSubtitle}>{`Categoría: ${auction.category}`}</Text>
              <Text style={styles.cardSubtitle}>{`Finaliza: ${auction.date}`}</Text>
              <TouchableOpacity style={[styles.button, { marginTop: 16 }]} onPress={() => navigation.navigate('AuctionDetail')}>
                <Text style={styles.buttonText}>ENTRAR</Text>
              </TouchableOpacity>
            </View>
          </View>
        ))}
      </ScrollView>
      <BottomNav navigation={navigation} activeRoute="Home" />
    </View>
  );
}

const localStyles = StyleSheet.create({
  cardHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  currencyTag: {
    paddingHorizontal: 10,
    paddingVertical: 6,
    borderRadius: 12,
    backgroundColor: colors.lightGray,
  },
  currencyText: {
    fontWeight: '700',
    color: colors.black,
  },
});
