public class Spreadsheet {

    private int xDim;
    private int yDim;
    private Grid table;
    
    public Spreadsheet(int xDim, int yDim) {

        this.xDim = xDim;
        this.yDim = yDim;
        table = new Grid(xDim, yDim);
        compileSpreadsheet();
    }

    public static void main(String[] args) {

        Spreadsheet st = new Spreadsheet(4, 4);
        st.printSpredsheet();
    }

    public void compileSpreadsheet() {
        for (int x = 0; x <= xDim; x++) {
            for (int y = 0; y <= yDim; y++) {
                if (x == 0 & y==0) {
                    Cell c = new Cell(0, 0);
                    c.updateContent("");
                    table.put(c);
                }
                else if (x == 0) {
                    table.put(new HeaderCell(x, y));
                } else if (y == 0) {
                    table.put(new HeaderCell(x, y));
                } else {
                    table.put(new Cell(x, y));
                }
            }
        }
    }

    public void printSpredsheet() {
        for (int x = 0; x <= xDim; x++) {
            System.out.println("_____________________________");
            for (int y = 0; y <= xDim; y++) {
                Cell c = table.get(x, y);
                System.out.print(" | " + c.show());
            }
            System.out.println();
        }
    }
}
