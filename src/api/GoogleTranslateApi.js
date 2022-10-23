import axios from "axios"
import { API_ENDPOINT } from '../constants'

const GoogleTranslateApi = {

    // Lấy nghĩa của từ
    getDefinition: async (term, from = "auto", to = "vi") => {
        if(term && term.trim().length > 0)
            try {
                const res = await axios.get(`${API_ENDPOINT.GOOGLE_TRANSLATE}?client=gtx&sl=${from}&tl=${to}&dt=t&q=${term}`)
                return res.data[0][0].filter(definition => definition && typeof definition === 'string')
                    .map(definition => definition.trim().toLowerCase())
                    .filter(definition => definition !== term.trim().toLowerCase())
            }  catch (err) {
                console.log(err)
            }
        
        return []
    },
}

export default GoogleTranslateApi