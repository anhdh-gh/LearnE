import '../assets/css/ViewStudySetPage.css'
import { Header, Footer, UserInfo, WordCardSlide, WordCardList, RankStudySet, ModalConfirm } from "../components"
import { useParams } from 'react-router'
import { StudysetApi } from '../api'
import { useQuery } from '@tanstack/react-query'
import { useLayoutEffect , useState } from 'react'
import { useDispatch } from "react-redux"
import { showLoader, hideLoader, showNotFound, hideNotFound } from '../redux/actions'
import { STATUS_CODES, ROUTE_PATH } from '../constants'
import _ from 'lodash'
import { Notification } from '../utils'
import { CopyToClipboard } from 'react-copy-to-clipboard'
// import { DictionaryApi } from '../api'
import { History } from '../components/NavigateSetter'
import { CommonUtil } from '../utils'
import Timecode from 'react-timecode'

const ViewDetailStudySet = (props) => {

    const { studysetId } = useParams()
    const dispatch = useDispatch()
    const [ showRank, setShowRank ] = useState(false)
    const [studysetRetest, setStudysetRetest] = useState(false)
    
    const { data: responseGetStudysetById, isLoading, isFetching, isError, refetch: getStudysetById } = useQuery(
        ["getStudysetById"],
        async () => {
            const response = await StudysetApi.getStudysetById(studysetId)
            if (response?.meta?.code === STATUS_CODES.SUCCESS) {
                response.data.wordCards = response?.data?.wordCards?.map((wordCard) => {
                    const newWordCard = {
                        key: {
                            text: wordCard.key,
                        },
                        value: {
                            text: wordCard.value,
                        }
                    }

                    // const getInforWord = (word, infoNewWordCard) => {
                    //     word?.text?.trim()?.split(" ")?.length === 1 && DictionaryApi.getInforWord(word)
                    //         .then(res => infoNewWordCard.info = res)
                    // }

                    // getInforWord(wordCard?.key, newWordCard.key)
                    // getInforWord(wordCard?.value, newWordCard.value)

                    // const getInforWord = (word, infoNewWordCard) => {
                    //     if(word.text.trim().split(" ").length === 1) {
                    //         DictionaryApi.getInforWord(word)
                    //             .then(res => infoNewWordCard.info = res)
                    //     }
                    // }

                    return newWordCard
                })
            }

            return response
        },
        {
            refetchOnWindowFocus: false,
        }
    )

    useLayoutEffect (() => {
        if (isLoading || isFetching) {
            dispatch(showLoader())
        } else {
            dispatch(hideLoader())
        }

        if (isError || (responseGetStudysetById?.meta && responseGetStudysetById?.meta?.code !== STATUS_CODES.SUCCESS)) {
            dispatch(showNotFound())
        } else {
            dispatch(hideNotFound())
        }

        return () => {
            dispatch(hideLoader())
            dispatch(hideNotFound())
        }
    }, [responseGetStudysetById, dispatch, isError, isFetching, isLoading])


    return !isLoading && !isFetching && !isError && responseGetStudysetById?.meta?.code === STATUS_CODES.SUCCESS && !_.isEmpty(responseGetStudysetById?.data) && <>
        <Header />
        <div className="ViewStudySetPage-container">

            <div className="container-xl">
                <p className="title cursor-pointer" onClick={getStudysetById}>{responseGetStudysetById?.data?.title}</p>

                <div className="row flex-column-reverse flex-md-row">
                    <div className="col-md-2">
                        <div className="func d-flex d-md-block justify-content-between mt-3 mt-0">

                            <CopyToClipboard
                                text={window.location.href}
                                onCopy={(text, result) =>
                                    result
                                        ? Notification.success("Copied to clipboard")
                                        : Notification.error("Error, try again")
                                }
                            >
                                <div className="flex justify-start items-end">
                                    <i className="fas fa-share-square" /> <span className='ps-2' style={{ lineHeight: "100%" }}>Share</span>
                                </div>
                            </CopyToClipboard>

                            <div className="flex justify-start items-end" onClick={() => setShowRank(true)}>
                                <i className="fa-solid fa-ranking-star" /> <span className='ps-2' style={{ lineHeight: "100%" }}>Rank</span>
                            </div>

                            <div className="flex justify-start items-end" onClick={() => !responseGetStudysetById?.data?.testResult ? History.push(`${ROUTE_PATH.STUDY_SET_TEST}/${responseGetStudysetById?.data?.id}`) : setStudysetRetest(responseGetStudysetById?.data)}>
                                {
                                    !responseGetStudysetById?.data?.testResult 
                                        ? <><i className="fa-solid fa-file-pen" /> <span className='ps-2' style={{ lineHeight: "100%" }}>Test</span></>
                                        : <><i className="fa-solid fa-file-pen" /> <span className='ps-2' style={{ lineHeight: "100%" }}>Retest</span></>
                                }
                            </div>
                        </div>
                    </div>
                    <div className="col-md-10">
                        <WordCardSlide wordCards={responseGetStudysetById?.data?.wordCards} />
                    </div>
                </div>

                <div className="border-top mt-4 py-4 author">
                    <UserInfo className="cursor-pointer" limit={30} user={responseGetStudysetById?.data?.ownerUser} onClick={() => History.push(`${ROUTE_PATH.STUDY_SET_VIEW}/${responseGetStudysetById?.data?.ownerUser?.id}/0`)}/>
                    <div className="description mt-3">{responseGetStudysetById?.data?.description}</div>
                </div>

                <WordCardList className="min-vh-100" wordCards={responseGetStudysetById?.data?.wordCards} />
            </div>
        </div>
        <Footer />

        <RankStudySet studysetId={responseGetStudysetById?.data?.id} show={showRank} onHide={() => setShowRank(false)}/>

        <ModalConfirm
            show={studysetRetest ? true : false}
            setShow={() => setStudysetRetest(false)}
            title={studysetRetest ? `Test: ${studysetRetest?.title}` : 'Closing'}
            message={studysetRetest && <div className='py-2'>
                <div className="py-1"><i className="fa-solid fa-arrows-to-dot"></i> Score: {(studysetRetest?.testResult?.score?.toFixed(2))}</div>
                <div className="py-1"><i className="fa-solid fa-arrows-to-dot"></i> Completion time: <Timecode time={studysetRetest?.testResult?.completionTime} /></div>
                <div className="py-1"><i className="fa-solid fa-arrows-to-dot"></i> Last updated: {CommonUtil.getDateStringFromMilliseconds(studysetRetest?.testResult?.updateTime || studysetRetest?.testResult?.createTime)}</div>
            </div>}
            handleNo={() => setStudysetRetest(false)}
            handleYes={() => History.push(`${ROUTE_PATH.STUDY_SET_TEST}/${studysetRetest?.id}`)}
            labelYes="Retest"
            labelNo="Close"
        />
    </>
}

export default ViewDetailStudySet