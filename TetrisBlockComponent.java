/*
 *Bryan Yang
 *Gallatin
 *1st Period
 *Class the creates TetrisBlockComponent objects
 */
import java.awt.Color;

import info.gridworld.actor.*;
import info.gridworld.grid.*;
/**
 * 
 * @author Bryan Yang
 * Class the creates TetrisBlockComponent objects
 */
public class TetrisBlockComponent extends Actor
{
	private Location loc;
	private int id;

	/**
	 * Constructs a TetrisBlockComponent
	 * A TetrisBlockComponent has a location and an ID
	 * @param l the location
	 * @param i the ID
	 */
	public TetrisBlockComponent(Location l, int i)
	{
		id=i;
		loc=l;
	}
	/**
	 * Returns the location
	 * @return the location
	 */
	public Location getLocation()
	{
		return loc;
	}
	/**
	 * Sets the location 
	 * @param l the location that is set
	 */
	public void setLocation(Location l)
	{
		loc=l;
	}
	/**
	 * Returns the ID
	 * @return the ID
	 */
	public int getId()
	{
		return id;
	}
	/**
	 * Moves the TetrisBlockComponent to a specified location
	 * @param loc the location to be moved to
	 * @param g the grid 
	 */
	public void moveTo(Location loc, Grid g)
	{
		Grid <Actor> gr = g;
		Location curr = getLocation();
		Location next = loc;
		if(gr.isValid(next) && gr.get(next)==null)
		{
			moveTo(next);
			setLocation(next);
		}
	}
	/**
	 * Moves the TetrisBlockDown down 
	 * @param g the grid
	 */
	public void moveDown(Grid g)
	{
		Grid <Actor> gr = g;
		Location curr = getLocation();
		Location next = new Location(curr.getRow()+1, curr.getCol());

		if (canMoveDown(gr))
		{
			moveTo(next);
			setLocation(next);
		}
	}
	/**
	 * Returns whether the TetrisBlockComponent can move to a specified location
	 * @param g the grid
	 * @param loc the location to be moved to
	 * @return whether the TetrisBlockComponent can move to a specified location
	 */
	public boolean canMoveTo(Grid g, Location loc)
	{
		Grid<Actor> gr = g;
		return gr.isValid(loc) && gr.get(loc)==null;		
	}
	/**
	 * Returns whether the TetrisBlockComponent can move down
	 * @param g the grid
	 * @return whether the TetrisBlockComponent can move down
	 */
	public boolean canMoveDown(Grid g)
	{
		Grid <Actor> gr = g;
		Location curr = getLocation();
		Location next = new Location(curr.getRow()+1, curr.getCol());
		return gr.isValid(next) && gr.get(next)==null;
	}
	/**
	 * Moves the TetrisBlockComponent to the left
	 * @param g the grid
	 */
	public void moveLeft(Grid g)
	{
		Grid <Actor> gr = g;
		Location curr = getLocation();
		Location next = new Location(curr.getRow(), curr.getCol()-1);

		if (gr.isValid(next) && gr.get(next)==null)
		{
			moveTo(next);
			setLocation(next);
		}
	}
	/**
	 * Moves the TetrisBlockComponent to the right
	 * @param g the grid
	 */
	public void moveRight(Grid g)
	{
		Grid <Actor> gr = g;
		Location curr = getLocation();
		Location next = new Location(curr.getRow(), curr.getCol()+1);

		if (gr.isValid(next) && gr.get(next)==null)
		{
			moveTo(next);
			setLocation(next);
		}
	}
	
}