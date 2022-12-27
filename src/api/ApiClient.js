import axios from "axios"
import queryString from "query-string"
import { API_ENDPOINT, KEY, ROUTE_PATH, STATUS_CODES } from "../constants"
import { UserApi } from '../api'
import Cookie from 'js-cookie'
import { History } from "../components/NavigateSetter"
import { Notification } from "../utils"

const createAxios = (contentType, URL) => {
    let axiosInstant = axios.create()
    axiosInstant.defaults.baseURL = URL
    // axiosInstant.defaults.withCredentials = true
    // axiosInstant.defaults.timeout = 20000
    axiosInstant.defaults.headers = { "Content-Type": contentType }
    axiosInstant.defaults.headers = { "Access-control-allow-origin": "*" }
    axiosInstant.defaults.headers = localStorage.getItem(KEY.ACCESS_TOKEN) && localStorage.getItem(KEY.TOKEN_TYPE) && { Authorization: `${localStorage.getItem(KEY.TOKEN_TYPE)} ${localStorage.getItem(KEY.ACCESS_TOKEN)}` }
    axiosInstant.interceptors.request.use(
        (config) => {
            if(localStorage.getItem(KEY.ACCESS_TOKEN) && localStorage.getItem(KEY.TOKEN_TYPE)) {
                config.headers = {
                    ...config.headers,
                    Authorization: `${localStorage.getItem(KEY.TOKEN_TYPE)} ${localStorage.getItem(KEY.ACCESS_TOKEN)}` 
                }
            }

            return config
        },
        (error) => {
            Notification.error(error)
            window.location.reload()
        }
    )
    axiosInstant.interceptors.response.use(
        (response) => {
            const config = response?.config
            const { meta } = response.data
            const refreshToken = Cookie.get(KEY.REFRESH_TOKEN)
            if(meta.code === 401 && refreshToken) {
                return UserApi.handleRefreshToken(refreshToken)()
                    .then(res => {
                        const { data: dataRefreshToken, meta: metaRefreshToken } = res
                        if(metaRefreshToken.code === STATUS_CODES.SUCCESS) {
                            const { accessToken, tokenType, refreshToken } = dataRefreshToken
                            Cookie.set(KEY.REFRESH_TOKEN, refreshToken)
                            localStorage.setItem(KEY.ACCESS_TOKEN, accessToken)
                            localStorage.setItem(KEY.TOKEN_TYPE, tokenType)
        
                            config.headers = {
                                ...config.headers,
                                Authorization: `${localStorage.getItem(KEY.TOKEN_TYPE)} ${localStorage.getItem(KEY.ACCESS_TOKEN)}` 
                            }
        
                            return axiosInstant(config) // Set lại thông tin và thực hiện gọi lại request trước đó
                        } else {
                            History.push(ROUTE_PATH.SIGN_IN)
                        }
                    })
            }

            return response
        },
        (error) => {
            // const config = error?.config
            // const refreshToken = Cookie.get(KEY.REFRESH_TOKEN)
            // if (error?.response?.status === 401 && !config?.sent && refreshToken) {
            //     config.sent = true

            //     const response = UserApi.handleRefreshToken(refreshToken)
            //     const { data, meta } = response
            //     if(meta.code === STATUS_CODES.SUCCESS) {
            //         const { accessToken, tokenType, refreshToken } = data
            //         Cookie.set(KEY.REFRESH_TOKEN, refreshToken)
            //         localStorage.setItem(KEY.ACCESS_TOKEN, accessToken)
            //         localStorage.setItem(KEY.TOKEN_TYPE, tokenType)

            //         config.headers = {
            //             ...config.headers,
            //             Authorization: `${localStorage.getItem(KEY.TOKEN_TYPE)} ${localStorage.getItem(KEY.ACCESS_TOKEN)}` 
            //         }

            //         return axiosInstant(config)
            //     }         

            // }

            // throw error

            Notification.error(error)
        }
    )
    return axiosInstant
}

const handleResult = (api) => {
    return api
        .then(res => Promise.resolve(res?.data))
        .catch(err => {
            // console.log(err)
            Notification.error(err)
        })
}

let getAxiosInstance
const getAxios = () => {
    if(!getAxiosInstance || ((!localStorage.getItem(KEY.ACCESS_TOKEN) || !localStorage.getItem(KEY.TOKEN_TYPE)) && !Cookie.get(KEY.REFRESH_TOKEN))) {
        getAxiosInstance = createAxios("application/json", API_ENDPOINT.LEARNE_GATEWAY_API)
    }

    return getAxiosInstance
}

let getAxiosForMultipartInstance
const getAxiosForMultipart = () => {
    if(!getAxiosForMultipartInstance || ((!localStorage.getItem(KEY.ACCESS_TOKEN) || !localStorage.getItem(KEY.TOKEN_TYPE)) && !Cookie.get(KEY.REFRESH_TOKEN))) {
        getAxiosForMultipartInstance = createAxios("multipart/form-data", API_ENDPOINT.LEARNE_MULTIMEDIA_API)
    }

    return getAxiosForMultipartInstance
}

const handleUrl = (url, query) => {
    return queryString.stringifyUrl({ url: url, query }, { arrayFormat: "comma" })
}

const ApiClient = {
    get: (url, payload) => handleResult(getAxios().get(handleUrl(API_ENDPOINT.LEARNE_GATEWAY_API + url, payload))),

    post: (url, payload) => handleResult(getAxios().post(API_ENDPOINT.LEARNE_GATEWAY_API + url, payload)),

    postMultimedia: (url, payload) =>  handleResult(getAxios().post(API_ENDPOINT.LEARNE_MULTIMEDIA_API + url, payload)),

    postMultipartFormData: (url, payload) =>  handleResult(getAxiosForMultipart().post(API_ENDPOINT.LEARNE_MULTIMEDIA_API + url, payload)),

    put: (url, payload) => handleResult(getAxios().put(API_ENDPOINT.LEARNE_GATEWAY_API + url, payload)),

    patch: (url, payload) => handleResult(getAxios().patch(API_ENDPOINT.LEARNE_GATEWAY_API + url, payload)),

    delete: (url, payload) => handleResult(getAxios().delete(API_ENDPOINT.LEARNE_GATEWAY_API + url, { data: payload })),
}

export default ApiClient