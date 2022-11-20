import { useParams } from 'react-router'
import { QuestionApi } from '../api'
import { useQuery } from '@tanstack/react-query'
import { useLayoutEffect, useState } from 'react'
import _ from 'lodash'
import { showLoader, hideLoader, showNotFound, hideNotFound } from '../redux/actions'
import { useDispatch } from "react-redux"
import { STATUS_CODES } from '../constants'
import { Card } from 'react-bootstrap'
import { CommonUtil } from '../utils'
import Timer from 'react-timer-wrapper'
import Timecode from 'react-timecode'
import { Form, InputGroup, Offcanvas } from 'react-bootstrap'
import { useWindowSize } from '../hooks'

const TestFile = () => {

    const dispatch = useDispatch()
    const { groupId } = useParams()
    const [countUpTimer, setCountUpTimer] = useState(0)
    const [answers, setAnswers] = useState([])
    const [ widthWindow ] = useWindowSize()
    const [audioCurent, setAudioCurent] = useState({ isPlaying: false })
    const [showOffcanvas, setShowOffcanvas] = useState(widthWindow >= 768 ? false : true)

    const { data: responseGetByGroupId, isLoading: isLoadingGetByGroupId, isFetching: isFetchingGetByGroupId, isError: isErrorGetByGroupId } = useQuery(
        ["getByGroupId"],
        () => QuestionApi.getByGroupId(groupId),
        {
            refetchOnWindowFocus: false,
        }
    )

    useLayoutEffect(() => {
        if (isLoadingGetByGroupId || isFetchingGetByGroupId) {
            dispatch(showLoader())
        } else {
            dispatch(hideLoader())
        }

        if (isErrorGetByGroupId || (responseGetByGroupId?.meta && responseGetByGroupId?.meta?.code !== STATUS_CODES.SUCCESS)) {
            dispatch(showNotFound())
        } else {
            dispatch(hideNotFound())
        }

        return () => {
            dispatch(hideLoader())
            dispatch(hideNotFound())
        }
    }, [responseGetByGroupId, dispatch, isErrorGetByGroupId, isFetchingGetByGroupId, isLoadingGetByGroupId])

    return !isLoadingGetByGroupId && !isFetchingGetByGroupId && !isErrorGetByGroupId && responseGetByGroupId?.meta?.code === STATUS_CODES.SUCCESS && !_.isEmpty(responseGetByGroupId?.data) && <>
        <div className='container-fluid p-0'>
            <div className='row g-0 h-screen'>
                <div className="col-lg-9 col-md-8">
                    <object
                        frameBorder="0"
                        className='w-full h-screen'
                        aria-label={responseGetByGroupId?.data?.questions[0]?.text}
                        data={`${responseGetByGroupId?.data?.questions[0]?.pdf}#toolbar=0&navpanes=0&scrollbar=0`}
                    />
                </div>

                <div className="col-lg-3 col-md-4 hidden md:block">
                    <Card className="h-screen overflow-auto p-0 rounded-none border-0">
                        <Card.Header className='p-0 border-0 bg-white'>
                            <div className='flex px-2.5 pt-2.5'>
                                <div className='p-2.5 bg-indigo-500 text-white rounded rounded-sm'>MC</div>
                                <div className='px-2'>
                                    <div className='font-bold'>{responseGetByGroupId?.data?.questions[0]?.text}</div>
                                    <div className='text-sm'>{CommonUtil.getDateStringFromMilliseconds(responseGetByGroupId?.data?.questions[0]?.updateTime || responseGetByGroupId?.data?.questions[0]?.createTime)}</div>
                                </div>
                            </div>

                            <div className='px-2.5 mb-3.5'>
                                <div className='text-center mt-4 bg-indigo-400 text-white rounded rounded-sm py-2'>
                                    <div className='text-sm'>Time remaining</div>
                                    <div className='font-bold'>
                                        <Timer active={true} duration={responseGetByGroupId?.data?.questions[0]?.time} onTimeUpdate={({ time }) => setCountUpTimer(time)} />
                                        <Timecode time={responseGetByGroupId?.data?.questions[0]?.time - countUpTimer} />
                                    </div>
                                </div>
                            </div>
                        </Card.Header>
                        <Card.Body className='p-0 overflow-auto'>
                            <div className='font-bold mt-4 text-center px-2.5'>Answer sheet</div>

                            <div className='mt-4 flex flex-wrap'>
                                {responseGetByGroupId?.data?.questions[0]?.answers.map((answer, index) => <div className='grow max-w-full p-2.5' key={answer?.id}>
                                    <InputGroup id={answer?.id} className="mb-3 max-w-full flex-nowrap">
                                        <InputGroup.Text>{index + 1}</InputGroup.Text>
                                        <Form.Control
                                            type="text"
                                            className=""
                                            onChange={e => {
                                                setAnswers(preAnswers => {
                                                    preAnswers[index] = e.target.value.trim()
                                                    return [...preAnswers]
                                                })
                                            }}
                                            value={answers[index] ? answers[index] : ''}
                                            style={{ width: `${(answers[index]?.length ? answers[index]?.length + 4 : 4)}ch` }}
                                        />
                                        {answer?.audio && <InputGroup.Text
                                            onClick={() => {
                                                audioCurent?.audio?.pause()
                                                setAudioCurent({ isPlaying: false })
                                                if (audioCurent?.index !== index) {
                                                    const audio = new Audio(answer?.audio)
                                                    setAudioCurent({ audio, isPlaying: true, index })
                                                    audio.play()
                                                }
                                            }}
                                            className='cursor-pointer'><i className={`fas fa-volume-up hover:text-yellow-500 active:scale-95 ${audioCurent?.index === index && audioCurent?.isPlaying && 'text-yellow-500'}`} /></InputGroup.Text>}
                                    </InputGroup>
                                </div>)}
                            </div>
                        </Card.Body>

                        <Card.Footer className='p-0 border-0 bg-white'>
                            <div className='mt-3.5 text-center px-2.5 pb-2.5'>
                                <div className='text-center bg-indigo-600 text-white rounded rounded-sm py-2 cursor-pointer hover:bg-indigo-700'>Submit</div>
                                <div className='text-slate-500 mt-3 cursor-pointer hover:text-slate-600'>Cancel</div>
                            </div>
                        </Card.Footer>
                    </Card>
                </div>
            </div>

        </div>

        <Offcanvas backdrop={false} className="md:hidden w-full" show={showOffcanvas} onHide={() => setShowOffcanvas(prevShowOffcanvas => !prevShowOffcanvas)}>
            <Offcanvas.Body className='p-0'>
                <Card className="h-screen overflow-auto p-0 rounded-none border-0">
                    <Card.Header className='p-0 border-0 bg-white'>
                        <div className='flex px-2.5 pt-2.5'>
                            <div className='p-2.5 bg-indigo-500 text-white rounded rounded-sm'>MC</div>
                            <div className='px-2'>
                                <div className='font-bold'>{responseGetByGroupId?.data?.questions[0]?.text}</div>
                                <div className='text-sm'>{CommonUtil.getDateStringFromMilliseconds(responseGetByGroupId?.data?.questions[0]?.updateTime || responseGetByGroupId?.data?.questions[0]?.createTime)}</div>
                            </div>

                            <div className='ml-auto px-2 cursor-pointer hover:text-yellow-500 active:scale-95' onClick={() => setShowOffcanvas(prevShowOffcanvas => !prevShowOffcanvas)}><i className="fa-solid fa-xmark text-2xl"></i></div>
                        </div>

                        <div className='px-2.5 mb-3.5'>
                            <div className='text-center mt-4 bg-indigo-400 text-white rounded rounded-sm py-2'>
                                <div className='text-sm'>Time remaining</div>
                                <div className='font-bold'>
                                    <Timer active={true} duration={responseGetByGroupId?.data?.questions[0]?.time} onTimeUpdate={({ time }) => setCountUpTimer(time)} />
                                    <Timecode time={responseGetByGroupId?.data?.questions[0]?.time - countUpTimer} />
                                </div>
                            </div>
                        </div>
                    </Card.Header>
                    <Card.Body className='p-0 overflow-auto'>
                        <div className='font-bold mt-4 text-center px-2.5'>Answer sheet</div>

                        <div className='mt-4 flex flex-wrap'>
                            {responseGetByGroupId?.data?.questions[0]?.answers.map((answer, index) => <div className='grow max-w-full p-2.5' key={answer?.id}>
                                <InputGroup id={answer?.id} className="mb-3 max-w-full flex-nowrap">
                                    <InputGroup.Text>{index + 1}</InputGroup.Text>
                                    <Form.Control
                                        type="text"
                                        className=""
                                        onChange={e => {
                                            setAnswers(preAnswers => {
                                                preAnswers[index] = e.target.value.trim()
                                                return [...preAnswers]
                                            })
                                        }}
                                        value={answers[index] ? answers[index] : ''}
                                        style={{ width: `${(answers[index]?.length ? answers[index]?.length + 4 : 4)}ch` }}
                                    />
                                    {answer?.audio && <InputGroup.Text
                                        onClick={() => {
                                            audioCurent?.audio?.pause()
                                            setAudioCurent({ isPlaying: false })
                                            if (audioCurent?.index !== index) {
                                                const audio = new Audio(answer?.audio)
                                                setAudioCurent({ audio, isPlaying: true, index })
                                                audio.play()
                                                audio.onended = () => setAudioCurent({ isPlaying: false })
                                            }
                                        }}
                                        className='cursor-pointer'><i className={`fas fa-volume-up hover:text-yellow-500 active:scale-95 ${audioCurent?.index === index && audioCurent?.isPlaying && 'text-yellow-500'}`} /></InputGroup.Text>}
                                </InputGroup>
                            </div>)}
                        </div>
                    </Card.Body>

                    <Card.Footer className='p-0 border-0 bg-white'>
                        <div className='mt-3.5 text-center px-2.5 pb-2.5'>
                            <div className='text-center bg-indigo-600 text-white rounded rounded-sm py-2 cursor-pointer hover:bg-indigo-700'>Submit</div>
                            <div className='text-slate-500 mt-3 cursor-pointer hover:text-slate-600'>Cancel</div>
                        </div>
                    </Card.Footer>
                </Card>
            </Offcanvas.Body>
        </Offcanvas>

        <div
            className='md:hidden z-10 fixed bottom-0 px-3 py-2 bg-indigo-900 hover:bg-indigo-800 rounded-tr-lg text-white cursor-pointer'
            onClick={() => setShowOffcanvas(prevShowOffcanvas => !prevShowOffcanvas)}
        >
            <i className={`fa-solid fa-angle-right`}></i>
        </div>
    </>
}

export default TestFile