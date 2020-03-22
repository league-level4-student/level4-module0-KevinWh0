//https://www.codewars.com/kata/the-wrong-way-cow
//
//Task
//Given a field of cows find which one is the Wrong-Way Cow and return her position.
//
//Notes:
//
//There are always at least 3 cows in a herd
//There is only 1 Wrong-Way Cow!
//Fields are rectangular
//The cow position is zero-based [x,y] of her head (i.e. the letter c)
//Examples
//Ex1
//
//cow.cow.cow.cow.cow
//cow.cow.cow.cow.cow
//cow.woc.cow.cow.cow
//cow.cow.cow.cow.cow
//Answer: [6,2]
//
//Ex2
//
//c..........
//o...c......
//w...o.c....
//....w.o....
//......w.cow
//Answer: [8,4]
//
//Notes
//The test cases will NOT test any situations where there are "imaginary" cows, so your solution does not need to worry about such things!
//
//To explain - Yes, I recognize that there are certain configurations where an "imaginary" cow may appear that in fact is just made of three other "real" cows.
//In the following field you can see there are 4 real cows (3 are facing south and 1 is facing north). There are also 2 imaginary cows (facing east and west).
//
//...w...
//..cow..
//.woco..
//.ow.c..
//.c.....

package extras.the_wrong_way_cow;

public class TheWrongWayCow {
	static int U;
	static int D;
	static int L;
	static int R;



	static boolean isSmallest(char val) {
		//System.err.println(U + " " + D + " " + L + " " + R);
		int value = -1;
		if (val == 'U')
			value = U;
		if (val == 'D')
			value = D;
		if (val == 'L')
			value = L;
		if (val == 'R')
			value = R;


//		if (value <= U && U != 0) {
//			if (value <= D && D != 0) {
//				if (value <= L && L != 0) {
//					if (value <= R && R != 0) {
//						return true;
//					}
//				}
//			}
//		}
		if(value == 1) return true;
		
		return false;

	}

	public static int[] findWrongWayCow(final char[][] field) {
		
		
		U = 0;
		D = 0;
		L = 0;
		R = 0;

		int[] uLoc = { -1, -1 };
		int[] dLoc = { -1, -1 };
		int[] lLoc = { -1, -1 };
		int[] rLoc = { -1, -1 };
		
		// Fill in the code to return the x,y coordinate position of the
		// head (letter 'c') of the wrong way cow!
		int cposX = -1;
		int cposY = -1;
		System.out.println("cow: " + field[0][0] + field[0][1] + field[0][2]);
		System.out.println(field[0].length + " " + field.length);

		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[0].length; j++) {
				// System.out.println(i + " i and j " + j);
				if (field[i][j] == 'c') {

					String down = "";
					String left = "";
					String right = "";

					try {
						if (("" + field[i][j] + field[i - 1][j] + field[i - 2][j]).equals("cow")) {
							U++;
							uLoc[0] = i;
							uLoc[1] = j;
							//System.out.println("U(x y)" + i + " " + j);

						}
						// System.out.println("up :" + up);
					} catch (Exception e) {
					}
					try {

						if (("" + field[i][j] + field[i + 1][j] + field[i + 2][j]).equals("cow")) {
							D++;
							dLoc[0] = i;
							dLoc[1] = j;
							//System.out.println("D(x y)" + i + " " + j);
						}
						// System.out.println("down :" + down);
					} catch (Exception e) {
					}
					try {

						
						if (("" + field[i][j] + field[i][j - 1] + field[i][j - 2]).equals("cow")) {
							L++;
							lLoc[0] = i;
							lLoc[1] = j;
							//System.out.println("L(x y)" + i + " " + j);

						}
						// System.out.println("left :" + left);
					} catch (Exception e) {
					}
					try {

						if (("" + field[i][j] + field[i][j + 1] + field[i][j + 2]).equals("cow")) {
							R++;
							rLoc[0] = i;
							rLoc[1] = j;
							//System.out.println("R(x y)" + i + " " + j);

						}
						// System.out.println("right:" + right);
					} catch (Exception e) {
					}






//					System.err.println("c");
//					for (int j2 = -1; j2 < 2; j2++) {
//						for (int k = -1; k < 2; k++) {
//							if(field[i + j2][j + k] == 'o') {
//								if(field[i + j2 + Math.abs(cposX - (i + j2))][j + k + Math.abs(cposY - (j + k))] == 'w') {
//									
//								}
//							}
//						}
//					}
				}

			}
		}

		//System.out.println(U + " " + D + " " + L + " " + R);

		if (isSmallest('U')) {
			cposX = uLoc[0];
			cposY = uLoc[1];
		}
		if (isSmallest('D')) {
			cposX = dLoc[0];
			cposY = dLoc[1];
			
		}
		if (isSmallest('L')) {
			cposX = lLoc[0];
			cposY = lLoc[1];
		}
		if (isSmallest('R')) {
			cposX = rLoc[0];
			cposY = rLoc[1];
		}

		return new int[] { cposY, cposX };
	}
}
