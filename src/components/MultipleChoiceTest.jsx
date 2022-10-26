import '../assets/css/MultipleChoiceTest.css'
import { useState } from 'react'
import { MultipleChoiceQuestion } from './index'
import { Button, ProgressBar, OverlayTrigger, Tooltip } from 'react-bootstrap'
import Timer from 'react-timer-wrapper'
import Timecode from 'react-timecode'

const MultipleChoiceTest = (props) => {

    console.log('dm')

    const { test, handleTestResult, handleReTest } = props
    const [ showResult, setShowResult ] = useState(false)
    const [ countUpTimer, setCountUpTimer ] = useState(0)

    const showTestScore = e => {
        const numberQuestionEmpty = test.reduce((acc, item) => item.choice === '' ? acc + 1 : acc, 0) // Số câu không làm
        const numberQuestionWrong = test.reduce((acc, item) => item.choice !== item.correct && item.choice !== '' ? acc + 1 : acc, 0) // Số câu sai
        const numberQuestionCorrect = test.reduce((acc, item) => item.choice === item.correct ? acc + 1 : acc, 0) // Số câu đúng
        const score = numberQuestionCorrect/test.length * 10
        setShowResult([numberQuestionEmpty, numberQuestionWrong, numberQuestionCorrect])
        handleTestResult(score, countUpTimer)
    }

    const resetTest = e => {
        setShowResult(false)
        setCountUpTimer(0)
        handleReTest()
    }

    return <div className="MultipleChoiceTest-container p-2 p-sm-3 p-md-4">
        <h5 className="fw-bold pb-3 border-4 border-bottom border-danger border-start-0 border-end-0 border-top-0 d-inline-block">{`Multiple choice questions (${test.length})`}</h5>
        {
            test.map((item, index) =>
                <MultipleChoiceQuestion
                    testItem={item}
                    showResult={showResult}
                    key={item.id}
                />
            )
        }

        <div className="mt-5 flex justify-between">
            {!showResult && <Button onClick={showTestScore} className="fw-bold">Submit</Button>}

            {showResult && <Button onClick={resetTest} className="fw-bold">Retest</Button>}

            <div className="flex justify-between items-end text-lime-500 fw-bold text-3xl">
                {!showResult && <Timer active={true} duration={null} onTimeUpdate={({time}) => setCountUpTimer(time)}>
                    <Timecode />
                </Timer> }   
                {showResult && <Timecode time={countUpTimer} />}            
            </div>
        </div>

        {showResult && <><ProgressBar className="mt-5 fs-6 fw-bold">
            <ProgressBar max={test.length} variant="danger" now={showResult[0]} 
                label={<OverlayTrigger
                    placement="bottom"
                    overlay={<Tooltip>Empty</Tooltip>}
            ><span>{`${showResult[0]}/${test.length}`}</span></OverlayTrigger>}/>
            
            <ProgressBar max={test.length} variant="warning" now={showResult[1]} 
                label={<OverlayTrigger
                    placement="bottom"
                    overlay={<Tooltip>Incorrect</Tooltip>}
            ><span>{`${showResult[1]}/${test.length}`}</span></OverlayTrigger>}/>

            <ProgressBar max={test.length} variant="success" now={showResult[2]} 
                label={<OverlayTrigger
                    placement="bottom"
                    overlay={<Tooltip>Correct</Tooltip>}
            ><span>{`${showResult[2]}/${test.length}`}</span></OverlayTrigger>}/>
        </ProgressBar></>} 
    </div>
}

export default MultipleChoiceTest