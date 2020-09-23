package com.example.SpringBootApp.dao;

import com.example.SpringBootApp.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> listUsers() {
        List<User> list = em.createQuery("from User").getResultList();

        return list;
    }

    @Override
    public void add(User user) {
        em.persist(user);
    }

    @Override
    public void delete(User user) {
        em.remove(em.contains(user) ? user : em.merge(user));
    }

    @Override
    public void edit(User user) {
        em.merge(user);
    }

    @Override
    public User getById(Long id) {

        return em.find(User.class, id);
    }

    @Override
    public User fingByUserName(String username) {

        TypedQuery<User> query = em.createQuery("from User u where u.username= :username", User.class);
        return query.setParameter("username", username).getSingleResult();
    }
}
