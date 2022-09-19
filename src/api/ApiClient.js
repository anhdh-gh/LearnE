import axios from "axios"
import queryString from "query-string"
import { API_ENDPOINT, KEY } from "../constants"

const URL = API_ENDPOINT.LEARNE_GATEWAY_API

const createAxios = () => {
    let axiosInstant = axios.create()
    axiosInstant.defaults.baseURL = URL
    // axiosInstant.defaults.withCredentials = true
    axiosInstant.defaults.timeout = 20000
    axiosInstant.defaults.headers = { "Content-Type": "application/json" }
    axiosInstant.defaults.headers = { "access-control-allow-origin": "*" }
    axiosInstant.defaults.headers = localStorage.getItem(KEY.ACCESS_TOKEN) && { Authorization: `${localStorage.getItem(KEY.TOKEN_TYPE)} ${localStorage.getItem(KEY.ACCESS_TOKEN)}` }
    axiosInstant.interceptors.response.use(
        (response) => {
            return response
        },
        (error) => {
            throw error
        }
    )
    return axiosInstant
}

const handleResult = (api) => {
    return api
        .then(res => Promise.resolve(res.data))
        .catch(err => console.log(err))
}

const getAxios = createAxios()

const handleUrl = (url, query) => {
    return queryString.stringifyUrl({ url: url, query }, { arrayFormat: "comma" })
}

const ApiClient = {
    get: (url, payload) => handleResult(getAxios.get(handleUrl(URL + url, payload))),

    post: (url, payload) => handleResult(getAxios.post(URL + url, payload)),

    put: (url, payload) => handleResult(getAxios.put(URL + url, payload)),

    patch: (url, payload) => handleResult(getAxios.patch(URL + url, payload)),

    delete: (url, payload) => handleResult(getAxios.delete(URL + url, { data: payload })),
}

export default ApiClient