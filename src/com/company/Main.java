package com.company;

import com.company.DAO.LibraryBooksDAO;
import com.company.DAO.UserDAO;
import com.company.Entity.LibraryBooks;
import com.company.Utils.HibernateUtil;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String loginOrReg;
        String logUsername;
        String logPassword;
        String regUsername;
        String regPassword;
        UserDAO userDAO = new UserDAO();
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("What you would like to do?:\n" +
                    "1. For login.\n" +
                    "2. For register.\n" +
                    "Please select a number for operation:");
            loginOrReg = scan.next();
            if (loginOrReg.contains("1")) {
                System.out.println("Enter your username:");
                logUsername = scan.next();
                System.out.println("Enter your password:");
                logPassword = scan.next();
                if (userDAO.login(logUsername, logPassword)) {
                    if (userDAO.getUserRole(logUsername).equals("user")){
                        userFunc();
                    }
                    else {
                        adminFunc();
                    }
                }
            } else if (loginOrReg.contains("2")) {
                System.out.println("Create your username:");
                regUsername = scan.next();
                System.out.println("Create your password:");
                regPassword = scan.next();
                userDAO.registration(regUsername, regPassword);
            }
            HibernateUtil.getSessionFactory().close();
            break;
        }
    }

    public static void adminFunc(){
        while (true) {
            LibraryBooksDAO libraryBooksDAO = new LibraryBooksDAO();
            Scanner scan = new Scanner(System.in);
            String chooseOpAdmin;
            String titleBook;
            String summaryBook;
            int pagesInBook;
            int bookDelete;
            int bookUpdate;
            String newTitle;
            String newSummary;
            int newPages;
            System.out.println("Welcome! Which operation you would like to execute?\n" +
                    "1. For adding a new book.\n" +
                    "2. For deleting a book.\n" +
                    "3. For editing a book.\n" +
                    "4. For log out." +
                    "Please enter the number of the option:");
            chooseOpAdmin = scan.next();
            if (chooseOpAdmin.contains("1")) {
                System.out.println("Title of the book?:");
                scan.nextLine();
                titleBook = scan.nextLine();
                System.out.println("Summary of the book?:");
                summaryBook = scan.nextLine();
                System.out.println("Pages in book?:");
                pagesInBook = scan.nextInt();
                System.out.println("Adding the book!");
                LibraryBooks newBook = new LibraryBooks(titleBook, summaryBook, pagesInBook);
                libraryBooksDAO.addBook(newBook);
            } else if (chooseOpAdmin.contains("2")) {
                libraryBooksDAO.allAvailableBooks();
                System.out.println("\nBooks currently reserved are not shown!\n");
                System.out.println("Enter the ID of the book to delete:");
                bookDelete = scan.nextInt();
                libraryBooksDAO.deleteBookByID(bookDelete);
            } else if (chooseOpAdmin.contains("3")) {
                libraryBooksDAO.allAvailableBooks();
                System.out.println("\nBooks currently reserved are not shown!\n");
                System.out.println("Enter id of the book to edit:\n");
                bookUpdate = scan.nextInt();
                System.out.println("Enter new title of the book:\n");
                scan.nextLine();
                newTitle = scan.nextLine();
                System.out.println("Enter new summary of the book:\n");
                newSummary = scan.nextLine();
                System.out.println("Enter new pages of the book:\n");
                newPages = scan.nextInt();
                libraryBooksDAO.editBook(bookUpdate, newTitle, newSummary, newPages);
                System.out.println("Book updated!");
            }
            else {
                break;
            }
        }
    }
    public static void userFunc() {
        while (true) {
            LibraryBooksDAO libraryBooksDAO = new LibraryBooksDAO();
            Scanner scan = new Scanner(System.in);
            int pickBook;
            String chooseOpUser;
            libraryBooksDAO.allAvailableBooks();
            System.out.println("\nPlease select the operation by number:\n" +
                    "1. For searching for books.\n" +
                    "2. For log out.\n");
            chooseOpUser = scan.next();
            if (chooseOpUser.equals("1")) {
                pickBook = scan.nextInt();
                libraryBooksDAO.bookReservation(pickBook);
                System.out.println("Book reserved!");
            }
            else {
                break;
            }
        }
    }
}