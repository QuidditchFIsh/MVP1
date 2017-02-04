package MVP1;
import java.lang.Math;
import java.util.Random;

public class Functions 
{
	public static double energy(int [][] ising,int i,int j)
	{
		int n = ising[0].length;
		//Here i and j will be the random point which was chosen in the previous dynamics class
		double sum =0;

		sum = ising[Math.floorMod(i+1, n)][j] + ising[Math.floorMod(i-1, n)][j] + ising[i][Math.floorMod(j-1, n)] + ising[i][Math.floorMod(j+1, n)];
		sum *= ising[i][j];

		return sum;
	}
	public static double energyHalf(int[][] ising ,int i,int j)
	{
		int n = ising[0].length;
		double sum =0;
		//This method will only sum over the up and left modules to get the total energy divided by 2.
		sum = ising[Math.floorMod(i+1, n)][j] + ising[Math.floorMod(i-1, n)][j];
		sum *= ising[i][j];

		return sum;
		
	}
	public static boolean acceptOrReject(double energyDiff,double temp)
	//This method will accept or reject the change based on certain conditions
	{
		Random rand = new Random();
		double probability =-1;
		double r=-1;
		if(energyDiff < 0)
		{
			return true;
		}
		else
		{
			//note that we have set kB and J to 1 in this simulation 
			probability = Math.exp((-1*energyDiff)/temp);
			r = rand.nextDouble();
			if(probability >= r)
			{
				return true;
			}
			else
			{
				return false;
			}

		}

	}
	public static double totalEnergy(int[][] ising)
	{
		//Quick method to calculate the total energy there is a better way where yopu loop over only the up and left cells (CHEKCK THIS)!!!!!
		double sum=0;
		//double sum1=0;
		for(int i=0;i<ising[0].length;i++)
			for(int j =0;j<ising[0].length;j++)
			{
				sum+= energy(ising,i,j);
				//sum1 += energyHalf(ising,i,j);
			}
		return sum/2 ;
		
	}

	public static double normalisedTotalMagnetisation(int[][] ising)
	{
		int sum=0;
		for(int i=0;i<ising[0].length;i++)
			for(int j =0;j<ising[0].length;j++)
				sum=+ising[i][j];
		return sum;
		///(Math.pow((ising[0].length),2))
	}
	public static double normalisedTotalMagSquared(int[][] ising)
	{
		double sum =0;
		for(int i=0;i<ising[0].length;i++)
			for(int j =0;j<ising[0].length;j++)
			{
				sum+=(Math.pow(ising[i][j],2));
				
			}
			
		
		return sum;
		///(Math.pow((ising[0].length),2))
		
	}


}
