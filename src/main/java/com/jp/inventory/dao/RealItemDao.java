package com.jp.inventory.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jp.inventory.model.Item;

@Repository
public class RealItemDao implements ItemDao {
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public RealItemDao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("hibernateDemo");
    }

    // public RealItemDao(Item item) {
    // entityManager.persist(item);
    // }

    @Override
    public List<Item> getAllItems() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String jpql = "SELECT i FROM  Item i";
            TypedQuery<Item> query1 = entityManager.createQuery(jpql, Item.class);
            return query1.getResultList();
        } finally {
            entityManager.close();
        }

    }

    @Override
    public Optional<Item> getItem(Integer itemId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getItem'");
    }

    @Override
    public int removeItem(Integer itemId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeItem'");
    }

    @Override
    public Optional<Item> insertItem(Integer itemId, Item item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertItem'");
    }

    @Override
    public boolean validateId(Integer itemId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateId'");
    }

    @Override
    public boolean validateLocationId(Integer locationId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateLocationId'");
    }

}
