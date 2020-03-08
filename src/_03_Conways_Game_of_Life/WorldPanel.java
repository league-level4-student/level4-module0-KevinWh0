package _03_Conways_Game_of_Life;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class WorldPanel extends JPanel implements MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private int cellsPerRow;
	private int cellSize;
	
	private Timer timer;
	
	//1. Create a 2D array of Cells. Do not initialize it.
	Cell[][] cells;
	
	
	public WorldPanel(int w, int h, int cpr) {
		setPreferredSize(new Dimension(w, h));
		addMouseListener(this);
		timer = new Timer(500, this);
		this.cellsPerRow = cpr;
		System.out.println(h);
		//2. Calculate the cell size.
		cellSize = h/cpr;
		
		//3. Initialize the cell array to the appropriate size.
		cells = new Cell[cpr][cpr];
		//3. Iterate through the array and initialize each cell.
		//   Don't forget to consider the cell's dimensions when 
		//   passing in the location.
		for (int i = 0; i < cellsPerRow; i++) {
			for (int j = 0; j < cellsPerRow; j++) {
				cells[i][j] = new Cell(i*cellSize, j*cellSize, cellSize);
			}
		}
		
	}
	
	public void randomizeCells() {
		//4. Iterate through each cell and randomly set each
		//   cell's isAlive memeber to true of false
		Random rand = new Random();
		for (int i = 0; i < cellsPerRow; i++) {
			for (int j = 0; j < cellsPerRow; j++) {
				cells[i][j].isAlive = rand.nextBoolean();
			}
		}
		repaint();
	}
	
	public void clearCells() {
		//5. Iterate through the cells and set them all to dead.
		for (int i = 0; i < cellsPerRow; i++) {
			for (int j = 0; j < cellsPerRow; j++) {
				cells[i][j].isAlive = false;
			}
		}
		repaint();
	}
	
	public void startAnimation() {
		timer.start();
	}
	
	public void stopAnimation() {
		timer.stop();
	}
	
	public void setAnimationDelay(int sp) {
		timer.setDelay(sp);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//6. Iterate through the cells and draw them all
        for (int i = 0; i < this.cells.length; ++i) {
            for (int j = 0; j < this.cells[0].length; ++j) {
                this.cells[i][j].draw(g);
            }
        }
        //Bounding Box
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
	}
	
	//advances world one step
	public void step() {
		//7. iterate through cells and fill in the livingNeighbors array
		// . using the getLivingNeighbors method.
		int[][] livingNeighbors = new int[cellsPerRow][cellsPerRow];
		
        for (int i = 0; i < this.cells.length; i++) {
            for (int j = 0; j < this.cells[0].length; j++) {
                livingNeighbors[i][j] = this.getLivingNeighbors(i, j);
                cells[i][j].liveOrDie(livingNeighbors[i][j]);
            }
        }
		//8. check if each cell should live or die
	
		
		
		
		repaint();
	}
	
	//9. Complete the method.
	//   It returns an int of 8 or less based on how many
	//   living neighbors there are of the 
	//   cell identified by x and y
	public int getLivingNeighbors(int x, int y){
		int numb = 0;
		
		for (int i = -1; i < 3; i++) {
			for (int j = -1; j < 3; j++) {
				if(i != 0 && j != 0) {
					if(x+i > -1 && y+j > -1 && x+i < cellsPerRow && y+j < cellsPerRow) {
						if(cells[x+i][y+j].isAlive) numb++;
					}
				}
			}
		}
		
		return numb;
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//10. Use e.getX() and e.getY() to determine
		//    which cell is clicked. Then toggle
		//    the isAlive variable for that cell.
		if(this.cells[e.getX()/this.cellSize][e.getY()/this.cellSize].isAlive) {
			this.cells[e.getX()/this.cellSize][e.getY()/this.cellSize].isAlive = false;
		}else {
			this.cells[e.getX()/this.cellSize][e.getY()/this.cellSize].isAlive = true;
		}
		
		
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		step();		
	}
}
