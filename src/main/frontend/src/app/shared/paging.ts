export class Paging {

  static defaultSize = 10;
  static pageSizes = [
    {value: 10, viewValue: "10"},
    {value: 20, viewValue: "20"},
    {value: 50, viewValue: "50"},
  ]

  constructor(public pageNumber: number = 1,
              public numberOfPages: number = 0,
              public pageSize: number = 10,
              public searchTerm: string = "") {

  }


  public static samePage(compare: Paging, compareTo: Paging) {
    return compare.pageNumber === compareTo.pageNumber
      && compare.searchTerm === compareTo.searchTerm
      && compare.pageSize === compareTo.pageSize

  }
}
