package com.company.Utils;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputUtils {

    public static String inputNotEmptyStr(String str, Scanner scanner)
    {
        // Метод для ввода не пустой строки из консоли.
        System.out.print(str);
        String text = scanner.nextLine().trim();
        if (text.equals(""))
        {
            System.out.println("The string must not be empty.");
            return inputNotEmptyStr(str, scanner);
        }
        return text;
    }

    public static String inputStr(String str, Scanner scanner)
    {
        // Метод для ввода строки из консоли.
        System.out.print(str);
        String text = scanner.nextLine().trim();
        return text;
    }

    public static String inputWord(String str, Scanner scanner)
    {
        // Метод для ввода слова из консоли.
        System.out.print(str);
        String text = scanner.nextLine().trim();
        Pattern pattern = Pattern.compile("^\\w+$");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find())
        {
            return text;
        }
        System.out.println("Entered string must be word.");
        return inputWord(str, scanner);
    }

    public static String inputDate(String str, Scanner scanner)
    {
        // Метод для ввода даты из консоли в формате dd.mm.yyyy.
        Pattern pattern = Pattern.compile("^(\\d{2})\\.(\\d{2})\\.(\\d{4})$");
        System.out.print(str);
        String text = scanner.nextLine();
        Matcher matcher = pattern.matcher(text);
        if (matcher.find())
        {
            return text;
        }
        System.out.println("Input date incorrect format.");
        return inputDate(str, scanner);
    }

    public static String inputEmailAddress(String str, Scanner scanner)
    {
        // Метод для ввода email адреса из консоли.
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_\\-]+(\\.[a-zA-Z0-9_\\-]+)*@[a-zA-Z0-9_\\-]+(\\.[a-zA-Z0-9_\\-]+)*(\\.[a-zA-Z]{2,})$");
        System.out.print(str);
        String text = scanner.nextLine();
        Matcher matcher = pattern.matcher(text);
        if (matcher.find())
        {
            return text;
        }
        System.out.println("Email address is incorrect.");
        return inputEmailAddress(str, scanner);
    }
}