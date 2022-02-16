package ru.geekbrains.springlesson6.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.springlesson6.dao.ProductDao;
import ru.geekbrains.springlesson6.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
@Transactional
public class ProductRepository implements ProductDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Iterable<Product> findAll() {
        return entityManager.createNamedQuery("Product.findAll").getResultList();
    }

    @Override
    public Product findById(Long id) {
        TypedQuery<Product> query = entityManager.createNamedQuery("Product.findById", Product.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void saveOrUpdate(Product product) {
        if (product.getId() == null) {
            entityManager.persist(product);
        } else {
            entityManager.merge(product);
        }
    }

    @Override
    public void deleteById(Long id) {
        Product savedProduct = findById(id);
        entityManager.remove(savedProduct);
    }
}
