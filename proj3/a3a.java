
import java.util.ArrayList;
import java.util.List;

class a3a {
	public static void main(String[] args) {
		int parityBits = 0;

		byte[] bytes = args[0].getBytes();
		String converted = "";
		/*
		 * The following loop is partly taken from the internet
		 */
		for (byte b : bytes) {
			int v = b;
			for (int i = 0; i < 8; i++) {
				if ((v & 128) == 0) {
					converted += 0;
				} else {
					converted += 1;
				}
				v <<= 1;
			}
		}

		while (Math.pow(2, parityBits) < parityBits + converted.length() + 1) {
			parityBits++;
		}
				
		int[] arr = new int[converted.length()];
		for(int i = 0; i < converted.length(); i++) {
			arr[i] = Integer.parseInt(Character.toString(converted.charAt(i)));
		}
		
		List<Integer> filled = new ArrayList<>();
		
		for(int j2 = 0; j2 < arr.length; j2++) {
			filled.add(arr[j2]);
		}
		
		for(int i = 0; i < parityBits; i++) {
			int pow = (int) Math.pow(2, i);
			filled.add(pow-1, 0);
		}

		List<List<Integer>> lists = new ArrayList<>();

		int skip = 1;
		for (int i = 0; i < parityBits; i++) {
			List<Integer> list = new ArrayList<>();
			int pow = (int) Math.pow(2, i);
			for (int a = pow; a < filled.size(); a++) {
				for (int b = 0; b < skip; b++) {
					list.add(a);
					a++;
				}
				if (skip > 1) {
					a += skip - 1;
				}
			}
			lists.add(list);
			skip *= 2;
		}

		for (List<Integer> l : lists) {
			int nr = 0;
			for (int i : l) {
				if (i <= converted.length() + parityBits) {
					if (filled.get(i-1) == 1) {
						nr++;
					}
				}
			}
			if (nr % 2 == 0) {
				System.out.print(0);
			} else {
				System.out.print(1);
			}
		}
	}
}