# THE FINAL RECORD

by Moaz

## What does this do?
Have you ever been tired of tracking all your media consumption in many seperate apps?

Have you wished that there was just one place to store all my thoughts about many different mediums?

> **For Example:**
"I wish there were a place where I could keep track of the Harry Potter books I've read alongside all the movies I've watched from the franchise and also games I've played like Minecraft, with the ability to tag everything to easily sort and search my reviews!"

*The Final Record is your solution.*

## Description of the structure of my program

* **`Client.java`**: Serves as the main entry point and driver. It manages program execution, handles CSV file read/write operations (in `data/data.csv`), and validates input to avoid using reserved CSV formatting characters.
* **`TUI.java`**: A Text User Interface helper class that reads console input, by parsing and validation for integers, floats, booleans, and text.
* **`Review.java`**: Represents individual review entries containing fields for title, date, rating, description, and raw tags. It contains methods for printing formatted star ratings and generating string representations.
* **`ReviewReader.java`**: Responsible strictly for page viewing ("tabs" displaying 5 reviews per page), and executing search and sorting logic (by score, date, or tag associations).
* **`ReviewRepository.java`**: Responsible for parsing stored reviews from the CSV data as well as involved in deleting review data when requested.
* **`Tags.java`**: Handles tag parsing, extracting raw hashtag strings (e.g., `#Book#Fantasy`) into structured tag lists.
* **`Unit Tests`**: (`FileIOTest.java`, `ReviewTest.java`, `TagsTest.java`): Providing wide testing coverage for CSV validation, star-rating conversions, edge-case ratings, and tag parsing.

## Instructions to run

* Ensure Java Development Kit (JDK) 8 or higher is installed on your system and is available in the PATH variables of your system.
  run `javac -version` in your command prompt or terminal to verify if you have it on your system. If not you can install it at: https://adoptium.net/temurin/releases/?version=8
* Navigate to the root directory containing the source code files.
* Ensure a directory named `data` exists in the root directory (or create a folder with the same name).
* This application combines the javac/java compile and run command process:
  
  On Windows open the `run.bat` file in the project directory

  On Linux/Mac:
  
  Open your command line terminal or console then
  Navigate to the root directory containing the source code files using `cd (PathToProjectSource)`. then `ls` to verify the contents

  Ensure `run.sh` exists
  
  Run `chmod +x run.sh`

  Then run `./run.sh` to run the application