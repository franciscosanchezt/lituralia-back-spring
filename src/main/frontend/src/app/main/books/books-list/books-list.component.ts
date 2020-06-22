import {Component, OnInit} from '@angular/core';
import {BookService} from "../book.service";
import {Subject} from "rxjs";
import {Book} from "../book";
import {Paging} from "../../../shared/paging";
import {debounceTime, distinctUntilChanged, switchMap, tap} from "rxjs/operators";
import {isNumeric} from "rxjs/internal-compatibility";
import {RouterOutlet} from "@angular/router";

@Component({
  selector: 'app-books-list',
  templateUrl: './books-list.component.html',
  styleUrls: ['./books-list.component.css'],
  providers: [BookService]
})
export class BooksListComponent implements OnInit {


  books: Book[] = [];

  pagingObs: Subject<Paging> = new Subject<Paging>();
  pagingStatus: Paging = new Paging();

  selectedSize = Paging.defaultSize;
  pageSizes: { value: number, viewValue: string }[] = Paging.pageSizes

  activePages: any[] = [];


  constructor(private bookService: BookService,
              private routerOutlet: RouterOutlet) {
  }

  ngOnInit(): void {
    this.pagingObs.pipe(
      debounceTime(300),
      distinctUntilChanged((x, y) => Paging.samePage(x, y)),
      switchMap(paging => this.bookService.getBooks(paging)),
      tap(x => {
        this.pagingStatus = x.paging;
        this.selectedSize = x.paging.pageSize
        this.setActivePages()
        this.books = x.list
      })
    ).subscribe(value => {
    })
    this.pagingObs.next(this.pagingStatus)
  }

  private setActivePages() {
    let pagesList = []
    if (this.pagingStatus.numberOfPages < 9) {
      pagesList = Array(this.pagingStatus.numberOfPages).fill(this.pagingStatus.numberOfPages).map((x, i) => i + 1)
    } else {
      let pages: Set<any> = new Set();
      pages.add(1)
      pages.add(2)
      if (this.pagingStatus.pageNumber > 4) {
        pages.add('...')
      }

      pages.add(Math.max(1, this.pagingStatus.pageNumber - 1))
      pages.add(this.pagingStatus.pageNumber)
      pages.add(Math.min(this.pagingStatus.numberOfPages, this.pagingStatus.pageNumber + 1))

      if (this.pagingStatus.pageNumber < this.pagingStatus.numberOfPages - 3) {
        pages.add('... ')
      }
      pages.add(this.pagingStatus.numberOfPages - 1)
      pages.add(this.pagingStatus.numberOfPages)

      pagesList = Array.from(pages);
    }
    this.activePages = pagesList;
  }

  public isNumeric(val: any): val is number | string {
    return isNumeric(val)
  }

  navigateTo(page: any) {
    if (Number.isNaN(page) || page < 1 || page > this.pagingStatus.numberOfPages) {
      return;
    }
    this.pagingStatus.pageNumber = page
    this.pagingObs.next(this.pagingStatus);
  }


  changePageSize(pageSize: number) {
    this.pagingStatus.pageSize = pageSize
    this.pagingObs.next(this.pagingStatus);
  }

  search(term: string = "") {
    this.pagingStatus.searchTerm = term
    this.pagingObs.next(this.pagingStatus);
  }


}
