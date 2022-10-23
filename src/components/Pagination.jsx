import { Pagination as RbPagination } from 'react-bootstrap'
import { History } from './NavigateSetter'

const Pagination = (props) => {

    const { hrefPrev, hrefNext, hrefCurrent, disabledPrev, disabledNext, page, hide } = props

    return <div className={props?.className}>
        {props?.children}

        <RbPagination className={`mt-3 justify-content-center ${hide && 'd-none'}`}>
            <RbPagination.Prev disabled={disabledPrev} href={hrefPrev} onClick={(e) => {e.preventDefault(); History.push(hrefPrev)}}/>
            <RbPagination.Item href={hrefCurrent} onClick={(e) => {e.preventDefault(); History.push(hrefCurrent)}}>{page}</RbPagination.Item>
            <RbPagination.Next disabled={disabledNext} href={hrefNext} onClick={(e) => {e.preventDefault(); History.push(hrefNext)}}/>
        </RbPagination>
    </div>
}


export default Pagination