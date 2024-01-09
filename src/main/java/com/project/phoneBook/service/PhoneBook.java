package com.project.phoneBook.service;

import java.util.List;
import java.util.Optional;

import com.project.phoneBook.model.PhoneBookEntry;

public interface PhoneBook {
	public  List<PhoneBookEntry> getAllEntries();
	public Optional<PhoneBookEntry> getEntryById(Long id);
	 public PhoneBookEntry saveEntry(PhoneBookEntry entry);
	 public Optional<PhoneBookEntry> deleteEntry(Long id);
	 public PhoneBookEntry editEntry(Long id,PhoneBookEntry book);
	 public PhoneBookEntry editOnly(Long id,PhoneBookEntry book);
	 
}
