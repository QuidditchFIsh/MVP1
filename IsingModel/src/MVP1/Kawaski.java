package MVP1;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Kawaski extends Functions
{
	public static void kawaski(int[][] ising,int iterations,int temp,BufferedImage bi)
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
			if(ising[randi_1][randj_1] != ising[randi_2][randj_2])
			{
			//If statement to check if the positions picked are not anywhere near each other 
			//In vertical,horizonatl and diagonal directions
			if(Math.abs(randi_1 - randi_2) <=2 || Math.abs(randj_1 - randj_2) <=2)
			{
				
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
				graphics.update(ising, bi);
				
			}
			}
			
				
		}
		
	}
}
