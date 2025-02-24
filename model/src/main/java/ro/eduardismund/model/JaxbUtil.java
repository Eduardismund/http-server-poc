package ro.eduardismund.model;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lombok.SneakyThrows;

import java.io.PrintWriter;
import java.io.Reader;

public class JaxbUtil {
    private static final JAXBContext JAXB_CONTEXT;

    static {
        try {
            JAXB_CONTEXT = JAXBContext.newInstance(Person.class, Persons.class);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public static Marshaller createMarshaller(){
        final var marshaller = JAXB_CONTEXT.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        return marshaller;
    }

    @SneakyThrows
    public static Unmarshaller createUnmarshaller(){
        return JAXB_CONTEXT.createUnmarshaller();
    }

    @SneakyThrows
    public static void marshall(Object o, PrintWriter writer){
        createMarshaller().marshal(o,writer);
    }

    @SneakyThrows
    public static <T> T unmarshall(Class<T> cl, Reader reader){
        return cl.cast(createUnmarshaller().unmarshal(reader));
    }


}
