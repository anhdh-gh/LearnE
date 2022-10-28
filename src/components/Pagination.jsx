import { Pagination as RbPagination } from 'react-bootstrap'
import { History } from './NavigateSetter'

const Pagination = (props) => {

    const { 
        hrefPrev, onClickPrev,
        hrefNext, onClickNext,
        hrefCurrent, onClickCurrent,
        disabledPrev, disabledNext, 
        page, hide, totalPages } = props

    return <div className={props?.className}>
        {props?.children}

        <RbPagination className={`mt-3 justify-content-center ${hide && 'd-none'}`}>
            <RbPagination.Prev disabled={disabledPrev} href={hrefPrev} onClick={(e) => {e.preventDefault(); onClickPrev ? onClickPrev() : History.push(hrefPrev)}}/>
            <RbPagination.Item href={hrefCurrent} onClick={(e) => {e.preventDefault(); onClickCurrent ? onClickCurrent() : History.push(hrefCurrent)}}>{`${page}/${totalPages}`}</RbPagination.Item>
            <RbPagination.Next disabled={disabledNext} href={hrefNext} onClick={(e) => {e.preventDefault(); onClickNext ? onClickNext() : History.push(hrefNext)}}/>
        </RbPagination>
    </div>
}


export default Pagination