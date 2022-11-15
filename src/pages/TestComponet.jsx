import { MultimediaApi } from '../api'
import { useState } from "react"

const TestComponet = (props) => {

    // const handleUploadFile = (files, allFiles) => {
    //     const fileImages = files?.filter(file => file.file.type.includes('image'))
    //     const fileAudios = files?.filter(file => file.file.type.includes('audio'))
    //     if(fileImages && fileImages?.length > 1) {
    //         Notification.error("Only one image file is accepted")
    //     } else if(fileAudios && fileAudios?.length > 1) {
    //         Notification.error("Only one audio file is accepted")
    //     } else {
    //         const fileImage = fileImages?.[0]?.file
    //         const fileAudio = fileAudios?.[0]?.file
    //         if(fileImage && fileImage.size > 2000000) {
    //             Notification.error("The maximum size of the image file is 2Mb")
    //         } else if(fileAudio && fileAudio.size > 10000000) {
    //             Notification.error("The maximum size of the audio file is 10Mb")
    //         } else {
    //             MultimediaApi.questionUpload(fileImage, fileAudios)
    //             .then(res => {
    //                 console.log(res)
    //             })
    //         }
    //     }
    // }

    // return <>
    //     <ModalUploadFile
    //         modalTitle="Upload file"
    //         type={'image'}
    //         isShow={true}
    //         handleSubmit={handleUploadFile}
    //         accept="image/*,audio/*"
    //         inputContent={(files, extra) => extra.reject ? 'Image and audio only' : 'Drag and drop or click to select file'}
    //         styles={{
    //             dropzoneReject: { borderColor: 'red', backgroundColor: '#DAA' },
    //             inputLabel: (files, extra) => (extra.reject ? { color: 'red' } : {}),
    //         }}
    //         multiple={true}
    //         maxFiles={2}
    //         inputWithFilesContent={files => `${2 - files.length} more`}
    //         handleClose={() => {}}
    //     />
    // </>


    const [file, setFile] = useState()

    const onFormSubmit = (e) => {
        e.preventDefault() // Stop form submit
        // MultimediaApi.questionDeleteByGroupId("973a38ba-06df-4269-8abc-8e6f1f3c08a2")
        // .then(res => console.log(res))

        MultimediaApi.questionUpload(null, file)
        .then(res => {
            console.log(res)
        })
    }

    return <form onSubmit={onFormSubmit}>
        <h1>File Upload</h1>
        <input type="file" name="audio "onChange={e => setFile(e.target.files[0])} />
        <button type="submit">Upload</button>
    </form>
}

export default TestComponet