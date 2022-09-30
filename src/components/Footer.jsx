import { OverlayTrigger, Tooltip } from 'react-bootstrap'

const Footer = (props) => {

    const { className } = props

    return <div className={`
    border-bottom border-2 border-danger border-start-0 border-end-0 border-top-0
    d-flex flex-column align-items-center justify-content-around 
    p-2 py-3 ${className}`}
        style={{ backgroundColor: "#f3f7f9" }}
    >
        <div>
            <a target="_blank" rel="noreferrer" href="https://www.facebook.com/anhdh.fb" className="px-2">
                <OverlayTrigger placement="bottom" overlay={<Tooltip>Facebook</Tooltip>}>
                    <i className="fab fa-facebook h3 mb-0" />
                </OverlayTrigger>
            </a>

            <a rel="noreferrer" href="mailto:anhdh.cv@gmail.com" className="px-2">
                <OverlayTrigger placement="bottom" overlay={<Tooltip>Mail</Tooltip>}>
                    <i className="far fa-envelope text-danger h3 mb-0" />
                </OverlayTrigger>
            </a>

            <a target="_blank" rel="noreferrer" href="https://github.com/anhdh-gh" className="px-2">
                <OverlayTrigger placement="bottom" overlay={<Tooltip>Github</Tooltip>}>
                    <i className="fab fa-github text-dark h3 mb-0" />
                </OverlayTrigger>
            </a>

            <a target="_blank" rel="noreferrer" href="https://www.youtube.com/channel/UCuZUp0A9aLrfHSPr3eg05Ow" className="px-2">
                <OverlayTrigger placement="bottom" overlay={<Tooltip>Youtube</Tooltip>}>
                    <i className="fab fa-youtube text-danger h3 mb-0" />
                </OverlayTrigger>
            </a>
        </div>

        <p className="fw-bold text-center m-0 mt-2">
            ©{new Date().getFullYear()}. ALL RIGHTS RESERVED. | Design by <a target="_blank" rel="noreferrer" className="text-danger text-decoration-none" href="https://www.facebook.com/anhdh.fb">
                Đỗ Hùng Anh
            </a>
        </p>

    </div>
}

export default Footer