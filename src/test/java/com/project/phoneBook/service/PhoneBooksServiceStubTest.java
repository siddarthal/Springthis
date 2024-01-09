package com.project.phoneBook.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.project.phoneBook.model.PhoneBookEntry;
import com.project.phoneBook.repo.PhoneBookRepository;


public class PhoneBooksServiceStubTest {
	@Test
	public void testGetAllEntries() {
		StubClass dataServiceStub=new StubClass();
		PhoneBookService phoneBookService =new PhoneBookService(dataServiceStub);
		String strName;
		List<PhoneBookEntry> result =phoneBookService.getAllEntries();
		List<PhoneBookEntry> al=new ArrayList<>();
		PhoneBookEntry entry1=new PhoneBookEntry();
		entry1.setId((long) 1);
		entry1.setName("siddartha");
		entry1.setPhoneNumber("836762891");;
		al.add(entry1);
		assertEquals(al.get(0).getName(), result.get(0).getName()); 
		strName=result.get(0).getName();
		System.out.println(strName);
	}
	
}

class StubClass implements PhoneBookRepository{
	
	public List<PhoneBookEntry> findAll() {
		List<PhoneBookEntry> al=new ArrayList<>();
		PhoneBookEntry entry1=new PhoneBookEntry();
		entry1.setId((long) 1);
		entry1.setName("siddartha");
		entry1.setPhoneNumber("836762891");;
		al.add(entry1);
		return al;
		
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends PhoneBookEntry> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends PhoneBookEntry> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllInBatch(Iterable<PhoneBookEntry> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhoneBookEntry getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PhoneBookEntry getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PhoneBookEntry getReferenceById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends PhoneBookEntry> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends PhoneBookEntry> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends PhoneBookEntry> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PhoneBookEntry> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends PhoneBookEntry> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<PhoneBookEntry> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(PhoneBookEntry entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends PhoneBookEntry> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PhoneBookEntry> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PhoneBookEntry> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends PhoneBookEntry> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public <S extends PhoneBookEntry> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends PhoneBookEntry> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends PhoneBookEntry> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S extends PhoneBookEntry, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PhoneBookEntry findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}

