# Medical Record System

This medical record system is a Java program that shows interesting statistics on doctors, patients, and treatments.

## Description

This program is intended to show a user-friendly Java interface that uses an sqlite database to provide statistics of a medical record system. Not all tables in the database are able to be modified by the program. Instead, this program is intended to show how interesting queries can be executed and displayed by the system. The program provides one set of add, delete, and update queries. More information on the program construction and functionalities can be found in the Final Document in this repo.

## Dependencies

This program requires the JavaFX module. This module will be references in the command-line arguments to run the program.

## Usage

Run this program using the command

```bash
java -cp "bin;sqlite-jdbc-3.30.1.jar" --module-path=<path to JavaFX lib> --add-modules=javafx.controls gui.MedicalRecordGUI
```
in the root directory.
