import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Node implements Comparable<Node> {
		int x;
		int y;
		int cost;

		public Node(int x, int y, int cost) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			if (this.cost < o.cost)
				return -1;
			else
				return 1;
		}

	}

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int cnt, N = 1, board[][], dp[][], result;

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		int cnt = 0;
		while (true) {
			result = 0;

			StringTokenizer st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());

			if (N == 0) {
				System.out.println(sb);
				return;
			}

			cnt++;
			board = new int[N][N];
			dp = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine());
				Arrays.fill(dp[i], Integer.MAX_VALUE);
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			bfs();

			sb.append("Problem ").append(cnt).append(": ").append(dp[N-1][N-1]).append("\n");
		}
	}

	private static void bfs() {
		PriorityQueue<Node> pq = new PriorityQueue<>();

		pq.offer(new Node(0, 0, board[0][0]));

		while (!pq.isEmpty()) {
			Node node = pq.poll();

			for (int i = 0; i < 4; i++) {
				int nextX = node.x + dx[i];
				int nextY = node.y + dy[i];

				if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N)
					continue;

				int nextCost = node.cost + board[nextY][nextX];

				if (dp[nextY][nextX] > nextCost) {
					dp[nextY][nextX] = nextCost;
					pq.add(new Node(nextX, nextY, nextCost));
				}
			}
		}
	}

}