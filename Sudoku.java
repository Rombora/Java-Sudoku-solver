import java.util.Arrays;

public class Sudoku {
	
	public static int[][] GRID = {
		{5, 3, 0, 0, 7, 0, 0, 0, 0},
		{6, 0, 0, 1, 9, 5, 0, 0, 0},
		{0, 9, 8, 0, 0, 0, 0, 6, 0},
		{8, 0, 0, 0, 6, 0, 0, 0, 3},
		{4, 0, 0, 8, 0, 3, 0, 0, 1},
		{7, 0, 0, 0, 2, 0, 0, 0, 6},
		{0, 6, 0, 0, 0, 0, 2, 8, 0},
		{0, 0, 0, 4, 1, 9, 0, 0, 5},
		{0, 0, 0, 0, 8, 0, 0, 7, 9},
	};

// 	public static int[][] EXPECTED_SOLUTION = {
// 	{5, 3, 4, 6, 7, 8, 9, 1, 2},
//     {6, 7, 2, 1, 9, 5, 3, 4, 8},
//     {1, 9, 8, 3, 4, 2, 5, 6, 7},
//     {8, 5, 9, 7, 6, 1, 4, 2, 3},
//     {4, 2, 6, 8, 5, 3, 7, 9, 1},
//     {7, 1, 3, 9, 2, 4, 8, 5, 6},
//     {9, 6, 1, 5, 3, 7, 2, 8, 4},
//     {2, 8, 7, 4, 1, 9, 6, 3, 5},
//     {3, 4, 5, 2, 8, 6, 1, 7, 9},
// };
	
	private int[][] board;
	private int size;
	public static final int EMPTY = 0;

	public Sudoku(int[][] board, int R, int C) {
		this.size = R*C;
		this.board = new int[size][size];
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				this.board[i][j] = board[i][j];
			}
		}
	}
	
	private boolean check_row(int row, int number) {
		for (int i = 0; i < size; i++)
			if (board[row][i] == number)
				return true;
		return false;
	}

	private boolean check_column(int col, int number) {
		for (int i = 0; i < size; i++)
			if (board[i][col] == number)
				return true;
		return false;
	}
	
	private boolean check_box(int row, int col, int number) {
		int r = row - row % 3;
		int c = col - col % 3;
		
		for (int i = r; i < r + 3; i++)
			for (int j = c; j < c + 3; j++)
				if (board[i][j] == number)
					return true;
		
		return false;
	}

	
	private boolean check(int row, int col, int number) {
		return !check_row(row, number)  &&  !check_column(col, number)  &&  !check_box(row, col, number);
	}


	public boolean deselect(int row, int col){
		if(solve()) {
			return true;
		}else{
			board[row][col] = EMPTY;
		}
		return false;
	}

	public void select(int row, int col, int number){
		board[row][col] = number;
	}

	public boolean solve() {
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if (board[row][col] == EMPTY) {
					for (int number = 1; number <= size; number++) {
						if (check(row, col, number)) {
							select(row,col,number);
							if (deselect(row, col)==true){
								return true;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}
	


	public void display() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(" " + board[i][j]);
			}
		
			System.out.println();
		}
		
		System.out.println();
	}

	public static void solve_sudoku(){
		Sudoku sudoku = new Sudoku(GRID,3,3);
		sudoku.display();
		
		if (sudoku.solve()) {

			System.out.println("Solution");
			sudoku.display();


		} else {
			System.out.println("Could not be solved");
		}

	}

	public static void main(String[] args) {
		solve_sudoku();
	}

}