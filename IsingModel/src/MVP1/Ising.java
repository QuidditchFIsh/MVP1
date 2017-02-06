package MVP1;
import java.util.Random;
import java.awt.image.BufferedImage;
import java.lang.Math;
import java.io.BufferedWriter;
import java.io.FileWriter;
/*
 * Author: Nye Baker
 * Date Created: 20/01/2017
 * Description: Simulation of  a magnetic system only concidering spins of each atom on a fixed lattice Using the Ising Model
 */
//concider changing the grid to a boolean grid....
public class Ising 
{
	@SuppressWarnings("resource")
	public static void main(String [ ] args) throws Exception
	{
		//In the end these should be passed in by the user 
		//WILL NEED TO CREATE AN EXCEPTION TO HANDLE IS DYNAMICS IS PASSED IN WRONG
/*
 * n:size of the grid to be made
 * iterations:number of cycles which the simulation will perform
 * dynamics: 1=Kawasaki Dynamics 0: Galuber dynamics
 * temp is the temperature of the system in kelvin
 */
		int iterations =1000000;
		int n = 50;
		int dynamics =0;
		int [][] ising_Grid = new int[n][n];
		//A boolean to turn the graphics on or off
		boolean graphic = false;
		
		//Create a Buffered wirter to write the data to a file for the magnetisation
		BufferedWriter bw = new BufferedWriter(new FileWriter("output2"));
		
		Random rand = new Random();
		double temp = 0.1;
		if(temp < 0)
		{
			throw new Exception("Temp Can't be negitive you idiot");
		}
		try
		{
		//if(dynamics == 0)
		//{
		for (int i=0;i<n;i++)
		{
			for(int j =0;j<n;j++)
			{
				/*
				ising_Grid[i][j] = rand.nextInt(2);
				if(ising_Grid[i][j]==0)
				{
					ising_Grid[i][j] -= 1;
				//I THINK THERE IS A BETTER WAY TO DO THIS BUT THIS WILL DO FOR NOW
				 * 
				 *
				ising_Grid[i][j] =-1;
				}
				*/
				ising_Grid[i][j] =-1;
			}
		}
	//	}
		/*
		else
		{
			for(int i=0;i<n;i++)
				for(int j=0;j<n;j++)
				{
					if ( i < Math.floor(n/2))
					{
						ising_Grid[i][j] = 1;
					}
					else
					{
						ising_Grid[i][j] = -1;
					}
				}
		}
		*/
		//Initalise the model graphics WILL WANT TO INITALISE IT DIFFERENTLY FOR THE DIFFERENT ALGORITHMS SO CHANGE THIS
		BufferedImage bi = new BufferedImage(n, n, BufferedImage.TYPE_INT_RGB);
		if(graphic)
			graphics.initaslise(ising_Grid, bi);
		
		
		if(dynamics == 0)
		{
			//To calcuate the magnetisation loop over the program many times and record the magnetisation
			//at a certain temp
			//for(int k = 0; k < 4 ; k++)
				for(temp = 1.5; temp < 3.5 ; temp = temp + 0.01)
				{
					for (int i=0;i<n;i++)
						for(int j =0;j<n;j++)
						{
							//ising_Grid[i][j] = rand.nextInt(2);
							ising_Grid[i][j] = 1;
							//if(ising_Grid[i][j]==0)
							//{
								//ising_Grid[i][j] -= 1;
							//I THINK THERE IS A BETTER WAY TO DO THIS BUT THIS WILL DO FOR NOW
							
							//}
						}
							
					Glauber.glauber(ising_Grid, iterations,temp,bi,bw,graphic);
					System.out.println(temp);
				}
			bw.close();
		}
		else
		{
			//Kawaski
			// To set up the type of dyniamics the initalisation needs to be different instaed of just random we could choose 
			//the inital conditions to be half spin up and half spin down 
			Kawaski.kawaski(ising_Grid, iterations, temp, bi,graphic);
		}
		}
		catch(Exception e)
		{
			System.out.println("you prune,your mother was a hamster and your father smelt of elderberries");
			e.printStackTrace();
			System.exit(0);
		}
		
		
	}
	
}
