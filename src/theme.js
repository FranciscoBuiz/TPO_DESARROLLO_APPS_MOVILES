import { StyleSheet } from 'react-native';

export const colors = {
  background: '#FFFFFF',
  black: '#000000',
  gray: '#666666',
  lightGray: '#F2F2F2',
  yellow: '#F5C518',
  border: '#E6E6E6',
};

export const styles = StyleSheet.create({
  page: {
    flex: 1,
    backgroundColor: colors.background,
  },
  container: {
    flex: 1,
    paddingHorizontal: 20,
    paddingTop: 24,
  },
  title: {
    fontSize: 32,
    fontWeight: '700',
    color: colors.black,
  },
  subtitle: {
    marginTop: 8,
    fontSize: 12,
    color: colors.gray,
  },
  label: {
    marginTop: 24,
    fontSize: 12,
    fontWeight: '700',
    color: colors.black,
  },
  input: {
    height: 50,
    width: '100%',
    marginTop: 8,
    paddingHorizontal: 14,
    borderRadius: 12,
    backgroundColor: colors.lightGray,
    color: colors.black,
  },
  button: {
    height: 56,
    marginTop: 32,
    borderRadius: 14,
    backgroundColor: colors.yellow,
    justifyContent: 'center',
    alignItems: 'center',
  },
  buttonText: {
    color: colors.black,
    fontWeight: '700',
    fontSize: 14,
  },
  bottomNav: {
    height: 70,
    flexDirection: 'row',
    borderTopWidth: 1,
    borderTopColor: colors.border,
    backgroundColor: colors.background,
  },
  navButton: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    paddingVertical: 8,
  },
  navLabel: {
    marginTop: 4,
    fontSize: 10,
    fontWeight: '700',
  },
  card: {
    width: '100%',
    borderRadius: 16,
    borderWidth: 1,
    borderColor: colors.border,
    backgroundColor: colors.background,
    marginVertical: 10,
    overflow: 'hidden',
  },
  cardContent: {
    padding: 16,
  },
  cardTitle: {
    fontSize: 20,
    fontWeight: '700',
    color: colors.black,
  },
  cardSubtitle: {
    marginTop: 6,
    fontSize: 12,
    color: colors.gray,
  },
});
