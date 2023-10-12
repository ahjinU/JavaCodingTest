import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int cnt, N, M, result = Integer.MAX_VALUE;
	static int board[][];
	static List<CCTV> list = new ArrayList<>();

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };

	static int[][][] cctvDis = { { { 0 }, { 1 }, { 2 }, { 3 } }, { { 0, 2 }, { 1, 3 } },
			{ { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 0 } }, { { 0, 1, 2 }, { 1, 2, 3 }, { 2, 3, 0 }, { 3, 0, 1 } },
			{ { 0, 1, 2, 3 } } };

	static class CCTV {
		int y;
		int x;
		int num;

		public CCTV(int y, int x, int num) {
			super();
			this.y = y;
			this.x = x;
			this.num = num;
		}

		public CCTV(int y, int x, int num, int[] possDir) {
			super();
			this.y = y;
			this.x = x;
			this.num = num;
		}
	}

	public static void main(String[] args) throws IOException {

		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] > 0 && board[i][j] < 6) {// cctv
					list.add(new CCTV(i, j, board[i][j] - 1));
				}
			}
		}

		dfs(0, board);

		System.out.println(result);
	}

	private static void dfs(int num, int[][] arr) {

		if (num >= list.size()) {
			if (num == list.size()) {
				result = Math.min(countZero(arr), result);
			}
			return;
		}

		CCTV curCCTV = list.get(num);

		for (int dir = 0; dir < cctvDis[curCCTV.num].length; dir++) {
			int[][] tmp = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					tmp[i][j] = arr[i][j];
				}
			}
			int possiDirs[] = cctvDis[curCCTV.num][dir]; // { 0, 2 },
			for (int i = 0; i < possiDirs.length; i++) {
				monitor(curCCTV.x, curCCTV.y, tmp, possiDirs[i]); // 0
			}
			dfs(num + 1, tmp);
		}

	}

	private static int countZero(int[][] arr) {
		int cnt = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 0)
					cnt++;
			}
		}

		return cnt;
	}

	private static void monitor(int x, int y, int[][] tmp, int dir) {
		while (true) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if (nx < 0 || nx >= M || ny < 0 || ny >= N) // 범위 넘어서면
				break;
			if (tmp[ny][nx] == 6)
				break; // 벽이면
			tmp[ny][nx] = -1;
			x = nx;
			y = ny;
		}
	}

}