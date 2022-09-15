import { createSlice } from '@reduxjs/toolkit'

const UISlice = createSlice({
    name: 'UI',
    initialState: {
        isShowLoader: false,
    },
    reducers: {
        showLoader: (state, { payload }) => {
            return {
                ...state,
                isShowLoader: true,
            }
        },

        hideLoader: (state, { payload }) => {
            return {
                ...state,
                isShowLoader: false,
            }
        },
    }
})

export const { showLoader, hideLoader } = UISlice.actions

export default UISlice.reducer

/*
- ACTION_TYPE sẽ có dạng: name/method-of-reducers
  + UI/showLoader
  + UI/hideLoader

- Các hàm action 
  + showLoader(payload) {
      return {
          type: UI/showLoader,
          payload: payload,
      }
  }

  + hideLoader(payload) {
      return {
          type: UI/hideLoader,
          payload: payload,
      }
  }
*/

/*
- Tương đương việc:
    const UIReducer = (state = initialState, { type, payload }) => {
        switch (type) {
            case UI/showLoader:
                return {
                    ...state,
                    isShowLoader: true,
                }

            case UI/hideLoader:
                return {
                    ...state,
                    isShowLoader: false,
                }

            default:
                return state
        }
    }

- Với @redux-toolkit nó tự động thêm case default vào reducers
*/

/*
- https://viblo.asia/p/redux-toolkit-refactor-lai-redux-structure-RQqKL0pmK7z
- Một slice là sự kết hợp của reducer và action
*/