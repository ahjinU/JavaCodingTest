import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int cnt, N, result, dirSec[], dirInd, headX, headY, tailX, tailY;
	static String dirDir[];
	static int board[][];

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };

	public static void main(String[] args) throws IOException {

		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		board = new int[N][N];

		st = new StringTokenizer(bf.readLine());
		int appleCnt = Integer.parseInt(st.nextToken());

		// 사과 설정
		for (int i = 0; i < appleCnt; i++) {
			st = new StringTokenizer(bf.readLine());
			int ai = Integer.parseInt(st.nextToken()) - 1;
			int aj = Integer.parseInt(st.nextToken()) - 1;
			board[ai][aj] = -1;
		}

		st = new StringTokenizer(bf.readLine());
		int dirCnt = Integer.parseInt(st.nextToken());

		dirSec = new int[dirCnt];
		dirDir = new String[dirCnt];

		for (int i = 0; i < dirCnt; i++) {
			st = new StringTokenizer(bf.readLine());
			dirSec[i] = Integer.parseInt(st.nextToken());
			dirDir[i] = st.nextToken();
		}

		int sec = 0;
		int curDir = 1; // 처음엔 오른쪽
		int maxLen = 1;
		board[0][0] = maxLen;

		while (true) {
			
			sec++;

			if (!check(headX + dx[curDir], headY + dy[curDir]))
				break;


			headX += dx[curDir];
			headY += dy[curDir];

			// 사과일때
			if (board[headY][headX] == -1) {
				board[headY][headX] = ++maxLen; // 뱀 몸 증가
			}

			// 사과 아닐때
			else if (board[headY][headX] == 0) {
				board[headY][headX] = ++maxLen; // 뱀 몸 증가
				int tmpLen = board[tailY][tailX]; // 꼬리 인덱스
				board[tailY][tailX] = 0;// 꼬리 없애기
				for (int i = 0; i < 4; i++) {
					int nTailY = tailY + dy[i];
					int nTailX = tailX + dx[i];

					if (nTailY < 0 || nTailY >= N || nTailX < 0 || nTailX >= N)
						continue;

					// 꼬리 위치 변경
					if (board[nTailY][nTailX] == tmpLen + 1) {
						tailY = nTailY;
						tailX = nTailX;
					}
				}
			}

			if (dirInd < dirCnt && sec == dirSec[dirInd]) {
				if (dirDir[dirInd].equals("D")) { // 오른쪽 회전
					curDir += 1;
					if (curDir > 3) {
						curDir -= 4;
					}
				} else { // 왼쪽 회전
					curDir -= 1;
					if (curDir < 0) {
						curDir += 4;
					}
				}
				dirInd++; // 다음 회전 초
			}

		}

		System.out.println(sec);

	}

	private static boolean check(int j, int i) {

		if (i < 0 || i >= N || j < 0 || j >= N) {
			return false;
		}
		if (board[i][j] > 0) {
			return false;
		}
		return true;
	}

}