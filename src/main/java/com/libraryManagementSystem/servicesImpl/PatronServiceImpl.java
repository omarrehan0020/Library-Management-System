package com.libraryManagementSystem.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.libraryManagementSystem.Exception.PatronNotFoundException;
import com.libraryManagementSystem.model.Patron;
import com.libraryManagementSystem.repository.PatronRepository;
import com.libraryManagementSystem.services.PatronService;

@Repository
public class PatronServiceImpl implements PatronService{
	@Autowired
    private PatronRepository patronRepository;

	
	@Transactional
    @Override
    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

	@Transactional
    @Override
    @Cacheable("patronById")
    public Patron getPatronById(Long id) {
        
        Optional<Patron> optionalPatron = patronRepository.findById(id);
        if(!optionalPatron.isPresent())
        	throw new PatronNotFoundException("Patron with ID " + id + " not found");
        
        return optionalPatron.get();
    }

	@Transactional
    @Override
    public Patron addPatron(Patron patron) {
        return patronRepository.save(patron);
    }

	@Transactional
    @Override
    @CacheEvict(value = "patronById", key = "#id")
    public Patron updatePatron(Long id ,Patron patron) {
    	 Optional<Patron> optionalExistingPatron = patronRepository.findById(id);
	        if (optionalExistingPatron.isPresent()) {
	            Patron existingPatron = optionalExistingPatron.get();
	            existingPatron.setAge(patron.getAge());
	            existingPatron.setEmail(patron.getEmail());
	            existingPatron.setPhoneNumber(patron.getPhoneNumber());
	            existingPatron.setName(patron.getName());
	            return patronRepository.save(existingPatron);
	        } else {
	        	throw new PatronNotFoundException("Patron with ID " + patron.getId() + " not found");
	        }
    }

	@Transactional
    @Override
    public void deletePatron(Long id) {
       
        Optional<Patron> optionalExistingPatron = patronRepository.findById(id);
        if (optionalExistingPatron.isPresent()) {
        	 patronRepository.deleteById(id);
        } else {
        	throw new PatronNotFoundException("Patron with ID " + id + " not found");
        }
    }
}
