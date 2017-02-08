package MVP1;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

public class graphics 
{
	static int m =0;
	public static void update(int[][] ising,BufferedImage bi)
	{
		for (int x = 0; x < m; x++) 
			for (int y = 0; y < m; y++)
			{
				if(ising[x][y]==-1)
					bi.setRGB(x, y,Color.BLACK.getRGB());
				else
					bi.setRGB(x, y, Color.RED.getRed());
			}
	}
	
	public static BufferedImage initaslise(int [][] ising,final BufferedImage bi)
	{
		int n = ising[0].length;
		m=n;
		//final BufferedImage bi = new BufferedImage(n, n, BufferedImage.TYPE_INT_RGB);
		final Object lock = new Object();
		final JFrame f = new JFrame();
		f.setIgnoreRepaint(true);
		f.setVisible(true);
		f.setSize(n, n + f.getInsets().top);
		f.setExtendedState(Frame.MAXIMIZED_BOTH);
		f.addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent we) {System.exit(0);}});
		
		for (int x = 0; x < bi.getWidth(); x++) 
			for (int y = 0; y < bi.getHeight(); y++)
			{
				if(ising[x][y]==-1)
					bi.setRGB(x, y,Color.BLACK.getRGB());
				else
					bi.setRGB(x, y, Color.RED.getRed());
			}
		//A loop is needed to refersh the background everytime
		new Timer().scheduleAtFixedRate(new TimerTask() 
		{
			public void run() 
			{
				synchronized(lock)
				{
					f.getGraphics().drawImage(bi, 0, f.getInsets().top, f.getWidth(), f.getHeight() - f.getInsets().top, null);
				}
			}
		}, 0, 33);

		//another loop or thread will be needed to update the image so that the new grid is shown on the picture
		return bi;
		
	}

		
}
