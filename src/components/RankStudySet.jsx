import { UserInfo, Pagination, Loader } from "."
import { Modal } from 'react-bootstrap'
import { useQuery } from '@tanstack/react-query'
import { StudysetApi } from "../api"
import { useState, useEffect } from "react"
import { STATUS_CODES, ROUTE_PATH } from '../constants'
import _ from 'lodash'
import { RibbonContainer, RightRibbon } from 'react-ribbons'

const RankStudySet = (props) => {

    const { studysetId, show, onHide } = props
    const size = 3
    const [page, setPage] = useState(0)

    const { data: responseGetRankStudyset, isLoading, isFetching, isError, refetch: getRankStudyset } = useQuery(
        ["getRankStudyset", page],
        () => StudysetApi.getRankStudyset(studysetId, page, size),
        {
            refetchOnWindowFocus: false,
        }
    )

    useEffect(() => {
        getRankStudyset(page)
    }, [ page, getRankStudyset ])

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
                        onClickPrev={() => setPage(page - 1)}
                        hrefNext={`${ROUTE_PATH.STUDY_SET_VIEW_DETAIL}/${studysetId}`}
                        onClickNext={() => setPage(page + 1)}
                        hrefCurrent={`${ROUTE_PATH.STUDY_SET_VIEW_DETAIL}/${studysetId}`}
                        onClickCurrent={() => setPage(page)}
                        disabledPrev={responseGetRankStudyset?.data?.first}
                        disabledNext={responseGetRankStudyset?.data?.last}
                        page={parseInt(page) + 1}
                        totalPages={responseGetRankStudyset?.data?.totalPages}
                        hide={(responseGetRankStudyset?.data && (responseGetRankStudyset?.data?.totalElements <= size || _.isEmpty(responseGetRankStudyset?.data?.content))) ? true : false}
                    >
                        {
                            responseGetRankStudyset?.data?.content?.map((item, index) =>
                                <div key={item?.id} className="py-3 border-bottom border-1 border-primary border-start-0 border-end-0 border-top-0">
                                    <div>
                                        <RibbonContainer className="min-h-min">
                                            <RightRibbon backgroundColor="#014599" color="#f0f0f0">
                                                <span className="font-bold">{parseInt(responseGetRankStudyset?.data?.pageable?.offset) + index + 1}</span>
                                            </RightRibbon>
                                            <div>
                                                <UserInfo limit={50} user={item?.user} />
                                            </div>
                                        </RibbonContainer>                                        
                                    </div>
                                </div>
                            )
                        }
                    </Pagination>
            }

        </Modal.Body>
    </Modal>
}

export default RankStudySet