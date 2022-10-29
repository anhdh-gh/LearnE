import '../assets/css/TestStudySetPage.css'
import { Header, Footer, UserInfo, MultipleChoiceTest } from "../components"
import { useParams } from 'react-router'
import { StudysetApi } from '../api'
import { useQuery } from '@tanstack/react-query'
import { useEffect } from 'react'
import { useDispatch } from "react-redux"
import { showLoader, hideLoader, showNotFound, hideNotFound, showTopLoader, hideTopLoader } from '../redux/actions'
import { STATUS_CODES } from '../constants'
import _ from 'lodash'
import { CommonUtil } from '../utils'


const TestStudySet = (props) => {

    const { studysetId } = useParams()
    const dispatch = useDispatch()

    const { data: responseGetStudysetById, isLoading, isFetching, isError, refetch: getStudysetById } = useQuery(
        ["getStudysetById"],
        () => StudysetApi.getStudysetById(studysetId),
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

    const handleTestResult = (score, countUpTimer) => {
        dispatch(showTopLoader())
        StudysetApi.saveTestResult(responseGetStudysetById?.data?.id, countUpTimer, score)
            .then(response => {
                const { meta } = response
                if(meta?.code === STATUS_CODES.SUCCESS) {
                    dispatch(hideTopLoader())
                }
            })
            .catch(() => dispatch(hideTopLoader()))
    }

    const handleReTest = () => {
        getStudysetById(studysetId)
    }

    const convertwordCardsToTest = (wordCards) => {
        if(wordCards !== undefined) {
            const uniqueValues = wordCards.map(wordCard => wordCard.value.trim())
                .filter(value => value)
                .reduce((arr, currentValue) => {
                    if (arr.indexOf(currentValue) === -1)
                        arr.push(currentValue)
                    return arr
                }, [])

            const numberOfAnswers = (wordCards.length < 4) ? wordCards.length : 4
            const test = wordCards.map((wordCard, index) => ({
                question: wordCard.key,
                correct: wordCard.value,
                choice: '',
                answers: CommonUtil.shuffle([
                    wordCard.value,
                    ...CommonUtil.getRandom_K_number_unique(
                        0, 
                        uniqueValues.length < numberOfAnswers ? wordCards.length-1 : uniqueValues.length-1, 
                        numberOfAnswers-1, 
                        [uniqueValues.length < numberOfAnswers ? index : uniqueValues.indexOf(wordCard.value)], 
                        CommonUtil.getRandomIntInclusive
                    ).map(idx => uniqueValues.length < numberOfAnswers ? wordCards[idx].value : uniqueValues[idx])
                ]) 
            }))

            // Xáo trộn thứ tự câu
            return CommonUtil.shuffle(test).map((question, index) => ({id: index, ...question}))            
        }
    }

    return !isLoading && !isFetching && !isError && responseGetStudysetById?.meta?.code === STATUS_CODES.SUCCESS && !_.isEmpty(responseGetStudysetById?.data) && <>
        <Header/>
        <div className="TestStudySetPage-container">

            <div className="container-xl info">

                <p className="title">{`Test: ${responseGetStudysetById?.data?.title}`}</p>

                <div className="border-top mt-4 py-4 author">
                    <UserInfo limit={30} user={responseGetStudysetById?.data?.ownerUser}/>
                    <div className="description mt-3 text-break">{responseGetStudysetById?.data?.description}</div>
                </div>              

            </div>

            <div className="content py-3 py-md-4">
                <div className="container-xl test">
                    <MultipleChoiceTest handleTestResult={handleTestResult} handleReTest={handleReTest} test={convertwordCardsToTest(responseGetStudysetById?.data?.wordCards)}/>
                </div>
            </div>
        </div>
        <Footer/>
    </>
}

export default TestStudySet