package ro.eduardismund;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.JAXBContext;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class PersonsServlet extends HttpServlet {

    private final PersonsRepository personsRepository;
    static final Pattern UriWithId = Pattern.compile("^/persons/([^/]+)$");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/xml");
        final var matcher = UriWithId.matcher(req.getRequestURI());

        if(matcher.matches()) {
            final var personId = matcher.group(1);
            final var person = personsRepository.getPerson(personId);
            if(person == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            JaxbUtil.marshall(person, resp.getWriter());
        }else{
            final var persons = personsRepository.getPersons();
            JaxbUtil.marshall(new Persons(persons), resp.getWriter());
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/xml");
        final var person = JaxbUtil.unmarshall(Person.class, req.getReader());
        personsRepository.addPerson(person);
        JaxbUtil.marshall(person, resp.getWriter());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/xml");
        final var matcher = UriWithId.matcher(req.getRequestURI());

        if(matcher.matches()) {
            final var person = personsRepository.deletePerson(matcher.group(1));
            if(person == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            JaxbUtil.marshall(person, resp.getWriter());
            return;

        }
        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/xml");
        final var matcher = UriWithId.matcher(req.getRequestURI());

        if(matcher.matches()) {
            final var personId = matcher.group(1);
            final var person = JaxbUtil.unmarshall(Person.class, req.getReader());
            person.setId(personId);
            if(personsRepository.updatePerson(person)) {
                JaxbUtil.marshall(person, resp.getWriter());
            }else{
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
        else{
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }


}