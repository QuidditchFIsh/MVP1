package MVP1;
import java.util.*;
import java.awt.image.BufferedImage;
import java.lang.Math;
import java.io.*;

/*
 * Author: Nye Baker
 * Date Created: 20/01/2017
 * Description: Simulation of  a magnetic system only concidering spins of each atom on a fixed lattice Using the Ising Model
 */
//concider changing the grid to a boolean grid....
public class Ising 
{
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
		
		boolean graphic = false;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter 1 to show Graphics and 0 for data Run");
		if(input.nextInt() == 1){graphic = true;}

		System.out.println("Enter the number of iterations:");
		int iterations =input.nextInt();

		System.out.println("Enter the size of the system (nxn):");
		int n = input.nextInt();

		System.out.println("Enter the temperature of the system");
		double temp = input.nextDouble();

		double increments = 0.1;
		int tempNum = 2;
		int runs = 0;
		double tempMax =0;
		int counter=0;
		if(!graphic)
		{
			System.out.println("Enter the increments the system should run in:");
			increments = input.nextDouble();

			System.out.println("Enter the number of runs:");
			runs = input.nextInt();
			
			System.out.println("Enter the max temp:");
			tempMax = input.nextDouble();
			
			tempNum = (int) Math.floor((tempMax - temp )/ increments);
			
		}

		if(temp < 0){throw new Exception("Temperature Can't be negitive");}
		System.out.println("Enter 0 for Galuber and 1 for Kawaski:");
		int dynamics =input.nextInt();

		int [][] ising_Grid = new int[n][n];
		//Create a Buffered wirter to write the data to a file for the magnetisation
		System.out.println("Enter the name of the file which the output is being written to:");
		BufferedWriter bw = new BufferedWriter(new FileWriter(input.next()));
		Random rand = new Random();

		double[][][] result = new double[runs][tempNum][9];
		if(!graphic)
			for(double[][] row:result)
				for(double[] coloum :row)
					Arrays.fill(coloum, 0);
				
		//0 temp , 1 avg mag,2 avg mag error, 3 avg energy, 4 avg energy error,5 suseptability, 6 sus error, 7 heat capacity, 8 Cv error

		try
		{
			//Initalise the model graphics WILL WANT TO INITALISE IT DIFFERENTLY FOR THE DIFFERENT ALGORITHMS SO CHANGE THIS
			BufferedImage bi = new BufferedImage(n, n, BufferedImage.TYPE_INT_RGB);
			if(graphic)
				graphics.initaslise(ising_Grid, bi);


			if(dynamics == 0)
			{
				//To calcuate the magnetisation loop over the program many times and record the magnetisation
				//at a certain temp

				if(!graphic)
					for(int k = 0; k < runs ; k++)
					{
						for(; temp < tempMax ; temp += increments )
						{
							
							for(int[] row : ising_Grid)
								Arrays.fill(row, -1);
							result[k][counter]=Glauber.glauber(ising_Grid, iterations,temp,bi,graphic);
							counter++;
							System.out.println("The temperature of the System is: " + temp);
						}
					}
				else
				{
					for(int[] row : ising_Grid)
						Arrays.fill(row, -1);

					Glauber.glauber(ising_Grid, iterations,temp,bi,graphic);
					System.out.println("The temperature of the System is: " + temp);
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
			e.printStackTrace();
			System.exit(0);
		}


	}

}
