package com.sompopo.api.command.valueobject;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

@Data
public class Username {

    private final String value;

    
    @JsonCreator
    public Username(String value) {
        if (!isValidUsername(value)) {
            throw new IllegalArgumentException("Invalid username format: " + value);
        }
        this.value = value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Username username = (Username) o;
        return value.equals(username.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    private boolean isValidUsername(String username) {
        String regex = "^[a-zA-Z0-9_]{3,20}$";
        return username.matches(regex);
    }
}