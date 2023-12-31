import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N, peoples[], visited[], isA[], isB[], visitedMin[], total;
	static List<List<Integer>> list = new ArrayList<>();
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		peoples = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {// 1번부터 6번까지 인구수
			peoples[i] = Integer.parseInt(st.nextToken());
			total += peoples[i]; // 합계
		}

		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}

		for (int i = 1; i <= N; i++) {// 1번부터 6번까지 연결 정보
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());

			for (int j = 0; j < num; j++) {
				list.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}

		for (int cnt = 1; cnt <= N; cnt++) { // 개수만큼 조합
			visited = new int[N + 1]; // visited 배열 생성

			for (int i = N - cnt + 1; i <= N; i++) {
				visited[i] = 1;
			}

			do {
				isA = new int[cnt];
				isB = new int[N - cnt];
				int aCnt = 0, bCnt = 0;
				for (int i = 1; i < N + 1; i++) {
					if (visited[i] == 1) {
						isA[aCnt++] = i;
					} else
						isB[bCnt++] = i;
				}

				if (aCnt == 0 || bCnt == 0)
					continue;

				visitedMin = new int[N + 1];
				dfs(isA[0], isA);
				dfs(isB[0], isB);

				int ch = 0;
				for (int i = 1; i < visitedMin.length; i++) {
					if (visitedMin[i] == 0) {
						ch = 1;
						break;
					}
				}
				if (ch == 1)
					continue;

				int aPeopleCnt = 0;
				for (int i = 0; i < isA.length; i++) {
					aPeopleCnt += peoples[isA[i]];
				}
				int bPeopleCnt = total - aPeopleCnt;
				int curMin = Math.abs(aPeopleCnt - bPeopleCnt);
				if (curMin < result) {
					result = curMin;
				}

			} while (np(visited));
		}

		if (result == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(result);

	}

	private static void dfs(int a, int[] arr) {
		Queue<Integer> q = new LinkedList<>();
		visitedMin[a] = 1;
		q.offer(a);

		while (!q.isEmpty()) {
			int cur = q.poll();
			List<Integer> curList = list.get(cur);
			for (int i = 0; i < curList.size(); i++) {
				int nextInd = curList.get(i);
				if (visitedMin[nextInd] == 1)
					continue;
				
				int ch = 0;
				for (int j = 0; j < arr.length; j++) {
					if (arr[j] == nextInd) {
						ch = 1;
						break;
					}
				}

				if (ch == 1) {
					visitedMin[nextInd] = 1;
					q.offer(nextInd);
				}

			}
		}
	}

	private static boolean np(int[] arr) {

		int i = arr.length - 1;
		int j = arr.length - 1;

		while (i > 1 && arr[i - 1] >= arr[i]) {
			i--;
		}

		if (i == 1)
			return false;

		while (arr[j] <= arr[i - 1]) {
			j--;
		}

		swap(arr, j, i - 1);

		Arrays.sort(arr, i, arr.length);

		return true;
	}

	private static void swap(int[] arr, int j, int i) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
