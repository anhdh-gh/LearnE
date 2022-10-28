import { UserInfo, Pagination, Loader } from "."
import { Modal } from 'react-bootstrap'
import { useQuery } from '@tanstack/react-query'
import { StudysetApi } from "../api"
import { useState } from "react"
import { STATUS_CODES, ROUTE_PATH } from '../constants'
import _ from 'lodash'

const RankStudySet = (props) => {

    const { studysetId, show, onHide } = props
    const size = 10
    const [ page, setPage ] = useState(0)

    const { data: responseGetRankStudyset, isLoading, isFetching, isError } = useQuery(
        ["getRankStudyset"],
        () => StudysetApi.getRankStudyset(studysetId, page, size),
        {
            refetchOnWindowFocus: false,
        }
    )

    return <Modal show={show} fullscreen='md-down' size="lg" centered onHide={onHide}>
        <Modal.Header closeButton>
            <Modal.Title>Rank</Modal.Title>
        </Modal.Header>
        <Modal.Body>
            {
                !(!isLoading && !isFetching && !isError &&
                    responseGetRankStudyset?.meta?.code === STATUS_CODES.SUCCESS &&
                    !_.isEmpty(responseGetRankStudyset?.data))
                    ? <Loader forComponent={true} />
                    : <Pagination className="row"
                        hrefPrev={`${ROUTE_PATH.STUDY_SET_VIEW_DETAIL}/${studysetId}`}
                        hrefNext={`${ROUTE_PATH.STUDY_SET_VIEW_DETAIL}/${studysetId}`}
                        hrefCurrent={`${ROUTE_PATH.STUDY_SET_VIEW_DETAIL}/${studysetId}`}
                        disabledPrev={responseGetRankStudyset?.data?.first}
                        disabledNext={responseGetRankStudyset?.data?.last}
                        page={parseInt(page) + 1}
                        totalPages={responseGetRankStudyset?.data?.totalPages}
                        hide={(responseGetRankStudyset?.data && (responseGetRankStudyset?.data?.totalElements <= size || _.isEmpty(responseGetRankStudyset?.data?.content))) ? true : false}
                    >
                        {
                            responseGetRankStudyset?.data?.content?.map((item, index) =>
                                <div key={item?.id}>
                                    <UserInfo limit={50} user={item?.user} />
                                </div>
                            )
                        }
                    </Pagination>
            }

        </Modal.Body>
    </Modal>
}

export default RankStudySet