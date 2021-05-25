
import java.awt.Color;

import lib.Picture;

public class SeamCarver {

	Picture picture;
	private double[][] energy;
	private double maxEnergy = 1000;
   // create a seam carver object based on the given picture
   public SeamCarver(Picture picture)
   {
	   this.picture = picture;
   }

   // current picture
   public Picture picture()
   {
	   return picture;
   }

   // width of current picture
   public int width()
   {
	   return picture.width();
   }

   // height of current picture
   public int height()
   {
	   return picture.height();
   }

   // energy of pixel at column x and row y
   public double energy(int x, int y)
   {
	   //	a 3 by 4 image	//
	   ///////////////////////
	   //|(0,0)|(1,0)|(2,0)|//
	   //|(0,1)|(1,1)|(2,1)|//
	   //|(0,2)|(1,2)|(2,2)|//
	   //|(0,3)|(1,3)|(2,3)|//
	   ///////////////////////
	   
	   var pictureWidth = width();
	   var pictureHeight = height();
	   
	   //Energy for border pixels should be maxed
	   if(x == 0 || x == pictureWidth - 1 || y == 0 || y == pictureHeight - 1)
	   {
		   return maxEnergy;
	   }
	   
	   //Energy for non border pixel
	   
	   double xDelta = energyHelper(picture.get(x - 1, y), picture.get(x + 1, y));
	   double yDelta = energyHelper(picture.get(x, y - 1), picture.get(x, y + 1));
	   
	   return Math.sqrt(yDelta + yDelta);
	   
   }
   private double energyHelper(Color a, Color b)
   {
	   int red = a.getRed() - b.getRed();
	   int green = a.getGreen() - b.getGreen();
	   int blue = a.getBlue() - b.getBlue();
	   return (red*red) + (green*green) + (blue*blue);
   }

   // sequence of indices for horizontal seam
   public int[] findHorizontalSeam()
   {
	   
   }

   // sequence of indices for vertical seam
   public int[] findVerticalSeam()
   {
	   
   }

   // remove horizontal seam from current picture
   public void removeHorizontalSeam(int[] seam)
   {
	   
   }

   // remove vertical seam from current picture
   public void removeVerticalSeam(int[] seam)
   {
	   
   }

   //  unit testing (optional)
   public static void main(String[] args)
   {
	   
   }

}
