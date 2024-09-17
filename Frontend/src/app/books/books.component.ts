import { Component, Input } from '@angular/core';
import { Book } from '../book';
import { BooksService } from '../books.service';

@Component({
  selector: 'app-books',
  standalone: true,
  imports: [],
  template: `
    <div class="book-container">
      <div class="book">
        <div class="bookImage-container">
          <img src="{{Books.imageURL}}" alt="Book image" />
        </div>
        <div class="bookContent">
          <h2>{{Books.title}}</h2>
          <p>{{Books.author}}</p>
          <p><strong>{{Books.numCopies}}</strong> copies</p>
        </div>
      </div>
    </div>
  `,
  styleUrl: './books.component.css'
})
export class BooksComponent {
  @Input() Books!: Book;
}
