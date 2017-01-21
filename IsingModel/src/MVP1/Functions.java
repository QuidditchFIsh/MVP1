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
	public static void totalEnergy()
	{
		
	}

}
