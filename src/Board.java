import java.util.ArrayList;
import java.util.List;

public class Board {
	int[][] blocks;
	
	public Board(int[][] blocks) {
		this.blocks = blocks;
	}
	
	
	public int dimension() {
		return blocks.length;
	}
	
	public boolean isGoal() {
		int current = 1;
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks.length; j++) {
				if ((i == blocks.length - 1) && (j == blocks.length - 1)) {
					current = 0;
				}
				if (blocks[i][j] != current) {
					return false;
				}
				current++; 
			}	
		}	
		return true; 
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks.length; j++) {
				s.append(String.format("%2d ", blocks[i][j]));
			}
			s.append("\n");
		}
		return s.toString();
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other == null) {
			return false;
		}
		if (this.getClass() != other.getClass()) {
			return false;
		}
		Board board = (Board)other;
		if (this.dimension() != board.dimension()) {
			return false;
		}
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks.length; j++) {
				if (this.blocks[i][j] != board.blocks[i][j])
					return false;
			}
		}
		
		return true;
	}
	
	public Board twin() {
		int[][] twinBlocks = cloneBlocks();
		if (twinBlocks[0][0] != 0 && twinBlocks[0][1] != 0) {
			twinBlocks[0][0] = blocks[0][1];
			twinBlocks[0][1] = blocks[0][0];
		}
		else {
			twinBlocks[1][0] = blocks[1][1];
			twinBlocks[1][1] = blocks[1][0];
		}
		
		return new Board(twinBlocks);
	}
	
	public int hamming() {
		int hamming = 0;
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks.length; j++) {
				int value = blocks[i][j];
				if (value != 0 && value != i * blocks.length + j + 1)
					hamming++;
			}
		}
		return hamming;
	}
	
	public int manhattan() {
		int manhattan = 0;
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks.length; j++) {
				if (blocks[i][j] != 0) {
					int goalA = (blocks[i][j] - 1) / dimension();
					int goalB = (blocks[i][j] - 1) % dimension();
					manhattan += Math.abs(goalA - i) + Math.abs(goalB - j);
				}
			}
		}
		return manhattan;
	}
	
	public Iterable<Board> neighbors() {
		List<Board> neighbors = new ArrayList<Board>();
		int emptyIndex = getEmptyIndex(); 
		int i = emptyIndex / dimension();
		int j = emptyIndex % dimension();
		if (i < dimension() - 1) {
			int[][] bottomNeighbor = cloneBlocks();
			bottomNeighbor[i][j] = bottomNeighbor[i + 1][j];
			bottomNeighbor[i + 1][j] = 0;
			neighbors.add(new Board(bottomNeighbor));
		}
		if (j < dimension() - 1) {
			int[][] rightNeighbor = cloneBlocks();
			rightNeighbor[i][j] = rightNeighbor[i][j + 1];
			rightNeighbor[i][j + 1] = 0;
			neighbors.add(new Board(rightNeighbor));
		}
		if (i > 0) {
			int[][] topNeighbor = cloneBlocks();
			topNeighbor[i][j] = topNeighbor[i - 1][j];
			topNeighbor[i - 1][j] = 0;
			neighbors.add(new Board(topNeighbor));
		}
		if (j > 0) {
			int[][] leftNeighbor = cloneBlocks();
			leftNeighbor[i][j] = leftNeighbor[i][j - 1];
			leftNeighbor[i][j - 1] = 0;
			neighbors.add(new Board(leftNeighbor));
		}
		return neighbors;
	}
	
	private int[][] cloneBlocks() {
		int[][] clone = new int[this.blocks.length][this.blocks.length]; {
			for (int i = 0; i < blocks.length; i++) {
				for (int j = 0; j < blocks.length; j++) {
					clone[i][j] = blocks[i][j];
					
				}					
			}
		}
		return clone;
	}
	
	private int getEmptyIndex() {
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks.length; j++) {
				if (blocks[i][j] == 0) {
					return i * dimension() + j;
				}
			}
		}
		return -1;
	}

}
	
