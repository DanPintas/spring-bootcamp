package es.incentro.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostConstruct
    public void init() {
        personService.putData();
    }

    @GetMapping // either "/persons" or "/persons?companyId=1"
    private List<Person> get(@RequestParam(required = false) Long companyId) {
        return personService.getPersons(companyId);
    }

    @GetMapping("/{id}") // real path "/persons/{id}"
    private Person getById(@PathVariable long id) {
        return personService.getPerson(id);
    }

    @PostMapping
    public Person post(@RequestBody Person person) {
        return personService.createPerson(person);
    }

    @PatchMapping("/{id}") // real path "/persons/{id}"
    private Person patchById(@PathVariable long id, @RequestBody Map<String, Object> personMap) {
        return personService.updatePerson(id, personMap);
    }

    @PutMapping("/{id}") // real path "/persons/{id}"
    private Person putById(@PathVariable long id, @RequestBody Person person) {
        return personService.updatePerson(id, person);
    }

}
