package ru.spring.tutorial.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {
    private int id;

    @NotEmpty(message = "Не может быть пустым")
    @Size(min = 2, max = 100, message = "Поле должно содержать от 2 до 100 символов")
    private String title;

    @NotEmpty(message = "Не может быть пустым")
    @Size(min = 2, max = 50, message = "Поле должно содержать от 2 до 50 символов")
    private String author;

    @Min(value = 1700, message = "Необходимый формат: ГГГГ")
    private int year;

    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String toString() {
        return this.id + " " + this.title + " " + this.author + " " + this.year;
    }
}
