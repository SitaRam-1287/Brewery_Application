const initialState = {
  items: [],
};

let cartReducer = (state = initialState, action) => {
  switch (action.type) {
    case 'ADD_TO_CART': {
      return {
        ...state,
        items: [...state.items, action.payload],
      };
    }
    case 'INCREASE_QUANTITY': {
      const selectedItem = action.payload;
      const updatedItems = state.items.map(item => {
        console.log(item.id, selectedItem.id);
        if (item.id === selectedItem.id) {
          item.quantity++;
        }
        return item;
      });

      return {
        ...state,
        items: updatedItems,
      };
    }
    case 'DECREASE_QUANTITY': {
      const selectedItem = action.payload;
      const updatedItems = state.items.map(item => {
        console.log(item.id, selectedItem.id);
        if (item.id === selectedItem.id) {
          item.quantity--;
        }
        return item;
      });

      return {
        ...state,
        items: updatedItems,
      };
    }
    case 'REMOVE_ITEM_FROM_CART': {
      const selectedItem = action.payload;
      const updatedItems = state.items.filter(
        item => item.id !== selectedItem.id,
      );

      return {
        ...state,
        items: updatedItems,
      };
    }
    default:
      return state;
  }
};

export default cartReducer;
