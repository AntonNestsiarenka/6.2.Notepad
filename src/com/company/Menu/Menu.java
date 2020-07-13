package com.company.Menu;

import com.company.Notepad.Notepad;
import com.company.Utils.InputUtils;

import java.util.Scanner;

public class Menu {

    public static final String MAIN_MENU = "Main menu:\n" +
            "1. Show notes\n" +
            "2. Search notes\n" +
            "3. Sort notes\n" +
            "4. Add note\n" +
            "5. Help\n" +
            "6. Exit\n";

    public static final String SEARCH_MENU = "Search menu:\n" +
            "1. Search by theme\n" +
            "2. Search by date of creation\n" +
            "3. Search by email\n" +
            "4. Search by word\n" +
            "5. Back\n" +
            "6. Help\n" +
            "7. Exit\n";

    public static final String SORT_MENU = "Sort menu:\n" +
            "1. Sort by theme\n" +
            "2. Sort by date of creation\n" +
            "3. Sort by email\n" +
            "4. Back\n" +
            "5. Help\n" +
            "6. Exit\n";

    private Notepad notepad;
    private Scanner scanner;


    public Menu() {
        notepad = Notepad.createInstance();
        scanner = new Scanner(System.in);
    }

    public Notepad getNotepad() {
        return notepad;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void mainMenu()
    {
        System.out.println("Welcome to Notepad App!\n");
        boolean logic = true;
        while (logic)
        {
            System.out.println(MAIN_MENU);
            String command = InputUtils.inputStr("Enter command: ", scanner);
            switch (command.toLowerCase())
            {
                case ("1"):
                case ("show notes"):
                {
                    notepad.showNotepad();
                    break;
                }
                case ("2"):
                case ("search notes"):
                {
                    searchMenu();
                    break;
                }
                case ("3"):
                case ("sort notes"):
                {
                    sortMenu();
                    break;
                }
                case ("4"):
                case ("add note"):
                {
                    String themeInput = InputUtils.inputNotEmptyStr("Enter theme for note: ", scanner);
                    String emailInput = InputUtils.inputNotEmptyStr("Enter email address for note: ", scanner);
                    String textInput = InputUtils.inputNotEmptyStr("Enter text for note: ", scanner);
                    notepad.addNote(themeInput, emailInput, textInput);
                    break;
                }
                case ("5"):
                case ("help"):
                {
                    System.out.println("Description of all commands:\n" +
                            "1. Show notes - show all notes from notepad.\n" +
                            "2. Search notes - search note/notes by user-specified parameter.\n" +
                            "3. Sort notes - sorts notes by a given parameter.\n" +
                            "4. Add note - add new note to Notepad.\n" +
                            "5. Help - show description of all commands.\n" +
                            "6. Exit - application shutdown.\n" +
                            "Commands can be entered case insensitive or using numbers.\n");
                    break;
                }
                case ("6"):
                case ("exit"):
                {
                    scanner.close();
                    logic = false;
                    break;
                }
                default:
                {
                    System.out.println("Such a console command does not exist. For help, type help in the console.");
                }
            }
        }
    }

    private void searchMenu()
    {
        boolean logic = true;
        while (logic)
        {
            System.out.println(SEARCH_MENU);
            String command = InputUtils.inputStr("Enter command: ", scanner);
            switch (command.toLowerCase())
            {
                case ("1"):
                case ("search by theme"):
                {
                    String themeInput = InputUtils.inputNotEmptyStr("Enter theme for search: ", scanner);
                    System.out.println("Find notes:");
                    Notepad.printNotes(notepad.findByTheme(themeInput));
                    break;
                }
                case ("2"):
                case ("search by date of creation"):
                {
                    String dateInput = InputUtils.inputDate("Enter date for search: ", scanner);
                    System.out.println("Find notes:");
                    Notepad.printNotes(notepad.findByDate(dateInput));
                    break;
                }
                case ("3"):
                case ("search by email"):
                {
                    String email = InputUtils.inputEmailAddress("Enter email address for search: ", scanner);
                    System.out.println("Find notes:");
                    Notepad.printNotes(notepad.findByEmail(email));
                    break;
                }
                case ("4"):
                case ("search by word"):
                {
                    String wordInput = InputUtils.inputWord("Enter word for search: ", scanner);
                    System.out.println("Find notes:");
                    Notepad.printNotes(notepad.findByWord(wordInput));
                    break;
                }
                case ("5"):
                case ("back"):
                {
                    logic = false;
                    break;
                }
                case ("6"):
                case ("help"):
                {
                    System.out.println("Description of all commands:\n" +
                            "1. Search by theme - search all notes by theme.\n" +
                            "2. Search by date of creation - search all notes by date of creation.\n" +
                            "3. Search by email - search all notes by email.\n" +
                            "4. Search by word - search for notes containing a given word.\n" +
                            "5. Back - returns to the previous menu.\n" +
                            "6. Help - show description of all commands.\n" +
                            "7. Exit - application shutdown.\n" +
                            "Commands can be entered case insensitive or using numbers.\n");
                    break;
                }
                case ("7"):
                case ("exit"):
                {
                    scanner.close();
                    System.exit(0);
                    break;
                }
                default:
                {
                    System.out.println("Such a console command does not exist. For help, type help in the console.");
                }
            }
        }
    }

    private void sortMenu()
    {
        boolean logic = true;
        while (logic)
        {
            System.out.println(SORT_MENU);
            String command = InputUtils.inputStr("Enter command: ", scanner);
            switch (command.toLowerCase())
            {
                case ("1"):
                case ("sort by theme"):
                {
                    notepad.sortByTheme();
                    System.out.println("Notes are sorted by theme.");
                    break;
                }
                case ("2"):
                case ("sort by date of creation"):
                {
                    notepad.sortByDate();
                    System.out.println("Notes are sorted by date.");
                    break;
                }
                case ("3"):
                case ("sort by email"):
                {
                    notepad.sortByEmail();
                    System.out.println("Notes are sorted by email.");
                    break;
                }
                case ("4"):
                case ("back"):
                {
                    logic = false;
                    break;
                }
                case ("5"):
                case ("help"):
                {
                    System.out.println("Description of all commands:\n" +
                            "1. Sort by theme - sort all notes by theme.\n" +
                            "2. Sort by date of creation - sort all notes by date of creation.\n" +
                            "3. Sort by email - sort all notes by email.\n" +
                            "4. Back - returns to the previous menu.\n" +
                            "5. Help - show description of all commands.\n" +
                            "6. Exit - application shutdown.\n" +
                            "Commands can be entered case insensitive or using numbers.\n");
                    break;
                }
                case ("6"):
                case ("exit"):
                {
                    scanner.close();
                    System.exit(0);
                    break;
                }
                default:
                {
                    System.out.println("Such a console command does not exist. For help, type help in the console.");
                }
            }
        }
    }
}