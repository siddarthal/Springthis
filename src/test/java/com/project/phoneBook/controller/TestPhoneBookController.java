package com.project.phoneBook.controller;

import org.apache.coyote.http11.Http11InputBuffer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional; 

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.phoneBook.exception.PhoneBookException;
import com.project.phoneBook.model.PhoneBookEntry;
import com.project.phoneBook.service.PhoneBook;


@ExtendWith(MockitoExtension.class) 
public class TestPhoneBookController {
	
	List<PhoneBookEntry> actualList ;
	List<PhoneBookEntry> actualList2;
	List<PhoneBookEntry> actualList3;
	PhoneBookEntry entry1;
	PhoneBookEntry entry2;
	PhoneBookEntry entry3;
	Optional<PhoneBookEntry> optionalList;
	Optional<PhoneBookEntry> optionalList2;
	Optional<PhoneBookEntry> optionalList3;
	Logger logger = LoggerFactory.getLogger(PhoneBookController.class);
	
	@Mock
	private PhoneBook phoneBookServiceMock;
	@InjectMocks
	private PhoneBookController phoneBookContoller;
	

	@BeforeEach
	public void createPhoneBook() {
		actualList2 =new ArrayList<>();		
		actualList =new ArrayList<>();	
		actualList3 =new ArrayList<>();
		entry1=new PhoneBookEntry();		
		entry2=new PhoneBookEntry();	
		entry3=new PhoneBookEntry();
		entry1.setId((long) 1);
		entry1.setName("siddartha");
		entry1.setPhoneNumber("836762891"); 
		entry2.setId((long) 2);
		entry2.setName("Jeevan");
		entry2.setPhoneNumber("909092345");
		entry3.setId((long) 1);
		entry3.setName("siddartha");
		entry3.setPhoneNumber("836762891");
		optionalList=Optional.of(entry1);
		optionalList2=Optional.of(entry2);
		optionalList3=Optional.of(entry3);
		actualList2.add(entry2);
		actualList.add(entry1);
		actualList3.add(entry3);
		System.out.println("in before each block");
	}
	
