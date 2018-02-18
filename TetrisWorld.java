/*
 *Bryan Yang
 *Gallatin
 *1st Period
 *Class that creates the Tetris board
 */

import info.gridworld.actor.*;
import javax.swing.*;
import info.gridworld.grid.*;
import java.util.*;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import java.io.*;
/**
 * 
 * @author Bryan Yang
 * Class that creates the Tetris board
 */
public class TetrisWorld extends ActorWorld
{
	public static Grid<Actor> grid;
	public TetrisBlock currentBlock;
	public static boolean finished;
	private static int score;
	ArrayList <TetrisBlock> active;
	ArrayList <TetrisBlock> inactive;
	private int numSteps;
	private int difficulty;
	private boolean gameOver;
	private TetrisBlock nextBlock;

	
	/**
	 *Sets up the Tetris board
	 *@param i the difficulty
	 */
	public TetrisWorld(int i)
	{
		nextBlock = null;
		gameOver=false;
		difficulty=i;
		numSteps=0;
		score=0;
		setMessage("Press Start to begin\nScore: " + score + "\nNext block is: " + nextBlock);
		active = new ArrayList<TetrisBlock>();
		inactive = new ArrayList<TetrisBlock>();
		grid = getGrid();
		currentBlock = null;
		show();		
	}

	/**
	 * Moves the block down continually as the game is being played
	 * Drop rate depends on difficulty
	 */
	public void step() 
	{
		 
		if(difficulty==0)
		{
			numSteps++;	
		}
		if(difficulty==1)
		{
			numSteps+=2;	
		}
		if(difficulty==2)
		{
			numSteps+=5;
			//numSteps+=8;
		}
		
		if(currentBlock==null)
		{
			currentBlock=getNextBlock();
			nextBlock=inactive.get(0);
			setMessage("Press Start to begin\nScore: " + score + "\nNext block is: " + nextBlock);
			buildBlock(currentBlock);
			active.add(currentBlock);
		}
		else
		{
			
			if(!currentBlock.canMoveDown())
			{
				removeLines();
				try
				{
					if(!gameOver)
					{
						spawnBlock();
						nextBlock=inactive.get(0);
						setMessage("Press Start to begin\nScore: " + score + "\nNext block is: " + nextBlock);	
					}
					
				}
				catch(FileNotFoundException e)
				{
					
				}
			}
			else
			{
				if(!gameOver)
				{
					if(numSteps>=14)
					{
						currentBlock.act();
						numSteps=0;		
					}
				}
											
			}
			
		}
	}
	
