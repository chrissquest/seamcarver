
import lib.Picture;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

public class SeamCarver {

	Picture picture;
	public double[][] energy;
	private double maxEnergy = 1000;
    private int[][] visited;
   // create a seam carver object based on the given picture
   public SeamCarver(Picture picture)
   {
	   this.picture = picture;
	   
	   visited = new int[width()][height()];
	   energy = new double [width()][height()];
	   
	   for (int row = 0; row < width(); row++)
           for (int col = 0; col < height(); col++)
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
	   // How to get x delta (Delta)x2(x, y) = Redx(x, y)2 + Greenx(x, y)2 + Bluex(x, y)2
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
	   int[] seam = new int[width()]; //stores lowest energy seam
	   
       double[] distTo = new double[height()]; // stores the total energy of a pixels total path for all left origins.
       double[] oldDistTo = new double[height()]; // stores the previous row of pixel energy values so we can update distTo.
       
       //Visit every pixel in the image row by row
       for (int row = 0; row < width(); row++) {
           for (int col = 0; col < height(); col++) {
        	   dykstraHor(row, col, distTo, oldDistTo);
           }
           System.arraycopy(distTo, 0, oldDistTo, 0, height()); //after each row, store the distTo values to oldDistTo so we can update total distance -
       }													//when going through the next row.
       
       double min = oldDistTo[0]; //Pick first path total distance to compare to all others.
       int best = 0; // integer to keep track of which path is the smallest
       
       for (int index = 0; index < oldDistTo.length; index++) {
           if (oldDistTo[index] < min) {
               min = oldDistTo[index]; // if current distanced indexed is smaller make it the new minimum.
               best = index; //store what index min value was found at
           }
       }
//       System.out.println(min);
       
       seam[width() - 1] = best;
       for (int i = width() - 2; i >= 0; i--) { // Back track and fill seam array with the path with least energy
           seam[i] = visited[i+1][best];
           best = visited[i+1][best];			//Set best to now be the point we were just at
       }
       return seam;
   }

   // sequence of indices for vertical seam
   public int[] findVerticalSeam()
   {
	   int[] seam = new int[height()]; //stores lowest energy seam
	   
       double[] distTo = new double[width()]; // stores the total energy of a pixels total path for all top origins.
       double[] oldDistTo = new double[width()]; // stores the previous row of pixel energy values so we can update distTo.

       //Visit every pixel in the image row by row
       for (int col = 0; col < height(); col++) {
           for (int row = 0; row < width(); row++) {
        	   dykstraVert(row, col, distTo, oldDistTo);
           }
           System.arraycopy(distTo, 0, oldDistTo, 0, width()); //after each row, store the distTo values to oldDistTo so we can update total distance -
       }													   //when going through the next row.
       
       
       double min = oldDistTo[0]; //Pick first path total distance to compare to all others.
       int best = 0; // integer to keep track of which path is the smallest
       
       //compare each distance to min
       for (int index = 0; index < oldDistTo.length; index++) {
           if (oldDistTo[index] < min) {
               min = oldDistTo[index]; // if current distanced indexed is smaller make it the new minimum.
               best = index; //store what index min value was found at
           }
       }
      System.out.println(best);

       seam[height() - 1] = best;
       for (int i = height() - 2; i >= 0; i--) { // Back track and fill seam array with the path with least energy
           seam[i] = visited[best][i + 1];
           best = visited[best][i + 1]; //Set best to now be the point we were just at
       }
       
       
       return seam;
   }

   private void dykstraVert(int col, int row, double[] distTo, double[] oldDistTo) 
   {
	   //Dealing with top row of pixels
	   if (row == 0) {
           distTo[col] = maxEnergy;
           visited[col][row] = -1;
           return;
       }
	   
	   //Dealing with left column of pixels
       if (col == 0) {
           double a = oldDistTo[col];
           double b = oldDistTo[col + 1];
           double min = Math.min(a, b);
           distTo[col] = min + energy[col][row];
           if (a < min) {
        	   visited[col][row] = col;
           } else {
        	   visited[col][row] = col + 1;
           }
           return;
       }
       
       //Dealing with right column of pixels
       if (col == width() - 1) {
           // we have only 2 edges
           double a = oldDistTo[col];
           double b = oldDistTo[col - 1];
           double min = Math.min(a, b);
           distTo[col] = min + energy[col][row];
           if (a < min) {
        	   visited[col][row] = col;
           } else {
        	   visited[col][row] = col - 1;
           }
           return;
       }

       //Dealing with the middle value of pixels
       
       // for 3 edges
       double left = oldDistTo[col - 1];
       double mid = oldDistTo[col];
       double right = oldDistTo[col + 1];

       double min = Math.min(Math.min(left, mid), right);

       distTo[col] = min + energy[col][row];
       if (min == left) {
    	   visited[col][row] = col - 1;
       } else if (min == mid) {
    	   visited[col][row] = col;
       } else {
    	   visited[col][row] = col + 1;
       }
   }
   
   private void dykstraHor(int col, int row, double[] distTo, double[] oldDistTo) 
   {
	   
	   //Dealing with left column of pixels
       if (col == 0) {
           distTo[row] = maxEnergy;
           visited[col][row] = -1;
           return;
       }
       
	   //Dealing with top row of pixels
	   if (row == 0) {
           double a = oldDistTo[row];
           double b = oldDistTo[row + 1];
           double min = Math.min(a, b);
           distTo[row] = min + energy[col][row];
           if (a < min) {
        	   visited[col][row] = row;
           } else {
        	   visited[col][row] = row + 1;
           }
           return;
       }
       
       //Dealing with right column of pixels
       if (row == height() - 1) {
           // we have only 2 edges
           double a = oldDistTo[row];
           double b = oldDistTo[row - 1];
           double min = Math.min(a, b);
           distTo[row] = min + energy[col][row];
           if (a < min) {
        	   visited[col][row] = col;
           } else {
        	   visited[col][row] = col - 1;
           }
           return;
       }

       //Dealing with the middle value of pixels
       // for 3 edges
       double left = oldDistTo[row - 1];
       double mid = oldDistTo[row];
       double right = oldDistTo[row + 1];

       double min = Math.min(Math.min(left, mid), right);

       distTo[row] = min + energy[col][row];
       if (min == left) {
    	   visited[col][row] = row - 1;
       } else if (min == mid) {
    	   visited[col][row] = row;
       } else {
    	   visited[col][row] = row + 1;
       }
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
