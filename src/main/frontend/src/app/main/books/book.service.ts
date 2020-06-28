import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {Book} from "./book";
import {catchError, tap} from "rxjs/operators";
import {environment} from "../../../environments/environment";
import {Page} from "../../shared/paging/page";

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private booksUrl: string = environment.apiUrl + '/books';
  private httpOptions = {headers: new HttpHeaders({'Content-Type': 'application/json'})};

  constructor(private http: HttpClient) {
  }

  public getAllBooks(page = 0, size = 10): Observable<Page<Book>> {
    let pageParams: HttpParams = new HttpParams()
    .append('page', String(page))
    .append('size', String(size));
    return this.http.get<any>(this.booksUrl, {params: pageParams}).pipe(
      tap(page => console.log(page)),
      catchError(error => this.handleError('getAllBookss'))
    );
  }


  public searchBooks(page = 0, size = 10, searchTerm = ""): Observable<Page<Book>> {
    let params: HttpParams = new HttpParams()
    .append('page', String(page))
    .append('size', String(size));
    if (searchTerm !== null && searchTerm.length > 0) {
      params = params.append('searchTerm', searchTerm);
    }
    return this.http.get<any>(this.booksUrl + '/search', {params: params}).pipe(
      tap(page => console.log(page)),
      catchError(error => this.handleError('searchBookss'))
    );
  }


  // public getBooks(paging: Paging) {
  //   let params: HttpParams = new HttpParams()
  //   .append('pageNumber', String(paging.page))
  //   .append('pageSize', String(paging.size));
  //
  //   if (paging.searchTerm !== null && paging.searchTerm.length > 0) {
  //     params = params.append('searchTerm', paging.searchTerm);
  //   }
  //   return this.http.get<any>(this.booksUrl, {params: params}).pipe(
  //     tap(obj => console.log(obj)),
  //     map(value => new PagedList<Book>(value.data, value.paging)),
  //     catchError(error => this.handleError('getBookss'))
  //   );
  // }

  // public getPagedBooks(pageNumber: number = 1, pageSize: number = 20, titleLike: string = ""): Observable<PagedList<Book>> {
  //   let params: HttpParams = new HttpParams()
  //   .append('pageNumber', String(pageNumber))
  //   .append('pageSize', String(pageSize))
  //   .append('titleLike', titleLike);
  //   return this.http.get<any>(this.booksUrl, {params: params}).pipe(
  //     tap(obj => console.log(obj)),
  //     map(value => new PagedList<Book>(value.books, value.paging)),
  //     catchError(error => this.handleError('getPagedBookss'))
  //   );
  // }

  public getBook(id: number): Observable<Book> {
    return this.http.get<Book>(this.booksUrl + '/' + id)
  }

  public addBook(book: Book): Observable<Book> {
    return this.http.post<Book>(this.booksUrl, book);
  }

  public updateBook(id: number, book: Book): Observable<Book> {
    return this.http.put<Book>(this.booksUrl + '/' + id, book,);
  }

  public deleteBook(id: number): Observable<any> {
    return this.http.delete(this.booksUrl + '/' + id);
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); // log to console instead
      console.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
}
