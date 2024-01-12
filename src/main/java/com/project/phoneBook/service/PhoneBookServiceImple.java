package com.project.phoneBook.service;

//import org.springframework.beans.factory.annotation.Autowired;s
import org.springframework.stereotype.Service;

import com.project.phoneBook.exception.PhoneBookException;
import com.project.phoneBook.model.PhoneBookEntry;
import com.project.phoneBook.repo.PhoneBookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneBookServiceImple implements PhoneBook {

    
    private PhoneBookRepository phoneBookRepository;
    
    

    public PhoneBookServiceImple(PhoneBookRepository phoneBookRepository) {
		super();
		this.phoneBookRepository = phoneBookRepository;
	}

	public  List<PhoneBookEntry> getAllEntries() {
    	 List<PhoneBookEntry> entries = phoneBookRepository.findAll();
    	if(!entries.isEmpty()) {
    		return phoneBookRepository.findAll();
    	}
    	else {
    		throw new PhoneBookException("No items found in the db try adding the items to db first");
    	}

    }
	public PhoneBookEntry getByName(String name){
		return phoneBookRepository.findByName(name);
	}

    public Optional<PhoneBookEntry> getEntryById(Long id) {
    	 Optional<PhoneBookEntry> entry =phoneBookRepository.findById(id);
    	if(entry.isPresent()) {
    		return phoneBookRepository.findById(id);
    	}
    	else {
    		throw new PhoneBookException("Unable to find data from db with following id:"+ id);
    	} 
    }

    public PhoneBookEntry saveEntry(PhoneBookEntry entry) {
    	PhoneBookEntry element= phoneBookRepository.save(entry);
    	if(element!=null) {
    		return element;
    	}
   	else {
   		throw new PhoneBookException("Unable to save the data in db");
    	}
   }

    public Optional<PhoneBookEntry> deleteEntry(Long id) {
    	Optional<PhoneBookEntry> entry =phoneBookRepository.findById(id);
        if(entry.isPresent()) {
        	 phoneBookRepository.deleteById(id);
    		return entry;
    	}
    	else {
    		throw new PhoneBookException("Unable to find data from db with following id:"+ id +"so cannot delete it");
    	}
    }
    public PhoneBookEntry editEntry(Long id,PhoneBookEntry book){
    	Optional<PhoneBookEntry> entry =phoneBookRepository.findById(id);
//    	List<PhoneBookEntry> entries = phoneBookRepository.findById(id);
    	if(entry.isPresent()) {
    		PhoneBookEntry existing = entry.get();
    		existing.setName(book.getName());
    		existing.setPhoneNumber(book.getPhoneNumber());
    		phoneBookRepository.save(existing);
    		return existing;
    	}
    	else {
//    		PhoneBookEntry newElement = new PhoneBookEntry();
//    		newElement.setId(id);
//    		newElement.setName(book.getName());
//    		newElement.setPhoneNumber(book.getPhoneNumber());
//    		PhoneBookEntry element= phoneBookRepository.save(newElement);
//    		book.setId(id);
//    		PhoneBookEntry element= phoneBookRepository.save(book);
//        	if(element!=null) {
//        		return element;
//        	}
//       	else {
       		throw new PhoneBookException("Unable to save the data in db");
        	}
    		
//    	}
    }
    public PhoneBookEntry editOnly(Long id,PhoneBookEntry book){
    	Optional<PhoneBookEntry> entry = phoneBookRepository.findById(id);
    	if(entry.isPresent()) {
    		PhoneBookEntry existing= entry.get();
    		if(book.getName()!=null) {
    			existing.setName(book.getName());
    		}
    		if(book.getPhoneNumber()!=null) {
    			existing.setPhoneNumber(book.getPhoneNumber());
    		}
    		phoneBookRepository.save(existing);
    		return existing;
    	}
    	else {
    		throw new PhoneBookException("Unable to find the data in db to edit it");
    	}
    }
}
