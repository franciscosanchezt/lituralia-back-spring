import {Pageable} from "./pageable";

export class Paging {

  static readonly defaultSize = 10;
  static readonly pageSizes = [
    {value: 10, viewValue: "10"},
    {value: 20, viewValue: "20"},
    {value: 50, viewValue: "50"},
  ]

  constructor(public page: number = 0,
              public pages: number = 0,
              public size: number = Paging.defaultSize,
              public searchTerm: string = "") {

  }

  public static setPagingData(pageable: Pageable, searchTerm = ""): Paging {
    return new Paging(
      pageable.pageable.pageNumber,
      pageable.totalPages,
      pageable.pageable.pageSize, searchTerm)

  }

  public equalTo(other: Paging) {
    return Paging.equalTo(this, other)
  }

  public static equalTo(compare: Paging, compareTo: Paging): boolean {
    return compare.page === compareTo.page
      && compare.size === compareTo.size
      && compare.searchTerm === compareTo.searchTerm

  }
}
