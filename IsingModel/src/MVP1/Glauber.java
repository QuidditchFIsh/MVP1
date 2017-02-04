package MVP1;
import java.util.Random;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.IOException;
public class Glauber extends Functions
{
	public static void glauber(int [][] ising,int iterations,double temp,BufferedImage bi,BufferedWriter bw) throws IOException
	{
		int n = ising[0].length;
		Random rand = new Random();
		double energyBefore=0;
		double energyAfter=0;
		double energyDiff = 0;
		double avgMag=0;
		double avgMagSqd=0;
		double avgEnergy=0;
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

			//&& iterations > iterations*0.8
			if (i % 100 ==0 && iterations > iterations*0.8) 
			{ 
				//graphics.update(ising, bi);
				avgMag += normalisedTotalMagnetisation(ising);
				avgMagSqd += normalisedTotalMagSquared(ising);
				avgEnergy += totalEnergy(ising)/n;
				
			}
		}
		double suseptability = (1/(n*n*temp))*((avgMagSqd/(iterations/100))-Math.pow((avgMag/(iterations/100)), 2));
		bw.write(String.valueOf( suseptability + " " + Math.abs(avgMag)/(iterations/100) + " " + (avgEnergy)/(iterations/100) + " " + String.valueOf(temp)));
		bw.newLine();
	}
}

