package app.repository.customer;

import app.model.Customer;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Transactional
public class CustomerRepositoryImpl implements CustomerRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Customer> findAll() {
		TypedQuery<Customer> query = em.createQuery("select c from Customer c", Customer.class);
		return query.getResultList();
	}

	@Override
	public Customer findById(Long id) {
		TypedQuery<Customer> query = em.createQuery("select c from Customer c where  c.id=:id", Customer.class);
		query.setParameter("id", id);
		try {
			return query.getSingleResult();
		}catch (NoResultException e){
			return null;
		}
	}

	@Override
	public void save(Customer model) {
		if(model.getId() != null){
			em.merge(model);
		} else {
			em.persist(model);
		}
	}

	@Override
	public void remove(Long id) {
		Customer customer = findById(id);
		if(customer != null){
			em.remove(customer);
		}
	}
}
