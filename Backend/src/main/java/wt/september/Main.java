package wt.september;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@SpringBootApplication
public class Main {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public static void main(String[] args){
        SpringApplication.run(Main.class, args);
    }

    @GetMapping("/")
    public String greet() {
        return "Hello World";
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @PostMapping("/users/add")
    public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam int age) {
        User n = new User();
        n.setName(name);
        n.setAge(age);
        userRepository.save(n);
        return name + " is saved";
    }

    @PutMapping("/users/update/{id}")
    public String updateUser(@PathVariable int id, @RequestBody User user) {
        User updatedUser = userRepository.findById(id).get();
        updatedUser.setName(user.getName());
        updatedUser.setAge(user.getAge());
        userRepository.save(updatedUser);

        return "User id: " + id + " updated";
    }

    @DeleteMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        User deleteUser = userRepository.findById(id).get();
        userRepository.delete(deleteUser);
        return "User with id: " + id + " deleted";
    }

    @GetMapping("/books")
    @CrossOrigin
    public List<Book> getBooks(){
        return (List<Book>) bookRepository.findAll();
    }

    @PostMapping("/books/add")
    public @ResponseBody String addNewBook(
            @RequestParam String title,
            @RequestParam String author,
            @RequestParam int numCopies,
            @RequestParam String imageURL) {
        Book b = new Book();
        b.setTitle(title);
        b.setAuthor(author);
        b.setNumCopies(numCopies);
        b.setImageURL(imageURL);
        bookRepository.save(b);
        return title + " is saved";
    }

    @PostMapping("/books/add/json")
    public String addNewBooksJSON(@RequestBody List<Book> books) throws Exception {
        for (Book book : books) {
            addNewBook(book.getTitle(), book.getAuthor(), book.getNumCopies(), book.getImageURL());
        }
        return "All " + books.size() + " books saved to database";
    }

    @PutMapping("/books/update/{id}")
    public String updateBook(@PathVariable int id, @RequestBody Book book) {
        Book updateBook = bookRepository.findById(id).get();
        updateBook.setTitle(book.getTitle());
        updateBook.setAuthor(book.getAuthor());
        bookRepository.save(updateBook);

        return "Book id: " + id + " updated";
    }

    @DeleteMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        Book deleteBook = bookRepository.findById(id).get();
        bookRepository.delete(deleteBook);
        return "Book with id: " + id + " deleted";
    }
}


