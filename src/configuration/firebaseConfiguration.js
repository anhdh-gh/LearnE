import { initializeApp } from "firebase/app"
import { getAnalytics } from "firebase/analytics"
import { getDatabase } from "firebase/database"

// TODO: Replace the following with your app's Firebase project configuration
const firebaseConfig = {
    apiKey: "AIzaSyDIeSr_V2SNfeql7kdj6m3boQKNVffrXvc",
    authDomain: "learne-bfb9c.firebaseapp.com",
    databaseURL: "https://learne-bfb9c-default-rtdb.asia-southeast1.firebasedatabase.app",
    projectId: "learne-bfb9c",
    storageBucket: "learne-bfb9c.appspot.com",
    messagingSenderId: "95824143039",
    appId: "1:95824143039:web:746440bdf662eae86c5c0a",
    measurementId: "G-VMC54VX31Z"
}

// Initialize Firebase
const app = initializeApp(firebaseConfig)
const analytics = getAnalytics(app)

export const auth =  getAuth(app)
export const realtimeDB = getDatabase(app)