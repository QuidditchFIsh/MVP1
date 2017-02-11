package MVP1;
//NEED TO CONSIDER THE CASE WHERE THE RANDOM NUMBERS ARE THE SAME	
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Kawaski extends Functions
{
	public static double[] kawaski(int[][] ising,int iterations,double temp,BufferedImage bi,boolean graphic) throws InterruptedException
	{
		int n = ising[0].length;
		Random rand = new Random();
		double energyBefore =0,energyAfter =0,energyDiff=0;
		double avgMag=0;
		double avgEnergy=0;
		int counter=0;
		double suseptability=0;
		double heatCapcity =0;
		double[] magnetisation = new double[(int) ((iterations*0.9)/10)] ;
		double[] energy = new double[(int) ((iterations*0.9)/10)] ;
		double[] results = new double[9];

		int randi_1 = 0;
		int randj_1 = 0;
		int randi_2 = 0;
		int randj_2 = 0;

		for(int i=0;i<iterations;i++)
		{
			//Pick two random points in the ising Grid making sure that they are not the same.IM NOT SURE THAT THIS IS THE SAFEST SOLUTION SINCE THERE STILL IS A POSSIBILITY THAT
			//THE SECOND RANDOM NUMBER COULD BE EQUAL TO THE FIRST

			randi_1 = rand.nextInt(n);
			randj_1 = rand.nextInt(n);
			randi_2 = rand.nextInt(n);
			randj_2 = rand.nextInt(n);

			//this is a check to make sure that the swap will actually do somthing 
			//CHECK IF THIS CONDITION ADDS ONTO THE ITERATIONS OR NOT

			if(ising[randi_1][randj_1] != ising[randi_2][randj_2])
			{
				//If statement to check if the positions picked are not anywhere near each other 
				//In vertical,horizonatl and diagonal directions
				//	System.out.printf("%d %d %d %d \n",randi_1,randj_1,randi_1,randj_2);
				if(nearestNeighbors(ising,randi_1,randi_2,randj_1,randj_2,n))
				{
					//calculate the energy of the system with the correction to compensate for the over counting 
					energyBefore = energy(ising,randi_1,randj_1) + energy(ising,randi_2,randj_2) - (ising[randi_1][randj_1] * ising[randi_2][randj_2]);
					ising[randi_1][randj_1] *=-1;
					ising[randi_2][randj_2] *=-1;
					energyAfter = energy(ising,randi_1,randj_1) + energy(ising,randi_2,randj_2) - (ising[randi_1][randj_1] * ising[randi_2][randj_2]);
				}

				else
					//If they are not near each other we can proceed as usual
				{
					//calcuate the energy of the points before the swap
					energyBefore = energy(ising,randi_1,randj_1) + energy(ising,randi_2,randj_2);
					ising[randi_1][randj_1] *=-1;
					ising[randi_2][randj_2] *=-1;
					energyAfter = energy(ising,randi_1,randj_1) + energy(ising,randi_2,randj_2);
				}
				if(acceptOrReject(-energyBefore + energyAfter,temp) == false)
				{
					ising[randi_1][randj_1] *=-1;
					ising[randi_2][randj_2] *=-1;
				}
				//&& iterations > iterations*0.95
				if (i % n*n ==0 ) 
				{ 
					magnetisation[counter] = normalisedTotalMagnetisation(ising);
					energy[counter] = totalEnergy(ising)/(n*n);
					counter++;
					if(graphic)
						graphics.update(ising, bi);
					avgMag += normalisedTotalMagnetisation(ising);
					avgEnergy += totalEnergy(ising)/(n*n);
				}	
				
			//	if(graphic)
				//	graphics.update(ising, bi);
				

				
			}
		}
		avgMag /= counter;
		avgEnergy/= counter;
		suseptability = standardDeviation(magnetisation,counter);
		heatCapcity = standardDeviation(energy,counter);
		
		results[0]=temp;
		results[1]=avgMag;
		results[3]=avgEnergy;
		results[5]=suseptability/(temp*n*n);
		results[7]=heatCapcity/(n*n*temp*temp);
		return results;
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
