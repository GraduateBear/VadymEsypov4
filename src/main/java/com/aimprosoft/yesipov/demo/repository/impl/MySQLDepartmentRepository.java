package com.aimprosoft.yesipov.demo.repository.impl;

import com.aimprosoft.yesipov.demo.domain.Department;
import com.aimprosoft.yesipov.demo.repository.DepartmentRepositoryCustom;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
public class MySQLDepartmentRepository implements DepartmentRepositoryCustom {

    private static final Logger log = Logger.getLogger(MySQLDepartmentRepository.class);

    @Autowired
    EntityManager entityManager;

    @Override
    public void update(Department department, long id) {
        try {
            entityManager.getTransaction().begin();

            Query query = entityManager.createNativeQuery("update Department set id = :newId, originalName = :name " +
                    "where id = :id");

            query.setParameter("newId", department.getId());
            query.setParameter("name", department.getOriginalName());
            query.setParameter("id", id);

            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            try {
                entityManager.getTransaction().rollback();
            } catch (RuntimeException e1) {
                log.error("Couldn't roll back transaction", e1);
            }
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
