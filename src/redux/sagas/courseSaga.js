import { STATUS_TYPE, STATUS_CODES } from "../../constants"
import ACTION_TYPE_SAGA from "../actions/ACTION_TYPE_SAGA"
import { 
    showLoader, hideLoader, 
    showTopLoader, hideTopLoader, 
    showNotFound, hideNotFound,
    saveCourse,
    fetchCourseProcessingDone,
} from '../actions'
import { CourseApi } from '../../api'
import { call, put, takeLatest, select, delay } from 'redux-saga/effects'
import { CommonUtil } from '../../utils'
import _ from 'lodash'

const { GET_COURSE_BY_ID, UPDATE_LESSON_STATUS } = ACTION_TYPE_SAGA
const { SUCCESS } = STATUS_CODES

function handleCourseData(course) {
    // Set status for course
    const lessons = course.chapters.reduce((lessons, chapter) => [...lessons, ...chapter.lessons], [])
    course.status = lessons.every(lesson => lesson?.status === STATUS_TYPE.FINISHED)
        ? STATUS_TYPE.FINISHED
        : lessons.every(lesson => !lesson?.status || lesson?.status === STATUS_TYPE.UNFINISHED)
          ? STATUS_TYPE.UNFINISHED
          : STATUS_TYPE.PROCESSING

    // Set tổng số lượng lesson, tổng duration, tổng số lượng lesson đã hoàn thành, phần trăm hoàn thành course
    course.lessons = lessons
    course.totalDuration = lessons.reduce((save, lesson) => CommonUtil.addTimeString(save, lesson.duration), "00:00:00")
    course.numberOfLessonsFinished = lessons.reduce((sum, lesson) => sum + (lesson.status === STATUS_TYPE.FINISHED ? 1 : 0), 0)
    course.percent = parseInt(course.numberOfLessonsFinished / course.lessons.length * 100)

    // Set status và số lượng bài đã học trong chapter for chapter
    course.chapters.forEach((chapter, indexChapter) => {
        const numberOfLessonFinshed = chapter.lessons.reduce((sum, lesson) => sum + (lesson.status === STATUS_TYPE.FINISHED ? 1 : 0), 0)
        const numberOfLessonUnFinshed = chapter.lessons.reduce((sum, lesson) => sum + (lesson.status === STATUS_TYPE.UNFINISHED ? 1 : 0), 0)
        chapter.status = numberOfLessonFinshed === chapter.lessons.length ? STATUS_TYPE.FINISHED
            : numberOfLessonUnFinshed === chapter.lessons.length ? STATUS_TYPE.UNFINISHED
            : STATUS_TYPE.PROCESSING
        chapter.numberOfLessonFinshed = numberOfLessonFinshed
        chapter.totalDuration = chapter.lessons.reduce((save, lesson) => CommonUtil.addTimeString(save, lesson.duration), "00:00:00")
        chapter.displayName = `${indexChapter + 1}. ${chapter.name}`
    })

    // Lấy ra chapter đầu tiên có lesson status là PROCESSING, nếu không có thì lấy cái UNFISHED đầu tiên
    const chapterCurrentProcessing = course.chapters.filter(
        chapter => chapter.lessons.some(chapter => chapter.status === STATUS_TYPE.PROCESSING))[0]
    course.chapterCurrentProcessing = chapterCurrentProcessing || course.chapters.filter(
        chapter => chapter.lessons.some(chapter => chapter.status === STATUS_TYPE.UNFINISHED))[0]

    // Lấy ra lesson đầu tiên trong chapterCurrentProcessing có lesson status là PROCESSING, nếu không có thì lấy cái UNFISHED đầu tiên
    const lessonCurrentProcessing = chapterCurrentProcessing?.lessons.filter(
        lesson => lesson.status === STATUS_TYPE.PROCESSING)[0]
    course.lessonCurrentProcessing = lessonCurrentProcessing || course.chapterCurrentProcessing?.lessons.filter(
        lesson => lesson.status === STATUS_TYPE.UNFINISHED)[0]

    // Tinh điểm cho các lessonExercises
    lessons.forEach(lesson => {
        lesson.lessonExercises.forEach(lessonExercise => {
            if (lessonExercise?.status === STATUS_TYPE.FINISHED) {
                lessonExercise.score = lessonExercise
                    .lessonQuestions
                    .reduce((sum, lessonQuestion) => lessonQuestion.question.score + sum, 0)
            }
        })
    })

    return course
}

function* getCourseByIdWorker({ payload }) {
    yield delay(500)
    const { courseId } = payload

    yield put(showLoader())
    try {
        const responseCourse = yield call(CourseApi.handleGetCourseDetailForUser(courseId))
        const { data: dataCourse, meta: metaCourse } = responseCourse
        if(metaCourse.code === SUCCESS) {
            yield put(saveCourse(handleCourseData(dataCourse)))
            yield put(hideNotFound())
        } else {
            yield put(showNotFound())
        }
        yield put(hideLoader())
        yield put(fetchCourseProcessingDone())
    } catch (error) {
        console.error(error)
    }
}

function* updateLessonStatusWorker({ payload }) {
    const { lessonId, status } = payload

    yield put(showTopLoader())
    try {
        const { meta: metaUpdateLesson } = yield call(CourseApi.updateLessonStatus(lessonId, status))
        if(metaUpdateLesson.code === SUCCESS) {
            let course = yield select(state => state.course)
            course = _.cloneDeep(course)
            const res = course.chapters.some(chapter => 
                chapter.lessons.some(lesson => {
                    if(lesson.id === lessonId) {
                        lesson.status = status
                        return true
                    }

                    return false
                }))

            if(res) {
                yield put(saveCourse(handleCourseData(course)))
            }
        }
    } catch (error) {
        console.error(error)
    }
    yield put(hideTopLoader())
}

function* courseWatcher() {
    yield takeLatest(GET_COURSE_BY_ID, getCourseByIdWorker)
    yield takeLatest(UPDATE_LESSON_STATUS, updateLessonStatusWorker)
}

export default courseWatcher