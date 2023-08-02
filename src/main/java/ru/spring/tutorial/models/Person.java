package ru.spring.tutorial.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    private int id;

    @NotEmpty(message = "Не может быть пустым")
    @Size(min = 3, max = 100, message = "Поле должно содержать от 3 до 100 букв")
    private String name;

    @Min(value = 1900, message = "Необходимый формат: ГГГГ")
    private int year;

    public Person(int id, String name, int year, String email) {
        this.id = id;
        this.name = name;
        this.year = year;
    }
    public Person() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
