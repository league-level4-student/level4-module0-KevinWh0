package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
        final int rx = MazeMaker.randGen.nextInt(maze.getWidth());
        final int ry = MazeMaker.randGen.nextInt(maze.getHeight());		
		//5. call selectNextPath method with the randomly selected cell
		
        selectNextPath(maze.getCell(rx, ry));

        final int ryB1 = MazeMaker.randGen.nextInt(maze.getHeight());	
     
        final int ryB2 = MazeMaker.randGen.nextInt(maze.getHeight());	
        
        maze.getCell(0, ryB1).setWestWall(false);
        maze.getCell(maze.getWidth()-1, ryB2).setEastWall(false);

        
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
		currentCell.setBeenVisited(true);

		//B. Get an ArrayList of unvisited neighbors using the current cell and the method below
		ArrayList<Cell> unvisitedNeighbors = new ArrayList<Cell>();
		
		unvisitedNeighbors = getUnvisitedNeighbors(currentCell);
		
		
		if(unvisitedNeighbors.size() > 0) {
		//C. if has unvisited neighbors,
		
			//C1. select one at random.
			Random rand = new Random();
			
			Cell randCell = unvisitedNeighbors.get(rand.nextInt(unvisitedNeighbors.size()));
			//C2. push it to the stack
			uncheckedCells.push(randCell);
			//C3. remove the wall between the two cells
			removeWalls(currentCell, randCell);
			
			
			//C4. make the new cell the current cell and mark it as visited
			currentCell = randCell;
			//C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
			
		}else {
		//D. if all neighbors are visited
		
			//D1. if the stack is not empty
			if(uncheckedCells.size() > 0) {
				// D1a. pop a cell from the stack
				Cell poppedCell = uncheckedCells.pop();
				// D1b. make that the current cell
				currentCell = poppedCell;
		
				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);

			}
		}
		
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if(c1.getX() == c2.getX()) {

			if(c1.getY() > c2.getY()) {
				c2.setSouthWall(false);
				c1.setNorthWall(false);
			}else {
				c2.setNorthWall(false);
				c1.setSouthWall(false);

			}
			

		}
		if(c1.getY() == c2.getY()) {
			if(c1.getX()+1 == c2.getX()) {
				c2.setWestWall(false);
				c1.setEastWall(false);
			}else {
				c1.setWestWall(false);
				c2.setEastWall(false);

			}
			
		}
		
		
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell currentCell) {
		ArrayList<Cell> unvisitedNeighbors = new ArrayList<Cell>();
		
		try {
		if(!maze.getCell(currentCell.getX(), currentCell.getY()-1).hasBeenVisited()) {
			unvisitedNeighbors.add(maze.getCell(currentCell.getX(), currentCell.getY()-1));
		}
		}catch(Exception e) {}
		try {
		if(!maze.getCell(currentCell.getX(), currentCell.getY()+1).hasBeenVisited()) {
			unvisitedNeighbors.add(maze.getCell(currentCell.getX(), currentCell.getY()+1));
		}
		}catch(Exception e) {}
		try {
		if(!maze.getCell(currentCell.getX()-1, currentCell.getY()).hasBeenVisited()) {
			unvisitedNeighbors.add(maze.getCell(currentCell.getX()-1, currentCell.getY()));

		}
		}catch(Exception e) {}
		try {
		if(!maze.getCell(currentCell.getX()+1, currentCell.getY()).hasBeenVisited()) {
			unvisitedNeighbors.add(maze.getCell(currentCell.getX()+1, currentCell.getY()));
		}
		}catch(Exception e) {}
		
		return unvisitedNeighbors;
	}
	
	static int getWidth() {
		return maze.getWidth();
	}
	static int getHeight() {
		return maze.getHeight();
	}
	
}
