import { Pagination as RbPagination } from 'react-bootstrap'
import { History } from './NavigateSetter'

const Pagination = (props) => {

    const { 
        hrefPrev, onClickPrev,
        hrefNext, onClickNext,
        hrefCurrent, onClickCurrent,
        disabledPrev, disabledNext, 
        page, hide, totalPages, classNamePagination } = props

    return <div className={props?.className}>
        {props?.children}

        <RbPagination className={`justify-content-center ${hide && 'd-none'} ${classNamePagination}`}>
            <RbPagination.Prev disabled={disabledPrev} href={hrefPrev} onClick={(e) => {e.preventDefault(); onClickPrev ? onClickPrev() : History.push(hrefPrev)}}/>
            <RbPagination.Item href={hrefCurrent} onClick={(e) => {e.preventDefault(); onClickCurrent ? onClickCurrent() : History.push(hrefCurrent)}}>{`${page ? page : ''}${totalPages ? `/${totalPages}` : ''}`}</RbPagination.Item>
            <RbPagination.Next disabled={disabledNext} href={hrefNext} onClick={(e) => {e.preventDefault(); onClickNext ? onClickNext() : History.push(hrefNext)}}/>
        </RbPagination>
    </div>
}


export default Pagination