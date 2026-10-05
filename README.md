# THE FINAL RECORD

by Moaz

## What does this do?
Have you ever been tired of tracking all your media consumption in many seperate apps?

Have you wished that there was just one place to store all my thoughts about many different mediums?

> **For Example:**
"I wish there were a place where I could keep track of the Harry Potter books I've read alongside all the movies I've watched from the franchise and also games I've played like Minecraft, with the ability to tag everything to easily sort and search my reviews by tag!"

*The Final Record is your solution.*

## Description of the structure of my program

The program follows a modular Object-Oriented structure written in Java:

* **`Client.java`**: Serves as the main entry point and driver. It manages program execution, handles persistent CSV file read/write operations (`data/data.csv`), and enforces input validation against reserved CSV formatting characters.
* **`TUI.java`**: A Text User Interface helper class that encapsulates console input parsing and validation for integers, floats, booleans, and text.
* **`Review.java`**: Represents individual review entities containing fields for title, date, rating, description, and raw tags. It contains methods for rendering formatted star ratings and generating string representations.
* **`ReviewReader.java`**: Responsible for parsing stored reviews from the CSV data store, implementing page viewing ("tabs" displaying 5 reviews per page), and executing search and sorting logic (by score, date, or tag associations).
* **`Tags.java`**: Handles tag parsing, extracting raw hashtag strings (e.g., `#Book#Fantasy`) into structured tag lists.
* **Unit Tests (`FileIOTest.java`, `ReviewTest.java`, `TagsTest.java`)**: Provide comprehensive testing coverage for CSV validation, star-rating conversions, edge-case ratings, and tag parsing.

## Instructions to run

* Ensure Java Development Kit (JDK) 8 or higher is installed on your system.
* Open your command line terminal or console.
* Navigate to the root directory containing the source code files.
* Ensure a directory named `data` exists in the root directory (or create one using `mkdir data`).
* Compile all Java source files by running:
  `javac *.java`
* Run the application by executing:
  `java Client`
* Follow the on-screen menu prompts to either log a new review or search, filter, and view existing records.