package ro.eduardismund;

import java.time.LocalDate;
import java.util.*;

public class PersonsRepository {
    private Map<String, Person> persons;

    public PersonsRepository() {
        persons = new HashMap<String, Person>();
        persons.put("123", new Person("Ed", "J", LocalDate.parse("2022-12-12"), "123" ));
    }

    public List<Person> getPersons(){
        return persons.values().stream().toList();
    }

    public String addPerson(Person person){
        final var uuid = UUID.randomUUID().toString();
        person.setId(uuid);
        persons.put(uuid, person);
        return uuid;
    }

    public boolean updatePerson(Person person){
        if(!persons.containsKey(person.getId())){
            return false;
        }
        persons.put(person.getId(), person);
        return true;
    }

    public Person deletePerson(String id){
        return persons.remove(id);
    }

    public Person getPerson(String id){
        return persons.get(id);
    }
}