	@Test
	public void getAll() {
		when(phoneBookServiceMock.getAllEntries()).thenReturn(actualList);
		ResponseEntity<?> entity= phoneBookContoller.getAllEntries();
		assertNotNull(entity.getBody());
		assertNotEquals(actualList2,entity.getBody());
		assertEquals(actualList3,entity.getBody());
		assertEquals(actualList,entity.getBody());
		logger.info("status code {}",entity.getStatusCode());
	    logger.info("second code {}",HttpStatus.OK);
	    assertEquals(HttpStatus.OK,entity.getStatusCode());
		
	}
	@Test 
	public void getAllStatus404(){
		when(phoneBookServiceMock.getAllEntries()).
		thenThrow(new PhoneBookException("Unable to find data from db "));
		 ResponseEntity<?> responseEntity=phoneBookContoller.getAllEntries();
		 phoneBookContoller.getAllEntries();
		 logger.info("status code in get all Status {}",responseEntity.getStatusCode());
		 assertNotNull(responseEntity.getBody());
		 assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
	}
	
	
	@Test
	public void getById() {
		when(phoneBookServiceMock.getEntryById(Mockito.anyLong())).thenReturn(optionalList);
		ResponseEntity<?> entity = phoneBookContoller.getEntryById((long)2);
		assertNotNull(entity.getBody());
		assertEquals(optionalList,entity.getBody());
		assertNotEquals(optionalList2,entity.getBody());
		assertEquals(optionalList3,entity.getBody());
		logger.info("status code  {}",entity.getStatusCode());
		assertEquals(HttpStatus.OK,entity.getStatusCode());
	}
	@Test
	public void getByIDstatus404(){
		when(phoneBookServiceMock.getEntryById(Mockito.anyLong())).
		thenThrow(new PhoneBookException("Unable to find data from db "));
		ResponseEntity<?> responseEntity = phoneBookContoller.getEntryById((long)2);
		logger.info("status code in getbyid status {}",responseEntity.getStatusCode());
		 assertNotNull(responseEntity.getBody());
		 assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
		
	}
	@Test 
	public void postData() {
		when(phoneBookServiceMock.saveEntry(entry1)).thenReturn(entry1);
		ResponseEntity<?> entity = phoneBookContoller.saveEntry(entry1);
		assertNotNull(entity.getBody());
		assertEquals(entry1,entity.getBody());
		assertNotEquals(entry2,entity.getBody());
		assertEquals(entry3,entity.getBody());
		assertEquals(HttpStatus.CREATED,entity.getStatusCode());
	}
	@Test
	public void postDataStatus404() {
		when(phoneBookServiceMock.saveEntry(Mockito.any())).
		thenThrow(new PhoneBookException("Unable to find data from db "));
		ResponseEntity<?> responseEntity = phoneBookContoller.saveEntry(entry1);
		logger.info("status code in post {}",responseEntity.getStatusCode());
		assertNotNull(responseEntity.getBody());
		assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
	}
	@Test
	public void deleteTestEntry() {
		when(phoneBookServiceMock.deleteEntry(Mockito.anyLong())).thenReturn(optionalList);
		ResponseEntity<?> entity = phoneBookContoller.deleteEntry((long)1);
		assertNotNull(entity.getBody());
		assertEquals(optionalList,entity.getBody());
		assertNotEquals(optionalList2,entity.getBody());
		assertEquals(optionalList3,entity.getBody());
		assertEquals(HttpStatus.OK,entity.getStatusCode());
	}
	@Test
	public void deleteEntryStatus404() {
		when(phoneBookServiceMock.deleteEntry(Mockito.anyLong())).
		thenThrow(new PhoneBookException("Unable to find data from db "));
		ResponseEntity<?> responseEntity = phoneBookContoller.deleteEntry((long)1);
		logger.info("status code in delete status {}",responseEntity.getStatusCode());
		assertNotNull(responseEntity.getBody());
		assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
	}
	@Test
	public void putDataTest() {
		when(phoneBookServiceMock.editEntry(Mockito.anyLong(),Mockito.any())).thenReturn(entry1);
		ResponseEntity<?> entity = phoneBookContoller.editAndUpdateEntry((long)1,entry1);
		assertNotNull(entity.getBody());
		assertEquals(entry1,entity.getBody());
		assertNotEquals(entry2,entity.getBody());
		assertEquals(entry3,entity.getBody());
		assertEquals(HttpStatus.OK,entity.getStatusCode());
	}
	@Test
	public void putDataStatus404() {
		when(phoneBookServiceMock.editEntry(Mockito.anyLong(),Mockito.any())).
		thenThrow(new PhoneBookException("Unable to find data from db "));
		ResponseEntity<?> responseEntity =phoneBookContoller.editAndUpdateEntry((long)1,entry1);
		logger.info("status code in put status {}",responseEntity.getStatusCode());
		assertNotNull(responseEntity.getBody());
		assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
	}
	
	@Test
	public void patchDataTest() {
		when(phoneBookServiceMock.editOnly(Mockito.anyLong(),Mockito.any())).thenReturn(entry1);
		ResponseEntity<?> entity = phoneBookContoller.editOnlyEntry((long)1,entry1);
		assertNotNull(entity.getBody());
		assertEquals(entry1,entity.getBody());
		assertNotEquals(entry2,entity.getBody());
		assertEquals(entry3,entity.getBody());
		assertEquals(HttpStatus.OK,entity.getStatusCode());
		
	}
	@Test
	public void patchDataStatus404() {
		when(phoneBookServiceMock.editOnly(Mockito.anyLong(),Mockito.any())).
		thenThrow(new PhoneBookException("Unable to find data from db "));
		ResponseEntity<?> responseEntity =phoneBookContoller.editOnlyEntry((long)1,entry1);
		logger.info("status code in patch status {}",responseEntity.getStatusCode());
		assertNotNull(responseEntity.getBody());
		assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
	}
}
