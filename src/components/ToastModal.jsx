import { useSelector } from "react-redux"
import { hideToastModal } from '../redux/actions'
import Button from 'react-bootstrap/Button'
import Modal from 'react-bootstrap/Modal'
import { useDispatch } from "react-redux"

const ToastModal = (props) => {

    const { isShow, message, type } = useSelector(state => state.UI.ToastModal)
    const dispatch = useDispatch()

    const handleClose = () => {
        dispatch(hideToastModal())
    }

    return <Modal scrollable centered size={`${message?.length <= 100 ? 'sm' : message?.length <= 500 ? 'lg' : 'xl'}`} show={isShow} onHide={handleClose}>
        <Modal.Header closeButton>
            <Modal.Title>Notification</Modal.Title>
        </Modal.Header>
        <Modal.Body className="">{message}</Modal.Body>
        <Modal.Footer>
            <Button variant={type === "info" ? "primary" : type === "warn" ? "warning" : type === "error" ? "danger" : "success"} onClick={handleClose}>Close</Button>
        </Modal.Footer>
    </Modal>
}

export default ToastModal