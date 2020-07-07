export interface Pageable {
  pageable: {
    sort: {
      sorted: boolean,
      unSorted: boolean,
      empty: boolean
    },
    pageNumber: number,
    pageSize: number,
    offset: number,
    paged: boolean,
    unPaged: boolean
  },
  numberOfElements: number,
  totalPages: number,
  totalElements: number,
  first: boolean,
  last: boolean
}
