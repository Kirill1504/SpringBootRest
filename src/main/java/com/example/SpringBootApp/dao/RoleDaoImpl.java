package com.example.SpringBootApp.dao;

import com.example.SpringBootApp.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Role findRoleByRoleName(String role) {
        TypedQuery<Role> query = em.createQuery("from Role r where r.role= :role", Role.class);

        return query.setParameter("role", role).getSingleResult();
    }
}
