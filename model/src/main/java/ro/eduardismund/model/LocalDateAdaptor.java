package ro.eduardismund.model;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;

public class LocalDateAdaptor extends XmlAdapter<String, LocalDate> {

    public LocalDate unmarshal(String v) {
        if (v == null || v.isBlank()) {
            return null;
        }
        return LocalDate.parse(v);
    }

    public String marshal(LocalDate v){
        if (v == null) {
            return null;
        }
        return v.toString();
    }

}