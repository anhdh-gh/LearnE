import axios from "axios"
import { API_ENDPOINT } from '../constants'

const createAxios = () => {
    let axiosInstant = axios.create()
    axiosInstant.interceptors.response.use(
        response => response,
        error => {
            console.clear()
        }
    )
    return axiosInstant
}

const axiosDictionaryApi = createAxios()

const DictionaryApi = {

    // Lấy toàn bộ thông tin của các key và value (bao gồm nghĩa, phát âm, từ loại,... ) thông qua api
    getInforWord: async (word) => {
        try {
            const res = await axiosDictionaryApi.get(`${API_ENDPOINT.FREE_DICTIONARY_API}/${word}`)
            return res.data
        } 
        catch (err) {
            // console.error(err)
        }
    },
}

export default DictionaryApi