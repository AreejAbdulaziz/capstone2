package com.example.capstone2.Controller;

import com.example.capstone2.Model.Orders;
import com.example.capstone2.Service.OrdersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrdersService ordersService;
    @GetMapping("/get")
    public ResponseEntity getAllOrders(){
        return ResponseEntity.status(200).body(ordersService.getAllOrders());
    }
    @PostMapping("/rent")
    public ResponseEntity rentBook(@RequestBody@Valid Orders order, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        ordersService.rentBook(order);
        return ResponseEntity.status(200).body("order added!");
    }
    //////////////////////21////////////////////////
    @PutMapping("/return/{orderId}")
    public ResponseEntity returnBook(@PathVariable Integer orderId){
        ordersService.returnBook(orderId);
        return ResponseEntity.status(200).body("book returned!");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateOrder(@PathVariable Integer id,@RequestBody@Valid Orders order,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        ordersService.updateOrder(id, order);
        return ResponseEntity.status(200).body("order updated!");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrder(@PathVariable Integer id){
        ordersService.deleteOrder(id);
        return ResponseEntity.status(200).body("order deleted!");
    }
    /////////////////22//////////////////
    @GetMapping("/notReturned")
    public ResponseEntity notReturned(){
        return ResponseEntity.status(200).body(ordersService.findNotReturnedOrders());
    }
    ////////////////23//////////////////
    @GetMapping("/returned")
    public ResponseEntity returned(){
        return ResponseEntity.status(200).body(ordersService.findReturnedOrders());
    }
}
