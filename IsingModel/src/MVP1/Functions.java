package MVP1;
import java.lang.Math;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Functions 
{
	public static double energy(int [][] ising,int i,int j)
	{
		int n = ising[0].length;
		//Here i and j will be the random point which was chosen in the previous dynamics class
		double sum =0;

		sum = ising[Math.floorMod(i+1, n)][j] + ising[Math.floorMod(i-1, n)][j] + ising[i][Math.floorMod(j-1, n)] + ising[i][Math.floorMod(j+1, n)];
		sum *= ising[i][j];

		return -1*sum;
	}
	public static double energyHalf(int[][] ising ,int i,int j)
	{
		int n = ising[0].length;
		double sum =0;
		//This method will only sum over the up and left modules to get the total energy divided by 2.
		sum = ising[Math.floorMod(i+1, n)][j] + ising[i][Math.floorMod(j+1, n)];
		sum *= ising[i][j];

		return -1*sum;
		
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
		{
			for(int j =0;j<ising[0].length;j++)
			{
				sum+= energy(ising,i,j);
				//sum += energyHalf(ising,i,j);
			}
		}
		//divide by two to compensate for double counting
		return sum/2 ;
		
	}
	public static double totalEnergySqd(int[][] ising)
	{
		//Quick method to calculate the total energy there is a better way where yopu loop over only the up and left cells (CHEKCK THIS)!!!!!
		double sum=0;
		//double sum1=0;
		for(int i=0;i<ising[0].length;i++)
		{
			for(int j =0;j<ising[0].length;j++)
			{
				sum+= Math.pow(energy(ising,i,j),2);
				//sum1 += energyHalf(ising,i,j);
			}
		}
		return sum ;
		
	}
	

	public static double normalisedTotalMagnetisation(int[][] ising) throws InterruptedException
	{
		double sum=0;
		int n = ising[0].length;
		for(int i=0;i<n;i++)
		{
			for(int j =0;j<n;j++)
			{
				sum+=ising[i][j];
			}
		}
		//System.out.println(Math.abs(sum));
		//TimeUnit.SECONDS.sleep(1);
		return Math.abs(sum)/(n*n);
		///(Math.pow((ising[0].length),2))
	}
	public static double normalisedTotalMagSquared(int[][] ising) throws InterruptedException
	{
		double sum =0;
		int n = ising[0].length;
		for(int i=0;i<n;i++)
		{
			for(int j =0;j<n;j++)
			{
				sum += ising[i][j] * ising[i][j];
				//sum +=normalisedTotalMagnetisation(ising) * normalisedTotalMagnetisation(ising);
				
			}
		}
		
		//System.out.println(sum+"-");
		//TimeUnit.SECONDS.sleep(1);
		return sum/(n*n);
		///(Math.pow((ising[0].length),2))
		
	}
	public static double standardDeviation(double[] mag,double sweeps)
	{
		double mag1 =0,magSqd =0;
		
		for(int i=0;i<mag.length;i++)
		{
			mag1 += mag[i];
			magSqd+= mag[i] * mag[i];
		}
		//mag1 *=mag1;
		magSqd /= sweeps;
		mag1 /= sweeps;
		//System.out.println(sweeps + " " + mag.length);
		return (magSqd - (mag1*mag1));
		
		
	}
	public static double standardDeviation(double [] sample)
	{
		double sum=0;
		double mean = mean(sample);
		for(int i=0;i<sample.length;i++)
		{
			sum +=Math.pow(( sample[i] - mean),2);
		}
		
		return (sum/(sample.length-1));
	}
	public static double mean(double[] sample)
	{
		double sum =0;
		
		for(int i=0;i<sample.length;i++)
		{
			sum += sample[i];
		}
		return sum/sample.length;
	}


}
