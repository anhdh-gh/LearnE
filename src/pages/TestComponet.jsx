import { ModalUploadFile } from '../components'

const TestComponet = (props) => {

    const handleSubmit = () => {
        
    }

    return <>
        <ModalUploadFile
            modalTitle="Upload image"
            type={'image'}
            isShow={true}
            handleSubmit={handleSubmit}
            accept="image/*,audio/*,video/*"
            inputContent={(files, extra) => extra.reject ? 'Image only' : 'Drag and drop or click to select file'}
            styles={{
                dropzoneReject: { borderColor: 'red', backgroundColor: '#DAA' },
                inputLabel: (files, extra) => (extra.reject ? { color: 'red' } : {}),
            }}
            multiple={false}
            maxFiles={1}
            handleClose={() => {}}
        />
    </>
}

export default TestComponet