import axios from "axios"
import queryString from "query-string"
import { API_ENDPOINT } from "../constants"

const URL = API_ENDPOINT.LEARNE_GATEWAY_API

const createAxios = () => {
    let axiosInstant = axios.create()
    axiosInstant.defaults.baseURL = URL
    axiosInstant.defaults.withCredentials = true
    axiosInstant.defaults.timeout = 20000
    axiosInstant.defaults.headers = { "Content-Type": "application/json" }
    axiosInstant.defaults.headers = { "access-control-allow-origin": "*" }
    axiosInstant.defaults.headers = localStorage.getItem("accessToken") && { Authorization: `Bearer ${localStorage.getItem("accessToken")}` }
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
        .then((res) => {
            return Promise.resolve(res)
        })
        .catch((err) => {
            if (err.msg) return Promise.reject({ ...err })
            return Promise.reject({ ...err, msg: err })
        })
}

const getAxios = createAxios()

const handleUrl = (url, query) => {
    return queryString.stringifyUrl({ url: url, query }, { arrayFormat: "comma" })
}

export default ApiClient = {
    get: (url, payload) => handleResult(getAxios.get(handleUrl(url, payload))),

    post: (url, payload) => handleResult(getAxios.post(url, payload)),

    put: (url, payload) => handleResult(getAxios.put(url, payload)),

    patch: (url, payload) => handleResult(getAxios.patch(url, payload)),

    delete: (url, payload) => handleResult(getAxios.delete(url, { data: payload })),
}