package es.incentro.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CompanyRepository companyRepository;

    public void putData() {
        Company company = new Company();
        company.setName("ThoughtWorks");
        company = companyRepository.save(company);

        Person alice = new Person();
        alice.setName("Alice");
        alice.setCompany(company);
        personRepository.save(alice);
    }

    public Person createPerson(Person person) {
        return personRepository.saveAndRefresh(person);
    }

    public List<Person> getPersons(Long companyId) {
        return companyId == null ? personRepository.findAll() : personRepository.findByCompany_Id(companyId);
    }

    public Person getPerson(long id) {
        return personRepository.findById(id).orElse(null);
    }

    public Person updatePerson(long id, Person person) {
        person.setId(id);
        return personRepository.save(person);
    }

    public Person updatePerson(long id, Map<String, Object> personMap) {
        Person person = getPerson(id);
        if(personMap.containsKey("name")) {
            person.setName((String) personMap.get("name"));
        }
        if(personMap.containsKey("company")) {
            Map<String, Object> companyMap = (Map<String, Object>) personMap.get("company");
            if(companyMap.containsKey("id")) {
                Company company = new Company();
                company.setId((Long) companyMap.get("id"));
                person.setCompany(company);
            }
        }
        return personRepository.save(person);
    }

}
