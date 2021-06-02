package com.spreadsheetmodel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class SpreadsheetIO {

    /**
     * Dummy abstract method.
     */
    public abstract void spreadsheetEngine();

    /**
     * Writes a Spreadsheet object to a file.
     * @param path a String path
     * @param s a SpreadSheet object.
     * @throws IOException in case the file doesn't exist.
     */
    public static void writeToFile(final String path,final Spreadsheet s) throws IOException  {

        final Path targetPath = Paths.get(path);

        final ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(targetPath.toString()));

        oos. writeObject(s);

    }

    /**
     * Reads from a file, and returns a Spreadsheet object.
     * @param path the String path to a jtc file.
     * @return a Spreadsheet.
     * @throws IOException throws an error in case it
     * @throws SpreadsheetException if it can't deserialize a spreadsheet.
     */
    public static Spreadsheet readFromFile(final String path)
            throws IOException, SpreadsheetException {
        final Path inputPath = Paths.get(path);

        final ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(inputPath.toString()));


        Spreadsheet deserializedSpreadsheet = null;

        try {
            deserializedSpreadsheet = (Spreadsheet) ois.readObject();
        } catch (ClassNotFoundException exception) {
            throw new SpreadsheetException("Spreadsheet at the given path "
                    + path + "cannot be imported", exception);
        }
        return deserializedSpreadsheet;
    }

}
