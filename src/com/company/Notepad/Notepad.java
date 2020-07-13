package com.company.Notepad;

import com.company.Note.Note;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Notepad {

    public static final String FILE_NAME_OF_CATALOG = "notes.txt";
    private static final String REGEX_TITLE = "^([a-zA-Z]+) \\| (.+)$";

    private static Notepad notepad;

    private ArrayList<Note> notes;

    public static Notepad createInstance()
    {
        if (notepad == null)
        {
            notepad = new Notepad();
        }
        return notepad;
    }

    public void addNote(String theme, String email, String text)
    {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_\\-]+(\\.[a-zA-Z0-9_\\-]+)*@[a-zA-Z0-9_\\-]+(\\.[a-zA-Z0-9_\\-]+)*(\\.[a-zA-Z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        if (matcher.find())
        {
            Note note = new Note(theme, email, text);
            try {
                writeNoteDataToFile(note);
                notes.add(note);
            } catch (IOException e) {
                System.out.println("Failed add data of note to file");
                e.getMessage();
                return;
            }
            System.out.println("Note added successfully");
            return;
        }
        System.out.println("Email address is incorrect.");
        System.out.println("Note not added.");
    }

    private Notepad()
    {
        notes = new ArrayList<>();
    }

    public void showNotepad()
    {
        System.out.println("All notes: ");
        int i = 1;
        for (Note note : notes)
        {
            System.out.print(i++ + ". ");
            note.printInfo();
            System.out.println();
        }
    }

    public void sortByDateOfCreation()
    {
        notes.sort(Note::compareTo);
    }

    public void writeNoteDataToFile(Note note) throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_NAME_OF_CATALOG, true);
        StringBuilder noteData = new StringBuilder("Theme | ");
        noteData.append(note.getTheme() + "\n");
        noteData.append("DateOfCreation | " + Note.DATE_FORMAT.format(note.getDateOfCreation().getTime()) + "\n");
        noteData.append("Email | " + note.getEmail() + "\n");
        noteData.append("Text | " + note.getText() + "\n");
        fileWriter.write("\n" + noteData.toString());
        fileWriter.flush();
        fileWriter.close();
    }

    public void notepadInit() throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_NAME_OF_CATALOG)));
        Pattern pattern = Pattern.compile(REGEX_TITLE);
        Note currentNote = new Note();
        while(true)
        {
            String temp = null;
            try {
                if (!((temp = bufferedReader.readLine()) != null))
                {
                    if (!currentNote.equals(new Note()))
                        notes.add(currentNote);
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Matcher matcher = pattern.matcher(temp);
            if (matcher.find())
            {
                switch (matcher.group(1))
                {
                    case ("Theme"):
                    {
                        currentNote.setTheme(matcher.group(2));
                        break;
                    }
                    case ("DateOfCreation"):
                    {
                        String[] dateTime = matcher.group(2).split("\\s");
                        String[] date = dateTime[0].split("\\.");
                        String[] time = dateTime[1].split(":");
                        GregorianCalendar gregorianCalendar = new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[1]) - 1, Integer.parseInt(date[0]), Integer.parseInt(time[0]), Integer.parseInt(time[1]));
                        currentNote.setDateOfCreation(gregorianCalendar);
                        break;
                    }
                    case ("Email"):
                    {
                        currentNote.setEmail(matcher.group(2));
                        break;
                    }
                    case ("Text"):
                    {
                        currentNote.setText(matcher.group(2));
                        break;
                    }
                }
            }
            else
            {
                notes.add(currentNote);
                currentNote = new Note();
            }
        }
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sortByDateOfCreation();
    }

    public ArrayList<Note> findByTheme(String theme)
    {
        ArrayList<Note> findNotes = new ArrayList<>();
        for (Note note : notes)
        {
            if (theme.equals(note.getTheme()))
            {
                findNotes.add(note);
            }
        }
        findNotes.trimToSize();
        return findNotes;
    }

    public ArrayList<Note> findByDate(String date)
    {
        ArrayList<Note> findNotes = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        for (Note note : notes)
        {
            if (date.equals(dateFormat.format(note.getDateOfCreation().getTime())))
            {
                findNotes.add(note);
            }
        }
        findNotes.trimToSize();
        return findNotes;
    }

    public ArrayList<Note> findByEmail(String email)
    {
        ArrayList<Note> findNotes = new ArrayList<>();
        for (Note note : notes)
        {
            if (email.equals(note.getEmail()))
            {
                findNotes.add(note);
            }
        }
        findNotes.trimToSize();
        return findNotes;
    }

    public ArrayList<Note> findByWord(String word)
    {
        ArrayList<Note> findNotes = new ArrayList<>();
        Pattern pattern = Pattern.compile("(^|\\s+|[,.!?\"(/]+|-)" + word.toLowerCase() + "(\\s+|[,.!?\")/]+|-|$)");
        for (Note note : notes)
        {
            Matcher matcher = pattern.matcher(note.getText().toLowerCase());
            if (matcher.find())
            {
                findNotes.add(note);
            }
        }
        findNotes.trimToSize();
        return findNotes;
    }

    public void sortByTheme()
    {
        Comparator<Note> themeComparator = new Comparator<Note>() {
            @Override
            public int compare(Note o1, Note o2) {
                return o1.getTheme().compareTo(o2.getTheme());
            }
        };
        notes.sort(themeComparator);
    }

    public void sortByDate()
    {
        Comparator<Note> dateComparator = new Comparator<Note>() {
            @Override
            public int compare(Note o1, Note o2) {
                return o1.getDateOfCreation().compareTo(o2.getDateOfCreation());
            }
        };
        notes.sort(dateComparator);
    }

    public void sortByEmail()
    {
        Comparator<Note> emailComparator = new Comparator<Note>() {
            @Override
            public int compare(Note o1, Note o2) {
                return o1.getEmail().compareTo(o2.getEmail());
            }
        };
        notes.sort(emailComparator);
    }

    public static void printNotes(ArrayList<Note> notes)
    {
        int i = 1;
        for (Note note : notes)
        {
            System.out.print(i++ + ". ");
            note.printInfo();
            System.out.println();
        }
    }
}