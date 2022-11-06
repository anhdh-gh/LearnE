import { createSlice } from '@reduxjs/toolkit'

const UISlice = createSlice({
    name: 'UI',
    initialState: {
        InitIsProcessing: {
            isProcessing: true
        },

        FetchCourseProcessing: {
            isProcessing: true
        },

        Loader: {
            isShow: false
        },

        TopLoader: {
            isShow: false,
            isDisplay: false
        },

        ProgressTopLoader: {
            percent: 0
        },

        NotFound: {
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
            height: 0
        },

        Footer: {
            height: 0
        },

        CourseHeader: {
            height: 0
        },

        Url: {
            previous: undefined,
            current: undefined,
        }, 

        BrowserWindow: {
            dimension: {
                width: window.innerWidth,
                height: window.innerHeight
            }
        }
    },
    reducers: {
        initIsProcessingDone: (state, { payload }) => {
            return {
                ...state,
                InitIsProcessing: {
                    ...state.InitIsProcessing,
                    isProcessing: false
                }
            }
        },

        fetchCourseProcessingDone: (state, { payload }) => {
            return {
                ...state,
                FetchCourseProcessing: {
                    ...state.FetchCourseProcessing,
                    isProcessing: false
                }
            }
        },

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

        showTopLoader: (state, { payload }) => {
            return {
                ...state,
                TopLoader: {
                    ...state.TopLoader,
                    isShow: true,
                    isDisplay: true
                }
            }
        },

        hideTopLoader: (state, { payload }) => {
            return {
                ...state,
                TopLoader: {
                    ...state.TopLoader,
                    isShow: false
                }
            }
        },

        offDisplayTopLoader: (state, { payload }) => {
            return {
                ...state,
                TopLoader: {
                    ...state.TopLoader,
                    isShow: false,
                    isDisplay: false
                }
            }
        },

        
        setPercentProgressTopLoader: (state, { payload }) => {
            return {
                ...state,
                ProgressTopLoader: {
                    ...state.ProgressTopLoader,
                    percent: payload < 0 ? 0 : payload >= 100 ? 99.9999999999 : payload
                }
            }
        },

        showNotFound: (state, { payload }) => {
            return {
                ...state,
                NotFound: {
                    ...state.NotFound,
                    isShow: true
                }
            }
        },

        hideNotFound: (state, { payload }) => {
            return {
                ...state,
                NotFound: {
                    ...state.NotFound,
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

        setHeightFooter: (state, { payload }) => {
            return {
                ...state,
                Footer: {
                    ...state.Footer,
                    height: payload !== 0 ? payload : state.Footer.height
                }
            }
        },

        setHeightCourseHeader: (state, { payload }) => {
            return {
                ...state,
                CourseHeader: {
                    ...state.CourseHeader,
                    height: payload !== 0 ? payload : state.Header.height
                }
            }
        },

        setDimensionBrowserWindow: (state, { payload }) => {
            return {
                ...state,
                BrowserWindow: {
                    ...state.BrowserWindow,
                    dimension: {
                        ...state.BrowserWindow.dimension,
                        width: payload.width,
                        height: payload.height
                    }
                }
            }
        }
    }
})

export const { 
    showLoader, hideLoader, 
    showToastModal, hideToastModal, 
    showAuthSignInIsButtonSignInSpin, hideAuthSignInIsButtonSignInSpin, 
    showAuthSignUpIsButtonSignUpSpin,hideAuthSignUpIsButtonSignUpSpin, 
    setUrl, 
    showAuthSignUpIsPageSignUp,hideAuthSignUpIsPageSignUp, 
    showAuthSignInIsPageSignIn, hideAuthSignInIsPageSignIn, 
    setHeightHeader, setHeightFooter,
    setHeightCourseHeader, 
    setDimensionBrowserWindow, 
    showTopLoader, hideTopLoader, offDisplayTopLoader,
    showNotFound, hideNotFound,
    initIsProcessingDone,
    fetchCourseProcessingDone,
    setPercentProgressTopLoader,
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