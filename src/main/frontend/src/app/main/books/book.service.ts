import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {Book} from "./book";
import {catchError, map, tap} from "rxjs/operators";
import {PagedList} from "../../shared/paged-list";
import {Paging} from "../../shared/paging";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private booksUrl: string = environment.apiUrl + '/books';
  private httpOptions = {headers: new HttpHeaders({'Content-Type': 'application/json'})};

  constructor(private http: HttpClient) {
  }

  public getAllBooks(): Observable<Book[]> {
    return this.http.get<any>(this.booksUrl).pipe(
      tap(page => console.log(page)),
      map(result => result.data),
      catchError(error => this.handleError('getAllBooks'))
    );
  }

  public getBooks(paging: Paging) {
    let params: HttpParams = new HttpParams()
    .append('pageNumber', String(paging.pageNumber))
    .append('pageSize', String(paging.pageSize));

    if (paging.searchTerm !== null && paging.searchTerm.length > 0) {
      params = params.append('searchTerm', paging.searchTerm);
    }
    return this.http.get<any>(this.booksUrl, {params: params}).pipe(
      tap(obj => console.log(obj)),
      map(value => new PagedList<Book>(value.data, value.paging)),
      catchError(error => this.handleError('getPagedBooks'))
    );
  }

  public getPagedBooks(pageNumber: number = 1, pageSize: number = 20, titleLike: string = ""): Observable<PagedList<Book>> {
    let params: HttpParams = new HttpParams()
    .append('pageNumber', String(pageNumber))
    .append('pageSize', String(pageSize))
    .append('titleLike', titleLike);
    return this.http.get<any>(this.booksUrl, {params: params}).pipe(
      tap(obj => console.log(obj)),
      map(value => new PagedList<Book>(value.books, value.paging)),
      catchError(error => this.handleError('getPagedBooks'))
    );
  }

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

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      console.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
