import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int T, V, E;
	static int[] parents;
	static Edge[] edgeList;

	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	static void make() {
		parents = new int[V+1];

		for (int i = 1; i <= V; i++) {
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

		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken()); // 정점 수
			E = Integer.parseInt(st.nextToken());// 간선 개수

			make();

			edgeList = new Edge[E];
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()); // 정점 수
				int b = Integer.parseInt(st.nextToken());// 간선 개수
				int c = Integer.parseInt(st.nextToken());// 간선 개수

				edgeList[i] = new Edge(a, b, c);
			}

			Arrays.sort(edgeList);

			long result = 0;
			int cnt = 0;

			for (Edge edge : edgeList) {
				if(union(edge.from,edge.to)) {
					result += edge.weight;
					if(++cnt == V-1) break;
				}
			}

			sb.append("#").append(tc + 1).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
}