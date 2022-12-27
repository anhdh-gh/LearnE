import '../assets/css/Audio.css'
import { useSpeechSynthesis } from 'react-speech-kit'
import { OverlayTrigger, Tooltip } from 'react-bootstrap'
import { useState, useLayoutEffect } from 'react'
import { DictionaryApi } from '../api'

const AudioWord = (props) => {

    const { word, autoGetInfo = true } = props

    const { speak, cancel, speaking, supported, voices } = useSpeechSynthesis()
    const [ phonetic, setPhonetic ] = useState()

    const getPhonetic = (info) => {
        return info?.map(info => info.phonetics)
        .reduce((array, phonetic) => {
            array.push(...phonetic)
            return array
        }, [])
        .map(phoneticItem => phoneticItem.text)
        .filter(phonetic => phonetic)
        .shift()
    }

    useLayoutEffect (() => {
        console.log(word)
        // Trích xuất phiên âm
        if (word?.info) {
            setPhonetic(getPhonetic(word?.info))
        } else if(word?.text?.trim()?.split(" ")?.length === 1 && word?.type === 'key' && autoGetInfo) {
            DictionaryApi.getInforWord(word.text)
            .then(info => setPhonetic(getPhonetic(info)))
        }
    }, [word, autoGetInfo])

    const filterVoice = (lang) => {
        const voicesFilter = voices.filter(voice => voice.lang.trim().toLowerCase().includes(lang.trim().toLowerCase()))
        return voicesFilter && voicesFilter.length > 0 ? voicesFilter[0] : voices[0]
    }

    const handleClickAudio = e => {
        e.stopPropagation()

        if (speaking)
            cancel()
        else
            speak({
                text: word.text,
                voice: word.type === 'key'
                    ? filterVoice("en-US")
                    : filterVoice("vi-VN")
            })
    }

    return !supported
        ? <></>
        : <OverlayTrigger placement="bottom" overlay={phonetic ? <Tooltip>{phonetic}</Tooltip> : <></>}>
            <span className={`audio cursor-pointer
                ${props.className} 
                ${speaking && 'audio-play'}`} onClick={handleClickAudio}
            ><i className="fas fa-volume-up"></i></span>
        </OverlayTrigger>
}

export default AudioWord

// Cái response của api dictionary này nó chứa cả audio
// Nghĩ cách xem làm thế nào để show được audio
// https://api.dictionaryapi.dev/api/v2/entries/en/agreement