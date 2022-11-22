import { UserInfo, Pagination, Loader } from "."
import { Modal, Accordion } from 'react-bootstrap'
import { useQuery } from '@tanstack/react-query'
import { QuestionApi } from "../api"
import { useState, useLayoutEffect  } from "react"
import { STATUS_CODES } from '../constants'
import _ from 'lodash'
import { RibbonContainer, RightRibbon } from 'react-ribbons'
import Timecode from 'react-timecode'
import { CommonUtil } from '../utils'
import { useSelector } from 'react-redux'

const RankQuestion = (props) => {

    const { questionId, show, onHide } = props
    const size = 5
    const [page, setPage] = useState(0)
    const currentUrl = useSelector(state => state.UI.Url.current)

    const { data: responseGetRankQuestion, isLoading, isFetching, isError, refetch: getRankQuestion } = useQuery(
        ["getRankQuestion", page],
        () => QuestionApi.getRank(questionId, page, size),
        {
            refetchOnWindowFocus: false,
        }
    )

    useLayoutEffect (() => {
        getRankQuestion(page)
    }, [page, getRankQuestion])

    return <Modal scrollable show={show} fullscreen='md-down' size="lg" centered onHide={onHide}>
        <Modal.Header closeButton>
            <Modal.Title>Rank</Modal.Title>
        </Modal.Header>
        <Modal.Body>
            {
                !(!isLoading && !isFetching && !isError &&
                    responseGetRankQuestion?.meta?.code === STATUS_CODES.SUCCESS &&
                    !_.isEmpty(responseGetRankQuestion?.data))
                    ? <Loader forComponent={true} />
                    : _.isEmpty(responseGetRankQuestion?.data?.content) ? <div className="text-center">Empty data</div>
                    : responseGetRankQuestion?.data?.content?.map((item, index) =>
                        <Accordion key={item?.id}>
                            <Accordion.Item eventKey={item?.id} className={`border-start-0 border-end-0 border-top-0 rounded-none`}>
                                <Accordion.Header className='d-flex ShowCourseDetail-accordion-header'>
                                    <RibbonContainer className="min-h-min min-w-full">
                                        <RightRibbon backgroundColor="#014599" color="#f0f0f0">
                                            <span className="font-bold">{parseInt(responseGetRankQuestion?.data?.pageable?.offset) + index + 1}</span>
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
        {parseInt(responseGetRankQuestion?.data?.totalPages) > 1 && <Modal.Footer>
            <Pagination
                classNamePagination={`m-0`}
                hrefPrev={currentUrl}
                onClickPrev={() => setPage(page - 1)}
                hrefNext={currentUrl}
                onClickNext={() => setPage(page + 1)}
                hrefCurrent={currentUrl}
                onClickCurrent={() => getRankQuestion(page)}
                disabledPrev={_.isEmpty(responseGetRankQuestion) || isLoading || isFetching || isError || responseGetRankQuestion?.data?.first}
                disabledNext={_.isEmpty(responseGetRankQuestion) || isLoading || isFetching || isError || responseGetRankQuestion?.data?.last}
                disableCurrent={_.isEmpty(responseGetRankQuestion) || isLoading || isFetching || isError}
                page={parseInt(page) + 1}
                totalPages={responseGetRankQuestion?.data?.totalPages}
                hide={(responseGetRankQuestion?.data && (responseGetRankQuestion?.data?.totalElements <= size || _.isEmpty(responseGetRankQuestion?.data?.content))) ? true : false}
            />
        </Modal.Footer>}
    </Modal>
}

export default RankQuestion

/*
- Khi người dùng click lại vào nút ranh thì có nên load lại trạng thái ban đầu là page = 0 không.
- Hiện tại:
    - Nếu người dùng đang click và xem page rank thứ n.
    - Rồi tắt modal đi
    - Khi click lại thì nó vẫn hiển thị page rank thứ n (chứ không phải là load lại thành page = 0)
*/