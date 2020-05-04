import java.util.Arrays;

class Main {

    /*
    0= PATH
    1 = RIGHT PATH
    2 = BORDER
    */
    final static int PATH = 1;
    final static int BORDER = 2;


    
    private static int[][] GRID = { 
        { 0, 0, 0, 2, 0, 0, 0 },
        { 0, 0, 0, 2, 0, 0, 0 },
        { 0, 0, 0, 2, 2, 2, 0 },
        { 0, 0, 0, 0, 0, 2, 0 },
        { 0, 0, 0, 2, 0, 2, 0 },
        { 0, 0, 0, 2, 0, 0, 0 },
        { 0, 0, 0, 2, 0, 0, 0 },
        { 0, 0, 0, 2, 0, 0, 0 } 
    };

    public static void main(String[] args) {
        Main maze = new Main(GRID);
        boolean solved = maze.solve();
        System.out.println(maze.toString());
    }

    private int[][] grid;
    private int height;
    private int width;

    private int[][] map;

    public Main(int[][] grid) {
        this.grid = grid;
        this.height = grid.length;
        this.width = grid[0].length;

        this.map = new int[height][width];
    }

    public boolean solve() {
        traverse(7,0);
        map[7][0] = PATH;
        return true;
    }

    private boolean traverse(int i, int j) {
        if (!isValid(i,j)) {
            return false;
        }

          SetBorder();

        if ( isEnd(i, j) ) {
            map[i][j] = PATH;
            return true;
        } else {
            map[i][j] = 3;
        }


        
        // East
        if (traverse(i, j + 1)) {
            map[i][j + 1] = PATH;
            return true;
        }

        // North
        if (traverse(i - 1, j)) {
            map[i-1][j] = PATH;
            return true;
        }

        // South
        if (traverse(i + 1, j)) {
            map[i + 1][j] = PATH;
            return true;
        }
        // West
        if (traverse(i, j - 1)) {
            map[i][j - 1] = PATH;
            return true;
        }

        RemoveTried(i, j);
        

        return false;
    }

    private boolean isEnd(int i, int j) {
        return i == height - 1 && j == width - 1;
    }

    private boolean isValid(int i, int j) {
        if (inRange(i, j) && isOpen(i, j) && !isTried(i, j) && !isBorder(i, j)) {
            return true;
        }

        return false;
    }

    private boolean isOpen(int i, int j) {
        return grid[i][j] == 0;
    }

    private boolean isBorder(int i, int j) {
        return grid[i][j] == 2;
    }
    
        public boolean SetBorder() {
            map[0][3] = BORDER;
            map[1][3] = BORDER;
            map[2][3] = BORDER;
            map[2][4] = BORDER;
            map[2][5] = BORDER;
            map[3][5] = BORDER;
            map[4][5] = BORDER;
            map[7][3] = BORDER;
            map[6][3] = BORDER;
            map[5][3] = BORDER;
            map[4][3] = BORDER;
            return true;
        }
    
    public boolean RemoveTried(int i, int j){
      if (map[i][j] == 3){
        map[i][j] = 0;
        return true;
      }
      return false;
    }

    private boolean isTried(int i, int j) {
        return map[i][j] == 3;
    }

    private boolean inRange(int i, int j) {
        return inHeight(i) && inWidth(j);
    }

    private boolean inHeight(int i) {
        return i >= 0 && i < height;
    }

    private boolean inWidth(int j) {
        return j >= 0 && j < width;
    }

    public String toString() {
        String s = "";
        for (int[] row : map) {
            s += Arrays.toString(row) + "\n";
        }

        return s;
    }

}