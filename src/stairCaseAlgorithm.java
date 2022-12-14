import java.awt.print.Printable;
import java.util.Iterator;

public class stairCaseAlgorithm {
    
    static int userInput = 3;
    static int[][] grid = new int[userInput][userInput];
    static int rowIndex = 0;
    static int columnIndex = 0;
    static int counter = 1;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        for(int i = 0; i < grid.length; ++i) {
            for(int j = 0; j<grid.length; ++j) {
                grid[i][j] = 0;
            }
        }

        
        // Add the first entry
        columnIndex = Math.round(userInput/2);
        grid[rowIndex][columnIndex] = counter;
        while(!grid_full()) {
            int[] next_row_column_indices = get_next_index();
            System.out.println(next_row_column_indices[0]);
            System.out.println(next_row_column_indices[1]);
            move_index(next_row_column_indices);
            print_grid();
        }
    }

    private static void print_grid() {
        // TODO Auto-generated method stub
        for(int i = 0; i < grid.length; ++i) {
            for(int j = 0; j<grid.length; ++j) {
                System.out.print(grid[i][j]);
            }
            System.out.print("\n");
        }
        
    }

    private static boolean grid_full() {
        // TODO Auto-generated method stub
        if (counter == userInput*userInput) {
            return true;
        } else {
            return false;
        }
        
    }

    private static int[] get_next_index() {
        // TODO Auto-generated method stub
        int temp_next_row = rowIndex;
        int temp_next_column = columnIndex;
        
        temp_next_row = (rowIndex-1)%3;
        temp_next_column = (columnIndex+1)%3;
        
        return new int[] {temp_next_row, temp_next_column};
    }

    private static void move_index(int[] next_position) {
        // TODO Auto-generated method stub
        if (grid[next_position[0]][next_position[1]] == 0) {
            grid[next_position[0]][next_position[1]] = ++counter;
            rowIndex = next_position[0];
            columnIndex = next_position[1];
        } else {
            rowIndex = (rowIndex+1) % 3;
            grid[rowIndex][columnIndex] = ++counter;
        }
    }

}
