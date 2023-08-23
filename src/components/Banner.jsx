import React, { useRef, useEffect } from 'react';
import { View, FlatList, Image, StyleSheet } from 'react-native';

function Banner({ images }) {
  const flatListRef = useRef(null);
  let currentIndex = 0;

  const handleSlide = () => {
    if (currentIndex < images.length - 1) {
      currentIndex++;
    } else {
      currentIndex = 0;
    }
    flatListRef.current.scrollToIndex({ animated: true, index: currentIndex });
  };

  useEffect(() => {
    const slideInterval = setInterval(handleSlide, 5000); // Slide every 5 seconds

    return () => {
      clearInterval(slideInterval);
    };
  }, []);

  const renderBannerItem = ({ item }) => (
    <View style={styles.bannerItem}>
      <Image style={styles.bannerImage} source={item.source} />
    </View>
  );

  return (
    <View style={styles.bannerContainer}>
      <FlatList
        ref={flatListRef}
        horizontal
        pagingEnabled // Enables snapping to items like a carousel
        showsHorizontalScrollIndicator={false}
        data={images}
        keyExtractor={(item, index) => index.toString()}
        renderItem={renderBannerItem}
        contentContainerStyle={styles.bannerItemsContainer}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  bannerContainer: {
    marginBottom: 20,
  },
  bannerItem: {
    marginRight: 10,
  },
  bannerImage: {
    width: 305,
    height: 160,
    borderRadius: 10,
    resizeMode: 'cover',
  },
  bannerItemsContainer: {
    flexDirection: 'row',
    paddingVertical: 10,
    marginLeft: 10,
  },
});

export default Banner;
