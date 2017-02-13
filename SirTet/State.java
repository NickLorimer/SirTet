public class State {
	private boolean[][] squares;
	int x;
	int y;

	/**
	 * Constructor that takes in a single int the defines the width of the state
	 * initialises squares and sets them all to true
	 * TO BE USED FOR INITIALISING A PROBELM ONLY
	 */
	public State (int x, int y) {
		this.x = x;
		this.y = y;
		squares = new boolean[x][y];
		for(int i = 0; i < x; i++) {
			for (int k = 0; k < y; k++) {
				squares[i][k] = true;
			}
		}
	}

	/**
	 * Constructor that creates a copy of another state
	 * FOR PLACING PIECES INTO
	 */
	public State (State old) {
		x = old.xlen();
		y = old.ylen();
		this.squares = new boolean[x][y];
		for(int i = 0; i < x; i++) {
			for (int k = 0; k < y; k++) {
				this.squares[i][k] = old.get(i,k);
			}
		}
	}

	/**
	 * returns the coordinates of the "first" square to be filled when placing
	 * a piece
	 */
	public int[] getStart() {
		int[] result = new int[2];
		for(int i = 0; i < x; i++) {
			for (int k = 0; k < y; k++) {
				if (this.get(i, k)) {
					result[0] = i;
					result[1] = k;
					return result;
				}
			}
		}
		return result;
	}

	/**
	 * Returns the value at the specified coordinate
	 */
	public boolean get(int x, int y) {
		return squares[x][y];
	}

	/**
	 * Updates the state by shifting all the squares to the left if the first
	 * column is filled up
	 */
	public void update() {
		boolean finished = false;
		int count = 0;
		while (!finished) {
			for (int i = 0; i < this.y; i++) {
				if (!this.get(0,i)) {
					count++;
				}
			}
			if (count == this.y) {
				count = 0;
				for(int i = 0; i < x-1; i++) {
					for (int k = 0; k < y; k++) {
						this.squares[i][k] = this.squares[i+1][k];
					}
				}
				for (int i = 0; i < this.y; i++) {
					this.set(this.x-1, i, true);
				}
			}
			else {
				finished = true;
			}
		}
	}
	/**
	 * Sets the value at position x,y to @value
	 */
	public void set(int x, int y, boolean value) {
		this.squares[x][y] = value;
	}

	// boring accessor
	public boolean[][] getSquares () {
		return squares;
	}

	// boring accessor
	public int xlen () {
		return this.x;
	}

	//boring accessor
	public int ylen() {
		return this.y;
	}

	/**
	 * Displays the state, "X" denotes a filled square "0" denotes an empty one
	 */
	public void display () {
		for(int i = 0; i < y; i++) {
			for (int k = 0; k < x; k++) {
				if(squares[k][i]){
					System.out.print("0");
				}else {
					System.out.print("X");
				}
			}
			System.out.println();
		}
	}

	public String genKey () {
		String result = "";
		for(int i = 0; i < y; i++) {
			for (int k = 0; k < x; k++) {
				if(squares[k][i]){
					result+= '0';
				}else {
					result += 'X';
				}
			}
		}
		return result;
	}

	public String toString () {
		String result = "";
		for(int i = 0; i < y; i++) {
			for (int k = 0; k < x; k++) {
				if(squares[k][i]){
					result+= '0';
				}else {
					result += 'X';
				}
			}
		}
		return result;
	}

	public boolean equals(Object other) {
		if (other instanceof State) {
			State that = (State) other;
			for(int i = 0; i < x; i++) {
				for (int k = 0; k < y; k++) {
					if (this.get(i,k) != that.get(i,k)) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}

}