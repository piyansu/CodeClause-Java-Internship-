-- Create the 'library' database
CREATE DATABASE library;

-- Switch to the 'library' database
USE library;

-- Drop the 'books' table if it exists
DROP TABLE books;

-- Insert data into the 'books' table
INSERT INTO books VALUES
(111, 'Clean Code: A Handbook of Agile Software Craftsmanship', 'Robert C. Martin', 1),
(112, 'Data Smart: Using Data Science to Transform Information into Insight', 'John W. Foreman', 1),
(113, 'The Art of Computer Programming', 'Donald E. Knuth', 1),
(114, 'Code Complete: A Practical Handbook of Software Construction', 'Steve McConnell', 1),
(115, 'Introduction to Algorithms', 'Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, Clifford Stein', 1),
(116, 'Refactoring: Improving the Design of Existing Code', 'Martin Fowler', 1),
(117, 'The Pragmatic Programmer: Your Journey to Mastery', 'Andrew Hunt, David Thomas', 1),
(118, 'Design Patterns: Elements of Reusable Object-Oriented Software', 'Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides', 1);

-- Select all records from the 'books' table
SELECT * FROM books;

-- Drop the 'students' table if it exists
DROP TABLE students;

-- Insert data into the 'students' table
INSERT INTO students VALUES
(6110, 'Lionel Messi', 1),
(6111, 'Cristiano Ronaldo', 2),
(6120, 'Neymar Jr.', 2),
(6123, 'Kylian Mbappe', 0),
(6124, 'Mohamed Salah', 1),
(6139, 'Robert Lewandowski', 0);

-- Select all records from the 'students' table
SELECT * FROM students;
