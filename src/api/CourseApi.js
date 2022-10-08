import { STATUS_TYPE, QUESTION_TYPE } from '../constants'
import { CommonUtil } from '../utils'

const course = {
    id: "course_id",
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
    chapters: [...Array(30).keys()].map((chapter, index) => ({
        id: `Chapter id ${index + 1}`,
        // createTime: `yyyy-mm-dd hh:mi:ss.fff ${index + 1}`,
        // updateTime: `yyyy-mm-dd hh:mi:ss.fff ${index + 1}`,
        name: `Chapter name ${index + 1}`,
        lessons: [...Array(5).keys()].map((lesson, indexLesson) => ({
            id: `Chapter id ${index + 1} - Lesson id ${indexLesson + 1}`,
            // createTime: `yyyy-mm-dd hh:mi:ss.fff ${index + 1}`,
            // updateTime: `yyyy-mm-dd hh:mi:ss.fff ${index + 1}`,
            name: `Lesson name ${indexLesson + 1}`,
            duration: `${indexLesson}1:02:15`,
            // description: `Lesson description ${index + 1}`,
            video: `https://youtu.be/Z3rOofQx01A`,
            status: indexLesson < 2 ? STATUS_TYPE.UNFINISHED : indexLesson > 2 && indexLesson < 4 && index > 5 ? STATUS_TYPE.PROCESSING : STATUS_TYPE.FINISHED,
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
        if(courseId !== course.id)
            return null

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
        course.chapterCurrentProcessing = chapterCurrentProcessing

        // Lấy ra lesson đầu tiên trong chapterCurrentProcessing có lesson status là PROCESSING
        const lessonCurrentProcessing = chapterCurrentProcessing.lessons.filter(
            lesson => lesson.status === STATUS_TYPE.PROCESSING)[0]
        course.lessonCurrentProcessing = lessonCurrentProcessing

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
}

export default CourseApi