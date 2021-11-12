package phonebook;

import java.util.Objects;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    final static String NAME_PATH = "C:\\Users\\Jeremiah\\Downloads\\find.txt";
    final static String PHONEBOOK_PATH = "C:\\Users\\Jeremiah\\Downloads\\directory.txt";

    // Store names files in memory
    private static String[] namesList(File file) {
        int count = 0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                count++;
            }
            String[] names = new String[count];
            Scanner sc = new Scanner(file);
            for (int i = 0; i < count; i++) {
                names[i] = sc.nextLine();
            }
            return names;
        } catch (FileNotFoundException e) {
            System.out.printf("File not found: %s", NAME_PATH);
            return null;
        }
    }

    // Store phonebook in memory
    private static String[] phoneBookList(File file) {
        int count = 0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                count++;
            }
            String[] phoneBook = new String[count];
            Scanner sc = new Scanner(file);
            for (int i = 0; i < count; i++) {
                phoneBook[i] = sc.nextLine();
            }
            return phoneBook;
        } catch (FileNotFoundException e) {
            System.out.printf("File not found: %s", PHONEBOOK_PATH);
            return null;
        }
    }

    // Method to calculate the time difference for how long it takes to search in ms.
    private static String timeDifference(long timeStart, long timeEnd) {
        long difference = timeEnd - timeStart;
        long minutes = (difference / 1000) / 60;
        long seconds = (difference / 1000) % 60;
        long milliseconds = difference - ((minutes * 60000) + (seconds * 1000));
        return "Time taken: " + minutes + " min. " + seconds + " sec. " +
                milliseconds + " ms.";
    }

    public static void main(String[] args) {
        File findFile = new File(NAME_PATH);
        File directoryFile = new File(PHONEBOOK_PATH);
        String[] names = namesList(findFile);
        String[] phoneBook = phoneBookList(directoryFile);
        int count = 0;
        System.out.println("Start searching...");
        long timeStart = System.currentTimeMillis();
        // Begin linear search
        for (String name : Objects.requireNonNull(names)) {
            for (String nameAndNumber : Objects.requireNonNull(phoneBook)) {
                if (nameAndNumber.contains(name)) {
                    count++;
                    break;
                }
            }
        }
        long timeEnd = System.currentTimeMillis();
        System.out.print("Found " + count + " / " + names.length + " entries. " +
                timeDifference(timeStart, timeEnd));
    }
}
