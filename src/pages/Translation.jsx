import '../assets/css/CreateEditStudySet.css'
import { Textarea, Header, Footer, AudioWord, SvelteJSONEditor } from '../components'
import { Button, OverlayTrigger, Tooltip, Badge } from 'react-bootstrap'
import { useState, useLayoutEffect } from "react"
import _ from "lodash"
import { GoogleTranslateApi, DictionaryApi } from '../api'
import { History } from '../components/NavigateSetter'
import { ROUTE_PATH } from '../constants'
import { useDebounce } from 'use-debounce'
import { CommonUtil } from '../utils'

const Translation = (props) => {
    const [term, setTerm] = useState('')
    const [definition, setDefinition] = useState('')
    const [termSearch] = useDebounce(term, 1000)
    const [from, setFrom] = useState('en')
    const [to, setTo] = useState('vi')
    const [infoOfTerm, setInfoOfTerm] = useState()

    useLayoutEffect (() => {
        window.scrollTo(0, 0)
    }, [])

    useLayoutEffect(() => {
        if (termSearch && !_.isEmpty(termSearch.trim())) {
            GoogleTranslateApi.getDefinition(termSearch.trim(), "auto", to)
                .then(res => {
                    if (res && !_.isEmpty(res) && res?.[0]) {
                        setDefinition(CommonUtil.capitalizeFirstLetter(res?.[0]))
                    } else {
                        setDefinition(termSearch)
                    }
                })

            if(termSearch.trim().split(" ").length === 1) {
                DictionaryApi.getInforWord(termSearch.trim())
                    .then(info => setInfoOfTerm(info))
            }
        }

        // eslint-disable-next-line
    }, [termSearch])

    return <>
        <Header />
        <div className="CreateEditStudySet-container">
            <div className="">
                <div className="container-xl">
                    <div className="infomation d-sm-flex">
                        <p className="title">Translation</p>
                        <Button className="fw-bold mt-3 mt-sm-0" onClick={() => History.push(ROUTE_PATH.HOME)}>
                            Home
                        </Button>
                    </div>
                </div>
            </div>

            <div className="wordCards py-4">
                <div className="container-xl">
                    <div className="wordCard-input mb-4">
                        <div className="header cursor-default">
                            <span className="index text-blue-700"><i className="fa-solid fa-language"></i></span>
                            <span onClick={e => { setFrom(to); setTo(from); setTerm(definition); setDefinition(''); setInfoOfTerm('') }} className="drag-icon cursor-pointer text-stone-900 hover:text-yellow-500 active:scale-95"><span className='font-bold'>{from.toUpperCase()}</span> <i className="fa-solid fa-right-left"></i> <span className='font-bold'>{to.toUpperCase()}</span></span>

                            <OverlayTrigger placement="bottom" overlay={<Tooltip>Clear</Tooltip>}>
                                <Badge
                                    bg="danger" className="delete-icon"
                                    onClick={e => {
                                        setTerm('')
                                        setDefinition('')
                                        setInfoOfTerm('')
                                    }}
                                ><i className="fas fa-trash-alt fs-6 active:scale-95" /></Badge>
                            </OverlayTrigger>
                        </div>

                        <div className="row input-type">
                            <div className="col-md-6">
                                <Textarea
                                    enter={false}
                                    title={<div onClick={e => { }} className='flex justify-between items-center'><span>TERM</span>{term && !_.isEmpty(term.trim()) && definition && !_.isEmpty(definition.trim()) ? <AudioWord autoGetInfo={false} word={{ text: term.trim(), type: from === 'en' ? 'key' : "value", info: infoOfTerm}} /> : `${term.length}/1000`}</div>}
                                    placeholder='Enter term'
                                    value={term}
                                    error={''}
                                    onChange={e => {
                                        if (e?.target?.value !== term) {
                                            setTerm(e.target.value)
                                            setDefinition('')
                                            setInfoOfTerm('')
                                        }
                                    }}
                                    maxLength={1000}
                                />
                            </div>

                            <div className="col-md-6 mt-5 mt-md-0">
                                <Textarea
                                    enter={false}
                                    title={<div onClick={e => { }} className='flex justify-between items-center'><span>DEFINITION</span>{definition && !_.isEmpty(definition.trim()) && <AudioWord word={{ text: definition.trim(), type: to === 'en' ? 'key' : "value"}} />}</div>}
                                    placeholder='Definition'
                                    value={definition}
                                    error={''}
                                    onChange={e => { }}
                                    readOnly={true}
                                />
                            </div>
                        </div>
                    </div>

                    {infoOfTerm && !_.isEmpty(infoOfTerm) && <div className="wordCard-input mb-4">
                        <div className="header cursor-default">
                            <span className="index"><i className="fa-solid fa-language text-blue-700"></i> <span className='font-normal'>Information of vocabulary</span></span>
                        </div>

                        <div className="wordCard-input mb-4">
                            <SvelteJSONEditor
                                content={{json: infoOfTerm}}
                                readOnly={true}
                            />
                        </div>
                    </div>}
                </div>
            </div>
        </div>
        <Footer />
    </>
}

export default Translation