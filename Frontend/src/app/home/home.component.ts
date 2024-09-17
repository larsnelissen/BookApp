import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Book } from '../book';
import { BooksService } from '../books.service';
import { BooksComponent } from '../books/books.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    BooksComponent],
  template: `
    <section>
      <app-books
        *ngFor="let Books of booksList"
        [Books]="Books">
      </app-books>
    </section>
  `,
  styleUrl: './home.component.css'
})
export class HomeComponent {
  booksList: Book[] = [];
  booksService: BooksService = inject(BooksService);

  constructor() {
    this.booksService.getAllBooks().then((booksList: Book[]) => {
      this.booksList = booksList;
    });
    console.log(this.booksList);
  };
}
