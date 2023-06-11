package kz.arb.ecoplaning.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum City {
    Almaty("Алматы"),
    Shymkent("Шымкент"),
    Astana("Астана"),
    Karaganda("Караганда"),
    Aktobe("Актобе"),
    Taraz("Тараз"),
    Pavlodar("Павлодар"),
    Semey("Семей"),
    Kostanay("Костанай"),
    Kyzylorda("Кызылорда"),
    Uralsk("Уральск"),
    Petropavlovsk("Петропавловск"),
    Aktau("Актау"),
    Temirtau("Темиртау"),
    Atyrau("Атырау"),
    Kokshetau("Кокшетау"),
    Taldykorgan("Талдыкорган"),
    Oral("Орал"),
    Rudny("Рудный"),
    Ekibastuz("Экибастуз"),
    Zhezkazgan("Жезказган"),
    Turkestan("Туркестан"),
    Kentau("Кентау"),
    Kaskelen("Каскелен"),
    Zhanaozen("Жанаозен");

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
