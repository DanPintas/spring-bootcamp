package es.incentro;

import es.incentro.data.Company;
import es.incentro.data.CompanyRepository;
import es.incentro.data.Person;
import es.incentro.data.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.transaction.Transactional;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) {
        Person person;

        System.out.println("\n----------\n");
        System.out.println("REPOSITORIES TEST");
        System.out.println("\n----------\n");

        Company companyA = new Company();
        companyA.setName("Company A");
        companyA = companyRepository.save(companyA);


        System.out.println("VALIDATION ERROR (name is required) \n");
        person = new Person();
        try {
            System.out.println(person + "\n");
//            personRepository.save(person);
        } catch (Exception e) {
            // do nothing
        }

        System.out.println("\n----------\n");


        System.out.println("Insert new people \n");

        person = new Person();
        person.setName("Alice");
        person.setCompany(companyA);
        System.out.println("Saved " + personRepository.save(person));

        person = new Person();
        person.setName("Bob");
        person.setCompany(companyA);
        System.out.println("Saved " + personRepository.save(person));

        System.out.println("\n----------\n");


        System.out.println("Find all people \n");

        System.out.println(personRepository.findAll());

        System.out.println("\n----------\n");


        System.out.println("Find by example \n");

        person = new Person();
        person.setName("O");
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();
        System.out.println(personRepository.findAll(Example.of(person, matcher)));

        System.out.println("\n----------\n");


        System.out.println("Find by custom query \n");

        System.out.println(personRepository.findByNameStartsWithIgnoreCase("ali"));

        System.out.println("\n----------\n");

        jdbcTemplate.query("select * from Person", (rs, row) -> {
            System.out.println(rs.getString("name"));
            return null;
        });

    }
}