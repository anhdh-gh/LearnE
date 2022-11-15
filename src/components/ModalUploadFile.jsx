import { Modal } from 'react-bootstrap'
import Dropzone from 'react-dropzone-uploader'

// https://react-dropzone-uploader.js.org/docs/examples
const ModalUploadFile = (props) => {

    const { 
        modalTitle, 
        isShow, 
        handleClose, 
        handleSubmit, 
        accept, 
        inputContent, 
        styles,
        submitButtonDisabled,
        maxFiles,
        multiple,
        canCancel,
        onChangeStatus,
        inputWithFilesContent
     } = props

    return <Modal scrollable centered show={isShow} onHide={handleClose}>
        <Modal.Header closeButton>
            <Modal.Title>{modalTitle}</Modal.Title>
        </Modal.Header>
        <Modal.Body className="">
            <Dropzone
                onSubmit={handleSubmit}
                accept={accept}
                inputContent={inputContent}
                styles={styles}
                submitButtonDisabled={submitButtonDisabled}
                maxFiles={maxFiles}
                multiple={multiple}
                canCancel={canCancel}
                onChangeStatus={onChangeStatus}
                inputWithFilesContent={inputWithFilesContent}
            />
        </Modal.Body>
    </Modal>
}

export default ModalUploadFile