	/**
	 * Moves the TetrisBlock according to which arrowkey is pressed
	 * Pressing spacebar turns the TetrisBlock
	 * @param description the description
	 * @param loc the location
	 */
	public boolean keyPressed(String description, Location loc)
    {
		if ((description.equals("A")|| description.equals("LEFT")))
		{	
			currentBlock.moveLeft();
			return true;    		
		}
		if ((description.equals("S")|| description.equals("DOWN")))
		{
			step();
			return true;
		}
		if ((description.equals("D") || description.equals("RIGHT")))
		{
			currentBlock.moveRight(); 	
			return true;
		}
		if ((description.equals("SPACE")))
		{
			currentBlock.turn();	
			return true;
		}
		if(description.equals("ESCAPE"))
		{
			try
			{
				gameOver();
			}
			catch(FileNotFoundException e)
			{
    			
			}
			return true;
		}    	
    return false;
 /**
  * Returns the next TetrisBlock that will be spawned
  * @return the next TetrisBlock that will be spawned
  */
    }
	public TetrisBlock getNextBlock()
	{
		while(inactive.size()<3)
		{
			int pick = (int)(Math.random()*7);
		//	int pick = 1;
		
			switch(pick)
			{
				case 0:
					TetrisBlock i = new TetrisBlock(grid,"I");
					inactive.add(i);  
					break;
				case 1: 
					TetrisBlock t = new TetrisBlock(grid,"T");
					inactive.add(t);
					break;
				case 2: 
					TetrisBlock j = new TetrisBlock(grid,"J");
					inactive.add(j);
					break;
				case 3:
					TetrisBlock l = new TetrisBlock(grid,"L"); 
					inactive.add(l);
					break;
				case 4:
					TetrisBlock o = new TetrisBlock(grid,"O");
					inactive.add(o);
					break;
				case 5: 
					TetrisBlock s = new TetrisBlock(grid,"S");
					inactive.add(s);
					break;
				case 6:
					TetrisBlock z = new TetrisBlock(grid,"Z");
					inactive.add(z); 
					break;
			}
		}
		TetrisBlock t = inactive.get(0);
		inactive.remove(0);
		return t;
			
	}
   /**
   * Updates the score message
   */
	public void updateScore()
	{
		setMessage("Press Start to begin\nScore: " + score);
	}
	/**
	 * Removes the lines when it is cleared and shifts the remaining TetrisBlockComponents
	 * down accordingly
	 */
	public void removeLines()
	{
		int count=0;
		int numLines=0;
		int rowRemoved=0;
		for(int r=0;r<grid.getNumRows();r++)
		{
			for(int c=0;c<grid.getNumCols();c++)
			{
				if(grid.get(new Location(r,c))!=null)
				{
					count++;
				}
			}
			if(count==10)
			{
				numLines++;
				for(int c=0;c<grid.getNumCols();c++)
				{
					grid.remove(new Location(r,c));
				}
				if(rowRemoved==0)
				{
					rowRemoved=r;
				}		
			}
			count=0;
		}
		if(numLines==1)
		{
			score+=40;
		}
		else if(numLines==2)
		{
			score+=100;
		}
		else if(numLines==3)
		{
			score+=300;
		}
		else if(numLines==4)
		{
			score+=1200;
		}
		updateScore();
		for(int r=rowRemoved;r>=0;r--)
		{
			for(int c=0;c<grid.getNumCols();c++)
			{
				if(grid.get(new Location(r,c))!=null)
				{
					TetrisBlockComponent t=((TetrisBlockComponent)grid.get(new Location(r,c)));
					while(t.canMoveDown(grid))
					{
						t.moveDown(grid);
					}
				}
			}
		}
		
	}
	/**
	 * Spawns the next block
	 * @throws FileNotFoundException
	 */
	public void spawnBlock() throws FileNotFoundException
	{
		if(!gameOver)
		{
		currentBlock=getNextBlock();
		ArrayList<TetrisBlockComponent> list = currentBlock.getShape();
		for(int i=0;i<list.size();i++)
		{
			if(grid.get(list.get(i).getLocation())!=null)
			{
				gameOver();
			}
		}
		buildBlock(currentBlock);
		active.add(currentBlock);
		}
	}
	/**
	 * Adds a TetrisBlock to the grid
	 * @param block the TetrisBlock to be added
	 */
	public void buildBlock(TetrisBlock block)
	{
		ArrayList<TetrisBlockComponent> list = block.getShape();
		for(int i=0;i<list.size();i++)
		{
			add(list.get(i).getLocation(),list.get(i));
		}
	}
	/**
	 * Ends the game and asks the player to submit name for high score 
	 * if applicable
	 * Player can restart the game if desired
	 * @throws FileNotFoundException
	 */
	public void gameOver() throws FileNotFoundException
	{
		
		gameOver = true;
		JFrame frame = new JFrame();
	
		 JOptionPane.showMessageDialog(null, "Game Over! Your score was " + score);
		 try
		{
			if(isTopTen())
			{
				saveScore();	
			}
				
		}
		catch(IOException e)
		{
			
		}
		 int viewscores = JOptionPane.showConfirmDialog(null, "Would you like to view high scores?",
				 "High Scores", JOptionPane.YES_OPTION);
		 if(viewscores== JOptionPane.YES_OPTION)
		 {
			 String s = loadScores();
			 JOptionPane.showMessageDialog(null, s, "High Scores", JOptionPane.PLAIN_MESSAGE);
		 }
		 
		 int begin = JOptionPane.showConfirmDialog( null, String.format("Would you like to play again?"), 
		         "Game Over!", JOptionPane.YES_NO_OPTION, 
		         JOptionPane.PLAIN_MESSAGE );
		 
		 if( begin == JOptionPane.YES_OPTION )
		 {
		 	frame.dispose();
		 	restartGame();
		 	}
		 	else
		 	{
		  	System.exit(0);
		 } 			
	}
	/**
	 * Saves the score if it qualifies to be in the top ten
	 * @throws IOException
	 */
	public void saveScore() throws IOException
	{
		if(isTopTen())
		{
		ArrayList<Score> scores = new ArrayList<Score>();
		
		File inFile = new File("TopScores.txt");
		Scanner reader = new Scanner(inFile);
		
		Scanner in = new Scanner(System.in);
		String name = JOptionPane.showInputDialog("Congratulations! You scored in the top 10. Please enter a name to be saved:");

		scores.add(new Score(name, score));
		while(reader.hasNext())
		{
			String rname = reader.next();
			int rscore = Integer.parseInt(reader.next());
			Score temp = new Score(rname, rscore);
			scores.add(temp);	
		}
		
		Collections.sort(scores);
		PrintWriter write = new PrintWriter(inFile);
		int i=0;
		while(i<10 && i<scores.size())
		{
			write.println(scores.get(i));
			i++;
		}
		JOptionPane.showMessageDialog(null,"Your score was saved.");
		write.close();
		}
	}
	/**
	 * Shows the top ten scores
	 * @throws FileNotFoundException
	 */
	public void showTopScores() throws FileNotFoundException
	{
		
		Scanner reader = new Scanner("TopScores.txt");
		while(reader.hasNextLine())
		{
			System.out.println (reader.nextLine());
		}
	}
	/**
	 * Returns whether the score qualifies to be in the top ten
	 * @return whether the score qualifies to be in the top ten
	 * @throws FileNotFoundException
	 */
	public boolean isTopTen() throws FileNotFoundException
	{
		int recordedScore;
		File inFile = new File("TopScores.txt");
		Scanner reader = new Scanner(inFile);
		int count=0;
		while(reader.hasNextLine())
		{
			String s=reader.nextLine();
			recordedScore = Integer.parseInt(s.substring(s.indexOf(" ")+1));
			
			if(score>recordedScore)
			{
				return true;
			}
			count++;
		}
		return count<10;	
		
	} 
	/**
	 * Loads the scores
	 * @return the scores
	 * @throws FileNotFoundException
	 */
	public String loadScores() throws FileNotFoundException
	{
		String result="";
		File inFile = new File("TopScores.txt");
		Scanner reader = new Scanner(inFile);
		while(reader.hasNextLine())
		{
			result+=reader.nextLine() + "\n";
		}
		
		return result;
	}
	/**
	 * Restarts the game by clearing the grid and resetting 
	 * game values
	 */
	public void restartGame()
	{
		for(int r=0;r<grid.getNumRows();r++)
			for(int c=0;c<grid.getNumCols();c++)
			{
				grid.remove(new Location(r,c));
			}
		gameOver=false;	
		numSteps=0;
	}
}