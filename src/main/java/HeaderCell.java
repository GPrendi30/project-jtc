public class HeaderCell extends Cell{
    
    private String alpha = "ABCDEFGHIJKLMNOPQR";

    public HeaderCell(final int xCoord, final int yCoord) {
        super(xCoord, yCoord);
        if (xCoord == 0 && yCoord != 0)
            updateContent(alpha.substring(yCoord - 1, yCoord));
        if (yCoord == 0 && xCoord != 0)
            updateContent("" + xCoord);
    }

    public static void main(String args[]) {
        Cell c = new HeaderCell(3, 0);
        System.out.println(c.show());
    }
}