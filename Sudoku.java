
public class Sudoku {
	private int row, col, p, q;

	void count(int mat[][], Sudoku s) {
		int[] row = new int[9];

		int rowCount = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (mat[i][j] != 0) {
					rowCount++;
					row[i] = rowCount;
				}
			}
			rowCount = 0;
		}
		for (int i = 0; i < 9; i++) {
			if (row[i] == 8) {
				int j = s.nextEmptyRow(mat, i);
				for (int num = 1; num < 10; num++) {
					int output = s.check(mat, i, j, num);
					if (output == 1) {
						mat[i][j] = num;
					}
				}
			}
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(" " + mat[i][j]);
			}
			System.out.println();
		}
	}

	void countCol(int mat[][], Sudoku s) {
		int[] col = new int[9];
		int colCount = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (mat[j][i] != 0) {
					colCount++;
					col[i] = colCount;
				}
			}
			colCount = 0;
			System.out.println(col[i]);
		}

		for (int i = 0; i < 9; i++) {
			if (col[i] == 8) {
				int j = s.nextEmptyCol(mat, i);
				System.out.println("checking:" + j);
				for (int num = 1; num < 10; num++) {
					int output = s.check(mat, j, i, num);
					if (output == 1) {
						mat[j][i] = num;
					}
				}
			}
		}
	}

	int nextEmptyRow(int mat[][], int x) {
		for (int j = 0; j < 9; j++) {
			if (mat[x][j] == 0) {
				return j;
			}
		}
		return -1;
	}

	int nextEmptyCol(int mat[][], int i) {
		for (int j = 0; j < 9; j++) {
			if (mat[j][i] == 0) {
				return j;
			}
		}
		return -1;
	}

	int[] nextPlace(int mat[][]) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (mat[i][j] == 0) {
					return new int[] { i, j };
				}
			}
		}
		return new int[] { -1, -1 };
	}

	int check(int mat[][], int i, int j, int e) {

		for (int x = 0; x < 9; x++) {
			if (e != mat[i][x])
				row = 1;
			else {
				row = 0;
				break;
			}
		}
		if (row == 1) {
			for (int x = 0; x < 9; x++) {
				if (e != mat[x][j])
					col = 1;
				else {
					col = 0;
					break;
				}
			}
			if (col == 1) {
				p = 3 * (i / 3);
				q = 3 * (j / 3);
				for (int x = p; x < p + 3; x++) {
					for (int y = q; y < q + 3; y++) {
						if (e == mat[x][y]) {
							return 0;
						}
						return 1;
					}
				}
			}
		}
		return 0;
	}

	boolean work(int mat[][], int i, int j) {
		System.out.println("Inside work");
		int get[] = nextPlace(mat);
		if (get[0] == -1)
			return true;
		for (int x = 1; x < 10; x++) {
			if (check(mat, get[0], get[1], x) == 1) {
				mat[get[0]][get[1]] = x;
				if (work(mat, get[0], get[1])) {
					return true;
				}
				mat[get[0]][get[1]] = 0;

			}
		}
		return false;
	}

	public static void main(String[] args) {
		int input[][] = { 
						{ 1, 2, 3, 4, 5, 6, 7, 8, 0 }, 
						{ 0, 5, 6, 7, 8, 9, 1, 0, 3 },
						{ 7, 8, 9, 1, 2, 3, 0, 5, 6 },
						{ 2, 3, 0, 5, 6, 0, 8, 9, 1 },
						{ 5, 6, 7, 8, 0, 1, 2, 3, 4 },
						{ 8, 9, 1, 0, 0, 4, 5, 6, 7 },
						{ 3, 4, 0, 6, 7, 8, 9, 1, 2 },
						{ 6, 0, 8, 9, 1, 0, 3, 4, 5 },
						{ 0, 1, 2, 3, 4, 5, 6, 7, 8 },
					};
		Sudoku s = new Sudoku();
		s.count(input, s);
		s.countCol(input, s);
		s.work(input, 0, 0);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(" " + input[i][j]);
			}
			System.out.println();
		}
	}
}
