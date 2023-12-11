package com.example.capstone2.Controller;

import com.example.capstone2.Model.Customer;
import com.example.capstone2.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping("/get")
    public ResponseEntity getAllCustomers(){
        return ResponseEntity.status(200).body(customerService.getAllCustomers());
    }
    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody@Valid Customer customer, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        customerService.addCustomer(customer);
        return ResponseEntity.status(200).body("customer added!");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCustomer(@PathVariable Integer id,@RequestBody@Valid Customer customer,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        customerService.updateCustomer(id, customer);
        return ResponseEntity.status(200).body("customer updated!");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Integer id){
        customerService.deleteCustomer(id);
        return ResponseEntity.status(200).body("customer deleted!");
    }
    //18
    @GetMapping("/logIn/{userName}/{email}")
    public ResponseEntity logIn(@PathVariable String userName,@PathVariable String email){
        return ResponseEntity.status(200).body(customerService.logIn(userName, email));
    }
    //19
    @PutMapping("/receipt/{orderId}")
    public ResponseEntity receipt(@PathVariable Integer orderId){
        return ResponseEntity.status(200).body(customerService.receipt(orderId));
    }
    //20
    @GetMapping("/getSpecial")
    public ResponseEntity findSpecial(){
        return ResponseEntity.status(200).body(customerService.findSpecialCustomer());
    }
}
