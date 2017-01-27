package MVP1;
import java.util.Random;
import java.awt.image.BufferedImage;
public class Glauber extends Functions
{
	public static double glauber(int [][] ising,int iterations,double temp,BufferedImage bi)
	{
		int n = ising[0].length;
		Random rand = new Random();
		double energyBefore=0;
		double energyAfter=0;
		double energyDiff = 0;
		double avgMag=0;
		double avgMagSqd=0;
		//find a better way to to this other than passing the bufferedIMage around and around
		//create and initalise the buffered image


		for (int i=0;i<iterations;i++)
		{
			//create random numbers to select a point in the ising grid to flip
			int randi = rand.nextInt(n);
			int randj = rand.nextInt(n);

			//calcuate the energy before the flip
			energyBefore = energy(ising,randi,randj);


			//Multiplying the position in the grid by -1 represnets flipping the spin of that atom
			ising[randi][randj] *= -1;

			//	 System.out.println((randi+1) + " " + (randj+1));
			//now the energy of the system after the flip is calculated
			energyAfter = energy(ising,randi,randj);
			energyDiff = energyBefore - energyAfter;

			if(acceptOrReject(energyDiff,temp) == false)
			{

				ising[randi][randj] *= -1;

			}
			//Now the metropolis algorithm has been completed we need to update the image 

			if (i % 100 ==0) 
			{ 
				graphics.update(ising, bi);
				avgMag+=normalisedTotalMagnetisation(ising);
				avgMagSqd += normalisedTotalMagSquared(ising);
			}
		}
		return (1/)
	}
}

