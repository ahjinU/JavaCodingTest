
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int T, N, M, score[], visited[];
	static List<List<Integer>> list;

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < T; tc++) {
			int result = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			score = new int[N + 1];
			list = new ArrayList<>();

			for (int i = 0; i < N + 1; i++) {
				list.add(new ArrayList<>());
			}

			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				list.get(a).add(b);
			}
			// 리스트 완성

			for (int i = 1; i < N + 1; i++) {
				visited = new int[N + 1];
				dfs(i, i);
			}

			for (int i = 1; i < N + 1; i++) {
				if (score[i] == N - 1)
					result++;
			}

			sb.append("#").append(tc + 1).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int num, int start) {
		List<Integer> cur = list.get(num);
		for (int j = 0; j < cur.size(); j++) {
			if (visited[cur.get(j)] == 0) {
				visited[cur.get(j)] = 1;
				score[start]++;
				score[cur.get(j)]++;
				dfs(cur.get(j), start);
			}
		}
		return;
	}
}