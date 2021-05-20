
import lib.Picture;

public class SeamCarver {

	Picture picture;
   // create a seam carver object based on the given picture
   public SeamCarver(Picture picture)
   {
	   
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
