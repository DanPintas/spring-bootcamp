package es.incentro.data;

import es.incentro.repo.BaseRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface EntityRepository<T> extends BaseRepository<T, Long> {

    List<Person> findByNameStartsWithIgnoreCase(String name);

}
