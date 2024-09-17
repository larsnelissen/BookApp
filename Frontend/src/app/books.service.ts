import { Injectable } from '@angular/core';
import { Book } from './book';

@Injectable({
  providedIn: 'root'
})

export class BooksService {

  url = 'http://localhost:8888/books';

  async getAllBooks(): Promise<Book[]> {
    const data = await fetch(this.url);
    return await data.json() ?? [];
  }
}
