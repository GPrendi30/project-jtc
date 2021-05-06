public class Grid {
    
    private Cell[][] grid;
    
    public Grid(int xDim, int yDim){
        grid = new Cell[xDim+1][yDim+1];
    }
    public static void main(String[] args) {

        //Grid g = new Grid(4,4);
        //Cell c = new HeaderCell(3, 0);
        //g.put(c);
        //System.out.println(g.get(3,0).show());
    }
    public void put(Cell c) {
        int x = c.getX();
        int y = c.getY();
        grid[x][y] = c;
    }

    public Cell get(int x, int y) {
        return grid[x][y];
    }


}