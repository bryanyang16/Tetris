/*
 *Bryan Yang
 *Gallatin
 *1st Period
 *Starts the Tetris game
 */
import javax.swing.*;
/**
 * 
 * @author Bryan Yang
 * Starts the Tetris game
 */
public class TetrisRunner
{
   public static void main(String[] args)
   { 
	   JFrame control = new JFrame();
	   int begin = JOptionPane.showConfirmDialog( null, "Would you like to play Tetris? Press start to begin.", 
		         "Welcome!", JOptionPane.YES_NO_OPTION, 
		         JOptionPane.PLAIN_MESSAGE );
		      if( begin == JOptionPane.YES_OPTION )
		         {
		         control.dispose();
				 String difficulty = JOptionPane.showInputDialog(null, "Choose a difficulty\nType E for Easy, M for Medium, or H for Hard");
				 	
				 if(difficulty.equalsIgnoreCase("E"))
		         {
		         	TetrisWorld game = new TetrisWorld(0);
		         }
		         else if(difficulty.equalsIgnoreCase("M"))
		         {
		         	TetrisWorld game = new TetrisWorld(1);
		         }
		         else if(difficulty.equalsIgnoreCase("H"))
		         {
		         	TetrisWorld game = new TetrisWorld(2);
		         }				 
		         else
		         {
		         	while(!difficulty.equalsIgnoreCase("E") && !difficulty.equalsIgnoreCase("M") && !difficulty.equalsIgnoreCase("H"))
		         	{
		         		JOptionPane.showMessageDialog(null, "Please input a correct difficulty");
		         		difficulty = JOptionPane.showInputDialog(null, "Choose a difficulty\nType E for Easy, M for Medium, and H for Hard");
		         		if(difficulty.equalsIgnoreCase("E"))
		         		{
		         			TetrisWorld game = new TetrisWorld(0);
		         		}
		         		else if(difficulty.equalsIgnoreCase("M"))
		         		{
		         			TetrisWorld game = new TetrisWorld(1);
		         		}
		         		else if(difficulty.equalsIgnoreCase("H"))
		         		{
		         			TetrisWorld game = new TetrisWorld(2);
		         		}
		         	}
		         }
		         }
		      else
		         {
		         System.exit(0);
		         } 
		     
   }
}