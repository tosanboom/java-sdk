package ir.boommarket.accounts;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Gender {
    MALE,
    FEMALE,
    LEGAL,
    PUBLIC;

    @JsonCreator
    static Gender of(String value) {
        for (Gender gender : Gender.values()) {
            if (gender.name().equalsIgnoreCase(value))
                return gender;
        }

        return null;
    }
}