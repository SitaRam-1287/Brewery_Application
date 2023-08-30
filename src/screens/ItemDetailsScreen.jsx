import React, {useState, useEffect} from 'react';
import {
  View,
  Text,
  Modal,
  Image,
  TouchableOpacity,
  StyleSheet,
  FlatList,
  ScrollView,
} from 'react-native';
import FastImage from 'react-native-fast-image';
import SkeletonPlaceholder from 'react-native-skeleton-placeholder';
import {get} from '../api';

export default function ItemDetailsScreen({route, navigation}) {
  const id = route.params.id;
  const [item, setItem] = useState({});
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    async function fetchData() {
      try {
        const response = await get(`/item/${id}`);
        setItem(response);
        setLoading(false);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    }
    fetchData();
  }, []);

  return (
    <Modal animationType="slide" transparent={false} visible={true}>
      <View style={styles.container}>
        <View style={styles.modalContent}>
          <TouchableOpacity
            onPress={() => navigation.goBack()}
            style={styles.closeButton}>
            <Text style={styles.closeButtonText}>X</Text>
          </TouchableOpacity>
          {loading ? (
            <SkeletonPlaceholder>
              <SkeletonPlaceholder.Item
                flexDirection="column"
                alignItems="center">
                <SkeletonPlaceholder.Item
                  width={150}
                  height={150}
                  borderRadius={10}
                  marginBottom={10}
                  marginTop={29}
                />
                <SkeletonPlaceholder.Item
                  width={200}
                  height={24}
                  borderRadius={4}
                  marginBottom={10}
                />
                <SkeletonPlaceholder.Item
                  width={'100%'}
                  height={18}
                  borderRadius={4}
                  marginBottom={5}
                />
                <SkeletonPlaceholder.Item
                  width={'100%'}
                  height={18}
                  borderRadius={4}
                  marginBottom={5}
                />
                <SkeletonPlaceholder.Item
                  width={'100%'}
                  height={18}
                  borderRadius={4}
                  marginBottom={20}
                />
              </SkeletonPlaceholder.Item>
            </SkeletonPlaceholder>
          ) : (
            <>
              <FastImage
                style={styles.image}
                source={{
                  uri: `data:image/png;base64,${item.image}`,
                  priority: FastImage.priority.normal,
                }}
                resizeMode={FastImage.resizeMode.cover}
              />
              <Text style={styles.name}>{item.name}</Text>
              <Text style={styles.price}>Price: Rs. {item.price}</Text>
              <Text style={styles.description}>{item.details}</Text>

              <ScrollView style={styles.reviewsScrollView}>
                <FlatList
                  data={item.ratings}
                  keyExtractor={rating => rating.id}
                  renderItem={({item: rating}) => (
                    <View style={styles.reviewItem}>
                      <Text style={styles.reviewAuthor}>
                        {rating.userFirstName} {rating.userLastName}
                      </Text>
                      <View style={styles.starRating}>
                        {Array.from({length: rating.rating}).map((_, index) => (
                          <Image
                            key={index}
                            source={require('../assets/icons/star.png')}
                            style={styles.starIcon}
                          />
                        ))}
                      </View>
                      <Text style={styles.reviewComment}>{rating.comment}</Text>
                    </View>
                  )}
                />
              </ScrollView>
            </>
          )}
        </View>
      </View>
    </Modal>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'rgba(0, 0, 0, 0.7)',
  },
  modalContent: {
    width: '100%',
    height: '100%',
    backgroundColor: 'white',
    borderRadius: 20,
    padding: 20,
    alignItems: 'center',
    elevation: 5,
    marginBottom: '-50%',
  },
  image: {
    width: 150,
    height: 150,
    borderRadius: 10,
    marginBottom: 10,
    marginTop: 29,
  },
  name: {
    fontSize: 20,
    fontWeight: 'bold',
    marginBottom: 5,
    color: 'gray',
  },
  price: {
    fontSize: 18,
    color: '#fdab00',
    marginBottom: 10,
  },
  description: {
    fontSize: 16,
    marginBottom: 20,
    color: 'gray',
  },
  closeButton: {
    position: 'absolute',
    top: 10,
    right: 10,
    backgroundColor: '#fdba00',
    borderRadius: 20,
    paddingVertical: 10,
    paddingHorizontal: 20,
  },
  closeButtonText: {
    color: 'white',
    fontWeight: 'bold',
  },
  reviewsScrollView: {
    maxHeight: 200,
  },
  reviewsContainer: {
    marginTop: 20,
  },
  reviewsTitle: {
    fontSize: 18,
    fontWeight: 'bold',
    marginBottom: 10,
    color: '#333',
  },
  reviewItem: {
    borderWidth: 1,
    borderColor: '#ddd',
    borderRadius: 10,
    padding: 10,
    marginBottom: 10,
  },
  reviewAuthor: {
    fontSize: 16,
    fontWeight: 'bold',
    marginBottom: 5,
    color: '#555',
  },
  reviewRating: {
    fontSize: 14,
    color: '#888',
    marginBottom: 5,
  },
  reviewComment: {
    fontSize: 14,
    color: '#333',
  },
  starIcon: {
    width: 20,
    height: 20,
    marginRight: 5,
  },
});
