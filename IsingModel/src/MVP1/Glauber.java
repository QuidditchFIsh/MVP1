package MVP1;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.IOException;
public class Glauber extends Functions 
{
	public static double[] glauber(int [][] ising,int iterations,double temp,BufferedImage bi,boolean graphic) throws IOException
	{
		int n = ising[0].length;
		Random rand = new Random();
		double energyBefore=0;
		double energyAfter=0;
		double avgMag=0;
		double avgEnergy=0;
		int counter=0;
		double suseptability=0;
		double heatCapcity =0;
		double[] magnetisation = new double[(int) ((iterations*0.9)/10)] ;
		double[] energy = new double[(int) ((iterations*0.9)/10)] ;
		double[] results = new double[9];
		//find a better way to to this other than passing the bufferedIMage around and around
		//create and initalise the buffered image


		for (int i=0;i<iterations;i++)
		{
			//create random numbers to select a point in the ising grid to flip
			int randi = rand.nextInt(n);
			int randj = rand.nextInt(n);

			//Calculate the energy before the flip
			energyBefore = energy(ising,randi,randj);


			//Multiplying the position in the grid by -1 represents flipping the spin of that atom
			ising[randi][randj] *= -1;

			//now the energy of the system after the flip is calculated
			energyAfter = energy(ising,randi,randj);

			if(acceptOrReject(-energyBefore + energyAfter,temp) == false)
			{

				ising[randi][randj] *= -1;

			}
			//Now the metropolis algorithm has been completed we need to update the image 

			//&& iterations > iterations*0.8
			if (i % 100 ==0 && iterations > iterations*0.95) 
			{ 
				magnetisation[counter] = normalisedTotalMagnetisation(ising);
				energy[counter] = totalEnergy(ising)/(n*n);
				counter++;
				if(graphic)
					graphics.update(ising, bi);
				avgMag += normalisedTotalMagnetisation(ising);
				avgEnergy += totalEnergy(ising)/(n*n);
				
				
			
			}
			
		}
		//avgMag /= ((iterations*0.9)/100);
		//avgEnergy/=((iterations*0.9)/100);
		suseptability = standardDeviation(magnetisation,(iterations*0.95)/100);
		suseptability = standardDeviation(magnetisation,counter);
		heatCapcity = standardDeviation(energy,(iterations*0.95)/100);
		heatCapcity = standardDeviation(energy,counter);
		
		results[0]=temp;
		results[1]=avgMag;
		results[2]=avgEnergy;
		results[3]=suseptability/(temp*n*n);
		results[4]=heatCapcity/(n*n*temp*temp);
		
		return results;
		
		//bw.write(String.valueOf( suseptability/(temp*n*n)+  " "+avgMag +" "+ heatCapcity/(n*n*temp*temp)  + " "+avgEnergy + " " +  String.valueOf(temp)));
		//bw.newLine();
	} 
}

