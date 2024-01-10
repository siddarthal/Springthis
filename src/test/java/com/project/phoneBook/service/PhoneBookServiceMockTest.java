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
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.project.phoneBook.exception.PhoneBookException;
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
	public void getAllException() {
		when(phoneBookMock.findAll()).thenReturn(Collections.emptyList());
		assertThrows(PhoneBookException.class,()->{
			phoneBookService.getAllEntries();
		});
		
	}
	@Test
	public void demo() {
//		fail();
	}
	
	@Test
	public void getByName() {
//		PhoneBookRepository getAllEntriesMock= mock(PhoneBookRepository.class);
		String str1="siddartha";
		String str2="reddy";
		
		when(phoneBookMock.findByName(Mockito.anyString()) ).thenReturn(entry1);
//		PhoneBookService getAllServiceTest= new PhoneBookService(getAllEntriesMock);
		PhoneBookEntry entry= phoneBookService.getByName(Mockito.anyString());
		assertNotNull(entry);
		assertEquals(str1,entry.getName());
		assertNotEquals(str2,entry.getName());
	}
//	@Test
//	public void getByNameException() {
//		when(phoneBookMock.findByName(Mockito.anyString()) ).thenReturn(null);
//	}
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
	public void getByIdException() {
		when(phoneBookMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		assertThrows(PhoneBookException.class,()->{
			phoneBookService.getEntryById((long)1);
		});
		
	}
	@Test 
	public void postEntry() {
		when(phoneBookMock.save(entry1)).thenReturn(entry1);
		PhoneBookEntry entry=phoneBookService.saveEntry(entry1);
		assertNotNull(entry);
		assertEquals(entry3,entry);
		assertNotEquals(entry2,entry);
//		assertEquals(entry1.getId(),entry.getId());
//		assertEquals(entry1.getName(),entry.getName());
//		assertEquals(entry1.getPhoneNumber(),entry.getPhoneNumber());
		
	}
	
	@Test
	public void postEntryException() {
		when(phoneBookMock.save(entry1)).thenReturn(null);
		assertThrows(PhoneBookException.class,()->{
			phoneBookService.saveEntry(entry1);
		});
	}
	@Test 
	public void deleteEntry()
	{
		Mockito.doNothing().when(phoneBookMock).deleteById(Mockito.anyLong());
		when(phoneBookMock.findById(Mockito.anyLong())).thenReturn(Optional.empty()).thenReturn(optionalList);
//		 phoneBookService.deleteEntry(entry1.getId());
		assertThrows(PhoneBookException.class, () -> {
            phoneBookService.deleteEntry(Mockito.anyLong());
            
        });
		phoneBookService.deleteEntry((long)1);
		verify(phoneBookMock, times(2)).findById(Mockito.anyLong());
		verify(phoneBookMock, times(1)).deleteById((long)1);
	}
	
	@Test
	public void editEntry() {
		when(phoneBookMock.findById(Mockito.anyLong())).thenReturn(optionalList);
		when(phoneBookMock.save(entry1)).thenReturn(entry1);
		PhoneBookEntry entry = phoneBookService.editEntry((long)1,entry1);
		assertNotNull(entry);
		assertEquals(entry1,entry);
		assertEquals(entry3,entry);
		assertNotEquals(entry2,entry);
	}
	@Test
	public void editEntryException() {
		when(phoneBookMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		assertThrows(PhoneBookException.class,()->{
			phoneBookService.editEntry((long)1,entry1);
		});
	}
	
	
	@Test
	public void editParticularEntry() {
		when(phoneBookMock.findById(Mockito.anyLong())).thenReturn(optionalList);
		when(phoneBookMock.save(entry1)).thenReturn(entry1);
		PhoneBookEntry entry = phoneBookService.editOnly((long)1,entry1);
		assertNotNull(entry);
		assertEquals(entry1,entry);
		assertEquals(entry3,entry);
	    assertNotEquals(entry2,entry);
	}
	@Test
	public void editParticularException() {
		when(phoneBookMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		assertThrows(PhoneBookException.class,()->{
			phoneBookService.editOnly((long)1,entry1);
		});
	}
}
