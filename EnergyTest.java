import lib.Picture;
import lib.StdOut;

public class EnergyTest {
	
	public static double[][] test1() {
		Picture x = new Picture("3x4.png");
		double[][]corrEn = new double[x.height()][x.width()];
		for(int row = 0; row < x.height(); row++) {
			for(int col = 0; col < x.width(); col++) {
				if(row ==  1 && col == 1) {
					corrEn[row][col] = 228.53;
				}
				else if (row ==  2 && col == 1) {
					corrEn[row][col] = 228.09;
				}
				else {
					corrEn[row][col] = 1000;
				}
			}
		}
		return corrEn;
	}

	public static void main(String[] args) {
		Picture picture = new Picture("3x4.png");
		StdOut.printf("Testing %dx%d Image\n", picture.width(), picture.height());

		SeamCarver sc1 = new SeamCarver(picture);

		StdOut.printf("Comparing values [a] / [b] where a is the computed value and b is the correct answer.\nComparing energy matrix\n");
		
		double[][]testMatrix = test1();
		
		for(int col =0; col < sc1.height();col++) {
			for(int row = 0;row<sc1.width();row++) {
				
				StdOut.print(sc1.energy[row][col]+" / "+testMatrix[col][row]+"\n");
			}
			StdOut.println("\n");
		}
		
		StdOut.printf("Comparing vertical seams.\n");
		int [] correctSeam = {0,1,1,0};
		int []answr = sc1.findVerticalSeam();
		for(int pixel=0;pixel<answr.length; pixel++) {
			StdOut.print(answr[pixel]+" / "+correctSeam[pixel]+"\n");
		}
		
		StdOut.printf("Comparing horizontal seams.\n");
		int [] correcthSeam = {1,2,1};
		int []hanswr = sc1.findHorizontalSeam();
		for(int pixel=0;pixel<hanswr.length; pixel++) {
			StdOut.print(hanswr[pixel]+" / "+correcthSeam[pixel]+"\n");
		}
		
		StdOut.printf("Test complete");
	}

}
