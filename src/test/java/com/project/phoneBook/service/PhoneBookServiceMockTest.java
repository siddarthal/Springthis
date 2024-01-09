package com.project.phoneBook.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.project.phoneBook.model.PhoneBookEntry;
import com.project.phoneBook.repo.PhoneBookRepository;

@ExtendWith(MockitoExtension.class) 
public class PhoneBookServiceMockTest {
	
	List<PhoneBookEntry> actualList ;
	List<PhoneBookEntry> actualList2;
	List<PhoneBookEntry> actualList3;
	PhoneBookEntry entry1;
	PhoneBookEntry entry2;
	PhoneBookEntry entry3;
	Optional<PhoneBookEntry> optionalList;
	
	@Mock
	private PhoneBookRepository phoneBookMock;
	@InjectMocks
	private PhoneBookService phoneBookService;
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
		actualList2.add(entry2);
		actualList.add(entry1);
		actualList3.add(entry3);
		System.out.println("in before each block");
	}
	
	@Test
	public void getAll() {
		when(phoneBookMock.findAll()).thenReturn(actualList);
		List<PhoneBookEntry> entry =phoneBookService.getAllEntries();
		assertNotNull(entry);
		assertEquals(actualList3.get(0),entry.get(0));
//		assertEquals(actualList.get(0).getId(),entry.get(0).getId());
//		assertEquals(actualList.get(0).getName(),entry.get(0).getName());
//		assertEquals(actualList.get(0).getPhoneNumber(),entry.get(0).getPhoneNumber());
		
		System.out.println(phoneBookService.getAllEntries().get(0).getName());
	}
	@Test
	public void getByName() {
//		PhoneBookRepository getAllEntriesMock= mock(PhoneBookRepository.class);
		String str1="siddartha";
		String str2="reddy";
		when(phoneBookMock.findByName(Mockito.anyString()) ).thenReturn(entry1);
//		PhoneBookService getAllServiceTest= new PhoneBookService(getAllEntriesMock);
		PhoneBookEntry entry= phoneBookService.getByName("siddartha");
		assertNotNull(entry);
		assertEquals(str1,entry.getName());
		assertNotEquals(str2,entry.getName());
	}
	@Test
	public void getById() {
		when(phoneBookMock.findById(Mockito.anyLong())).thenReturn(optionalList);
		Optional<PhoneBookEntry> entry = phoneBookService.getEntryById((long)1);
		assertNotNull(entry);
		long result=entry.get().getId();
		assertEquals((long)1,result);
		assertNotEquals((long)3,result);
	}
	@Test 
	public void postEntry() {
		when(phoneBookMock.save(entry1)).thenReturn(entry1);
		PhoneBookEntry entry=phoneBookService.saveEntry(entry1);
		assertNotNull(entry);
		assertEquals(entry3,entry);
//		assertEquals(entry1.getId(),entry.getId());
//		assertEquals(entry1.getName(),entry.getName());
//		assertEquals(entry1.getPhoneNumber(),entry.getPhoneNumber());
		
	}
//	@Test 
//	public void 
	

}
