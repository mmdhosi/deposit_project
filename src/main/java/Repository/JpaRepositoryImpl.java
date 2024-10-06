package Repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JpaRepositoryImpl<T, ID> implements JpaRepository<T, ID> {

    @PersistenceContext(unitName = "costumerPu")
    private EntityManager entityManager;

    public JpaRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public JpaRepositoryImpl() {
    }

    @Override
    public T save(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    public <S extends T> S findById(ID id) {
        S entity = (S) entityManager.find(getEntityClass(), id);
        if (entity == null) {
            String entityName = getEntityClass().getSimpleName();
            throw new IllegalArgumentException(String.format("Entity %s with ID %s not found",
                    entityName, id));
        }
        return entity;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

//    @Override
//    public boolean existsById(ID id) {
//        return entityManager.contains(entityManager.getReference(getEntityClass(), id));
//    }
    @Override
    public boolean existsById(ID id) {
        return findById(id) != null;
    }

    @Override
    public List<T> findAll() {
        return entityManager.createQuery("SELECT e FROM " + getEntityName() + " e", getEntityClass()).getResultList();
    }

    @Override
    public long count() {
        return (long) entityManager.createQuery("SELECT COUNT(e) FROM " + getEntityName() + " e").getSingleResult();
    }

    @Override
    public boolean deleteById(ID id) {
        T entity = findById(id);
        if (entity != null) {
            entityManager.remove(entity);
            return true;
        }
        return false;
    }

    @Override
    public void delete(T entity) {
        entityManager.remove(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        for (T entity : entities) {
            delete(entity);
        }
    }

    @Override
    public <S extends T> List<S> findAllById(Iterable<ID> ids) {
        List<S> result = new ArrayList<>();
        for (ID id : ids) {
            S entity = findById(id);
            if (entity != null) {
                result.add(entity);
            }
        }
        return result;
    }

    @Override
    public void flush() {
        entityManager.flush();
    }

    @Override
    public boolean isEmpty() {
        return findAll().isEmpty();
    }

    private Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private String getEntityName() {
        return getEntityClass().getSimpleName();
    }
}