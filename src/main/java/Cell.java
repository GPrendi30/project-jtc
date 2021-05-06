

public class Cell {

    private final int xCordinate;
    private final int yCordinate;
    private String content;
    private String type;
    
    public Cell(final int xCordinate,final int yCordinate) {
        this.xCordinate = xCordinate;
        this.yCordinate = yCordinate;
    }

    public void updateContent(final String content) {
        this.content = content;
    }

    public String show() {
        return this.content;
    }

    public int getX() {
        return xCordinate;
    }

    public int getY() {
        return yCordinate;
    }


}
