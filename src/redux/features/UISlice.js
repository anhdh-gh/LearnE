import { createSlice } from '@reduxjs/toolkit'

const UISlice = createSlice({
    name: 'UI',
    initialState: {
        Loader: {
            isShow: false
        },

        ToastModal: {
            isShow: false,
            message: '',
            type: ''
        },

        Auth: {
            signIn: {
                isButtonSignInSpin: false,
                isPageSignIn: false
            },

            signUp: {
                isButtonSignUpSpin: false,
                isPageSignUp: false
            }
        },

        Header: {
            userInfo: {
                isLoading: false
            },
            height: 0
        },

        Url: {
            previous: undefined,
            current: undefined,
        }
    },
    reducers: {
        showLoader: (state, { payload }) => {
            return {
                ...state,
                Loader: {
                    ...state.Loader,
                    isShow: true
                }
            }
        },

        hideLoader: (state, { payload }) => {
            return {
                ...state,
                Loader: {
                    ...state.Loader,
                    isShow: false
                }
            }
        },
        
        showToastModal: (state, { payload }) => {
            return {
                ...state,
                ToastModal: {
                    ...state.ToastModal,
                    isShow: true,
                    message: payload.message,
                    type: payload.type
                }
            }
        },

        hideToastModal: (state, { payload }) => {
            return {
                ...state,
                ToastModal: {
                    ...state.ToastModal,
                    isShow: false,
                }
            }
        },

        showAuthSignInIsButtonSignInSpin: (state, { payload }) => {
            return {
                ...state,
                Auth: {
                    ...state.Auth,
                    signIn: {
                        ...state.Auth.signIn,
                        isButtonSignInSpin: true
                    }
                }
            }
        },

        hideAuthSignInIsButtonSignInSpin: (state, { payload }) => {
            return {
                ...state,
                Auth: {
                    ...state.Auth,
                    signIn: {
                        ...state.Auth.signIn,
                        isButtonSignInSpin: false
                    }
                }
            }
        },        

        showLoadingHeaderUserInfo: (state, { payload }) => {
            return {
                ...state,
                Header: {
                    ...state.Header,
                    userInfo: {
                        ...state.Header.isLoading,
                        isLoading: true
                    }
                }
            }
        },

        hideLoadingHeaderUserInfo: (state, { payload }) => {
            return {
                ...state,
                Header: {
                    ...state.Header,
                    userInfo: {
                        ...state.Header.isLoading,
                        isLoading: false
                    }
                }
            }
        },

        showAuthSignUpIsButtonSignUpSpin: (state, { payload }) => {
            return {
                ...state,
                Auth: {
                    ...state.Auth,
                    signUp: {
                        ...state.Auth.signUp,
                        isButtonSignUpSpin: true
                    }
                }
            }
        },

        hideAuthSignUpIsButtonSignUpSpin: (state, { payload }) => {
            return {
                ...state,
                Auth: {
                    ...state.Auth,
                    signUp: {
                        ...state.Auth.signUp,
                        isButtonSignUpSpin: false
                    }
                }
            }
        },  

        setUrl: (state, { payload }) => {
            if(state.Url.current !== payload) {
                return {
                    ...state,
                    Url: {
                        ...state.Url,
                        previous: state.Url.current,
                        current: payload
                    }
                }                
            }
        },

        showAuthSignUpIsPageSignUp: (state, { payload }) => {
            return {
                ...state,
                Auth: {
                    ...state.Auth,
                    signUp: {
                        ...state.Auth.signUp,
                        isPageSignUp: true
                    }
                }
            }
        },

        hideAuthSignUpIsPageSignUp: (state, { payload }) => {
            return {
                ...state,
                Auth: {
                    ...state.Auth,
                    signUp: {
                        ...state.Auth.signUp,
                        isPageSignUp: false
                    }
                }
            }
        },  

        showAuthSignInIsPageSignIn: (state, { payload }) => {
            return {
                ...state,
                Auth: {
                    ...state.Auth,
                    signIn: {
                        ...state.Auth.signIn,
                        isPageSignIn: true
                    }
                }
            }
        },

        hideAuthSignInIsPageSignIn: (state, { payload }) => {
            return {
                ...state,
                Auth: {
                    ...state.Auth,
                    signIn: {
                        ...state.Auth.signIn,
                        isPageSignIn: false
                    }
                }
            }
        },  

        setHeightHeader: (state, { payload }) => {
            return {
                ...state,
                Header: {
                    ...state.Header,
                    height: payload !== 0 ? payload : state.Header.height
                }
            }
        },
    }
})

export const { 
    showLoader, hideLoader, showToastModal, hideToastModal, 
    showAuthSignInIsButtonSignInSpin, hideAuthSignInIsButtonSignInSpin, 
    showLoadingHeaderUserInfo, hideLoadingHeaderUserInfo, showAuthSignUpIsButtonSignUpSpin,
    hideAuthSignUpIsButtonSignUpSpin, setUrl, showAuthSignUpIsPageSignUp,
    hideAuthSignUpIsPageSignUp, showAuthSignInIsPageSignIn, hideAuthSignInIsPageSignIn, setHeightHeader
} = UISlice.actions

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