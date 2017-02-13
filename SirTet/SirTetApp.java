/**The application class for Sir Tet's*/
import java.math.BigInteger;
import java.util.*;

public class SirTetApp {

	public static BigInteger zero = new BigInteger("0");
	public static BigInteger one = new BigInteger("1");
	public static long res = 0;
	public static int totalPiecesToPlace = 0;
	public static Node startPiece;

	public static void main(String[] args) {
		ArrayList<String> inStr = new ArrayList<String>();
		ArrayList<Integer> widths = new ArrayList<Integer>();
		ArrayList<Integer> lengths = new ArrayList<Integer>();

		Scanner input = new Scanner(System.in);
		while (input.hasNextLine()) {
			String in = input.nextLine();
			if (!(in.length() > 0 && in.charAt(0) == '#')) {
				if (in.matches("")) {
					inStr.add(in);
				} else {
					inStr.add(in);
				}
			}
		}

		for (int i = 0; i < inStr.size(); i++) {
			if (inStr.get(i).matches("[0-9]+ [0-9]+")) {
				int width = Integer.parseInt(inStr.get(i).split(" ")[0]);
				int length = Integer.parseInt(inStr.get(i).split(" ")[1]);
				System.out.println("# " + width +" " + length);
				if (((width * length) % 4 != 0) || (width * length == 0)) {
					System.out.println("# 0");
				} else {
					System.out.println("# " + solve(width, length));
				}
			}
		}
	}

	public static BigInteger solve(int width, int length) {
		//Placing the states into the graph
		int numStates = 0;
		Piece pieces = new Piece();
		HashMap <String, Node> states = new HashMap<String, Node>();
		ArrayList<Node> queue = new ArrayList<Node>();
		State start;
		if (length < 4) {
			start = new State(length, width);
		}else {
			start = new State(4, width);
		}
		Node first = new Node(start, 0);
		startPiece = first;
		queue.add(first);
		states.put(start.genKey(), first);
		while (!queue.isEmpty()) {
			Node currNode = queue.remove(0);
			State current = currNode.state;
			int[] startLoc = current.getStart();
			for (int i = 0; i < 19; i++) {
				State newState = pieces.place(current, startLoc, pieces.pieces[i]);
				if (newState != null) {
					String key = newState.genKey();
					if (states.containsKey(key)) {
						Node newNode = states.get(key);
						currNode.addNeighbour(newNode);
					}else {
						numStates ++;
						Node newNode = new Node(newState, numStates);
						queue.add(newNode);
						states.put(key, newNode);
						currNode.addNeighbour(newNode);
					}
				}
			}
		}
		totalPiecesToPlace = (width * length) / 4;
		BigInteger [][] table = new BigInteger[numStates+1][totalPiecesToPlace + 1];
		Node[] statesArray = new Node[numStates + 1];
		for (Node val : states.values()) {
			statesArray[val.number] = val;
		}
		for(int i = 0; i < numStates + 1; i++) {
			for (int w = 0; w < totalPiecesToPlace + 1; w++) {
				table[i][w] = new BigInteger("0");
			}
		}
		table[0][0] = one;
		int row = 0;
		while (row < totalPiecesToPlace) {
			for (int i = 0; i < numStates; i++) {
				Node current = statesArray[i];
				for (Node n: current.neighbours) {
					table[n.number][row+1] = table[n.number][row+1].add(table[current.number][row]);
				}
			}
			row++;
		}
		return table[0][row];
	}

}













