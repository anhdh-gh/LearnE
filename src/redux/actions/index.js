import { 
    showLoader, hideLoader, showToastModal, hideToastModal, 
    showAuthSignInIsButtonSignInSpin, hideAuthSignInIsButtonSignInSpin, 
    showLoadingHeaderUserInfo, hideLoadingHeaderUserInfo, showAuthSignUpIsButtonSignUpSpin,
    hideAuthSignUpIsButtonSignUpSpin, setUrl, showAuthSignUpIsPageSignUp,
    hideAuthSignUpIsPageSignUp, showAuthSignInIsPageSignIn, hideAuthSignInIsPageSignIn, setHeightHeader,
    setHeightCourseHeader, setDimensionBrowserWindow
} from '../features/UISlice'
import { saveUser, removeUser } from '../features/UserSlice'
import { refreshToken, signIn, getUserInfo, signOut, signUp } from './userSagaAction'

export {
    showLoader,
    hideLoader,
    saveUser,
    refreshToken,
    getUserInfo,
    signIn,
    showToastModal, 
    hideToastModal,
    showAuthSignInIsButtonSignInSpin, 
    hideAuthSignInIsButtonSignInSpin,
    signOut,
    removeUser,
    showLoadingHeaderUserInfo,
    hideLoadingHeaderUserInfo,
    showAuthSignUpIsButtonSignUpSpin,
    hideAuthSignUpIsButtonSignUpSpin,
    signUp,
    setUrl,
    showAuthSignUpIsPageSignUp,
    hideAuthSignUpIsPageSignUp, 
    showAuthSignInIsPageSignIn, 
    hideAuthSignInIsPageSignIn,
    setHeightHeader,
    setHeightCourseHeader,
    setDimensionBrowserWindow,
}