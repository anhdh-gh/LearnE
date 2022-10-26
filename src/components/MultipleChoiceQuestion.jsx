import { Form } from 'react-bootstrap'
import { useRef } from 'react'

const MultipleChoiceQuestion = (props) => {

    const { id, question, correct, answers } = props.testItem
    const { showResult } = props

    const formRef = useRef()

    const handleInputChange = (answer) => {
        if(props?.testItem?.choice && props.testItem.choice === answer) {
            props.testItem.choice = ""
            formRef?.current?.reset()
        } else {
            props.testItem.choice = answer
        }
    }

    return <div className="mb-3 white-space_pre-line">
        <Form ref={formRef}>
            <p className={`fw-bold text-break ${
                showResult
                    ? correct === props.testItem.choice
                        ? 'text-success'
                        : props.testItem.choice
                            ? 'text-warning'
                            : 'text-danger'
                    : ''                
            }`}>{`${id + 1}. ${question}`}</p>
            {
                answers.map((answer, index) =>
                    <Form.Check
                        key={`answer-${id}-${index}`}
                        id={`answer-${id}-${index}`}
                        className="d-flex"
                    >
                        <Form.Check.Input
                            type="radio"
                            name={`answer-${id}`}
                            // onChange={e => handleInputChange(answer)}
                            onClick={e => handleInputChange(answer)}
                            disabled={showResult}
                            style={{ cursor: 'pointer', opacity: '1', minWidth: "1rem"}}
                            className="text-primary"
                        />
                        <Form.Check.Label
                            className={`ms-3 d-block text-break ${
                                showResult
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
                            style={{ cursor: 'pointer', opacity: '1'}}
                        >{answer}</Form.Check.Label>
                    </Form.Check>)
            }
        </Form>
    </div>
}

export default MultipleChoiceQuestion