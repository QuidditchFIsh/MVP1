package MVP1;

import java.util.Random;

public class Kawaski 
{
	public static void kawaski(int[][] ising,int iterations)
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
		}
		
	}
}
