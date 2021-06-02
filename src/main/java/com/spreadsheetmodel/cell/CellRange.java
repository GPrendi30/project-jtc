package com.spreadsheetmodel.cell;

import com.spreadsheetmodel.SpreadsheetException;
import com.spreadsheetmodel.sheet.Sheet;


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

    private String[][] calcContent(final Sheet sh) {
        final int startX = start.getLocation().getRow();
        final int startY = start.getLocation().getIntColumn();

        final int endX = end.getLocation().getRow();
        final int endY = end.getLocation().getIntColumn();

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
     * Copies data from one place to another.
     * @param sh a Sheet.
     * @param target the target cell.
     * @throws SpreadsheetException a general exception.
     */
    public void copyDataTo(final Sheet sh, final Cell target) throws SpreadsheetException {
        final int startX = target.getLocation().getRow();
        final int startY = target.getLocation().getIntColumn();
        final Cell targetY = sh.getCell(startX + sizeX - 1, startY + sizeY - 1);
        copyDataTo(sh, target, targetY);
    }


    /**
     * Copies data from one cell to another.
     * @param sh a Sheet.
     * @param targetStart the cell where to start.
     * @param targetEnd the the cell where to end.
     * @throws SpreadsheetException throws exception if ranges dont match in dimension.
     */
    public void copyDataTo(final Sheet sh,
                           final Cell targetStart,
                           final Cell targetEnd)
            throws SpreadsheetException {

        final int startX = targetStart.getLocation().getRow();
        final int startY = targetStart.getLocation().getIntColumn();

        final int endX = targetEnd.getLocation().getRow();
        final int endY = targetEnd.getLocation().getIntColumn();


        if ((endX - startX) + 1 != sizeX || (endY - startY) + 1 != sizeY) {
            throw new SpreadsheetException("Ranges should have the same dimension");

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
