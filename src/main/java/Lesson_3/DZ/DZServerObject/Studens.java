package Lesson_3.DZ.DZServerObject;

import Lesson_3.Book;

import java.io.Serializable;

public class Studens implements Serializable {
    int id;
    String name;

    transient Book book;

    public void setBook(Book book) {
        this.book = book;
    }

    public Studens(int id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("Studens up!");
    }

    public void info() {
        System.out.println(id + " " + name);
    }
}
