package MVP1;
//NEED TO CONSIDER THE CASE WHERE THE RANDOM NUMBERS ARE THE SAME	
import java.awt.image.BufferedImage;
import java.util.Random;

public class Kawaski extends Functions
{
	public static void kawaski(int[][] ising,int iterations,double temp,BufferedImage bi)
	{
		int n = ising[0].length;
		Random rand = new Random();
		double energyBefore =0,energyAfter =0,energyDiff=0;

		for(int i=0;i<iterations;i++)
		{
			//Pick two random points in the ising Grid
			int randi_1 = rand.nextInt(n);
			int randj_1 = rand.nextInt(n);
			int randi_2 = rand.nextInt(n);
			int randj_2 = rand.nextInt(n);
			//CHECK IF THIS CONDITION ADDS ONTO THE ITERATIONS OR NOT
			//if(ising[randi_1][randj_1] != ising[randi_2][randj_2])
			//{
			//If statement to check if the positions picked are not anywhere near each other 
			//In vertical,horizonatl and diagonal directions
			System.out.println(randi_1 + " " + randj_1 + " " + randi_2 + " " + randj_2);
			if(nearestNeighbors(ising,randi_1,randi_2,randj_1,randj_2,n))
			{
				System.out.println("welp");
			}
			else
				//If they are not near each other we can proceed as usual
			{
				//calcuate the energy of the points before the swap
				energyBefore = energy(ising,randi_1,randj_1) + energy(ising,randi_2,randj_2);
				//make the swap
				ising[randi_1][randj_1] *=-1;
				ising[randi_2][randj_2] *=-1;
				energyAfter = energy(ising,randi_1,randj_1) + energy(ising,randi_2,randj_2);
				energyDiff = energyAfter - energyBefore;
				if(acceptOrReject(energyDiff,temp) == false)
				{
					ising[randi_1][randj_1] *=-1;
					ising[randi_2][randj_2] *=-1;
				}
				//graphics.update(ising, bi);

			}
		}


	}
	public static boolean nearestNeighbors(int[][] ising,int randi_1,int randi_2,int randj_1,int randj_2,int n)
	/*
	 * This method will determin weather or not the random points have any nearest neighbors in common and if they do which type the neighbors are
	 */
	{
		if(randi_1 == randi_2 || randj_1 == randj_2)
		{
			if(Math.floorMod(randi_1+1, n) ==randi_2 || Math.floorMod(randi_1-1, n) ==randi_2 || Math.floorMod(randj_1+1, n) ==randj_2  || Math.floorMod(randj_1-1, n) ==randj_2)
			{
				return true;
			}
		}
		return false;
	
	}

}


//}
