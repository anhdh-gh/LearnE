import { STATUS_TYPE, STATUS_CODES } from '../constants'
import { CommonUtil } from '../utils'
import { ApiClient } from '../api'

const ROUTE_PATH = {
    GET_COURSE_DETAIL_FOR_USER: '/course/get-course-detail-for-user',
    UPDATE_LESSON_STATUS: "/course/update-lesson-status"
}

const CourseApi = {

    handleCourseData(course) {
        // Set status for course
        const lessons = course.chapters.reduce((lessons, chapter) => [...lessons, ...chapter.lessons], [])
        course.status = lessons.every(lesson => lesson?.status === STATUS_TYPE.FINISHED)
            ? STATUS_TYPE.FINISHED
            : lessons.every(lesson => !lesson?.status || lesson?.status === STATUS_TYPE.UNFINISHED)
              ? STATUS_TYPE.UNFINISHED
              : STATUS_TYPE.PROCESSING

        // Set status for course, tổng số lượng lesson, tổng duration, tổng số lượng lesson đã hoàn thành, phần trăm hoàn thành course
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

        // Lấy ra chapter đầu tiên có lesson status là PROCESSING
        const chapterCurrentProcessing = course.chapters.filter(
            chapter => chapter.lessons.some(chapter => chapter.status === STATUS_TYPE.PROCESSING))[0]
        course.chapterCurrentProcessing = chapterCurrentProcessing || course.chapters[0]

        // Lấy ra lesson đầu tiên trong chapterCurrentProcessing có lesson status là PROCESSING
        const lessonCurrentProcessing = chapterCurrentProcessing?.lessons.filter(
            lesson => lesson.status === STATUS_TYPE.PROCESSING)[0]
        course.lessonCurrentProcessing = lessonCurrentProcessing || course.chapters[0].lessons[0]

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
    },

    async handleGetCourseDetailForUser(courseId) {
        if(!courseId) {
            return null
        }

        const {data: course, meta } = await ApiClient.post(ROUTE_PATH.GET_COURSE_DETAIL_FOR_USER, {courseId})

        if(meta?.code !== STATUS_CODES.SUCCESS || !course || courseId !== course.id) {
            return null
        }

        return CourseApi.handleCourseData(course)
    },

    async updateLessonStatus(lessonId, status) {
        if(!lessonId || !status) {
            throw Error('Update lesson status fail')
        }
        
        const { data, meta } = await ApiClient.post(ROUTE_PATH.UPDATE_LESSON_STATUS, { lessonId, status })
        if(meta?.code !== STATUS_CODES.SUCCESS || !data) {
            throw Error('Update lesson status fail')
        } 

        return data
    }
}

export default CourseApi