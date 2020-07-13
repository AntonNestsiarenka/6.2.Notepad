package com.company.Note;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Objects;

public class Note implements Comparable<Note>{

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    private String theme;
    private GregorianCalendar dateOfCreation;
    private String email;
    private String text;

    public Note()
    {
        theme = "";
        dateOfCreation = new GregorianCalendar();
        email = "";
        text = "";
    }

    public Note(String theme, String email, String text)
    {
        this.theme = theme;
        dateOfCreation = new GregorianCalendar();
        this.email = email;
        this.text = text;
    }

    public Note(String theme, GregorianCalendar gregorianCalendar, String email, String text)
    {
        this.theme = theme;
        this.dateOfCreation = gregorianCalendar;
        this.email = email;
        this.text = text;
    }

    public String getTheme() {
        return theme;
    }

    public GregorianCalendar getDateOfCreation() {
        return dateOfCreation;
    }

    public String getEmail() {
        return email;
    }

    public String getText() {
        return text;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setDateOfCreation(GregorianCalendar dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void printInfo()
    {
        System.out.println(toString());
    }

    @Override
    public int compareTo(Note o) {
        return o.dateOfCreation.compareTo(dateOfCreation);
    }

    @Override
    public String toString() {
        return "Theme: " + theme +
                "\nDate of creation: " + DATE_FORMAT.format(dateOfCreation.getTime()) +
                "\nEmail: " + email +
                "\nText: " + text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return theme.equals(note.theme) &&
                dateOfCreation.equals(note.dateOfCreation) &&
                email.equals(note.email) &&
                text.equals(note.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(theme, dateOfCreation, email, text);
    }
}