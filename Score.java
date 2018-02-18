/*
 *Bryan Yang
 *Gallatin
 *1st Period
 *Class that creates TetrisBlock objects
 */
 
/**
 * @author Bryan Yang
 * Class that makes score objects and implements the Comparable interface
 */
public class Score implements Comparable
{
	private String name;
	private int score;
	/**
	 *Constructs a score object
	 *A score has a name and a score
	 */
	public Score(String s,int i)
	{
		name=s;
		score=i;
	}
	/**
	 *Returns the difference in score between two score objects  
	 *@param o the object to be compared to 
	 *@return the difference between the two scores
	 */
	public int compareTo(Object o)
	{
		return ((Score)o).score-score;
	}
	/**
	 *Returns the string version of score
	 */
	public String toString()
	{
		return name + " " + score;
	}
}