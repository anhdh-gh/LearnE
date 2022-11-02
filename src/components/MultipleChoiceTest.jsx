import '../assets/css/MultipleChoiceTest.css'
import "chartjs-plugin-datalabels"
import { useState, useEffect } from 'react'
import { MultipleChoiceQuestion } from './index'
import { Button, ProgressBar, OverlayTrigger, Tooltip } from 'react-bootstrap'
import Timer from 'react-timer-wrapper'
import Timecode from 'react-timecode'
import { Pie } from "react-chartjs-2"
import { Chart as ChartJS, ArcElement, Tooltip as TooltipJS, Legend, Title } from "chart.js"
import ChartDataLabels from "chartjs-plugin-datalabels"
import { CommonUtil } from '../utils'
import { setPercentProgressTopLoader } from '../redux/actions'
import { useDispatch } from 'react-redux'

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

let countUpTimer = 0


const MultipleChoiceTest = (props) => {

    const { test, handleTestResult, handleReTest } = props
    const [ showResult, setShowResult ] = useState(false)
    const [ viewResult, setViewResult ] = useState(false)
    const dispatch = useDispatch()

    const showTestScore = e => {
        const numberQuestionEmpty = test.reduce((acc, item) => item.choice === '' ? acc + 1 : acc, 0) // Số câu không làm
        const numberQuestionWrong = test.reduce((acc, item) => item.choice !== item.correct && item.choice !== '' ? acc + 1 : acc, 0) // Số câu sai
        const numberQuestionCorrect = test.reduce((acc, item) => item.choice === item.correct ? acc + 1 : acc, 0) // Số câu đúng
        const score = numberQuestionCorrect / test.length * 10
        setShowResult([numberQuestionEmpty, numberQuestionWrong, numberQuestionCorrect])
        data.datasets[0].data = [numberQuestionEmpty / test.length, numberQuestionWrong / test.length, numberQuestionCorrect / test.length]
        handleTestResult(score, countUpTimer)
        dispatch(setPercentProgressTopLoader(0))
    }

    const resetTest = e => {
        handleReTest()
        dispatch(setPercentProgressTopLoader(0))
    }

    useEffect(() => {
        window.scrollTo(0, 0)
    }, [ showResult ])

    return <div className="MultipleChoiceTest-container p-2 p-sm-3 p-md-4">
        {!showResult && <>
            <h5 className="fw-bold pb-3 border-3 border-bottom border-danger border-start-0 border-end-0 border-top-0 d-inline-block">{`Multiple choice questions (${test.length})`}</h5>
            {
                test.map((item, index) =>
                    <MultipleChoiceQuestion
                        testItem={item}
                        showResult={showResult}
                        key={item.id}
                        totalQuestion={test.length}
                    />
                )
            }

            <div className="mt-5 flex justify-between">
                <Button onClick={showTestScore} className="fw-bold">Submit</Button>

                <div className="flex justify-between items-end text-lime-500 fw-bold text-3xl">
                    <Timer active={true} duration={null} onTimeUpdate={({ time }) => countUpTimer = time}>
                        <Timecode />
                    </Timer>
                </div>
            </div>
        </>}

        {showResult && <>
            <h5 className="fw-bold pb-3 border-3 border-bottom border-danger border-start-0 border-end-0 border-top-0 d-inline-block">Test result</h5>

            <div className='row mt-4'>
                <div className="col-md-6">
                    <div>
                        <h6 className="fw-bold">Detailed results</h6>

                        <div className='py-2'>
                            <div className="py-1"><i className="fa-solid fa-arrows-to-dot"></i> Score: {(showResult[2] / test.length * 10).toFixed(2)}</div>
                            <div className="py-1"><i className="fa-solid fa-arrows-to-dot"></i> Completion time: <Timecode time={countUpTimer} /></div>
                            <div className="py-1"><i className="fa-solid fa-arrows-to-dot"></i> Last updated: {CommonUtil.getDateStringFromMilliseconds(new Date().getTime())}</div>
                        </div>

                        <div className="flex justify-between my-4">
                            <Button onClick={resetTest} className="fw-bold">Retest</Button>
                            <Button onClick={() => setViewResult(!viewResult)} className="fw-bold">Answers</Button>
                        </div>

                        <ProgressBar className="mt-4 fs-6 fw-bold">
                            <ProgressBar max={test.length} variant="danger" now={showResult[0]}
                                label={<OverlayTrigger
                                    placement="bottom"
                                    overlay={<Tooltip>Empty</Tooltip>}
                                ><span>{`${showResult[0]}/${test.length}`}</span></OverlayTrigger>} />

                            <ProgressBar max={test.length} variant="warning" now={showResult[1]}
                                label={<OverlayTrigger
                                    placement="bottom"
                                    overlay={<Tooltip>Incorrect</Tooltip>}
                                ><span>{`${showResult[1]}/${test.length}`}</span></OverlayTrigger>} />

                            <ProgressBar max={test.length} variant="success" now={showResult[2]}
                                label={<OverlayTrigger
                                    placement="bottom"
                                    overlay={<Tooltip>Correct</Tooltip>}
                                ><span>{`${showResult[2]}/${test.length}`}</span></OverlayTrigger>} />
                        </ProgressBar>
                    </div>
                </div>
                <div className="col-md-6 mt-4 mt-md-0">
                    <div className="relative min-h-full">
                        <Pie options={options} data={data} />
                    </div>
                </div>
            </div>

            {viewResult && <div className="mt-5">
                <h5 className="">View result</h5>
                {
                    test.map((item, index) =>
                        <MultipleChoiceQuestion
                            testItem={item}
                            showResult={showResult}
                            key={item.id}
                            totalQuestion={test.length}
                        />
                    )
                }                
            </div>}
        </>}
    </div>
}

export default MultipleChoiceTest