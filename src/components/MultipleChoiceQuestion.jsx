import { Form } from 'react-bootstrap'
import { useRef } from 'react'
import { AudioWord } from '.'
import { useEffect } from 'react'

const MultipleChoiceQuestion = (props) => {

    const { totalQuestion } = props
    const { id, question, correct, answers } = props.testItem
    const { showResult } = props

    const formRef = useRef()

    const handleInputChange = (answer) => {
        if (props?.testItem?.choice && props.testItem.choice === answer) {
            props.testItem.choice = ""
            formRef?.current?.reset()
        } else {
            props.testItem.choice = answer
        }
    }


    return <div className="mb-3 white-space_pre-line">
        <Form ref={formRef} className="card shadow-md my-5">
            <div className="card-body">
                <div className='flex mx-2 justify-between'>
                    <div className='flex'>
                        <div className="text-slate-500 font-semibold">Term</div>
                        <div>
                            <AudioWord className="ps-3 align-self-start" word={{ text: `${question}`, type: 'key' }} />
                        </div>
                    </div>

                    <div className="text-slate-500">{`${id + 1} of ${totalQuestion}`}</div>
                </div>

                <p className={`fw-bold mx-2 mt-4 text-break text-lg ${showResult
                    ? correct === props.testItem.choice
                        ? 'text-success'
                        : props.testItem.choice
                            ? 'text-warning'
                            : 'text-danger'
                    : ''
                    }`}>{`${question}`}</p>

                <div className="">
                    <div className="mx-2 pt-4">Choose matching definition</div>

                    <div className="flex flex-wrap">
                        {
                            answers.map((answer, index) => <Form.Check
                                id={`answer-${id}-${index}`}
                                key={`answer-${id}-${index}`}
                                className={`d-flex p-0 m-0 pt-3 grow mx-2`}
                            >
                                <Form.Check.Input
                                    type="radio"
                                    name={`answer-${id}`}
                                    // onChange={e => handleInputChange(answer)}
                                    onClick={e => handleInputChange(answer)}
                                    disabled={showResult}
                                    style={{ cursor: 'pointer', opacity: '1', minWidth: "1rem" }}
                                    className="text-primary absolute opacity-0 w-0 h-0 peer"
                                />
                                <Form.Check.Label
                                    className={`grow d-block p-3 card peer-checked:bg-blue-50 peer-checked:border-blue-500 hover:border-black text-break ${showResult
                                        ? answer === props.testItem.choice
                                            ? correct === props.testItem.choice
                                                ? 'text-success fw-bold'
                                                : 'text-warning'
                                            : answer === correct
                                                ? 'text-success fw-bold'
                                                : ''
                                        : ''
                                        }`}
                                    htmlFor={`answer-${id}-${index}`}
                                    style={{ cursor: 'pointer', opacity: '1' }}
                                >{answer}</Form.Check.Label>
                            </Form.Check>)
                        }
                    </div>
                </div>
            </div>
        </Form>
    </div>
}

export default MultipleChoiceQuestion