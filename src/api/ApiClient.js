import axios from "axios"
import queryString from "query-string"
import { API_ENDPOINT, KEY, STATUS_CODES } from "../constants"
import { UserApi } from '../api'
import Cookie from 'js-cookie'

const URL = API_ENDPOINT.LEARNE_GATEWAY_API

const createAxios = () => {
    let axiosInstant = axios.create()
    axiosInstant.defaults.baseURL = URL
    // axiosInstant.defaults.withCredentials = true
    axiosInstant.defaults.timeout = 20000
    axiosInstant.defaults.headers = { "Content-Type": "application/json" }
    axiosInstant.defaults.headers = { "access-control-allow-origin": "*" }
    axiosInstant.defaults.headers = localStorage.getItem(KEY.ACCESS_TOKEN) && { Authorization: `${localStorage.getItem(KEY.TOKEN_TYPE)} ${localStorage.getItem(KEY.ACCESS_TOKEN)}` }
    axiosInstant.interceptors.request.use(
        (config) => {
            if(localStorage.getItem(KEY.ACCESS_TOKEN)) {
                config.headers = {
                    ...config.headers,
                    Authorization: `${localStorage.getItem(KEY.TOKEN_TYPE)} ${localStorage.getItem(KEY.ACCESS_TOKEN)}` 
                }
            }

            return config
        },
        (error) => {
            console.error(error) 
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

            // console.error(error)
            // throw error

            window.location.reload()
        }
    )
    return axiosInstant
}

const handleResult = (api) => {
    return api
        .then(res => Promise.resolve(res?.data))
        .catch(err => {
            // console.log(err)
            window.location.reload()
        })
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