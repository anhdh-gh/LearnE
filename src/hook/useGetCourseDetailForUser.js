import { CourseApi } from '../api'
import { useQuery } from '@tanstack/react-query'
import { KEY_REACT_QUERY } from '../constants'

// https://viblo.asia/p/react-js-suc-manh-cua-react-query-Qbq5QRnwKD8
// https://stackoverflow.com/questions/72828361/what-is-staletime-cachetime-in-react-query
// https://stackoverflow.com/questions/70238846/react-query-query-is-not-using-cache
function useGetCourseDetailForUser(courseId) {

    const getCourseDetailForUser = useQuery([ KEY_REACT_QUERY.GET_COURSE_DETAIL_FOR_USER ], () => CourseApi.handleGetCourseDetailForUser(courseId), {
        cacheTime: Infinity,
        refetchOnWindowFocus: false,
        staleTime: Infinity,
        refetchInterval: false
    })

    return getCourseDetailForUser
}

export default useGetCourseDetailForUser