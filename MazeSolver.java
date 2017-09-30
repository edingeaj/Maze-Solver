import java.io.File;
import java.util.*;

/**
 * Loads a specified maze formatted as a .txt file and finds the solution path
 * (if it exists) using three types of searches: Breadth-first, Depth-first, and
 * Uniform-cost. The solution path is then printed to the console.
 * 
 * @author Andrew Edinger
 */
public class MazeSolver {

	static final char[] DIRECTIONS = { 'N', 'E', 'S', 'W' };
	static Node start;

	public static void main(String[] args) {

		char[][] maze = loadMaze();

		System.out.println("BFS: " + BFS(maze, start).display());
		System.out.println("DFS: " + DFS(maze, start).display());
		System.out.println("UCS: " + UCS(maze, start).display() + '\n');

	}

	/**
	 * Prompts user for a maze file to be read into the program 
	 *
	 * @param None.
	 * @return maze 2d char array as read from specified .txt file.
	 * 
	 */
	public static char[][] loadMaze() {
		Scanner console = new Scanner(System.in);

		System.out.println("Enter filename:");
		String fileName = console.next();
		char[][] maze = null;

		try {
			File filePath = new File(fileName);

			Scanner sc = new Scanner(filePath);

			int height = sc.nextInt();
			int width = sc.nextInt();
			sc.nextLine();
			maze = new char[2 * height + 1][2 * width + 1];

			int i = 0;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();

				/* Reads .txt file into a character array */
				for (int j = 0; j < line.length(); j++) {
					maze[i][j] = line.charAt(j);
					if (maze[i][j] == 'S') {
						start = new Node(j, i);
					}
				}
				i++;

			}

			sc.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		console.close();

		return maze;

	}

	/**
	 * Checks for walls adjacent to the current location to determine possible
	 * moves.
	 * 
	 * @param current
	 *            Current Node
	 * @param direction
	 *            N, E, S, or W
	 * @return true if there is an open space in the specified direction, false if
	 *         there is a wall
	 */
	public static boolean canMove(char direction, Node current, char[][] maze) {
		if (direction == 'N') {
			return (maze[current.getY() - 1][current.getX()] == ' ');
		} else if (direction == 'E') {
			return (maze[current.getY()][current.getX() + 1] == ' ');
		} else if (direction == 'S') {
			return (maze[current.getY() + 1][current.getX()] == ' ');
		} else if (direction == 'W') {
			return (maze[current.getY()][current.getX() - 1] == ' ');
		}

		return false;
	}

	/**
	 * Solves maze using a breadth-first search.
	 * 
	 * @param maze
	 *            Maze to be solved.
	 * @param start
	 *            Starting node.
	 * @return Final Node containing solution path.
	 */
	public static Node BFS(char[][] maze, Node start) {
		LinkedList<Node> frontier = new LinkedList<Node>();
		LinkedList<Node> explored = new LinkedList<Node>();

		frontier.addFirst(start);
		while (frontier.size() > 0) {
			Node currentNode = frontier.removeFirst();
			explored.add(currentNode);

			if (maze[currentNode.getY()][currentNode.getX()] == 'F') {
				return currentNode;
			}

			for (int i = 0; i < 4; i++) {
				Node nextNode = new Node(DIRECTIONS[i], currentNode, 1);
				if (canMove(DIRECTIONS[i], currentNode, maze) && !(explored.contains(nextNode))) {
					if (nextNode.getX() == 1 || nextNode.getX() == (maze.length - 2) || nextNode.getY() == 1
							|| nextNode.getY() == (maze[0].length - 2)) {
						nextNode.addCost(10);
					}
					frontier.add(nextNode);
				}
			}
		}

		return new Node();
	}

	/**
	 * Solves maze using a depth-first search.
	 * 
	 * @param maze
	 *            Maze to be solved.
	 * @param start
	 *            Starting node.
	 * @return Final Node containing solution path.
	 */
	public static Node DFS(char[][] maze, Node start) {
		LinkedList<Node> frontier = new LinkedList<Node>();
		LinkedList<Node> explored = new LinkedList<Node>();

		frontier.addFirst(start);
		while (frontier.size() > 0) {
			Node currentNode = frontier.removeFirst();
			explored.add(currentNode);

			if (maze[currentNode.getY()][currentNode.getX()] == 'F') {
				return currentNode;
			}

			for (int i = 0; i < 4; i++) {
				Node nextNode = new Node(DIRECTIONS[i], currentNode, 1);
				if (canMove(DIRECTIONS[i], currentNode, maze) && !(explored.contains(nextNode))) {
					if (nextNode.getX() == 1 || nextNode.getX() == (maze.length - 2) || nextNode.getY() == 1
							|| nextNode.getY() == (maze[0].length - 2)) {
						nextNode.addCost(10);
					}
					frontier.addFirst(nextNode);
				}
			}
		}

		return new Node();
	}

	/**
	 * Solves maze using a uniform cost search.
	 * 
	 * @param maze
	 *            Maze to be solved.
	 * @param start
	 *            Starting node.
	 * @return Final Node containing solution path.
	 */
	public static Node UCS(char[][] maze, Node start) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>();
		LinkedList<Node> explored = new LinkedList<Node>();

		frontier.add(start);
		while (frontier.size() > 0) {
			Node currentNode = frontier.poll();
			explored.add(currentNode);

			if (maze[currentNode.getY()][currentNode.getX()] == 'F') {

				return currentNode;
			}

			for (int i = 0; i < 4; i++) {
				Node nextNode = new Node(DIRECTIONS[i], currentNode, 1);
				if (canMove(DIRECTIONS[i], currentNode, maze) && !(explored.contains(nextNode))) {
					if (nextNode.getX() == 1 || nextNode.getX() == (maze.length - 2) || nextNode.getY() == 1
							|| nextNode.getY() == (maze[0].length - 2)) {
						nextNode.addCost(10);
					}
					frontier.add(nextNode);
				}
			}
		}

		return new Node();
	}

}
