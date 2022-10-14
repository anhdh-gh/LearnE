import { CourseApi } from '../api'
import { useMutation, useQueryClient } from '@tanstack/react-query'
import { KEY_REACT_QUERY } from '../constants'
import _ from 'lodash'


// https://daily-dev-tips.com/posts/react-query-mutating-data/
// https://tanstack.com/query/v4/docs/quick-start
// https://tanstack.com/query/v4/docs/guides/mutations
// https://tanstack.com/query/v4/docs/guides/updates-from-mutation-responses
function useUpdateLessonStatus() {

    const queryClient = useQueryClient()

    const updateLessonStatus = useMutation(
        ({lessonId, status}) =>  CourseApi.updateLessonStatus(lessonId, status),
        {
            onSuccess: (data, {lessonId, status}) => { // data: do api trả về, variables là các biến đã được truyền vào (ở đây là {lessonId, status})
                queryClient.setQueryData(
                    [KEY_REACT_QUERY.GET_COURSE_DETAIL_FOR_USER],
                    (oldCourse) => {
                        const course = _.cloneDeep(oldCourse)
                        course?.chapters?.some(chapter => {
                            const lesson = chapter.lessons.filter(lesson => lesson.id === lessonId)[0]
                            if (!lesson)
                                return false
                            lesson.status = status

                            return true
                        })
                        return CourseApi.handleCourseData(course)
                    })
            }
        }
    )

    return updateLessonStatus
}

export default useUpdateLessonStatus