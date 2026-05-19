import React from 'react';
import { View, TouchableOpacity, Text } from 'react-native';
import { styles, colors } from '../theme';

const items = [
  { name: 'Home', label: 'INICIO' },
  { name: 'AuctionDetail', label: 'SUBASTA' },
  { name: 'Sell', label: 'VENDER' },
  { name: 'Profile', label: 'PERFIL' },
];

export default function BottomNav({ navigation, activeRoute }) {
  return (
    <View style={styles.bottomNav}>
      {items.map((item) => {
        const active = activeRoute === item.name;
        return (
          <TouchableOpacity
            key={item.name}
            style={[styles.navButton, active && { backgroundColor: colors.lightGray }]}
            onPress={() => navigation.navigate(item.name)}
          >
            <Text style={[styles.navLabel, { color: active ? colors.black : colors.gray }]}>
              {item.label}
            </Text>
          </TouchableOpacity>
        );
      })}
    </View>
  );
}
