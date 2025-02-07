package com.sompopo.api.command.valueobject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Data;

@Data
public class Msisdn {

    private final String value;

    public Msisdn(String value) {
        if (!isValidMsisdn(value)) {
            throw new IllegalArgumentException("Invalid MSISDN format: " + value);
        }
        this.value = value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Msisdn msisdn = (Msisdn) o;
        return value.equals(msisdn.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    private boolean isValidMsisdn(String msisdn) {
        String regex = "^\\+\\d{1,3}\\d{4,14}$"; 
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(msisdn);
        return matcher.matches();
    }
}