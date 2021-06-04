import lib.Picture;
import lib.StdOut;

public class EnergyTest {
	
	private static double[][]corrEn;
	private static Picture picture = new Picture("3x4.png");
	private static SeamCarver testObj = new SeamCarver(picture);
	
	public static boolean threeByFourEnergytest() {
		corrEn = new double[picture.height()][picture.width()];
		double[][]corrEn = new double[picture.height()][picture.width()];
		
		for(int row = 0; row < picture.height(); row++) {
			for(int col = 0; col < picture.width(); col++) {
				if(row ==  1 && col == 1) {
					corrEn[row][col] = 228.527898;
				}
				else if (row ==  2 && col == 1) {
					corrEn[row][col] = 228.09;
				}
				else {
					corrEn[row][col] = 1000;
				}
			}
		}
		for(int row = 0;  row < picture.height(); row++ ) {
			for(int col = 0; col < picture.width(); col++) {
				
				if ((int)testObj.energy[col][row] != (int)corrEn[row][col]) {
					System.out.printf("The energy value at %d and %d is incorrect. \n%f does not eqaul %f",row,col,testObj.energy[row][col],corrEn[row][col]);
					return false;
				}
			}
		}
		System.out.printf("The energy values are correct.\n");
		return true;
	}
	public static boolean threeByFourSeamTest() {
		int[] corrSeamV = {0,1,1,0};
		int[] corrSeamH = {1,2,1};
		int[] testSeamH = testObj.findHorizontalSeam();
		int[] testSeamV = testObj.findVerticalSeam();
		
		for(int num=0; num< testSeamV.length;num++) {
			if(testSeamV[num] != corrSeamV[num]) {
				System.out.printf("The vertical seam is wrong.");
				return false;
			}
		}
		for(int num=0; num< testSeamH.length;num++) {
			if(testSeamH[num] != corrSeamH[num]) {
				System.out.printf("The horizontal seam is wrong.");
				return false;
			}
		}
		System.out.printf("The seams are correct.\n");
		return true;
	}
	
	public static void pictureDemo(int cols,int rows) {
		Picture chamPic = new Picture("chameleon.png");
		chamPic.show();
		SeamCarver cham = new SeamCarver(chamPic);
		for (int i = 0; i < rows; i++) {
            int[] horizontalSeam = cham.findHorizontalSeam();
            cham.removeHorizontalSeam(horizontalSeam);
        }

        for (int i = 0; i < cols; i++) {
            int[] verticalSeam = cham.findVerticalSeam();
            cham.removeVerticalSeam(verticalSeam);
        }
        cham.picture.show();
        int wDiff = chamPic.width()-cham.width();
        int hDiff = chamPic.height()-cham.height();
        if (wDiff != cols || hDiff !=rows) {
        	System.out.printf("There were %d colums deleted and %d rows deleted. There should have been %d columns deleted and %d rows deleted instead.");
        }
        else {
            StdOut.printf("new image size : %d columns by %d rows \nold image size : %d columns by %d rows \nDifferenc in dimensions : %d columns by %d rows\n", cham.width(), cham.height(),chamPic.width(),chamPic.height(),wDiff,hDiff);

        }
	}
		
	public static void main(String[] args) {
		
		threeByFourEnergytest();
		threeByFourSeamTest();
		pictureDemo(100,100);
		System.out.printf("Test Complete.");
	}

}
