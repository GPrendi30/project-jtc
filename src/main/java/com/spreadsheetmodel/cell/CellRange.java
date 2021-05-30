package com.spreadsheetmodel.cell;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.sheet.Sheet;
import com.spreadsheetview.tui.SpreadsheetTui;

public class CellRange {

    private final Cell start;
    private final Cell end;
    private final String[][] content;
    private final int sizeX;
    private final int sizeY;

    /**
     * Creates a new CellRange object.
     * @param sh a Sheet.
     * @param start the starting cell.
     * @param end the ending cell.
     */
    public CellRange(final Sheet sh,final Cell start, final Cell end) {
        this.start = start;
        this.end = end;
        content = calcContent(sh);
        final int startX = start.getLocation().getRow();
        final int startY = start.getLocation().getIntColumn();

        final int endX = end.getLocation().getRow();
        final int endY = end.getLocation().getIntColumn();
        sizeX = (endX - startX) + 1;
        sizeY = (endY - startY) + 1;
    }

    private String[][] calcContent(Sheet sh) {
        final int startX = start.getLocation().getRow();
        final int startY = start.getLocation().getIntColumn();

        final int endX = end.getLocation().getRow();
        final int endY = end.getLocation().getIntColumn();
        System.out.println(startX + " " + endX);
        System.out.println(startY + " " + endY);
        String[][] tempData = new String[endX - startX + 1][endY - startY + 1];
        int i = 0;
        int j = 0;
        for (int x = startX;  x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {

                tempData[i][j] = sh.getCell(x,y).getText();
                System.out.print(tempData[i][j] + " ");
                j++;
            }
            System.out.println();
            i++;
            j = 0;
        }
        return tempData;
    }

    /**
     * The main method.
     * @param args a String[].
     */
    public static void main(String[] args ) {
        Spreadsheet s = new Spreadsheet();
        SpreadsheetTui t = new SpreadsheetTui(s);
        s.importCsv("/home/geri/Desktop/test_folder/a.csv");
        t.updateView();
        s.selectCell("B2");
        Cell b2 = s.getCurrentCell();
        s.selectCell("C4");
        Cell c4 = s.getCurrentCell();
        CellRange r = new CellRange(s.getCurrentSheet(), b2, c4);

        s.selectCell("G13");
        r.copyDataTo(s.getCurrentSheet(), s.getCurrentCell());
        t.updateView();

    }

    /**
     * Copies data from one place to another.
     * @param sh a Sheet.
     * @param target the target cell.
     */
    public void copyDataTo(final Sheet sh, final Cell target) {
        int startX = target.getLocation().getRow();
        int startY = target.getLocation().getIntColumn();

        int i = 0;
        int j = 0;
        for (int x = startX; x < startX + sizeX; x++) {
            for (int y = startY; y < startY + sizeY; y++) {
                sh.update(x,y, this.content[i][j]);
                j++;
            }
            i++;
            j = 0;
        }
    }

<<<<<<< HEAD
    public void copyDataTo(final Sheet sh, final Cell targetStart, final Cell targetEnd) throws Exception {
=======
    /**
     * Copies data from one cell to another.
     * @param sh a Sheet.
     * @param targetStart the cell where to start.
     * @param targetEnd the the cell where to end.
     */
    public void copyDataTo(final Sheet sh, final Cell targetStart, final Cell targetEnd) {
>>>>>>> 86467ca01ca6903f7d3cf0aa01bc84ebded9f38d
        int startX = targetStart.getLocation().getRow();
        int startY = targetStart.getLocation().getIntColumn();

        int endX = targetEnd.getLocation().getRow();
        int endY = targetEnd.getLocation().getIntColumn();

<<<<<<< HEAD
        if ((endX-startX) + 1 != sizeX || (endY - startY) + 1 != sizeY) {
            throw new Exception("Ranges should have the same dimension");
=======
        if ((endX - startX) + 1 != sizeX || (endY - startY) + 1 != sizeY) {

            // TODO correct this empty block

>>>>>>> 86467ca01ca6903f7d3cf0aa01bc84ebded9f38d
        }

        int i = 0;
        int j = 0;
        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                sh.update(x,y, this.content[i][j]);
                j++;
            }
            i++;
            j = 0;
        }
    }

}
