import { STATUS_TYPE, QUESTION_TYPE } from '../constants'
import { CommonUtil } from '../utils'

const course = {
    id: "Course id",
    // createTime: "yyyy-mm-dd hh:mi:ss.fff",
    // updateTime: "yyyy-mm-dd hh:mi:ss.fff",
    name: "Làm việc với Terminal & Ubuntu",
    // author: "Đỗ Hùng Anh",
    // image: "https://files.fullstack.edu.vn/f8-prod/courses/13/13.png",
    // description: "Khóa học ReactJS từ cơ bản tới nâng cao, kết quả của khóa học này là bạn có thể làm hầu hết các dự án thường gặp với ReactJS. Cuối khóa học này bạn sẽ sở hữu một dự án giống Tiktok.com, bạn có thể tự tin đi xin việc khi nắm chắc các kiến thức được chia sẻ trong khóa học này.",
    // level: "Basic",
    // price: "Free",
    numberOfPeople: 1000,
    // targets: [...Array(8).keys()].map((item, index) => ({
    //     id: `Target id ${index + 1}`,
    //     text: `Biết cách tối ưu hiệu năng ứng dụng ${index + 1}`,
    //     createTime: `yyyy-mm-dd hh:mi:ss.fff ${index + 1}`,
    //     updateTime: `yyyy-mm-dd hh:mi:ss.fff ${index + 1}`
    // })),
    // requests: [...Array(4).keys()].map((item, index) => ({
    //     id: `Request id ${index + 1}`,
    //     createTime: `yyyy-mm-dd hh:mi:ss.fff ${index + 1}`,
    //     updateTime: `yyyy-mm-dd hh:mi:ss.fff ${index + 1}`,
    //     text: `Nắm chắc HTML, CSS, đã có sản phẩm tự tay làm ${index + 1}`
    // })),
    chapters: [...Array(3).keys()].map((chapter, index) => ({
        id: `Chapter id ${index + 1}`,
        // createTime: `yyyy-mm-dd hh:mi:ss.fff ${index + 1}`,
        // updateTime: `yyyy-mm-dd hh:mi:ss.fff ${index + 1}`,
        name: `Chapter name ${index + 1}`,
        lessons: [...Array(10).keys()].map((lesson, indexLesson) => ({
            id: `Lesson id ${indexLesson + 1}`,
            // createTime: `yyyy-mm-dd hh:mi:ss.fff ${index + 1}`,
            // updateTime: `yyyy-mm-dd hh:mi:ss.fff ${index + 1}`,
            name: `Lesson name ${indexLesson + 1}`,
            duration: `${indexLesson}1:02:15`,
            // description: `Lesson description ${index + 1}`,
            video: `https://youtu.be/Z3rOofQx01A`,
            status: indexLesson < 4 && STATUS_TYPE.FINISHED,
            lessonExercises: [...Array(4).keys()].map((item, index) => ({
                id: `LessonExercises id ${index + 1}`,
                // createTime: `yyyy-mm-dd hh:mi:ss.fff ${index + 1}`,
                // updateTime: `yyyy-mm-dd hh:mi:ss.fff ${index + 1}`,
                name: `LessonExercise name ${index + 1}`,
                // description: `LessonExercise description ${index + 1}`,
                status: index < 2 && STATUS_TYPE.FINISHED,
                lessonQuestions: [...Array(4).keys()].map((item, index) => ({
                    id: `LessonQuestions id ${index + 1}`,
                    // createTime: `yyyy-mm-dd hh:mi:ss.fff ${index + 1}`,
                    // updateTime: `yyyy-mm-dd hh:mi:ss.fff ${index + 1}`,
                    score: index,
                    question: {
                        id: `Question id ${index + 1}`,
                        // createTime: `yyyy-mm-dd hh:mi:ss.fff ${index + 1}`,
                        // updateTime: `yyyy-mm-dd hh:mi:ss.fff ${index + 1}`,
                        questionType: QUESTION_TYPE.VOCABULARY,
                        text: `Đề bài question ${index + 1}`,
                        image: "https://files.fullstack.edu.vn/f8-prod/courses/13/13.png",
                        audio: "https://dictionary.cambridge.org/vi/media/english/uk_pron/u/ukp/ukpro/ukpromi029.mp3",
                        score: index / 2,
                        answers: [...Array(4).keys()].map((item, index) => ({
                            id: `Answer id ${index + 1}`,
                            // createTime: `yyyy-mm-dd hh:mi:ss.fff ${index + 1}`,
                            // updateTime: `yyyy-mm-dd hh:mi:ss.fff ${index + 1}`,
                            text: `Đáp án ${index + 1}`,
                            isCorrect: index === 0 ? true : false,
                            isChoice: index === 0 ? true : false
                        }))
                    }
                }))
            }))
        }))
    }))
}

const CourseApi = {

    handleGetCourseDetailForUser(courseId) {

        // Set status và tổng số lượng lesson, tổng duration for course
        const lessons = course.chapters.reduce((lessons, chapter) => [...lessons, ...chapter.lessons], [])
        course.status = lessons.every(lesson => lesson?.status === STATUS_TYPE.FINISHED)
            ? STATUS_TYPE.FINISHED
            : lessons.every(lesson => lesson?.status || lesson?.status === STATUS_TYPE.UNFINISHED)
              ? STATUS_TYPE.UNFINISHED
              : STATUS_TYPE.PROCESSING

        course.lessons = lessons
        course.totalDuration = lessons.reduce((save, lesson) => CommonUtil.addTimeString(save, lesson.duration), "00:00:00")

        // Set status và số lượng bài đã học trong chapter for chapter
        course.chapters.forEach(chapter => {
            const numberOfLessonFinshed = chapter.lessons.reduce((sum, lesson) => sum + (lesson.status === STATUS_TYPE.FINISHED ? 1 : 0), 0)
            chapter.status = numberOfLessonFinshed === chapter.lessons.length ? STATUS_TYPE.FINISHED
                : numberOfLessonFinshed === 0 ? STATUS_TYPE.UNFINISHED
                : STATUS_TYPE.PROCESSING
            chapter.numberOfLessonFinshed = numberOfLessonFinshed
        })

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

        return () => course
    }
}

export default CourseApi