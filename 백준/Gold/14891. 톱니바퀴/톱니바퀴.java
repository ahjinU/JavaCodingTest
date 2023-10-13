import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int cnt, N, M, result;

	static int[][] board = new int[4][8];
	static int[][] point = { { 2, 6 }, { 2, 6 }, { 2, 6 }, { 2, 6 } };
	static int[] sum = { 1, 2, 4, 8 };

	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(bf.readLine());
			int num = Integer.parseInt(st.nextToken());

			for (int j = 7; j >= 0; j--) {
				board[i][j] = num % 10;
				num /= 10;
			}

		}

		st = new StringTokenizer(bf.readLine());
		int cmd = Integer.parseInt(st.nextToken());

		for (int c = 0; c < cmd; c++) {
			st = new StringTokenizer(bf.readLine());
			int num = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());

			rotate(num, dir, board);

//			for (int i = 0; i < 4; i++) {
//				for (int j = 0; j < 2; j++) {
//					System.out.print(point[i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println();
		}

		for (int i = 0; i < 4; i++) {
			int tweleve = point[i][0] - 2;
			if (tweleve < 0) {
				tweleve += 8;
			}

			if (board[i][tweleve]  == 1) {
				result += sum[i];
			}
		}
		
		System.out.println(result);

	}

	private static void rotate(int num, int dir, int[][] arr) {
		int[] dirs = new int[4];
		dirs[num] = dir;

		int front = num;

		while (front - 1 >= 0) {
			if (arr[front][point[front][1]] == arr[front - 1][point[front - 1][0]]) {
				dirs[front - 1] = 0;
				break;
			} else {
				dirs[front - 1] = dirs[front] * -1;
			}
			front--;
		}

		int rear = num;

		while (rear + 1 < arr.length) {
			if (arr[rear][point[rear][0]] == arr[rear + 1][point[rear + 1][1]]) {
				dirs[rear + 1] = 0;
				break;
			} else {
				dirs[rear + 1] = dirs[rear] * -1;
			}
			rear++;
		}

		for (int i = 0; i < 4; i++) {
			if (dirs[i] == 1) {
				point[i][0]--;
				if (point[i][0] < 0) {
					point[i][0] += 8;
				}
				point[i][1]--;
				if (point[i][1] < 0) {
					point[i][1] += 8;
				}
			} else if (dirs[i] == -1) {
				point[i][0]++;
				if (point[i][0] > 7) {
					point[i][0] -= 8;
				}
				point[i][1]++;
				if (point[i][1] > 7) {
					point[i][1] -= 8;
				}
			}
		}

	}

}