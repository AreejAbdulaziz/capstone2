package com.example.capstone2.Controller;

import com.example.capstone2.Model.Publisher;
import com.example.capstone2.Service.PublisherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/publisher")
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;
    @GetMapping("/get")
    public ResponseEntity getAllPublishers(){
        return ResponseEntity.status(200).body(publisherService.getAllPublisher());
    }
    @PostMapping("/add")
    public ResponseEntity addPublisher(@RequestBody@Valid Publisher publisher, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        publisherService.addPublisher(publisher);
        return ResponseEntity.status(200).body("publisher added!");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updatePublisher(@PathVariable Integer id,@RequestBody@Valid Publisher publisher,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        publisherService.updatePublisher(id, publisher);
        return ResponseEntity.status(200).body("publisher updated!");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePublisher(@PathVariable Integer id){
        publisherService.deletePublisher(id);
        return ResponseEntity.status(200).body("publisher deleted!");
    }
}
