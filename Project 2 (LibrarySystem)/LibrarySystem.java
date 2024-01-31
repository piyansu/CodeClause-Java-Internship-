package normal_project;

import java.sql.*;
import java.util.Scanner;

public class LibrarySystem {

    static int studentId;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/library";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Piyansu@2002";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean isStudent = false;
        boolean isAdmin = false;

        // Connect to the database
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            System.out.println("Connected to the library database");
            Statement statement = connection.createStatement();

            // Create the Books table if it does not exist
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Books (id INT PRIMARY KEY, title VARCHAR(1000), author VARCHAR(500), is_available BOOLEAN)");

            // Create the Students table if it does not exist
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Students (id INT PRIMARY KEY, name VARCHAR(50), books_issued INT)");

            while (true) {
                if (!isStudent && !isAdmin) {
                    System.out.println("\n** Library System Menu **");
                    System.out.println("1. Admin Login");
                    System.out.println("2. Student Login");
                    System.out.println("3. Exit");
                    System.out.print("Enter Your Choice: ");
                    choice = scanner.nextInt();
                    System.out.println("");
                    scanner.nextLine();
                    switch (choice) {
                        case 1:
                            System.out.print("Enter Admin Password: ");
                            String adminPassword = scanner.nextLine();
                            if (adminPassword.equals("1234")) {
                                isAdmin = true;
                                System.out.println("Admin login successful");
                            } else {
                                System.out.println("Incorrect password. Access denied.");
                                System.out.println("");
                            }
                            break;
                        case 2:
                            System.out.print("Enter Student ID: ");
                            studentId = scanner.nextInt();
                            scanner.nextLine();
                            ResultSet studentResultSet = statement.executeQuery("SELECT * FROM Students WHERE id = " + studentId);
                            if (studentResultSet.next()) {
                                isStudent = true;
                                System.out.println("Student login successful");
                                System.out.println("");
                            } else {
                                System.out.print("Student not found. Access denied.");
                                System.out.println("");
                            }
                            break;
                        case 3:
                            System.out.println("Exiting the Library System. Goodbye!");
                            return;
                        default:
                            System.out.println("Invalid choice. Please enter a valid option.");
                    }
                } else if (isStudent) {
                    displayStudentMenu();
                    choice = scanner.nextInt();
                    System.out.println("");
                    switch (choice) {
                        case 1:
                            borrowBook(statement, scanner, studentId);
                            break;
                        case 2:
                            returnBook(statement, scanner, studentId);
                            break;
                        case 3:
                            isStudent = false;
                            System.out.println("Logged out successfully.");
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter a valid option.");
                    }
                } else if (isAdmin) {
                    displayAdminMenu();
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    switch (choice) {
                        case 1:
                            addBook(statement, scanner);
                            break;
                        case 2:
                            removeBook(statement, scanner);
                            break;
                        case 3:
                            displayAvailableBooks(statement);
                            break;
                        case 4:
                            isAdmin = false;
                            System.out.println("Logged out successfully.");
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter a valid option.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Display Student Menu
    private static void displayStudentMenu() {
        System.out.println("** Student Menu **");
        System.out.println("1. Borrow a Book");
        System.out.println("2. Return a Book");
        System.out.println("3. Logout");
        System.out.print("Enter Your Choice: ");
    }

    // Display Admin Menu
    private static void displayAdminMenu() {
        System.out.println("\n** Admin Menu **");
        System.out.println("1. Add a Book");
        System.out.println("2. Remove a Book");
        System.out.println("3. Display Available Books");
        System.out.println("4. Logout");
        System.out.print("Enter Your Choice: ");
    }

    // Borrow a Book
    private static void borrowBook(Statement statement, Scanner scanner, int studentId) throws SQLException {
        ResultSet studentResultSet = statement.executeQuery("SELECT * FROM Students WHERE id = " + studentId);
        
        if (studentResultSet.next()) {
            String studentName = studentResultSet.getString("name");
            int booksIssued = studentResultSet.getInt("books_issued");
            
            System.out.println("Student ID: " + studentId + ", Student Name: " + studentName + ", Books Issued: " + booksIssued);

            if (booksIssued >= 2) {
                // Student has already borrowed the maximum allowed number of books
                System.out.println("Sorry, you have already borrowed the maximum number of books (2). You cannot borrow more.");
                System.out.println("");
            } else {
                System.out.print("Enter Book ID to Borrow: ");
                int bookId = scanner.nextInt();
                scanner.nextLine();
                ResultSet bookResultSet = statement.executeQuery("SELECT * FROM Books WHERE id = " + bookId);
                
                if (bookResultSet.next() && bookResultSet.getBoolean("is_available")) {
                    statement.executeUpdate("UPDATE Books SET is_available = false WHERE id = " + bookId);
                    statement.executeUpdate("UPDATE Students SET books_issued = books_issued + 1 WHERE id = " + studentId);
                    System.out.println("Book borrowed successfully.");
                    System.out.println("");
                } else {
                    System.out.println("");
                    System.out.println("Book not available for borrowing.");
                }
            }
        } else {
            System.out.println("Student not found.");
            System.out.println("");
        }
    }


    // Return a Book
    private static void returnBook(Statement statement, Scanner scanner, int studentId) throws SQLException {
        ResultSet studentResultSet = statement.executeQuery("SELECT * FROM Students WHERE id = " + studentId);
        
        if (studentResultSet.next()) {
            String studentName = studentResultSet.getString("name");
            int booksIssued = studentResultSet.getInt("books_issued");
            
            System.out.println("Student ID: " + studentId + ", Student Name: " + studentName + ", Books Issued: " + booksIssued);

            if (booksIssued > 0) {
                System.out.print("Enter Book ID to Return: ");
                int returnBookId = scanner.nextInt();
                scanner.nextLine();
                ResultSet returnBookResultSet = statement.executeQuery("SELECT * FROM Books WHERE id = " + returnBookId);

                if (returnBookResultSet.next() && !returnBookResultSet.getBoolean("is_available")) {
                    statement.executeUpdate("UPDATE Books SET is_available = true WHERE id = " + returnBookId);
                    statement.executeUpdate("UPDATE Students SET books_issued = books_issued - 1 WHERE id = " + studentId);
                    System.out.println("Book returned successfully.");
                    System.out.println("");
                } else {
                    System.out.println("Invalid Book ID or the book is not issued to this student.");
                    System.out.println("");
                }
            } else {
                System.out.println("No books issued. Nothing to return.");
                System.out.println("");
            }
        } else {
            System.out.println("Student not found.");
            System.out.println("");
        }
    }


    // Add a Book
    private static void addBook(Statement statement, Scanner scanner) throws SQLException {
        System.out.println("");
        System.out.print("Enter Book ID: ");
        int addBookId = scanner.nextInt();
        scanner.nextLine();

        // Check if the book with the given ID already exists
        ResultSet existingBookResultSet = statement.executeQuery("SELECT * FROM Books WHERE id = " + addBookId);
        if (existingBookResultSet.next()) {
            // Book with the given ID already exists
            System.out.println("A book with ID " + addBookId + " already exists in the database.");
        } else {
            // Book with the given ID does not exist, proceed with adding the new book
            System.out.print("Enter Book Title: ");
            String addBookTitle = scanner.nextLine();
            System.out.print("Enter Book Author: ");
            String addBookAuthor = scanner.nextLine();
            statement.executeUpdate("INSERT INTO Books (id, title, author, is_available) VALUES (" + addBookId + ", '" + addBookTitle + "', '" + addBookAuthor + "', true)");
            System.out.println("");
            System.out.println("!!!!!Book added successfully.!!!!!");
        }
    }

    // Remove a Book
    private static void removeBook(Statement statement, Scanner scanner) throws SQLException {
        System.out.println("");
        System.out.print("Enter Book ID to Remove: ");
        int removeBookId = scanner.nextInt();
        scanner.nextLine();

        // Check if the book with the given ID exists
        ResultSet bookResultSet = statement.executeQuery("SELECT * FROM Books WHERE id = " + removeBookId);
        if (bookResultSet.next()) {
            // Book exists, proceed with deletion
            statement.executeUpdate("DELETE FROM Books WHERE id = " + removeBookId);
            System.out.println("");
            System.out.println("!!!!Book removed successfully.!!!!");
        } else {
            // Book not found
            System.out.println("");
            System.out.println("Book with ID " + removeBookId + " not found in the database.");
        }
    }

    // Display Available Books
    private static void displayAvailableBooks(Statement statement) throws SQLException {
        System.out.println("\n** Available Books **");
        ResultSet availableBooksResultSet = statement.executeQuery("SELECT * FROM Books WHERE is_available = true");
        while (availableBooksResultSet.next()) {
            System.out.println("Book ID: " + availableBooksResultSet.getInt("id") +
                    ", Title: " + availableBooksResultSet.getString("title") +
                    ", Author: " + availableBooksResultSet.getString("author"));
        }
    }
}
