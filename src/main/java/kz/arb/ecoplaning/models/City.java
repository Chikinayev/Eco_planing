package kz.arb.ecoplaning.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum City {
    Almaty("Алматы"), Shymkent("Шымкент"), Astana("Астана");

    String value;

    City(String value) {
        this.value = value;
    }
    @JsonValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
