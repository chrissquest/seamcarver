import lib.Picture;
import lib.StdOut;

public class EnergyTest {

	public static void main(String[] args) {
		Picture picture = new Picture("3x4.png");
		StdOut.printf("Testing 3x4 Image.\n", picture.width(), picture.height());

		SeamCarver sc = new SeamCarver(picture);

		StdOut.printf("Comparing values.\n");

		for (int row = 0; row < sc.height(); row++) {
			for (int col = 0; col < sc.width(); col++)
				StdOut.println("do test here");
			StdOut.println();
		}
		StdOut.printf("Test complete/incomplete");
	}

}
