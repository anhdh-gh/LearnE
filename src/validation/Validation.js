import { Notification } from "../utils"

const Validation = {
    signIn: (email, password) => {
        return Validation.email(email) && Validation.emptyPassword(password)
    },

    emptyPassword: (password) => {
        if(!password || !password.trim()) {
            Notification.error("Password is not empty")
            return false
        }

        return true
    },

    strongPassword: (password) => {
        const validPassword = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$/ // eslint-disable-line
        if(!password.match(validPassword)) {
            Notification.error("Password is not strong")
            return false
        }

        return true
    },

    email: (email) => { 
        if(!email || !email.trim()) {
            Notification.error("Email is not empty")
            return false
        }

        const validEmail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/ // eslint-disable-line
        if(!email.match(validEmail)) {
            Notification.error("Email is not valid")
            return false
        }

        return true
    }
}

export default Validation
