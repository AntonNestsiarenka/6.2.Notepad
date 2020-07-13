package com.company;

import com.company.Menu.Menu;
import com.company.Notepad.Notepad;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        Notepad notepad = Notepad.createInstance();
        try {
            notepad.notepadInit();
        } catch (FileNotFoundException e) {
            System.out.println("Initialisation notes from notepad failed.");
            System.out.println(e.getMessage());
            return;
        }
        Menu menu = new Menu();
        menu.mainMenu();
    }
}
