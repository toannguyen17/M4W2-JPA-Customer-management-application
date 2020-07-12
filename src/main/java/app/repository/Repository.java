package app.repository;

import app.model.Customer;

import java.util.List;

public interface Repository<T> {
	void save(T entity);

	Customer findById(Long aLong);

	List<T> findAll();

	void remove(Long id);
}
