package ro.eduardismund;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "person")
public class Person {

    @XmlAttribute
    private String firstName;
    @XmlAttribute
    private String lastName;
    @XmlAttribute
    @XmlJavaTypeAdapter(type=LocalDate.class,
            value=LocalDateAdaptor.class)
    private LocalDate birthDate;
    @XmlAttribute
    private String id;
}
