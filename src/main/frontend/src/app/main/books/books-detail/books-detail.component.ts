import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {ActivatedRoute, Params} from "@angular/router";
import {BookService} from "../book.service";
import {Book} from "../book";

@Component({
  selector: 'app-books-detail',
  templateUrl: './books-detail.component.html',
  styleUrls: ['./books-detail.component.css']
})
export class BooksDetailComponent implements OnInit {
  id: number;
  editMode = false;
  book: Book;
  bookForm: FormGroup = new FormGroup({
    // 'cover': new FormControl(),
    'bookId': new FormControl(0),
    'isbn': new FormControl(),
    'title': new FormControl(),
    'publishDate': new FormControl(),
    // 'publisherId': new FormControl(0),
    'synopsis': new FormControl()
  })

  constructor(private route: ActivatedRoute,
              private bookService: BookService) {
  }

  ngOnInit(): void {
    this.route.params
    .subscribe(
      (params: Params) => {
        this.id = +params['bookId'];
        this.editMode = params['bookId'] != null;
        this.bookService.getBook(this.id).subscribe(
          book => {
            this.book = book
            this.initForm();
          })
      }
    )
  }

  private initForm() {
    let bookId = 0;
    let isbn = '';
    let title = '';
    let synopsis = '';
    let publishDate = '';
    // let cover = '';
    // let publisherId = 0;

    if (this.editMode && this.book) {
      bookId = this.book.bookId;
      isbn = this.book.isbn
      title = this.book.title
      synopsis = this.book.synopsis
      publishDate = this.book.publishDate
      // cover = this.book.cover
      // publisherId =this.book.publisherId
    }
    this.bookForm.setValue({
      // cover: cover,
      bookId: bookId,
      isbn: isbn,
      title: title,
      synopsis: synopsis,
      publishDate: publishDate,
      // publisherId: publisherId,
    })

  }

  onSubmit() {

  }
}
