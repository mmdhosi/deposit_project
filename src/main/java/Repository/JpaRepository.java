package Repository;

import java.util.List;

public interface JpaRepository<T, ID> {

    T save(T entity);

    <S extends T> S findById(ID id);

    boolean existsById(ID id);

    List<T> findAll();

    long count();

    boolean deleteById(ID id);

    void delete(T entity);

    void deleteAll(Iterable<? extends T> entities);

    <S extends T> List<S> findAllById(Iterable<ID> ids);

    void flush();

    boolean isEmpty();


}