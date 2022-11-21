import { useParams } from 'react-router'
import { QuestionApi } from '../api'
import { useQuery } from '@tanstack/react-query'
import { useLayoutEffect, useState } from 'react'
import _ from 'lodash'
import { showLoader, hideLoader, showNotFound, hideNotFound } from '../redux/actions'
import { useDispatch, useSelector } from "react-redux"
import { ROUTE_PATH, STATUS_CODES } from '../constants'
import { Card } from 'react-bootstrap'
import { CommonUtil } from '../utils'
import Timer from 'react-timer-wrapper'
import Timecode from 'react-timecode'
import { Form, InputGroup, Offcanvas, Modal, Button, ProgressBar, OverlayTrigger, Tooltip } from 'react-bootstrap'
import { useWindowSize } from '../hooks'
import { ModalConfirm } from '../components'
import { History } from '../components/NavigateSetter'
import { Pie } from "react-chartjs-2"
import { Chart as ChartJS, ArcElement, Tooltip as TooltipJS, Legend, Title } from "chart.js"
import ChartDataLabels from "chartjs-plugin-datalabels"


ChartJS.register(ArcElement, TooltipJS, Legend, ChartDataLabels, Title)

const options = {
    plugins: {
        title: {
            display: true,
            font: {
                size: 14
            },
            text: "Statistical chart of test scores",
            align: "center",
            position: "top",
        },
        legend: {
            position: "bottom"
        },
        datalabels: {
            color: "#000",
            formatter: (value, ctx) => {
                let sum = 0
                let dataArr = ctx.chart.data.datasets[0].data
                dataArr.map((data) => (sum += data))
                let percentage = ((value * 100) / sum).toFixed(0) + "%"
                return percentage === '0%' ? '' : percentage
            }
        },
        tooltip: {
            callbacks: {
                label: (tooltipItem, data) => {
                    const percent = tooltipItem.raw *= 100
                    return `${tooltipItem.label}: ${percent.toFixed(0)}%`
                },
            }
        }
    },
    maintainAspectRatio: false,
    responsive: true
}

const data = {
    labels: ["Empty", "Incorrect", "Correct"],
    datasets: [
        {
            label: "Statistical chart of test scores",
            data: [30, 40, 30],
            backgroundColor: [
                "rgba(220, 53, 69, 0.2)",
                "rgba(255, 193, 7, 0.2)",
                "rgba(25, 135, 84, 0.2)"
            ],
            borderColor: [
                "rgba(220, 53, 69, 1)",
                "rgba(255, 193, 7, 1)",
                "rgba(25, 135, 84, 1)"
            ],
            borderWidth: 1
        }
    ]
}


