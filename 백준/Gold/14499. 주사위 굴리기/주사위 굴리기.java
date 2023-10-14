import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int board[][], N, M, dice[], curx, cury;

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		dice = new int[7];
		// 1이 위 6이 아래

		cury = Integer.parseInt(st.nextToken());
		curx = Integer.parseInt(st.nextToken());

		int cnt = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < cnt; i++) {
			int cmd = Integer.parseInt(st.nextToken());

			switch (cmd) {
			case 1:
				moveRight(cury, curx);
				break;
			case 2:
				moveLeft(cury, curx);
				break;
			case 3:
				moveUp(cury, curx);
				break;
			case 4:
				moveDown(cury, curx);
				break;
			}

		}

		System.out.println(sb);

	}

	private static void moveLeft(int y, int x) {
		int nextX = x - 1;

		if (nextX < 0)
			return;

		int tmp = dice[6];
		dice[6] = dice[4];
		dice[4] = dice[1];
		dice[1] = dice[3];
		dice[3] = tmp;

		if (board[y][nextX] == 0) {
			board[y][nextX] = dice[6];
		} else {
			dice[6] = board[y][nextX];
			board[y][nextX] = 0;
		}

		sb.append(dice[1]).append("\n");

		curx = nextX;
	}

	private static void moveRight(int y, int x) {
		int nextX = x + 1;

		if (nextX >= M)
			return;

		int tmp = dice[6];
		dice[6] = dice[3];
		dice[3] = dice[1];
		dice[1] = dice[4];
		dice[4] = tmp;

		if (board[y][nextX] == 0) {
			board[y][nextX] = dice[6];
		} else {
			dice[6] = board[y][nextX];
			board[y][nextX] = 0;
		}

		sb.append(dice[1]).append("\n");

		curx = nextX;
	}

	private static void moveUp(int y, int x) {
		int nextY = y - 1;

		if (nextY < 0) {
			return;
		}

		int tmp = dice[5];
		dice[5] = dice[6];
		dice[6] = dice[2];
		dice[2] = dice[1];
		dice[1] = tmp;

		if (board[nextY][x] == 0) {
			board[nextY][x] = dice[6];
		} else {
			dice[6] = board[nextY][x];
			board[nextY][x] = 0;
		}

		sb.append(dice[1]).append("\n");

		cury = nextY;
	}

	private static void moveDown(int y, int x) {
		int nextY = y + 1;

		if (nextY >= N) {
			return;
		}

		int tmp = dice[6];
		dice[6] = dice[5];
		dice[5] = dice[1];
		dice[1] = dice[2];
		dice[2] = tmp;

		if (board[nextY][x] == 0) {
			board[nextY][x] = dice[6];
		} else {
			dice[6] = board[nextY][x];
			board[nextY][x] = 0;
		}
		sb.append(dice[1]).append("\n");

		cury = nextY;
	}

}