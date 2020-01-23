package es.incentro.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {

    T refresh(T entity);

    default T saveAndRefresh(T entity) {
        entity = save(entity);
        return refresh(entity);
    }

}
