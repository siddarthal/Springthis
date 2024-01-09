package com.project.phoneBook.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.phoneBook.exception.PhoneBookException;
import com.project.phoneBook.model.PhoneBookEntry;
import com.project.phoneBook.service.PhoneBook;



@RestController
@RequestMapping("/api/phonebook")
public class PhoneBookController {

   Logger logger = LoggerFactory.getLogger(PhoneBookController.class);
	@Autowired
    private PhoneBook phoneBookService;

    @GetMapping
    public ResponseEntity<?> getAllEntries() {
    	try {
  
    		return ResponseEntity.ok(phoneBookService.getAllEntries());
    	}
    	catch(PhoneBookException e) {
    		logger.error(e.getMessage());
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getErrors("404"));
    	}
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEntryById(@PathVariable Long id) {
    	try {
    		 return ResponseEntity.ok(phoneBookService.getEntryById(id));
    	}
    	catch(PhoneBookException e) {
    		logger.error(e.getMessage());
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getErrors("404"));
    		}
    }

    @PostMapping
    public ResponseEntity<?> saveEntry(@RequestBody PhoneBookEntry entry) {
    	try {
        return ResponseEntity.status(HttpStatus.CREATED).body(phoneBookService.saveEntry(entry));
    	}
    	catch(PhoneBookException e) {
    		logger.error(e.getMessage());
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getErrors("404"));
    	}
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEntry(@PathVariable Long id) {
    	try {
    		return ResponseEntity.ok(phoneBookService.deleteEntry(id));
    	}
    	catch(PhoneBookException e) {
    		logger.error(e.getMessage());
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getErrors("404"));
    	}
       
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editAndUpdateEntry(@PathVariable Long id ,@RequestBody PhoneBookEntry entry){
    	try {
    		return ResponseEntity.ok(phoneBookService.editEntry(id,entry));
    	}
    	catch(PhoneBookException e) {
    		logger.error(e.getMessage());
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getErrors("404"));
    	}
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> editOnlyEntry(@PathVariable Long id,@RequestBody PhoneBookEntry entry){
    	try {
    		return ResponseEntity.ok(phoneBookService.editOnly(id,entry));
    	}
    	catch(PhoneBookException e) {
    		logger.error(e.getMessage());
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getErrors("404"));
    	}
    }
}
