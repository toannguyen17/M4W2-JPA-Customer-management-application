package app.service;

import app.model.Customer;
import app.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
	List<Customer> findAll();

	Customer findById(Long id);

	void save(Customer customer);

	void remove(Long id);
}