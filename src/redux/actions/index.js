// UI
import { 
    showLoader, hideLoader, 
    showToastModal, hideToastModal, 
    showAuthSignInIsButtonSignInSpin, hideAuthSignInIsButtonSignInSpin, 
    showAuthSignUpIsButtonSignUpSpin, hideAuthSignUpIsButtonSignUpSpin, 
    setUrl, 
    showAuthSignUpIsPageSignUp, hideAuthSignUpIsPageSignUp, 
    showAuthSignInIsPageSignIn, hideAuthSignInIsPageSignIn, 
    setHeightHeader,
    setHeightCourseHeader, setDimensionBrowserWindow, 
    showTopLoader, hideTopLoader, offDisplayTopLoader,
    showNotFound, hideNotFound,
    initIsProcessingDone,
    fetchCourseProcessingDone,
    setPercentProgressTopLoader,
} from '../features/UISlice'

// Common
import { initInfo } from './commonAction'

// User
import { saveUser, removeUser } from '../features/UserSlice'
import { signIn, signOut, signUp } from './userAction'

// Course
import { getCourseById, updateLessonStatus } from './courseAction'
import { saveCourse, removeCourse } from '../features/CourseSlice'

export {
    // UI
    showLoader, hideLoader,
    showToastModal, hideToastModal,
    showAuthSignInIsButtonSignInSpin, hideAuthSignInIsButtonSignInSpin,
    showAuthSignUpIsButtonSignUpSpin, hideAuthSignUpIsButtonSignUpSpin,
    setUrl,
    showAuthSignUpIsPageSignUp, hideAuthSignUpIsPageSignUp, 
    showAuthSignInIsPageSignIn, hideAuthSignInIsPageSignIn,
    setHeightHeader, setHeightCourseHeader,
    setDimensionBrowserWindow,
    showTopLoader, hideTopLoader, offDisplayTopLoader,
    showNotFound, hideNotFound,
    initIsProcessingDone,
    fetchCourseProcessingDone,
    setPercentProgressTopLoader,

    // Common
    initInfo,

    // User
    signIn,
    signOut,
    signUp,
    saveUser,
    removeUser,

    // Course
    saveCourse, removeCourse,
    getCourseById,
    updateLessonStatus
}