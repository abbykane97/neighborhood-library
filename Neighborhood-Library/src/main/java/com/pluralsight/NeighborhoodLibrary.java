package com.pluralsight;

import java.util.Scanner;

public class NeighborhoodLibrary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Book[] books = new Book[5];

        books[0] = new Book(626, "9781982185824", "I'm Glad My Mom Died");
        books[1] = new Book(777, "9781603094672", "Kodi");
        books[2] = new Book(999,"9781603094900", "Rivers");
        books[3] = new Book(420, "9781603094368", "Lost Girls");
        books[4] = new Book(888, "9781603094924", "The Delicacy");


        System.out.println(" ");

        System.out.println( "Welcome to the Library!");




        boolean running = true;
        while (running) {

            System.out.println("\nSelect an option: ");
            System.out.println("1. Show available books");
            System.out.println("2. Show checked out books");
            System.out.println("3. Exit\n");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1: showCheckedInBooks(books, scanner);
                break;

                case 2: showCheckedOutBooks(books, scanner);
                break;

                case 3:
                    running = false;
                    System.out.println("bye!");
                    return;
                default:

                    System.out.println("Invalid. Try again.");
            }


        }

    }

    public static void showCheckedInBooks(Book[] books, Scanner scanner) {
        System.out.println("Checked In Books:\n");
        for (Book book : books) {
            if (!book.isCheckedOut()) {
                System.out.println(book.getId() + ": " + book.getTitle() + " (ISBN: " + book.getIsbn() + ")");
            }
        }

        System.out.println(" ");
        System.out.println("If you would like to check out a book enter the ID of the book (or enter 0 to return to the home screen):");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        if (bookId == 0) {
            return;
        }

        checkOutBook(books,bookId, scanner);

            }
    public static void checkOutBook(Book[] books, int bookId, Scanner scanner) {
        for (Book book : books) {
            if (book.getId() == bookId && !book.isCheckedOut()) {
                System.out.println("Enter your name: ");
                String name = scanner.nextLine();
                book.checkOut(name);
                return;
            }
        }

        System.out.println("Invalid book ID or book is already checked out.");

    }

    public static void showCheckedOutBooks(Book[] books, Scanner scanner) {
        System.out.println("Checked Out Books:\n");
        for (Book book : books) {
            if (book.isCheckedOut()) {
                System.out.println("ID: " + book.getId() + ", ISBN: " + book.getIsbn() + ", Title: " + book.getTitle() + ", Checked Out By: " + book.getIsCheckedOutTo());
            }
        }
        System.out.println("\nEnter 'C' to check in a book or 'X' to return to the home screen");

        String choice = scanner.nextLine().toUpperCase();

        switch (choice){
            case "C":
                checkInBook(books, scanner);
                break;

            case "X":
                return;
            default:
                System.out.println("Invalid option. Try again.");
        }

    }

    public static void checkInBook(Book[] books, Scanner scanner){
        System.out.println("Enter the ID of the book you would like to return:");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        for (Book book : books) {
            if (book.getId() == bookId && book.isCheckedOut()) {
                book.checkIn();
                return;
            }
        }
        System.out.println("Invalid ID or book is already checked in.");
    }
}