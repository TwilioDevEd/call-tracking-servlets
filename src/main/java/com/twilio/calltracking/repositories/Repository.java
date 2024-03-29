package com.twilio.calltracking.repositories;

import com.twilio.calltracking.lib.Config;
import io.github.cdimascio.dotenv.Dotenv;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;


@SuppressWarnings({"unused", "checkstyle:visibilitymodifier"})
public abstract class Repository<T> {

    protected final Class<T> entityType;
    protected Map<String, String> properties;

    public Repository(Class<T> entity) {

        entityType = entity;
        properties = getProperties();
    }

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    @SuppressWarnings("unchecked") public Iterable<T> findAll() {
        EntityManager em = getEm();
        Query query =
            em.createQuery(String.format("SELECT e FROM %s e", entityType.getSimpleName()));

        Iterable<T> results = query.getResultList();
        em.close();
        return results;
    }

    /**
     * Retrieves an entity by its id.
     *
     * @param id The given id.
     * @return the entity with the given id
     */
    public T find(long id) {
        EntityManager em = getEm();
        T entity = em.find(entityType, id);
        em.refresh(entity);
        em.close();
        return entity;
    }

    /**
     * Saves a given entity. Use the returned instance for further operations.
     *
     * @param entity The entity
     * @return the saved entity
     */
    public T create(T entity) {
        EntityManager em = getEm();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        em.persist(entity);
        transaction.commit();
        em.close();
        return entity;
    }

    /**
     * Updates a given entity. Use the returned instance for further operations.
     *
     * @param entity The entity
     * @return the updated entity
     */
    public T update(T entity) {
        EntityManager em = getEm();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        T updatedEntity = em.merge(entity);
        transaction.commit();
        em.close();
        return updatedEntity;
    }

    /**
     * Deletes the entity.
     *
     * @param entity The entity
     */
    public void delete(T entity) {
        EntityManager em = getEm();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        em.remove(entity);
        transaction.commit();
        em.close();
    }

    private Map<String, String> getProperties() {
        Dotenv dotenv = Config.getDotenv();
        Map<String, String> config = new HashMap<>();

        if (dotenv.get("JDBC_URL") != null) {
            config.put("javax.persistence.jdbc.url", dotenv.get("JDBC_URL"));
        }

        if (dotenv.get("DB_USERNAME") != null) {
            config.put("javax.persistence.jdbc.user", dotenv.get("DB_USERNAME"));
        }

        if (dotenv.get("DB_PASSWORD") != null) {
            config.put("javax.persistence.jdbc.password", dotenv.get("DB_PASSWORD"));
        }

        return config;
    }

    protected EntityManager getEm() {
        return Persistence.createEntityManagerFactory("call-tracking-servlets", properties)
            .createEntityManager();
    }
}
