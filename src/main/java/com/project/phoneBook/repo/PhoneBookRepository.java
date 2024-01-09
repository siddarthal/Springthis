package com.project.phoneBook.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.phoneBook.model.PhoneBookEntry;

@Repository
public interface PhoneBookRepository extends JpaRepository<PhoneBookEntry, Long> {
	public PhoneBookEntry findByName(String name);
}
