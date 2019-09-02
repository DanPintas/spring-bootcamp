package es.incentro.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface EntityRepository<T> extends JpaRepository<T, Long> {

    List<Person> findByNameStartsWithIgnoreCase(String name);

}
