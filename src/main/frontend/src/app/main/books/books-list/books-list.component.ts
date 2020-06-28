import {Component, OnDestroy, OnInit} from '@angular/core';
import {BookService} from "../book.service";
import {BehaviorSubject} from "rxjs";
import {Book} from "../book";
import {Paging} from "../../../shared/paging/paging";
import {debounceTime, distinctUntilChanged, switchMap} from "rxjs/operators";
import {isNumeric} from "rxjs/internal-compatibility";
import {RouterOutlet} from "@angular/router";

@Component({
  selector: 'app-books-list',
  templateUrl: './books-list.component.html',
  styleUrls: ['./books-list.component.css'],
  providers: [BookService]
})
export class BooksListComponent implements OnInit, OnDestroy {


  books: Book[] = [];

  pagingStatus: Paging = new Paging();
  pagingObs: BehaviorSubject<Paging> = new BehaviorSubject<Paging>(this.pagingStatus);

  selectedSize = Paging.defaultSize;
  pageSizes: { value: number, viewValue: string }[] = Paging.pageSizes

  activePages: any[] = [];

  searchTerm = "";


  constructor(private bookService: BookService,
              private routerOutlet: RouterOutlet) {
  }

  ngOnDestroy(): void {
    this.pagingObs.unsubscribe()
  }

  ngOnInit(): void {
    this.pagingObs.pipe(
      debounceTime(300),
      distinctUntilChanged((x, y) => x.equalTo(y)),
      switchMap(paging => this.bookService.searchBooks(paging.page, paging.size, paging.searchTerm))
    ).subscribe(value => {
      this.books = value.content
      this.pagingStatus = Paging.setPagingData(value)
      this.pagingStatus.searchTerm = this.searchTerm
      this.setActivePages()
    })
    this.pagingObs.next(this.pagingStatus)
  }

  private setActivePages() {
    let pagesList = []
    const page = this.pagingStatus.page + 1
    if (this.pagingStatus.pages < 9) {
      pagesList = Array(this.pagingStatus.pages)
      .fill(this.pagingStatus.pages)
      .map((x, i) => i + 1)
    } else {
      let pages: Set<any> = new Set();
      pages.add(1)
      pages.add(2)
      if (page > 4) {
        pages.add('...')
      }

      pages.add(Math.max(1, page - 1))
      pages.add(page)
      pages.add(Math.min(this.pagingStatus.pages, page + 1))

      if (page < this.pagingStatus.pages - 3) {
        pages.add('... ')
      }
      pages.add(this.pagingStatus.pages - 1)
      pages.add(this.pagingStatus.pages)

      pagesList = Array.from(pages);
    }
    this.activePages = pagesList;
  }

  public isNumeric(val: any): val is number | string {
    return isNumeric(val)
  }

  navigateTo(page: any) {
    if (Number.isNaN(page) || page < 0 || page > this.pagingStatus.pages - 1) {
      return;
    }
    this.pagingStatus.page = page
    this.pagingObs.next(this.pagingStatus);
  }

  changePageSize(pageSize: number) {
    this.pagingStatus.size = pageSize
    this.pagingObs.next(this.pagingStatus);
  }

  search(term: string = "") {
    this.pagingStatus.searchTerm = term
    this.searchTerm = term
    this.pagingObs.next(this.pagingStatus);
  }


}
