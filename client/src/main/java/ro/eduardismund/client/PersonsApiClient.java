package ro.eduardismund.client;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ro.eduardismund.model.JaxbUtil;
import ro.eduardismund.model.Person;
import ro.eduardismund.model.Persons;

import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RequiredArgsConstructor
public class PersonsApiClient {
    private final HttpClient httpClient;
    private final URI baseUri;

    @SneakyThrows
    Persons getPersons() {

        final var request = HttpRequest.newBuilder().uri(baseUri).build();
        final var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return JaxbUtil.unmarshall(Persons.class, new StringReader(response.body()));
        }
        throw new IllegalStateException("Persons GET returned " + response.statusCode());
    }


    @SuppressWarnings("SameParameterValue")
    @SneakyThrows
    Person getPerson(String id) {

        URI resolve = baseUri.resolve(id);
        System.out.println(resolve);
        final var request = HttpRequest.newBuilder().uri(new URI(baseUri + "/" + id)).build();
        final var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return JaxbUtil.unmarshall(Person.class, new StringReader(response.body()));
        }
        throw new IllegalStateException("Person with id: " + id + " GET returned " + response.statusCode());
    }

    @SneakyThrows
    Person createPerson(Person person) {
        final var writer = new StringWriter();
        try (final var printWriter = new PrintWriter(writer)) {
            JaxbUtil.marshall(person, printWriter);
        }

        final var request = HttpRequest.newBuilder()
                .uri(baseUri)
                .header("Content-Type", "application/xml")
                .POST(HttpRequest.BodyPublishers.ofString(writer.toString()))
                .build();
        final var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 201) {
            return JaxbUtil.unmarshall(Person.class, new StringReader(response.body()));
        }
        throw new IllegalStateException("Person created with id: " + response.statusCode());

    }


    @SuppressWarnings("SameParameterValue")
    @SneakyThrows
    Person updatePerson(String id, Person person) {
        StringWriter stringWriter = new StringWriter();
        try (PrintWriter printWriter = new PrintWriter(stringWriter)) {
            JaxbUtil.marshall(person, printWriter);
        }

        final var request = HttpRequest.newBuilder()
                .uri(new URI(baseUri + "/" + id))
                .header("Content-Type", "application/xml")
                .PUT(HttpRequest.BodyPublishers.ofString(stringWriter.toString()))
                .build();

        final var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return JaxbUtil.unmarshall(Person.class, new StringReader(response.body()));
        }
        throw new IllegalStateException("Person with id: " + id + " PUT returned " + response.statusCode());
    }

    @SuppressWarnings("SameParameterValue")
    @SneakyThrows
    void deletePerson(String id) {
        final var request = HttpRequest.newBuilder()
                .uri(new URI(baseUri + "/" + id))
                .DELETE()
                .build();

        final var response = httpClient.send(request, HttpResponse.BodyHandlers.discarding());

        if (response.statusCode() != 200) {
            throw new IllegalStateException("Person with id: " + id + " DELETE returned " + response.statusCode());
        }
    }
}
