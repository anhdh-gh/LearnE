import '../assets/css/CardStudySet.css'
import { Card, Badge, OverlayTrigger, Tooltip } from 'react-bootstrap'
import { UserInfo } from './index'
import { useSelector } from 'react-redux'
import { History } from './NavigateSetter'
import { ROUTE_PATH } from '../constants'

const CardStudySet = (props) => {

    const { studyset, showHeader, showFooter, handleRemoveStudyset } = props

    const user = useSelector(state => state.user)

    return <>
        <Card className="card-study-set-container">
            {showHeader && <Card.Header>
                <UserInfo user={studyset?.ownerUser}/>
            </Card.Header>}

            <Card.Body onClick={() => History.push(`${ROUTE_PATH.STUDY_SET_VIEW_DETAIL}/${studyset?.id}`)}>
                <Card.Title className="title">
                    {
                        studyset?.title.length > 50
                        ? studyset?.title.substr(0, 50).concat('...')
                        : studyset?.title
                    }
                </Card.Title>
                <Card.Subtitle className="mb-2 text-muted">
                    <Badge pill bg="warning" text="dark">Terms: {studyset?.wordCards.length}</Badge>
                </Card.Subtitle>
                <Card.Text>
                    {
                        studyset?.description.length > 50
                        ? studyset?.description.substr(0, 50).concat('...')
                        : studyset?.description
                    }
                </Card.Text>
            </Card.Body>

            {showFooter && user?.id === studyset?.ownerUser?.id && <Card.Footer className="d-flex justify-content-between">
                <OverlayTrigger placement="bottom" overlay={<Tooltip>Edit</Tooltip>}>
                    <Badge bg="primary" onClick={() => History.push(`${ROUTE_PATH.STUDY_SET_EDIT}/${studyset?.id}`)}>
                        <i className="fas fa-edit fs-6"/>
                    </Badge>                          
                </OverlayTrigger>

                <OverlayTrigger placement="bottom" overlay={<Tooltip>Remove</Tooltip>}>
                    <Badge bg="danger" onClick={() => handleRemoveStudyset(studyset)}>
                        <i className="fas fa-trash-alt fs-6"/>
                    </Badge>            
                </OverlayTrigger>
            </Card.Footer>}
        </Card>    
    </> 
}

export default CardStudySet