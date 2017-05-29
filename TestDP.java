package algorithm;

import java.util.ArrayList;

import org.junit.Test;

/*
 *  ��̬�滮(dynamic programming)�����ǣ�ÿ�ξ��������ڵ�ǰ״̬�����漴����״̬��ת�ơ�һ���������о����ڱ仯��״̬�в��������ģ�
 *  ���ԣ����ֶ�׶����Ż����߽������Ĺ��̾ͳ�Ϊ��̬�滮��
 *  ����˼������η����ƣ�Ҳ�ǽ�����������ֽ�Ϊ���ɸ������⣨�׶Σ�����˳������ӽ׶Σ�ǰһ������Ľ⣬Ϊ��һ�����������ṩ�����õ���Ϣ��
 *  �������һ������ʱ���г����ֿ��ܵľֲ��⣬ͨ�����߱�����Щ�п��ܴﵽ���ŵľֲ��⣬���������ֲ��⡣���ν���������⣬���һ����������ǳ�ʼ����Ľ⡣
 ���ڶ�̬�滮���������������ص�����������ص㣬Ϊ�����ظ����㣬��ÿһ��������ֻ��һ�Σ����䲻ͬ�׶εĲ�ͬ״̬������һ����ά�����С�
 ����η����Ĳ���ǣ��ʺ����ö�̬�滮���������⣬���ֽ��õ����������������ǻ�������ģ�����һ���ӽ׶ε�����ǽ�������һ���ӽ׶εĽ�Ļ����ϣ����н�һ������⣩��

 */
public class TestDP {
	public static void main(String[] args) {
		// System.out.println(fib1(20));
		// System.out.println(fib2(20));
		// System.out.println(fib3(20));

		// System.out.println(eval(30));
		// System.out.println(eval2(30));

		// findCoin(11);

		// System.out.println(getFloor(39));
		//
		System.out.println(LCString("ABCDEBCKBCFC", "BCDEABCFC"));
		// System.out.println(LCS("ABCBDAB", "BDCABA"));
		//
		// int[] a = { 1, 4, 6, 2, 3, 5, 7, 8, -2 };
		//
		// // ���Գ�����������������LISDP n2
		// System.out.println("===" + LISDP(a));

		// System.out.println(editdis("kitten", "sitting")); // 3
	}

	// ��һ�������ݹ� Fibonacci
	// ���ڶ�̬�滮���������������ص�����������ص㣬Ϊ�����ظ����㣬��ÿһ��������ֻ��һ�Σ����䲻ͬ�׶εĲ�ͬ״̬������һ����ά�����С�
	public static int fib1(int n) {
		if (n <= 1) {
			return 1;
		}
		return fib1(n - 1) + fib1(n - 2);
	}

	public static int fib2(int n) {
		if (n <= 1) {
			return 1;
		}
		int last = 1;
		int nextToLast = 1;
		int total = 0;
		for (int i = 2; i <= n; i++) {
			total = last + nextToLast;
			last = nextToLast;
			nextToLast = total;
		}
		return total;
	}

	public static int fib3(int n) {
		if (n <= 1) {
			return 1;
		}
		int[] arr = new int[n + 1];
		arr[0] = 1;
		arr[1] = 1;
		for (int i = 2; i <= n; i++) {
			arr[i] = arr[i - 1] + arr[i - 2];
		}
		return arr[n];
	}

	// ��һ�������ݹ�
	public static double eval(int n) {
		if (n == 0) {
			return 1.0;
		}
		double sum = 0;
		for (int i = 0; i < n; i++) {
			sum += eval(i);
		}
		return (2.0 * sum / n) + n;
	}

	public static double eval2(int n) {
		double[] arr = new double[n + 1];
		arr[0] = 1;
		for (int i = 1; i <= n; i++) {
			double sum = 0.0;
			for (int j = 0; j < i; j++) {
				sum += arr[j];
			}
			arr[i] = (2.0 * sum / i) + i;
		}
		return arr[n];
	}

	// ¥���벣���� ��̬�滮
	public static int getFloor(int sum) {
		int n = 1;
		out: for (; n < sum; n++) {
			int[] arr = new int[n + 1];
			arr[0] = n;
			for (int i = 1; i <= n; i++) {
				arr[i] = arr[i - 1] + n - i;
			}
			if (arr[n] >= sum) {
				break out;
			}
		}
		return n;
	}

	@Test
	// �����������ֵΪ1Ԫ��3Ԫ��5Ԫ��Ӳ������ö����������ٵ�Ӳ�Ҵչ�11Ԫ��
	public void findCoin() {
		int[] cons = new int[11 + 1];
		for (int i = 1; i <= 11; i++) {
			int cons1 = i - 1;
			int cons3 = i - 3;
			int cons5 = i - 5;
			int minCons = cons[cons1];
			
			if (cons3 >= 0 && cons[cons3] < minCons) {
				minCons = cons[cons3];
			
			}
			if (cons5 >= 0 && cons[cons5] < minCons) {
				minCons = cons[cons5];
				
			}

			cons[i] = minCons + 1;

			System.out.println(i + ":" + cons[i]);
		}
		System.out.println("------------" + cons[11]);

	}

