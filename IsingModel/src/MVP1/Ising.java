package MVP1;
import java.util.Random;
import java.lang.Math;
/*
 * Author: Nye Baker
 * Date Created: 20/01/2017
 * Description: Simulation of  a magnetic system only concidering spins of each atom on a fixed lattice Using the Ising Model
 */

public class Ising 
{
	public static void main(String [ ] args)
	{
		//In the end these should be passed in by the user 
		//WILL NEED TO CREATE AN EXCEPTION TO HANDLE IS DYNAMICS IS PASSED IN WRONG
/*
 * n:size of the grid to be made
 * iterations:number of cycles which the simulation will perform
 * dynamics: 1=Kawasaki Dynamics 0: Galuber dynamics
 */
		int iterations =10;
		int n = 5;
		int dynamics =0;
		int [][] ising_Grid = new int[n][n];
		
		Random rand = new Random();
		
		for (int i=0;i<n;i++)
		{
			for(int j =0;j<n;j++)
			{
				ising_Grid[i][j] = rand.nextInt(2);
				if(ising_Grid[i][j]==0)
				{
					ising_Grid[i][j] -= 1;
				//I THINK THERE IS A BETTER WAY TO DO THIS BUT THIS WILL DO FOR NOW
				}
			}
		}
		
		
	}
	
}