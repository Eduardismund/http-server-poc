package ro.eduardismund.client;

import lombok.SneakyThrows;
import ro.eduardismund.model.Person;

import java.net.URI;
import java.net.http.HttpClient;
import java.time.LocalDate;

public class ClientApp {
    @SneakyThrows
    public static void main(String[] args) {
        try (var httpClient = HttpClient.newHttpClient()) {
            final var baseUri = new URI("http://localhost:8080/persons");
            final var personsApiClient = new PersonsApiClient(httpClient, baseUri);
            System.out.println(personsApiClient.getPersons());
            System.out.println(personsApiClient.getPerson("123"));
            // CREATE a new person
            Person newPerson = new Person();
            newPerson.setId("456");
            newPerson.setFirstName("John");
            newPerson.setLastName("Doe");
            newPerson.setBirthDate(LocalDate.now());

            System.out.println("\nCreating new person:");
            Person createdPerson = personsApiClient.createPerson(newPerson);
            System.out.println(createdPerson);

            Person newPerson2 = new Person();
            newPerson2.setId("123");
            newPerson2.setFirstName("Alex");
            newPerson2.setLastName("Deac");
            newPerson2.setBirthDate(LocalDate.now());
            Person createdPerson1 = personsApiClient.updatePerson("123",newPerson2);
            System.out.println("Created: " + createdPerson1);
            System.out.println(personsApiClient.getPersons());
            System.out.println(personsApiClient.getPerson("123"));

            personsApiClient.deletePerson("123");
            System.out.println(personsApiClient.getPersons());

        }


    }
}
