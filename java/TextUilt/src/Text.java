//冒泡排序
public class Text {
	public static void main(String[] args) {
		int c = 0;
		int[] a = { 12, 45, 1, 6, 456, 126 };
		// int [] b={45,88,9,65,63,12};
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				if (a[i] > a[j]) {
					c = a[i];
					a[i] = a[j];
					a[j] = c;
				}
			}
		}
		for (int i1 = 0; i1 < a.length; i1++) {
			System.out.println(a[i1]);
		}
	}
}