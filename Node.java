
/**
 * Accompanies the Maze Solver class. Represents one "square" on a maze, used to
 * trace a path through the maze.
 * 
 * @author Andrew Edinger
 */
public class Node implements Comparable<Object> {
	private int x;
	private int y;
	private String path;
	private int cost;

	public Node() {
		this.x = 0;
		this.y = 0;
		this.path = "";
		this.cost = 0;
	}

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		this.path = "";
		this.cost = 0;
	}

	/**
	 * Constructs a new node exactly one space in the given direction from the
	 * previous node
	 * 
	 * @param direction
	 *            N, E, S, or W
	 * @param prevNode
	 * @param cost
	 */
	public Node(char direction, Node prevNode, int cost) {
		if (direction == 'N') {
			this.y = prevNode.getY() - 2;
			this.x = prevNode.getX();
		} else if (direction == 'E') {
			this.y = prevNode.getY();
			this.x = prevNode.getX() + 2;
		} else if (direction == 'S') {
			this.y = prevNode.getY() + 2;
			this.x = prevNode.getX();
		} else if (direction == 'W') {
			this.y = prevNode.getY();
			this.x = prevNode.getX() - 2;
		} else {
			this.y = prevNode.getY();
			this.x = prevNode.getX();
		}

		this.path = prevNode.getPath() + direction;
		this.cost = prevNode.getCost() + cost;
	}

	public int compareTo(Object obj) {
		if ((obj instanceof Node)) {
			return Integer.compare(this.cost, ((Node) obj).getCost());
		}
		return 0;
	}

	/**
	 * @param otherNode
	 *            Node to be compared
	 * @return true if this Node has the same x and y values as the other Node,
	 *         false otherwise
	 */
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof Node) {
			Node otherNode = (Node) obj;
			return ((this.x == otherNode.getX()) && (this.y == otherNode.getY()));
		}
		return false;
	}

	public int hashCode() {
		int hash = 7;
		hash = 17 * hash + (this.x * 2 + this.y * 3);
		return hash;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int[] getLocation() {
		return new int[] { this.x, this.y };
	}

	public String getPath() {
		return this.path;
	}

	public int getCost() {
		return this.cost;
	}

	public void addCost(int cost) {
		this.cost += cost;
	}

	public String display() {
		if (this.path == "") {
			return "No solution";
		}
		return "Path: " + this.path + " Cost = " + this.cost;
	}
}
