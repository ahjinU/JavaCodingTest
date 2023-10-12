import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int cnt, N, M, result = Integer.MAX_VALUE;
	static int board[][], visited[];
	static List<Home> homeList = new ArrayList<>();
	static List<Chicken> chickenList = new ArrayList<>();

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };

	static class Home {
		int x;
		int y;
		int chickenDis;

		public Home(int y, int x) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	static class Chicken {
		int x;
		int y;

		public Chicken(int y, int x) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) throws IOException {

		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 1) { // 집이면
					homeList.add(new Home(i, j));
				} else if (board[i][j] == 2) { // 치킨
					chickenList.add(new Chicken(i, j));
				}
			}
		}

		visited = new int[chickenList.size()];

		for (int i = visited.length - M; i < visited.length; i++) {
			visited[i] = 1;
		}

		do {
			int[][] tmp = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					tmp[i][j] = board[i][j];
				}
			}

			for (int i = 0; i < visited.length; i++) {
				if (visited[i] == 0) { // 치킨집 버리기
					Chicken c = chickenList.get(i);
					tmp[c.y][c.x] = 0;
				}
			}

			result = Math.min(result, calc(tmp));

		} while (np(visited));

		System.out.println(result);
	}

	private static int calc(int[][] arr) {
		int sum = 0;
		List<Chicken> list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 2) {
					list.add(new Chicken(i, j));
				}
			}
		}

		for (int i = 0; i < homeList.size(); i++) {
			int min = Integer.MAX_VALUE;

			for (int j = 0; j < list.size(); j++) {
				min = Math.min(min,
						Math.abs(homeList.get(i).x - list.get(j).x) + Math.abs(homeList.get(i).y - list.get(j).y));
			}

			sum += min;

		}

		return sum;
	}

	private static boolean np(int[] arr) {

		int i = arr.length - 1;
		int j = arr.length - 1;

		while (i > 0 && arr[i] <= arr[i - 1]) {
			i--;
		}

		if (i == 0)
			return false;

		while (arr[j] <= arr[i - 1]) {
			j--;
		}

		swap(arr, i - 1, j);

		Arrays.sort(arr, i, arr.length);

		return true;
	}

	private static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

}