	// 1. ����������У�LCS�������Բ�����
	public static int LCS(String str1, String str2) {
		int length1 = str1.length();
		int length2 = str2.length();
		int longest = 0;
		int[][] arr = new int[length1 + 1][length2 + 1];
		int[][] b = new int[length1 + 1][length2 + 1];
		for (int i = 1; i <= length1; i++) {
			for (int j = 1; j <= length2; j++) {
				if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					arr[i][j] = arr[i - 1][j - 1] + 1;
					b[i][j] = 1;
				} else if (arr[i - 1][j] >= arr[i][j - 1]) {
					arr[i][j] = arr[i - 1][j];
					b[i][j] = 0;
				} else {
					arr[i][j] = arr[i][j - 1];
					b[i][j] = -1;
				}
				longest = Math.max(longest, arr[i][j]);
			}
		}
		for (int i = 0; i < length1 + 1; i++) {
			for (int j = 0; j < length2 + 1; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
		// int i = 0;
		// int j = 0;
		// while (i < length1 && j < length2) {
		// if (str1.charAt(i) == str2.charAt(j)) {
		// System.out.print(str1.charAt(i) + " ");
		// i++;
		// j++;
		// } else if (arr[i + 1][j] >= arr[i][j + 1]) {
		// i++;
		// } else {
		// j++;
		// }
		//
		// }
		System.out.println("----------------");
		for (int i = 0; i < length1 + 1; i++) {
			for (int j = 0; j < length2 + 1; j++) {
				System.out.print(b[i][j]);
			}
			System.out.println();
		}
		System.out.println("----------------");
		display(b, str1, length1, length2);
		System.out.println();
		return arr[length1][length2];
	}

	public static void display(int[][] b, String x, int i, int j) {
		if (i == 0 || j == 0)
			return;

		if (b[i][j] == 1) {
			display(b, x, i - 1, j - 1);
			System.out.print(x.charAt(i - 1) + " ");
		} else if (b[i][j] == 0) {
			display(b, x, i - 1, j);
		} else if (b[i][j] == -1) {
			display(b, x, i, j - 1);
		}
	}

	// ��������ַ�����LCS������������
	public static int LCString(String str1, String str2) {
		int length1 = str1.length();
		int length2 = str2.length();
		int longest = 0;
		int[][] arr = new int[length1 + 1][length2 + 1];
		for (int i = 1; i <= length1; i++) {
			for (int j = 1; j <= length2; j++) {
				if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					arr[i][j] = arr[i - 1][j - 1] + 1;
				} else {
					arr[i][j] = 0;
				}
				longest = Math.max(longest, arr[i][j]);
			}
		}
		System.out.println("----------------");
		for (int i = 0; i < length1 + 1; i++) {
			for (int j = 0; j < length2 + 1; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
		System.out.println("----------------");
		ArrayList<Character> list = new ArrayList<>();
		ArrayList<ArrayList<Character>> sumList = new ArrayList<>();
		for (int i = 1; i <= length1; i++) {
			for (int j = 1; j <= length2; j++) {
				if (arr[i][j] == longest) {
					int ii = i;
					int jj = j;
					while (arr[ii][jj] != 0) {
						list.add(0, str1.charAt(ii - 1));
						ii--;
						jj--;
					}
					sumList.add(new ArrayList<Character>(list));
					list.clear();
				}
			}
		}
		System.out.println(sumList);
		return longest;
	}

	// 3.����������У�LIS������̬�滮n2�ⷨ
	public static int LISDP(int[] arr) {
		int length = arr.length;
		int[] dp = new int[length];
		int sum = 0;
		for (int i = 0; i < length; i++) {
			int max = 0;

			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i] && dp[j] > max) {
					max = dp[j];
				}
			}
			dp[i] = max + 1;
			if (sum < dp[i]) {
				sum = dp[i];
			}
		}
		return sum;
	}

	// 4.�༭�������⣺
	public static int editdis(String str1, String str2) {
		int strLength1 = str1.length();
		int strLength2 = str2.length();
		int[][] edit = new int[strLength1 + 1][strLength2 + 1];
		for (int i = 1; i <= strLength1; i++) {
			for (int j = 1; j <= strLength2; j++) {
				if (j > i) {
					edit[i][j] = edit[i][i] + j - i;
				} else if (i > j) {
					edit[i][j] = edit[j][j] + i - j;
				} else {
					if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
						edit[i][j] = edit[i - 1][j - 1];
					} else {
						edit[i][j] = edit[i - 1][j - 1] + 1;
					}
				}
			}
		}
		System.out.println("----------------");
		for (int i = 0; i < strLength1 + 1; i++) {
			for (int j = 0; j < strLength2 + 1; j++) {
				System.out.print(edit[i][j]);
			}
			System.out.println();
		}
		System.out.println("----------------");

		return edit[strLength1][strLength2];
	}
}
