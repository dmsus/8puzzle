
public class Solver {

	public static void main(String[] args) {						
		Board board = new Board(readBlocks(args[0]));
		if (args.length == 1) { 
			System.out.printf("Dimension is %d\n", board.dimension());
			System.out.printf("Goal is %b\n", board.isGoal());
			System.out.println(board.toString());
			System.out.printf("Hamming is %d\n", board.hamming());
			System.out.printf("Manhattan is %d\n", board.manhattan());
			for (Board b : board.neighbors()) {
				System.out.println(b.toString());
			}
			
		}
		else {
			int[][] blocks1 = readBlocks(args[1]); // TODO: inline variable
			Board board1 = new Board(blocks1);
			System.out.printf("Boards are equal: %b\n", board.equals(board1));
			System.out.printf("Boards are twins: %b\n", board1.equals(board.twin()));
		}
		
	}
	
	private static int[][] readBlocks(String fileName) {
		In in = new In(fileName);
		int N = in.readInt();
		int[][] blocks = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				blocks[i][j] = in.readInt();
		return blocks;
	}

}