const TestFile = () => {

    const dispatch = useDispatch()
    const { groupId } = useParams()
    const [countUpTimer, setCountUpTimer] = useState(0)
    const [answers, setAnswers] = useState([])
    const [widthWindow] = useWindowSize()
    const [audioCurent, setAudioCurent] = useState({ isPlaying: false })
    const [showOffcanvas, setShowOffcanvas] = useState(widthWindow >= 768 ? false : true)
    const [cancelTest, setCancelTest] = useState(false)
    const { previous: previousUrl } = useSelector(state => state.UI.Url)
    const [submitTest, setSubmitTest] = useState(false)
    const [showResult, setShowResult] = useState(false)
    const [showScore, setShowScore] = useState(false)

    const { data: responseGetByGroupId, isLoading: isLoadingGetByGroupId, isFetching: isFetchingGetByGroupId, isError: isErrorGetByGroupId, refetch: getByGroupId } = useQuery(
        ["getByGroupId"],
        () => QuestionApi.getByGroupId(groupId),
        {
            refetchOnWindowFocus: false,
        }
    )

    const refreshPage = () => {
        getByGroupId()
        setSubmitTest(false)
        setShowResult(false)
        setCancelTest(false)
        setAudioCurent({ isPlaying: false })
        setShowOffcanvas(widthWindow >= 768 ? false : true)
        setAnswers([])
        setShowScore(false)
        setCountUpTimer(0)
    }

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
                                    <div className='text-sm'>Time</div>
                                    <div className='font-bold'>
                                        {!showResult ? <>
                                            <Timer active={true} duration={responseGetByGroupId?.data?.questions[0]?.time} onTimeUpdate={({ time }) => setCountUpTimer(time)} />
                                            <Timecode time={responseGetByGroupId?.data?.questions[0]?.time - countUpTimer} />
                                        </> : <>
                                            <Timecode time={countUpTimer} />
                                        </>}
                                    </div>
                                </div>
                            </div>
                        </Card.Header>
                        <Card.Body className='p-0 overflow-auto'>
                            <div className='font-bold mt-4 text-center px-2.5'>Answer sheet</div>

                            <div className='mt-4 flex flex-wrap'>
                                {responseGetByGroupId?.data?.questions[0]?.answers.map((answer, index) => <div className='grow w-full max-w-full p-2.5' key={answer?.id}>
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
                                            disabled={showResult}
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
                                        {showResult && <InputGroup.Text
                                            className={
                                                !answers[index] || answers[index]?.length === 0
                                                    ? 'text-red-600' :
                                                    answer?.text === answers[index]
                                                        ? 'text-lime-600' :
                                                        'text-yellow-600'
                                            }>{answer?.text}</InputGroup.Text>}
                                    </InputGroup>
                                </div>)}
                            </div>
                        </Card.Body>

                        <Card.Footer className='p-0 border-0 bg-white'>
                            <div className='mt-3.5 text-center px-2.5 pb-2.5'>
                                {!showResult ? <>
                                    <div className='text-center bg-indigo-600 text-white rounded rounded-sm py-2 cursor-pointer hover:bg-indigo-700' onClick={() => setSubmitTest(true)}>Submit</div>
                                    <div className='text-slate-500 mt-3 cursor-pointer hover:text-slate-600' onClick={() => setCancelTest(true)}>Cancel</div>
                                </> : <>
                                    <div className='text-center bg-indigo-600 text-white rounded rounded-sm py-2 cursor-pointer hover:bg-indigo-700' onClick={() => setShowScore(true)}>View score</div>
                                    <div className='text-slate-500 mt-3 cursor-pointer hover:text-slate-600' onClick={() => refreshPage()}>ReTest</div>
                                </>}
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
                                <div className='font-bold'>
                                    {!showResult ? <>
                                        <div className='text-sm'>Time remaining</div>
                                        <Timer active={true} duration={responseGetByGroupId?.data?.questions[0]?.time} onTimeUpdate={({ time }) => setCountUpTimer(time)} />
                                        <Timecode time={responseGetByGroupId?.data?.questions[0]?.time - countUpTimer} />
                                    </> : <>
                                        <div className='text-sm'>Time</div>
                                        <Timecode time={countUpTimer} />
                                    </>}
                                </div>
                            </div>
                        </div>
                    </Card.Header>
                    <Card.Body className='p-0 overflow-auto'>
                        <div className='font-bold mt-4 text-center px-2.5'>Answer sheet</div>

                        <div className='mt-4 flex flex-wrap'>
                            {responseGetByGroupId?.data?.questions[0]?.answers.map((answer, index) => <div className='grow w-full max-w-full p-2.5' key={answer?.id}>
                                <InputGroup id={answer?.id} className="mb-3 max-w-full flex-nowrap">
                                    <InputGroup.Text>{index + 1}</InputGroup.Text>
                                    <Form.Control
                                        type="text"
                                        className=""
                                        disabled={showResult}
                                        onChange={e => {
                                            setAnswers(preAnswers => {
                                                preAnswers[index] = e.target.value.trim()
                                                return [...preAnswers]
                                            })
                                        }}
                                        value={answers[index] ? answers[index] : ''}
                                        style={{ width: `${(answers[index]?.length ? answers[index]?.length + 4 : 4)}ch` }}
                                    />
                                    {showResult && <InputGroup.Text
                                        className={
                                            !answers[index] || answers[index]?.length === 0
                                                ? 'text-red-600' :
                                                answer?.text === answers[index]
                                                    ? 'text-lime-600' :
                                                    'text-yellow-600'
                                        }>{answer?.text}</InputGroup.Text>}
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
                            {!showResult ? <>
                                <div className='text-center bg-indigo-600 text-white rounded rounded-sm py-2 cursor-pointer hover:bg-indigo-700' onClick={() => setSubmitTest(true)}>Submit</div>
                                <div className='text-slate-500 mt-3 cursor-pointer hover:text-slate-600' onClick={() => setCancelTest(true)}>Cancel</div>
                            </> : <>
                                <div className='text-center bg-indigo-600 text-white rounded rounded-sm py-2 cursor-pointer hover:bg-indigo-700' onClick={() => setShowScore(true)}>View score</div>
                                <div className='text-slate-500 mt-3 cursor-pointer hover:text-slate-600' onClick={() => refreshPage()}>ReTest</div>
                            </>}
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

        <ModalConfirm
            show={cancelTest}
            setShow={() => setCancelTest(false)}
            title="Confirm"
            message={`Do you want to cancel the test?`}
            handleNo={() => setCancelTest(false)}
            handleYes={() => History.push(previousUrl || `${ROUTE_PATH.SHOW_ALL_QUESTION}/0`)}
        />

        <ModalConfirm
            show={submitTest}
            setShow={() => setSubmitTest(false)}
            title="Confirm"
            message={`Do you want to submit the test?`}
            handleNo={() => setSubmitTest(false)}
            handleYes={() => {
                // Số câu không làm
                const numberQuestionEmpty = 
                    responseGetByGroupId?.data?.questions[0]?.answers?.length - answers?.length
                    + answers.reduce((acc, answer) => !answer ? acc + 1 : acc, 0) 

                // Số câu sai
                const numberQuestionWrong = answers.reduce((acc, answer, index) => answer !== responseGetByGroupId?.data?.questions[0]?.answers?.[index].text ? acc + 1 : acc, 0) 

                // Số câu đúng
                const numberQuestionCorrect = responseGetByGroupId?.data?.questions[0]?.answers?.length - numberQuestionEmpty - numberQuestionWrong

                setShowResult([numberQuestionEmpty, numberQuestionWrong, numberQuestionCorrect])
                data.datasets[0].data = [numberQuestionEmpty / responseGetByGroupId?.data?.questions[0]?.answers?.length, numberQuestionWrong / responseGetByGroupId?.data?.questions[0]?.answers?.length, numberQuestionCorrect / responseGetByGroupId?.data?.questions[0]?.answers?.length]
                
                setShowScore(true)
                setSubmitTest(false)
            }}
        />

        <Modal show={showScore ? true : false} onHide={() => setShowScore(false)} backdrop="static" centered fullscreen={true}>
            <Modal.Header closeButton>
                <Modal.Title>View score</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <div className='flex flex-col min-h-full'>
                    <div className="">
                        <div>
                            <div className='py-2'>
                                <div className="py-1"><i className="fa-solid fa-arrows-to-dot"></i> Score: {(showResult[2] / responseGetByGroupId?.data?.questions[0]?.answers?.length * 10).toFixed(2)}</div>
                                <div className="py-1"><i className="fa-solid fa-arrows-to-dot"></i> Completion time: <Timecode time={countUpTimer} /></div>
                                <div className="py-1"><i className="fa-solid fa-arrows-to-dot"></i> Last updated: {CommonUtil.getDateStringFromMilliseconds(showScore && new Date().getTime())}</div>
                            </div>

                            <ProgressBar className="mt-4 fs-6 fw-bold">
                                <ProgressBar max={responseGetByGroupId?.data?.questions[0]?.answers?.length} variant="danger" now={showResult[0]}
                                    label={<OverlayTrigger
                                        placement="bottom"
                                        overlay={<Tooltip>Empty</Tooltip>}
                                    ><span>{`${showResult[0]}/${responseGetByGroupId?.data?.questions[0]?.answers?.length}`}</span></OverlayTrigger>} />

                                <ProgressBar max={responseGetByGroupId?.data?.questions[0]?.answers?.length} variant="warning" now={showResult[1]}
                                    label={<OverlayTrigger
                                        placement="bottom"
                                        overlay={<Tooltip>Incorrect</Tooltip>}
                                    ><span>{`${showResult[1]}/${responseGetByGroupId?.data?.questions[0]?.answers?.length}`}</span></OverlayTrigger>} />

                                <ProgressBar max={responseGetByGroupId?.data?.questions[0]?.answers?.length} variant="success" now={showResult[2]}
                                    label={<OverlayTrigger
                                        placement="bottom"
                                        overlay={<Tooltip>Correct</Tooltip>}
                                    ><span>{`${showResult[2]}/${responseGetByGroupId?.data?.questions[0]?.answers?.length}`}</span></OverlayTrigger>} />
                            </ProgressBar>
                        </div>
                    </div>
                    <div className="grow relative mt-4 flex justify-center">
                        <div className="absolute bottom-0 top-0">
                            <Pie options={options} data={data} />
                        </div>
                    </div>
                </div>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="primary" onClick={() => setShowScore(false)}>Close</Button>
            </Modal.Footer>
        </Modal>
    </>
}

export default TestFile