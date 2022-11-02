import { initializeApp } from "firebase/app"
import { getAnalytics } from "firebase/analytics"
import { getDatabase } from "firebase/database"

// TODO: Replace the following with your app's Firebase project configuration
const firebaseConfig = {
    apiKey: "AIzaSyBFflIeftc5O-m9TotdYgyVcLEFbXGskqQ",
    authDomain: "learne-41d47.firebaseapp.com",
    databaseURL: "https://learne-41d47-default-rtdb.asia-southeast1.firebasedatabase.app",
    projectId: "learne-41d47",
    storageBucket: "learne-41d47.appspot.com",
    messagingSenderId: "355681462174",
    appId: "1:355681462174:web:429db443ce8d93ea3a588f",
    measurementId: "G-0NYW3YPPC8"
}

// Initialize Firebase
const app = initializeApp(firebaseConfig)
const analytics = getAnalytics(app)

export const auth =  getAuth(app)
export const realtimeDB = getDatabase(app)