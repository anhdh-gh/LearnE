import { UserInfo, Pagination, Loader } from "."
import { Modal, Accordion } from 'react-bootstrap'
import { useQuery } from '@tanstack/react-query'
import { StudysetApi } from "../api"
import { useState, useEffect } from "react"
import { STATUS_CODES, ROUTE_PATH } from '../constants'
import _ from 'lodash'
import { RibbonContainer, RightRibbon } from 'react-ribbons'
import Timecode from 'react-timecode'
import { CommonUtil } from '../utils'

const RankStudySet = (props) => {

    const { studysetId, show, onHide } = props
    const size = 5
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
    }, [page, getRankStudyset])

    return <Modal scrollable show={show} fullscreen='md-down' size="lg" centered onHide={onHide}>
        <Modal.Header closeButton>
            <Modal.Title>Rank</Modal.Title>
        </Modal.Header>
        <Modal.Body>
            {
                !(!isLoading && !isFetching && !isError &&
                    responseGetRankStudyset?.meta?.code === STATUS_CODES.SUCCESS &&
                    !_.isEmpty(responseGetRankStudyset?.data))
                    ? <Loader forComponent={true} />
                    : responseGetRankStudyset?.data?.content?.map((item, index) =>
                        <Accordion key={item?.id}>
                            <Accordion.Item eventKey={item?.id} className={`border-start-0 border-end-0 border-top-0 rounded-none`}>
                                <Accordion.Header className='d-flex ShowCourseDetail-accordion-header'>
                                    <RibbonContainer className="min-h-min min-w-full">
                                        <RightRibbon backgroundColor="#014599" color="#f0f0f0">
                                            <span className="font-bold">{parseInt(responseGetRankStudyset?.data?.pageable?.offset) + index + 1}</span>
                                        </RightRibbon>
                                        <UserInfo limit={20} user={item?.user} />
                                    </RibbonContainer>
                                </Accordion.Header>
                                <Accordion.Body>
                                    <div>
                                        <div className="py-1"><i className="fa-solid fa-arrows-to-dot"></i> Score: {item?.score}</div>
                                        <div className="py-1"><i className="fa-solid fa-arrows-to-dot"></i> Completion time: <Timecode time={item?.completionTime}/></div>
                                        <div className="py-1"><i className="fa-solid fa-arrows-to-dot"></i> Last updated on {CommonUtil.getDateStringFromMilliseconds(item?.updateTime || item?.createTime)}</div>
                                    </div>
                                </Accordion.Body>
                            </Accordion.Item>
                        </Accordion>
                    )
            }
        </Modal.Body>
        {parseInt(responseGetRankStudyset?.data?.totalPages) > 1 && <Modal.Footer>
            <Pagination
                classNamePagination={`m-0`}
                hrefPrev={`${ROUTE_PATH.STUDY_SET_VIEW_DETAIL}/${studysetId}`}
                onClickPrev={() => setPage(page - 1)}
                hrefNext={`${ROUTE_PATH.STUDY_SET_VIEW_DETAIL}/${studysetId}`}
                onClickNext={() => setPage(page + 1)}
                hrefCurrent={`${ROUTE_PATH.STUDY_SET_VIEW_DETAIL}/${studysetId}`}
                onClickCurrent={() => getRankStudyset(page)}
                disabledPrev={_.isEmpty(responseGetRankStudyset) || isLoading || isFetching || isError || responseGetRankStudyset?.data?.first}
                disabledNext={_.isEmpty(responseGetRankStudyset) || isLoading || isFetching || isError || responseGetRankStudyset?.data?.last}
                disableCurrent={_.isEmpty(responseGetRankStudyset) || isLoading || isFetching || isError}
                page={parseInt(page) + 1}
                totalPages={responseGetRankStudyset?.data?.totalPages}
                hide={(responseGetRankStudyset?.data && (responseGetRankStudyset?.data?.totalElements <= size || _.isEmpty(responseGetRankStudyset?.data?.content))) ? true : false}
            />
        </Modal.Footer>}
    </Modal>
}

export default RankStudySet

/*
- Khi người dùng click lại vào nút ranh thì có nên load lại trạng thái ban đầu là page = 0 không.
- Hiện tại:
    - Nếu người dùng đang click và xem page rank thứ n.
    - Rồi tắt modal đi
    - Khi clcik lại thì nó vẫn hiển thị page rank thứ n (chứ không phải là load lại thành page = 0)
*/