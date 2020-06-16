import {Paging} from "./paging";

export class PagedList<T> {
  list: T[];
  paging: Paging;


  constructor(list: T[], paging: Paging) {
    this.list = list;
    this.paging = paging;
  }
}
