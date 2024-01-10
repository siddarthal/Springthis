package com.project.phoneBook.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PhoneBookEntryTest {
	PhoneBookEntry entry1;
	PhoneBookEntry entry2;
	PhoneBookEntry entry3;
	@BeforeEach
	public void intitialze() {
		entry1=new PhoneBookEntry();
		entry1.setId((long)1);
		entry1.setName("sidd");
		entry1.setPhoneNumber("8989832354");
		entry2=new PhoneBookEntry();
		entry2.setId((long)1);
		entry3=new PhoneBookEntry();
		entry3.setId((long)2);
	}
	@Test
	public void testId() {
		assertEquals(entry1.hashCode(),entry2.hashCode());
		assertNotEquals(entry1.hashCode(),entry3.hashCode());
		entry1.setId(null);
        entry2.setId(null);
        assertEquals(entry1.hashCode(), entry2.hashCode());
        entry1.setId((long) 1);
        entry2.setId(null);
        assertNotEquals(entry1.hashCode(), entry2.hashCode());
	}
	@Test
	public void equals() {
		assertEquals(entry1,entry2);
		assertNotEquals(entry1,entry3);
		 assertTrue(entry1.equals(entry2));
		 assertFalse(entry1.equals(entry3));
		 assertFalse(entry1.equals(null));
		 entry1.setId(null);
		 entry2.setId(null);
		 assertTrue(entry1.equals(entry2));
	}
	@Test
	public void settersAndGetters() {
		assertNotNull(entry1.getId());
		assertNotNull(entry1.getName());
		assertNotNull(entry1.getPhoneNumber());
		PhoneBookEntry entry4= new PhoneBookEntry();
		entry4.setId((long)4);
		entry4.setName("sid");
		entry4.setPhoneNumber("9080808873");
		assertEquals(entry1,entry1);
//		assertEquals((long)4,entry4.getId());
		
		
	}
	@Test
	public void compareWithClass() {
		assertNotEquals(entry1,"dummy");
	}
	@Test
	public void compareWithNull() {
		assertNotEquals(entry1,null);
		entry2.setId(null);
		assertFalse(entry2.equals(entry1));
	}
	
}
