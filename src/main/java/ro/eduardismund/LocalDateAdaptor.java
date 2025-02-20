package ro.eduardismund;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;

public class LocalDateAdaptor extends XmlAdapter<String, LocalDate> {

    public LocalDate unmarshal(String v) throws Exception {
        if (v == null || v.isBlank()) {
            return null;
        }
        return LocalDate.parse(v);
    }

    public String marshal(LocalDate v) throws Exception {
        if (v == null) {
            return null;
        }
        return v.toString();
    }

}