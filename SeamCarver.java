
import lib.Picture;

import java.awt.Color;

public class SeamCarver {

	Picture picture;
	public double[][] energy;
	private double maxEnergy = 1000;
   // create a seam carver object based on the given picture
   public SeamCarver(Picture picture)
   {
	   this.picture = picture;
	   this.energy = new double [width()][height()];
	   for (int row = 0; row < height(); row++)
           for (int col = 0; col < width(); col++)
        	   energy[row][col] = energy(row,col);
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
	   
	   int pictureWidth = width();
	   int pictureHeight = height();
	   
	   //Energy for border pixels should be maxed
	   if(x == 0 || x == pictureWidth - 1 || y == 0 || y == pictureHeight - 1)
	   {
		   return maxEnergy;
	   }
	   
	   //Energy for non border pixel
	   // How to get x and y delta (Delta)x2(x, y) = Redx(x, y)2 + Greenx(x, y)2 + Bluex(x, y)2
	   double xDelta = energyHelper(picture.get(x - 1, y), picture.get(x + 1, y));
	   double yDelta = energyHelper(picture.get(x, y - 1), picture.get(x, y + 1));
	   
	   //(Delta)x2(x, y) + (Delta)y2(x, y)
	   return Math.sqrt(xDelta + yDelta);
	   
   }
   private double energyHelper(Color a, Color b)
   {
	   double red = a.getRed() - b.getRed();
	   double green = a.getGreen() - b.getGreen();
	   double blue = a.getBlue() - b.getBlue();
	   return (red*red) + (green*green) + (blue*blue);
   }

   // sequence of indices for horizontal seam
   public int[] findHorizontalSeam()
   {
	   int[] seam = new int[height()];

	   return seam;
   }

   // sequence of indices for vertical seam
   public int[] findVerticalSeam()
   {

       int[] seam = new int[width()];
       int[] LEseam = new int[width()];
       double LEtotal = Double.MAX_VALUE;

       int LEpixel = 0;
       // find lowest energy pixel
       for (int x = 1; x < width() - 1; x++) {
            LEpixel = x;
            seam[0] = LEpixel - 1;
            double totalEnergy = 1000;

           // Calculate seam from LEpixel
           for (int y = 1; y < height(); y++) {
               // Middle pixel base
               double lowestEnergy = energy[LEpixel][y];

               // Compare left pixel
               if(energy[LEpixel - 1][y] < lowestEnergy){
                   lowestEnergy = energy[LEpixel - 1][y];
                   LEpixel = LEpixel - 1;
               }
               if(energy[LEpixel + 1][y] < lowestEnergy){
                   lowestEnergy = energy[LEpixel + 1][y];
                   LEpixel = LEpixel + 1;
               }

               seam[y] = LEpixel;
               totalEnergy += lowestEnergy;
           }

           // if it's a lower energy seam, use it
           if(totalEnergy < LEtotal) {
               LEtotal = totalEnergy;
               LEseam = seam;
           }

       }

       return LEseam;
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
