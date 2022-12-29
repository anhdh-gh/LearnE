import '../assets/css/Textarea.css'
import autosize from "autosize"
import { useRef, useLayoutEffect  } from 'react'

const Textarea = (props) => {
    const { title, value, placeholder, maxLength, enter, onChange, error, readOnly, setHeightDefinition, style, className } = props
    const texareaRef = useRef(null)

    useLayoutEffect (() => {
        if(!readOnly) {
            autosize(texareaRef.current)
        }
    }, [ readOnly ])

    useLayoutEffect (() => {
        if (!texareaRef.current || readOnly) {
            return
        }
        const resizeObserver = new ResizeObserver(() => {
            // Do what you want to do when the size of the element changes
            setHeightDefinition(texareaRef.current.clientHeight)
        })

        resizeObserver.observe(texareaRef.current)
        return () => resizeObserver.disconnect() // clean up 
    }, [ readOnly, setHeightDefinition ])

    const handleKeyPress = e => {
        if(enter === false && e.key === 'Enter')
            e.preventDefault()
    }

    return <div className="textarea-container" onClick={() => texareaRef.current.focus()}>
        <label className="textarea-label">{title}</label>
        <textarea 
            ref={texareaRef}
            className={`textarea-input ${className}`}
            value={value}
            placeholder={placeholder}
            rows={1}
            maxLength={maxLength}
            onKeyPress={handleKeyPress}
            onChange={onChange}
            readOnly={readOnly}
            style={style}
        />
        <div className={`textarea-underline ${error ? 'underline-textarea-error' : ''}`}/>
        {error && <div className="error">{error}</div>}
    </div>
}

export default Textarea