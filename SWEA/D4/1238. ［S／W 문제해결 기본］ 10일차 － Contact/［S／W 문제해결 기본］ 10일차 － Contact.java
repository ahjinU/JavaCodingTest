
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N, K, result = -1, resultInd = -1;
	static List<List<Integer>> list;
	private static int[] visited;

	public static void main(String[] args) throws IOException {
		for (int t = 0; t < 10; t++) {
			list = new ArrayList<>();
			result = -1;
			resultInd = -1;

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 데이터 수
			K = Integer.parseInt(st.nextToken()); // 시작 정점

			visited = new int[101];
			for (int i = 0; i < 101; i++) {
				list.add(new ArrayList<>());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N / 2; i++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				list.get(a).add(b);
			}

			visited[K] = 1;
			bfs(K);

			for (int i = 0; i < visited.length; i++) {
				if (visited[i] >= result) {
					result = visited[i];
					resultInd = i;
				}

			}
			sb.append("#").append(t + 1).append(" ").append(resultInd).append("\n");

		}
		System.out.println(sb);
	}

	private static void bfs(int a) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(a);
		while (!q.isEmpty()) {
			int fir = q.remove();
			List<Integer> cur = list.get(fir);
			for (int i = 0; i < cur.size(); i++) {
				if (visited[cur.get(i)] == 0) {
					visited[cur.get(i)] = visited[fir] + 1;
					q.offer(cur.get(i));
				}
			}
		}
	}

}