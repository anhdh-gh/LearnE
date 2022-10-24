import '../assets/css/ViewStudySetPage.css'
import { Header, Footer, UserInfo, WordCardSlide, WordCardList } from "../components"
import { useParams } from 'react-router'
import { StudysetApi } from '../api'
import { useQuery } from '@tanstack/react-query'
import { useEffect } from 'react'
import { useDispatch } from "react-redux"
import { showLoader, hideLoader, showNotFound, hideNotFound } from '../redux/actions'
import { STATUS_CODES, ROUTE_PATH } from '../constants'
import _ from 'lodash'
import { Notification } from '../utils'
import { CopyToClipboard } from 'react-copy-to-clipboard'
import { DictionaryApi } from '../api'
import { History } from '../components/NavigateSetter'

const ViewDetailStudySet = (props) => {

    const { studysetId } = useParams()
    const dispatch = useDispatch()

    const { data: responseGetStudysetById, isLoading, isFetching, isError } = useQuery(
        ["getStudysetById"],
        async () => {
            const response = await StudysetApi.getStudysetById(studysetId)
            if(response?.meta?.code === STATUS_CODES.SUCCESS) {
                response.data.wordCards = response?.data?.wordCards?.map((wordCard) => {
                    const newWordCard = {
                        key: {
                            text: wordCard.key,
                        },
                        value: {
                            text: wordCard.value,
                        }
                    }

                    const getInforWord = (word, infoNewWordCard) => {
                        DictionaryApi.getInforWord(word)
                        .then(res => infoNewWordCard.info = res)
                    }

                    getInforWord(wordCard?.key, newWordCard.key)
                    getInforWord(wordCard?.value, newWordCard.value)

                    return newWordCard
                })
            }         

            return response
        },
        {
            refetchOnWindowFocus: false,
        }
    )

    useEffect(() => {
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
        <Header/>
        <div className="ViewStudySetPage-container">

            <div className="container-xl">
                <p className="title">{responseGetStudysetById?.data?.title}</p>

                <div className="row flex-column-reverse flex-md-row">
                    <div className="col-md-2">
                        <div className="func d-flex d-md-block justify-content-between mt-3 mt-0">

                            <CopyToClipboard
                                text={window.location.href}
                                onCopy = {(text, result) => 
                                    result
                                    ? Notification.success("Copied to clipboard")
                                    : Notification.error("Error, try again")
                                }
                            >
                                <div className="share">
                                    <i className="fas fa-share-square"/> Share
                                </div>                                
                            </CopyToClipboard>

                            <div className="test" onClick={() => History.push(`${ROUTE_PATH.STUDY_SET_TEST}/${responseGetStudysetById?.data?.id}`)}>
                                <i className="fas fa-pencil-ruler"/> Test
                            </div>   
                        </div>
                    </div>
                    <div className="col-md-10">
                        <WordCardSlide wordCards={responseGetStudysetById?.data?.wordCards}/>
                    </div>
                </div>

                <div className="border-top mt-4 py-4 author">
                    <UserInfo limit={30} user={responseGetStudysetById?.data?.ownerUser}/>
                    <div className="description mt-3">{responseGetStudysetById?.data?.description}</div>
                </div>

                <WordCardList className="min-vh-100" wordCards={responseGetStudysetById?.data?.wordCards}/>
            </div>
        </div>
        <Footer/>
    </>
}

export default ViewDetailStudySet