package es.incentro.data;

import java.util.List;

public interface PersonRepository extends EntityRepository<Person> {

    List<Person> findByCompany_Id(Long companyId);

}
