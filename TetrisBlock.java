/*
 *Bryan Yang
 *Gallatin
 *1st Period
 *Class that creates TetrisBlock objects
 */
import java.util.*;
import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.util.ArrayList;
import java.awt.Color;
/**
 * 
 * @author Bryan Yang
 *Class that creates TetrisBlock objects
 *
 */
public class TetrisBlock 
{
	private ArrayList<TetrisBlockComponent> shape;
	private TetrisBlockComponent anchor;
	private String id;
	private Grid<Actor> gr;
	private int position;


	/**
	 * Constructs a TetrisBlock
	 *A TetrisBlock is made up of an ArrayList of TetrisBlockComponents called shape and
	 *has an anchor, id indicating which type of block it is, position, and grid that it belongs in
	 * @param g the Grid it is in
	 * @param s the ID
	 */
	public TetrisBlock(Grid g, String s)
	{
		switch(s)
		{
			case "I":
				position=0;
				id=s;
				gr=g; 
				shape = new ArrayList<TetrisBlockComponent>();
				anchor = new TetrisBlockComponent(new Location(0,4),1);
				shape.add(anchor);
				shape.add(new TetrisBlockComponent(new Location(0,anchor.getLocation().getCol()-1),2));
				shape.add(new TetrisBlockComponent(new Location(0,anchor.getLocation().getCol()+1),3));
				shape.add(new TetrisBlockComponent(new Location(0,anchor.getLocation().getCol()+2),4));
				for(int i=0;i<shape.size();i++)
				{
					shape.get(i).setColor(Color.CYAN);
				}
				break;
			case "T":		
				position=0;
				id=s;
				gr=g;
				shape = new ArrayList<TetrisBlockComponent>();
				anchor = new TetrisBlockComponent(new Location(1,5),1);
				shape.add(anchor);
				shape.add(new TetrisBlockComponent(new Location(1,anchor.getLocation().getCol()-1),2));
				shape.add(new TetrisBlockComponent(new Location(0,anchor.getLocation().getCol()),3));
				shape.add(new TetrisBlockComponent(new Location(1,anchor.getLocation().getCol()+1),4));
				
				for(int i=0;i<shape.size();i++)
				{
					shape.get(i).setColor(Color.MAGENTA);
				}
				break;
			case "J":			
				position=0;
				id=s;
				gr=g;
				shape = new ArrayList<TetrisBlockComponent>();
				anchor = new TetrisBlockComponent(new Location(1,3),1);
				shape.add(anchor);
				shape.add(new TetrisBlockComponent(new Location(1,anchor.getLocation().getCol()-1),3));
				shape.add(new TetrisBlockComponent(new Location(0,anchor.getLocation().getCol()-1),2));
				shape.add(new TetrisBlockComponent(new Location(1,anchor.getLocation().getCol()+1),4));
				for(int i=0;i<shape.size();i++)
				{
					shape.get(i).setColor(Color.BLUE);
				}
				break;
			case "L":
				position=0;
				id=s;
				gr=g;
				shape = new ArrayList<TetrisBlockComponent>();
				anchor = new TetrisBlockComponent(new Location(1,3),1);
				shape.add(anchor);
				shape.add(new TetrisBlockComponent(new Location(1,anchor.getLocation().getCol()-1),2));
				shape.add(new TetrisBlockComponent(new Location(0,anchor.getLocation().getCol()+1),4));
				shape.add(new TetrisBlockComponent(new Location(1,anchor.getLocation().getCol()+1),3));
				for(int i=0;i<shape.size();i++)
				{
					shape.get(i).setColor(Color.ORANGE);
				}
				break;
				
			case "O":		
				position=0;
				id=s;
				gr=g;
				shape = new ArrayList<TetrisBlockComponent>();
				anchor = new TetrisBlockComponent(new Location(0,4),1);
				shape.add(anchor);
				shape.add(new TetrisBlockComponent(new Location(1,anchor.getLocation().getCol()),3));
				shape.add(new TetrisBlockComponent(new Location(1,anchor.getLocation().getCol()+1),4));
				shape.add(new TetrisBlockComponent(new Location(0,anchor.getLocation().getCol()+1),2));
				for(int i=0;i<shape.size();i++)
				{
					shape.get(i).setColor(Color.YELLOW);
				}
				break;				
			case "S":			
				position=0;
				id=s;
				gr=g;
				shape = new ArrayList<TetrisBlockComponent>();
				anchor = new TetrisBlockComponent(new Location(1,4),1);
				shape.add(anchor);
				shape.add(new TetrisBlockComponent(new Location(0,anchor.getLocation().getCol()+1),4));
				shape.add(new TetrisBlockComponent(new Location(0,anchor.getLocation().getCol()),3));
				shape.add(new TetrisBlockComponent(new Location(1,anchor.getLocation().getCol()-1),2));
				for(int i=0;i<shape.size();i++)
				{
					shape.get(i).setColor(Color.GREEN);
				}
				break;				
			case "Z":
				position=0;
				id=s;
				gr=g;
				shape = new ArrayList<TetrisBlockComponent>();
				anchor = new TetrisBlockComponent(new Location(1,4),1);
				shape.add(anchor);
				shape.add(new TetrisBlockComponent(new Location(0,anchor.getLocation().getCol()-1),2));
				shape.add(new TetrisBlockComponent(new Location(0,anchor.getLocation().getCol()),3));
				shape.add(new TetrisBlockComponent(new Location(1,anchor.getLocation().getCol()+1),4));
				for(int i=0;i<shape.size();i++)
				{
					shape.get(i).setColor(Color.RED);
				}
				break;
		}										
	}
	/**
	 * Turns the TetrisBlock 90 degrees clockwise
	 */
	public void turn()
	{		
		
		switch(id)
		{
		case "I":
			
			if(canTurn())
			{
				position++;
				switch(position)
				{
				case 1:
					for(int i=0;i<shape.size();i++)
					{
						if(shape.get(i).getId()==2)
						{
							Location loc = getAnchor().getLocation();
							Location newloc= new Location(loc.getRow()-1,loc.getCol());
							if(shape.get(i).canMoveTo(gr,newloc))
							{
								shape.get(i).moveTo(newloc, gr);
							}
						}					
						if(shape.get(i).getId()==3)
						{
							Location loc = getAnchor().getLocation();
							Location newloc= new Location(loc.getRow()+1,loc.getCol());
							if(gr.isValid(newloc) && gr.get(newloc)==null)
							{
								shape.get(i).moveTo(newloc, gr);							
							}						
						}					
						if(shape.get(i).getId()==4)
						{
							Location loc = getAnchor().getLocation();
							Location newloc= new Location(loc.getRow()+2,loc.getCol());
							if(gr.isValid(newloc) && gr.get(newloc)==null)
							{
								shape.get(i).moveTo(newloc, gr);
							}				
						}
					}
					break;
				case 2:
				for(int i=0;i<shape.size();i++)
				{
					if(shape.get(i).getId()==2)
					{
						Location loc = getAnchor().getLocation();
						Location newloc= new Location(loc.getRow(),loc.getCol()+1);
						if(shape.get(i).canMoveTo(gr,newloc))
						{
							shape.get(i).moveTo(newloc, gr);
						}
					}					
					if(shape.get(i).getId()==3)
					{
						Location loc = getAnchor().getLocation();
						Location newloc= new Location(loc.getRow(),loc.getCol()-1);
						if(gr.isValid(newloc) && gr.get(newloc)==null)
						{
							shape.get(i).moveTo(newloc, gr);							
						}						
					}					
					if(shape.get(i).getId()==4)
					{
						Location loc = getAnchor().getLocation();
						Location newloc= new Location(loc.getRow(),loc.getCol()-2);
						if(gr.isValid(newloc) && gr.get(newloc)==null)
						{
							shape.get(i).moveTo(newloc, gr);
						}				
					}
				}
					break;
				case 3:
					for(int i=0;i<shape.size();i++)
					{
						if(shape.get(i).getId()==2)
						{
							Location loc = getAnchor().getLocation();
							Location newloc= new Location(loc.getRow()+1,loc.getCol());
							if(shape.get(i).canMoveTo(gr,newloc))
							{
								shape.get(i).moveTo(newloc, gr);
							}
						}					
						if(shape.get(i).getId()==3)
						{
							Location loc = getAnchor().getLocation();
							Location newloc= new Location(loc.getRow()-1,loc.getCol());
							if(gr.isValid(newloc) && gr.get(newloc)==null)
							{
								shape.get(i).moveTo(newloc, gr);							
							}						
						}					
						if(shape.get(i).getId()==4)
						{
							Location loc = getAnchor().getLocation();
							Location newloc= new Location(loc.getRow()-2,loc.getCol());
							if(gr.isValid(newloc) && gr.get(newloc)==null)
							{
								shape.get(i).moveTo(newloc, gr);
							}				
						}
					}
					break;
				}				
			}
			break;
		case "T":
			if(canTurn())
			{
				position++;
				switch(position)
				{
				case 1:
					Location loc = getAnchor().getLocation();
					Location newloc= new Location(loc.getRow()+1,loc.getCol());
					getBlock(2).moveTo(newloc, gr);										
					break;
				case 2:
					loc = getAnchor().getLocation();
					Location newloc2= new Location(loc.getRow(),loc.getCol()-1);
					getBlock(2).moveTo(newloc2,gr);				
					Location newloc3 = new Location(loc.getRow()+1,loc.getCol());
					getBlock(3).moveTo(newloc3,gr);
					break;
				case 3:
					loc = getAnchor().getLocation();
					newloc3= new Location(loc.getRow()-1,loc.getCol());
					Location newloc4 = new Location(loc.getRow()+1,loc.getCol());
					getBlock(3).moveTo(newloc3,gr);
					getBlock(4).moveTo(newloc4,gr);
					
					break;
				case 4:
					loc = getAnchor().getLocation();
					newloc4 = new Location(loc.getRow(),loc.getCol()+1);
					getBlock(4).moveTo(newloc4,gr);
					position=0;
					break;
				}
			}
				
		}

	
	}
	/**
	 * Returns whether the TetrisBlock can turn
	 * @return whether the TetrisBlock can turn
	 */
	public boolean canTurn()
	{
		ArrayList <Boolean> list = new ArrayList<Boolean>();
		boolean move = true;
		switch(id)
		{
		case "I":
			switch(position)
			{
			case 0:
				sortById();
				for(int i=0;i<shape.size();i++)
				{
					if(shape.get(i).getId()==2)
					{
						Location loc = getAnchor().getLocation();
						Location newloc= new Location(loc.getRow()-1,loc.getCol());
						if(shape.get(i).canMoveTo(gr,newloc))
						{
							list.add(true);
						}
						else
						{
							list.add(false);
						}
					}
					
					if(shape.get(i).getId()==3)
					{
						Location loc = getAnchor().getLocation();
						Location newloc= new Location(loc.getRow()+1,loc.getCol());
						if(gr.isValid(newloc) && gr.get(newloc)==null)
						{
							list.add(true);
							
						}
						else
						{
							list.add(false);
						}
					}
					
					if(shape.get(i).getId()==4)
					{
						Location loc = getAnchor().getLocation();
						Location newloc= new Location(loc.getRow()+2,loc.getCol());
						if(gr.isValid(newloc) && gr.get(newloc)==null)
						{
				
							list.add(true);
						}
						else
						{
							list.add(false);
						}
					}
				}
				
				boolean finished = false;
				int r=0;
				while(r<list.size() && !finished)
				{
					if(list.get(r)==false)
					{
						move=false;
						finished=true;
						
					}
					r++;
				}
				
				for(int p=0;p<list.size();p++)
				{
					list.remove(p);
				}
				break;
			case 1:
				sortById();
				for(int i=0;i<shape.size();i++)
				{
					if(shape.get(i).getId()==2)
					{
						Location loc = getAnchor().getLocation();
						Location newloc= new Location(loc.getRow(),loc.getCol()+1);
						if(shape.get(i).canMoveTo(gr,newloc))
						{
							list.add(true);
						}
						else
						{
							list.add(false);
						}
					}
					
					if(shape.get(i).getId()==3)
					{
						Location loc = getAnchor().getLocation();
						Location newloc= new Location(loc.getRow(),loc.getCol()-1);
						if(gr.isValid(newloc) && gr.get(newloc)==null)
						{
							list.add(true);
							
						}
						else
						{
							list.add(false);
						}
					}
					
					if(shape.get(i).getId()==4)
					{
						Location loc = getAnchor().getLocation();
						Location newloc= new Location(loc.getRow(),loc.getCol()-2);
						if(gr.isValid(newloc) && gr.get(newloc)==null)
						{
				
							list.add(true);
						}
						else
						{
							list.add(false);
						}
					}
				}
				
				finished = false;
				r=0;
				while(r<list.size() && !finished)
				{
					if(list.get(r)==false)
					{
						move=false;
						finished=true;
					}
				r++;
				}
				for(int p=0;p<list.size();p++)
				{
					list.remove(p);
				}
				break;
			case 2: 
				sortById();
				for(int i=0;i<shape.size();i++)
				{
					if(shape.get(i).getId()==2)
					{
						Location loc = getAnchor().getLocation();
						Location newloc= new Location(loc.getRow()+1,loc.getCol());
						if(shape.get(i).canMoveTo(gr,newloc))
						{
							list.add(true);
						}
						else
						{
							list.add(false);
						}
					}
					
					if(shape.get(i).getId()==3)
					{
						Location loc = getAnchor().getLocation();
						Location newloc= new Location(loc.getRow()-1,loc.getCol());
						if(gr.isValid(newloc) && gr.get(newloc)==null)
						{
							list.add(true);
							
						}
						else
						{
							list.add(false);
						}
					}
					
					if(shape.get(i).getId()==4)
					{
						Location loc = getAnchor().getLocation();
						Location newloc= new Location(loc.getRow()-2,loc.getCol());
						if(gr.isValid(newloc) && gr.get(newloc)==null)
						{
				
							list.add(true);
						}
						else
						{
							list.add(false);
						}
					}
				}
				
				finished = false;
				r=0;
				while(r<list.size() && !finished)
				{
					if(list.get(r)==false)
					{
						move=false;
						finished=true;
					}
				r++;
				}
				for(int p=0;p<list.size();p++)
				{
					list.remove(p);
				}
				position=0;
				break;
				
			}
			break;
		case "T":
			move=true;
			switch(position)
			{
			case 0:
				Location loc = getAnchor().getLocation();
				Location newloc= new Location(loc.getRow(),loc.getCol()+1);
				if(gr.isValid(newloc) && gr.get(newloc)==null)
				{
					move=true;	
				}			
				break;
			case 1:
				loc = getAnchor().getLocation();
				newloc= new Location(loc.getRow()+1,loc.getCol());
				if(gr.isValid(newloc) && gr.get(newloc)==null)
				{
					move=true;	
				}	
				break;
			case 2:
				loc = getAnchor().getLocation();
				break;
			case 3:
				loc = getAnchor().getLocation();
				break;
			}
			break;
		}
		
				return move;
	}
	/**
	 * Returns the TetrisBlockComponent with the given ID
	 * @param p the ID
	 * @return the TetrisBlockComponent with the given ID
	 */
	public TetrisBlockComponent getBlock(int p)
	{
		for(int i=0;i<shape.size();i++)
		{
			if(shape.get(i).getId()==p)
			{
				return shape.get(i);
			}
		}
		return null;
	}
	/**
	 * Returns whether the location is occupied by the TetrisBlock
	 * @param loc the location that is checked
	 * @return whether the location is occupied by the TetrisBlock
	 */
	public boolean partOfBlock(Location loc)
	{
		for(int i=0;i<shape.size();i++)
		{
			if(shape.get(i).getLocation().equals(loc))
			{
				return true;
			}
		}
		return false;
	}
	/**
	 * Sorts shape by ID in ascending order
	 */
	public void sortById()
	{
		ArrayList<TetrisBlockComponent> sort = shape;
		for(int p=0;p<sort.size()-1;p++)
			for(int i=0;i<sort.size()-p-1;i++)
			{
				if(sort.get(i).getId()>sort.get(i+1).getId())
				{
					TetrisBlockComponent temp = sort.get(i);
					sort.set(i,sort.get(i+1));
					sort.set(i+1, temp);
				}
			}
	}
	/**
	 * Moves the TetrisBlock down
	 */
	public void act()
	{
		boolean canMove = true;
	
		
			ArrayList <TetrisBlockComponent> list = exposed();
			for(int i=0;i<list.size();i++)
			{
				Location next = list.get(i).getLocation().getAdjacentLocation(Location.SOUTH);
				if(!gr.isValid(next) || gr.get(next)!=null)
				{
					canMove=false;
				}
			}
			if(canMove)
			{
				for(int i=0;i<shape.size();i++)
				{
					shape.get(i).moveDown(gr);
				}
			}						
	}
	/**
	 * Returns the TetrisBlockComponents in shape that can hit something 
	 * @return the TetrisBlockComponents in shape that can hit something 
	 */
	public ArrayList exposed()
	{
		ArrayList <TetrisBlockComponent> expose = new ArrayList<TetrisBlockComponent>();
		for(int i=0;i<shape.size();i++)
		{
			if(gr.get(shape.get(i).getLocation().getAdjacentLocation(Location.SOUTH))==null)
			{
				expose.add(shape.get(i));
			}
		}
		return expose;
	}
	/**
	 * Moves the TetrisBlock left
	 */
	public void moveLeft()
	{
		sortLeftToRight();
		if(gr.isValid(shape.get(0).getLocation().getAdjacentLocation(Location.LEFT)))
		{
			if(gr.get(shape.get(0).getLocation().getAdjacentLocation(Location.LEFT))==null);
			{
			
				for(int i=0;i<shape.size();i++)
				{
					shape.get(i).moveLeft(gr);
				}
			}	
		}
	}
	/**
	 * Moves the TetrisBlock right
	 */
	public void moveRight()
	{
		sortRightToLeft();
		if(gr.isValid(shape.get(0).getLocation().getAdjacentLocation(Location.RIGHT)))
		{
			if(gr.get(shape.get(0).getLocation().getAdjacentLocation(Location.RIGHT))==null);
		{

			
			for(int i=0;i<shape.size();i++)
			{
				shape.get(i).moveRight(gr);
			}
		}	
		}
	}
	/**
	 * Sorts shape by location from left to right  
	 */
	public void sortLeftToRight()
	{
		ArrayList<TetrisBlockComponent> sort = shape;
		for(int p=0;p<sort.size()-1;p++)
			for(int i=0;i<sort.size()-p-1;i++)
			{
				if(sort.get(i).getLocation().getCol()>sort.get(i+1).getLocation().getCol())
				{
					TetrisBlockComponent temp = sort.get(i);
					sort.set(i,sort.get(i+1));
					sort.set(i+1, temp);
				}
			}
	}
	/**
	 * Sorts shape by location from right to left 
	 */
	public void sortRightToLeft()
	{
		ArrayList<TetrisBlockComponent> sort = shape;
		for(int p=0;p<sort.size()-1;p++)
			for(int i=0;i<sort.size()-p-1;i++)
			{
				if(sort.get(i).getLocation().getCol()<sort.get(i+1).getLocation().getCol())
				{
					TetrisBlockComponent temp = sort.get(i);
					sort.set(i,sort.get(i+1));
					sort.set(i+1, temp);
				}
			}
	}
	/**
	 * Sorts shape by location from bottom to top 
	 */
	public void sortBottomToTop()
	{
		for(int p=0;p<shape.size()-1;p++)
			for(int i=0;i<shape.size()-p-1;i++)
			{
				if(shape.get(i).getLocation().getRow()<shape.get(i+1).getLocation().getRow())
				{
					TetrisBlockComponent temp = shape.get(i);
					shape.set(i,shape.get(i+1));
					shape.set(i+1, temp);
				}
			}
		
	}
	/**
	 * Returns an ArrayList of the bottommost pieces of the TetrisBlock
	 * @return an ArrayList of the bottommost pieces of the TetrisBlock
	 */
	public ArrayList getBottomMost()
	{
		ArrayList <TetrisBlockComponent> bottom = new ArrayList<TetrisBlockComponent>();
		sortBottomToTop();
		TetrisBlockComponent block = shape.get(0);
		for(int c=0;c<shape.size();c++)
		{
			if(shape.get(c).getLocation().getRow()>=block.getLocation().getRow())
			{
				bottom.add(shape.get(c));
			}
		}
		return bottom;
	}

	/**
	 * Returns the shape 
	 * @return the shape
	 */
	public ArrayList getShape()
	{
		return shape;
	}
	/**
	 * Returns the anchor
	 * @return the anchor
	 */
	public TetrisBlockComponent getAnchor()
	{
		return anchor;
	}
	/**
	 * Returns the ID 
	 * @return the ID 
	 */
	public String getId()
	{
		return id;
	}
	/**
	 * Returns the position 
	 * @return the position
	 */
	public int getPosition()
	{
		return position;
	}
	/**
	 * Returns the string version of TetrisBlock
	 */
	public String toString()
	{
		return id;
	}
	/**
	 * Returns whether the TetrisBlock can move down
	 * @return whether the TetrisBlock can move down
	 */
	public boolean canMoveDown()
	{
		boolean canMove=true;
		ArrayList <TetrisBlockComponent> list = getBottomMost();
		for(int i=0;i<list.size();i++)
		{
			Location next = list.get(i).getLocation().getAdjacentLocation(Location.SOUTH);
			if(!gr.isValid(next) || gr.get(next)!=null)
			{
				canMove=false;
			}
		}
		
		return canMove;
		
	}
	

}