import { ADD_TO_CART, ADD_TOTAL } from '../actions/action-types/cart-actions'


const initState = {
    addedItems: [],
    itemTotals: [],
    total: 0

}

const cartReducer = (state = initState, action) => {
    let tot = 0
    //INSIDE HOME COMPONENT
    if (action.type === ADD_TO_CART) {
        let addedItem = action.id
        if (state.addedItems.includes(addedItem) === false) state.addedItems.push(addedItem);
    }
    if (action.type === ADD_TOTAL) {
        let itemA = []
        itemA.id = action.id
        itemA.total = action.total
        if (state.itemTotals.length === 0) {
            state.itemTotals.push(itemA)
            tot= action.total
        }
        else {
            if (state.addedItems[0] === action.id) {
                state.itemTotals[0] = itemA
                if (state.addedItems[0] === 1 || state.addedItems[0] === 2) {
                    state.itemTotals[0].total = action.total
                    if(state.itemTotals.length >= 2){
                        tot= action.total + state.itemTotals[1].total
                    } else {
                        tot= action.total
                    }
                }
            } else {
                state.itemTotals[1] = itemA
                if (state.addedItems[1] === 1 || state.addedItems[1] === 2) {
                    state.itemTotals[1].total = action.total
                    tot= action.total + state.itemTotals[0].total
                }
            }
        }
        return {
            ...state,
            total: tot
        }

    } else {
        return state
    }

}

export default cartReducer
