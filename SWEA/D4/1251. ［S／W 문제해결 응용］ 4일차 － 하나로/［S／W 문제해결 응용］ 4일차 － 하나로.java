
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int T, N;
	static double E;
	static int parents[], xArr[], yArr[], visited[];
	static Edge edgeList[];

	static class Edge implements Comparable<Edge> {

		int from;
		int to;
		double weight;

		public Edge(int from, int to, double weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
	}

	static void make() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}

	static int find(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = find(parents[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return false;

		parents[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			make();

			xArr = new int[N];
			yArr = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				xArr[j] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				yArr[j] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			E = Double.parseDouble(st.nextToken());

			// 2개 뽑기
			visited = new int[N];
			for (int i = N - 2; i < visited.length; i++) {
				visited[i] = 1;
			}

			edgeList = new Edge[(N * (N - 1)) / 2]; // 나오는 모든 간선 수만큼 배열 생성
			int edgeInd = 0;

			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					long xlen = Math.abs(xArr[i] - xArr[j]);
					long ylen = Math.abs(yArr[i] - yArr[j]);
					double weightE = (Math.pow(xlen, 2) + Math.pow(ylen, 2)) * E;
					edgeList[edgeInd++] = new Edge(i, j, weightE);
				}
			}

			Arrays.sort(edgeList); // 정렬

			double result = 0;
			int cnt = 0;
			for (Edge edge : edgeList) {
				if (union(edge.from, edge.to)) {
					result += edge.weight;
					if (++cnt == N - 1)
						break;
				}
			}

			long answer = Math.round(result);
			sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
		}

		System.out.println(sb);
	}

}