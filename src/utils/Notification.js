import { toast } from "react-toastify"
import store from '../redux/store'
import { showToastModal, hideToastModal } from "../redux/actions"

const Notification = {
    error: message => {
        if(message.length > 50) {
            store.dispatch(hideToastModal())
            store.dispatch(showToastModal({message, type: "error"}))
        } else {
            toast.dismiss() // All the displayed toasts will be removed
            const id = toast.error(message)
            return id
        }
    },

    warn: message => {
        if(message.length > 50) {
            store.dispatch(hideToastModal())
            store.dispatch(showToastModal({message, type: "warn"}))
        } else {
            toast.dismiss()
            const id = toast.warn(message)
            return id
        }
    },

    info: message => {
        if(message.length > 50) {
            store.dispatch(hideToastModal())
            store.dispatch(showToastModal({message, type: "info"}))
        } else {
            toast.dismiss()
            const id = toast.info(message)
            return id
        }
    },    

    success: message => {
        if(message.length > 50) {
            store.dispatch(hideToastModal())
            store.dispatch(showToastModal({message, type: "success"}))
        } else {
            toast.dismiss()
            const id = toast.success(message)
            return id
        }
    },    

    loading: message => {
        toast.dismiss()
        const id = toast.loading(message)
        return id
    },

    update: (id, message) => {
        toast.update(id, {render: message})
    }
}

export default Notification