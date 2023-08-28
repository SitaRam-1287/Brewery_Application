import {ADD_TO_CART, INCREASE_QUANTITY} from './constants';

export const addToCart = item => ({
  type: ADD_TO_CART,
  payload: item,
});

export const increaseQuantity = itemId => ({
  type: INCREASE_QUANTITY,
  payload: itemId,
});
