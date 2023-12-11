package com.example.capstone2.Controller;
import com.example.capstone2.Model.Book;
import com.example.capstone2.Service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    @GetMapping("/get")
    public ResponseEntity getAllBooks(){
        return ResponseEntity.status(200).body(bookService.getAllBooks());
    }
    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody@Valid Book book, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        bookService.addBook(book);
        return ResponseEntity.status(200).body("book added!");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateBook(@PathVariable Integer id,@RequestBody@Valid Book book,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        bookService.updateBook(id, book);
        return ResponseEntity.status(200).body("book updated!");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBook(@PathVariable Integer id){
       bookService.deleteBook(id);
       return ResponseEntity.status(200).body("book deleted!");
    }
    //1
    @GetMapping("/get/{date1}/{date2}")
    public ResponseEntity findBookBetweenDate(@PathVariable LocalDate date1,@PathVariable LocalDate date2){
        return ResponseEntity.status(200).body(bookService.findBookBetweenDate(date1, date2));
    }
    //2
    @GetMapping("/get/c{customerId}")
    public ResponseEntity findBooksByIsAvailableAndCategoryPref(@PathVariable Integer customerId){
        return ResponseEntity.status(200).body(bookService.findBooksByIsAvailableAndCategoryPref(customerId));
    }
    //3
    @GetMapping("/getBy/a{customerId}")
    public ResponseEntity findBooksByAge(@PathVariable Integer customerId){
        return ResponseEntity.status(200).body(bookService.findBooksByAge(customerId));
    }
    //4
    @GetMapping("/getByB")
    public ResponseEntity findBookByBest(){
        return ResponseEntity.status(200).body(bookService.findBookByBest());
    }
    //5
    @PutMapping("/discount")
    public ResponseEntity discountBook(){
        bookService.discountBook();
        return ResponseEntity.status(200).body("books on discount!");
    }
    //6
    @GetMapping("/onDiscount")
    public ResponseEntity findBooksOnDiscount(){
        return ResponseEntity.status(200).body(bookService.booksOnDiscount());
    }
    //7
    @GetMapping("/language/{language}")
    public ResponseEntity displayByLanguage(@PathVariable String language){
        return ResponseEntity.status(200).body(bookService.displayByLanguage(language));
    }
    //8
    @PutMapping("/restockOne/{bookId}/{quantity}")
    public ResponseEntity reStockOneBook(@PathVariable Integer bookId,@PathVariable Integer quantity){
        bookService.reStockOneBook(bookId, quantity);
        return ResponseEntity.status(200).body("book restocked!");
    }
    //9
    @GetMapping("/getByT/{title}")
    public ResponseEntity findByTitleAndPubId(@PathVariable String title){
        return ResponseEntity.status(200).body(bookService.findByTitle(title));
    }
    //10
    @DeleteMapping("/deleteByRate")
    public ResponseEntity deleteBooksWithOneRating(){
        bookService.deleteBooksWithOneRating();
        return ResponseEntity.status(200).body("books deleted!");
    }
    //11
    @GetMapping("/getByP/{pubId}")
    public ResponseEntity findBookByPublisher(@PathVariable Integer pubId){
        return ResponseEntity.status(200).body(bookService.findBookByPub(pubId));
    }
    //12
    @GetMapping("/getByCat/{category}")
    public ResponseEntity findBookByCategory(@PathVariable String category){
        return ResponseEntity.status(200).body(bookService.findBookByCategory(category));
    }
    //13
    @GetMapping("/isAvailable")
    public ResponseEntity findBookByIsAvailable(){
        return ResponseEntity.status(200).body(bookService.findBookByIsAvailable());
    }
    //14
    @GetMapping("/before/{date}")
    public ResponseEntity findBookPublishedBeforeDate(@PathVariable LocalDate date){
        return ResponseEntity.status(200).body(bookService.booksPubBeforeDate(date));
    }
    //15
    @GetMapping("/after/{date}")
    public ResponseEntity findBookPublishedAfterDate(@PathVariable LocalDate date){
        return ResponseEntity.status(200).body(bookService.booksPubAfterDate(date));
    }
    //16
    @GetMapping("/mostRented")
    public ResponseEntity findBookByMostRented(){
        return ResponseEntity.status(200).body(bookService.findMostRented());
    }
    //17
    @GetMapping("/notAvailable")
    public ResponseEntity findBookNotAvailable(){
        return ResponseEntity.status(200).body(bookService.findBookNotAvailable());
    }
}
