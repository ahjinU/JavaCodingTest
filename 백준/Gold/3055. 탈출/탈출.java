import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int n, m, result = -1;
	static char board[][];
	static Deque<int[]> water = new LinkedList<>();
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {

		StringTokenizer st = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		int sx = 0, sy = 0;
		board = new char[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine());
			board[i] = st.nextToken().toCharArray();
			for (int j = 0; j < m; j++) {
				// 시작
				if (board[i][j] == 'S') {
					sx = j;
					sy = i;
				}
				// 물
				else if (board[i][j] == '*') {
					int[] w = { i, j };
					water.offer(w);
				}
			}
		}

		bfs(sy, sx);

		if (result == -1)
			System.out.println("KAKTUS");
		else
			System.out.println(result + 1);

	}

	private static void bfs(int y, int x) {
		Queue<int[]> q = new LinkedList<>();

		int[] add = { y, x };
		q.add(add);

		char max = ' ';

		while (!q.isEmpty()) {
			int cur[] = q.poll();

//			System.out.println(cur[1] + " " + cur[0]);
//			System.out.println(max);
			int curX = cur[1];
			int curY = cur[0];

			if (board[curY][curX] == '*')
				continue;

			for (int i = 0; i < 4; i++) {
				int nextX = curX + dx[i];
				int nextY = curY + dy[i];

				if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n)
					continue;

				if (board[nextY][nextX] == 'D') {
					result = board[curY][curX] - 'S';
					return;
				}

				if (board[nextY][nextX] != '.')
					continue;

				board[nextY][nextX] = (char) (board[curY][curX] + 1);
				int[] next = { nextY, nextX };
				q.add(next);

			}

			if (board[curY][curX] != max) {
				max = board[curY][curX];
				spread();
			}

//			for (int i = 0; i < n; i++) {
//				for (int j = 0; j < m; j++) {
//					System.out.print(board[i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println();
		}

	}

	private static void spread() {
		int cnt = water.size();
		for (int i = 0; i < cnt; i++) {
			int[] w = water.poll();
			int wx = w[1];
			int wy = w[0];

			for (int j = 0; j < 4; j++) {
				int nextX = wx + dx[j];
				int nextY = wy + dy[j];

				if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || board[nextY][nextX] == 'D'
						|| board[nextY][nextX] == 'X' || board[nextY][nextX] == '*' )
					continue;

				board[nextY][nextX] = '*';
				int[] newW = { nextY, nextX };
				water.offer(newW);
			}
		}
	}

}