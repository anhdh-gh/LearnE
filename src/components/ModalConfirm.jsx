import { Button, Modal } from 'react-bootstrap'

const ModalConfirm = (props) => {
    const { show, setShow, title, message, handleNo, handleYes, labelYes, labelNo } = props

    return <Modal show={show} onHide={() => setShow(false)} backdrop="static">
        <Modal.Header closeButton>
            <Modal.Title>{title}</Modal.Title>
        </Modal.Header>
        <Modal.Body><div className="overflow-auto mw-100">{message}</div></Modal.Body>
        <Modal.Footer>
            <Button variant="danger" onClick={handleYes}>{labelYes || 'Yes'}</Button>
            <Button variant="primary" onClick={handleNo}>{labelNo || 'No'}</Button>
        </Modal.Footer>
    </Modal>
}

export default ModalConfirm