<!-- -->
# JTC
## Java Tabular Calculator

*Gerald Prendi*  
*Di Pietro Enrico*

### Idea
Our project is to build a Spreadsheet capable of having a table of modifiable cells where you can evaluate formulas and expressions. We would like to introduce a GUI and a CLI.

### Milestone
* [] Implement the labs
* [] Have a table with cells
* [] Complete the Model
* [] Have a working CLI
* [] Have a working GUI
* [] _Add new features_
  * [] repl
  * [] CSV with formulas

### Architecture
MVC (Model View Controller)
We will try to divide the logic of the program from the graphical interface as described in the MVC.  
<img src="https://upload.wikimedia.org/wikipedia/commons/a/a0/MVC-Process.svg" alt="MVC" width="250"/>

### Design patterns  
##### _Factory pattern_  
We try to separate the logic from what the user sees so that everything is independent and easily improvable. The user follows a pattern (that we decide) to interact with the program.

<!-- _Singleton_  -->

##### _Observer pattern_  
We'll use the observer pattern for the GUI.


### Git branches
##### _main_
_main_ is the working branch were everything is correct, tested and cleaned up.

##### _dev_
_dev_ is the development branch were we work on adding new stuff.

##### _issue_
_issue_ is a temporary branch that we create to fix an issue. When the issue is fixed this branch is merged to _main_.

 ### Coding conventions
 We are going to develop our project following the [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html).

 ### Maven build tool
 We use _maven_ to check for the test coverage, code correctness and code maturity.

 ### TODO
 How to run guide.
