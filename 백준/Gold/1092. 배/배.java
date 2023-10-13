
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static int N, M, result;
	static List<Integer> ship = new ArrayList<>();
	static List<Integer> box = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < N; i++) {
			ship.add(Integer.parseInt(st.nextToken()));
		}
		st = new StringTokenizer(bf.readLine());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < M; i++) {
			box.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(ship, Collections.reverseOrder());
		Collections.sort(box, Collections.reverseOrder());

		if (box.get(0) > ship.get(0)) {
			System.out.println(-1);
			return;
		}
		
//		while (!box.isEmpty()) {
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < box.size(); j++) {
//					if (ship.get(i) >= box.get(j)) {
//						box.remove(j);
//						break;
//					}
//				}
//			}
//			result++;
//		}

		while (!box.isEmpty()) {
			int i = 0;
			int ind = 0;
			while (i < N) {
				if (ind == box.size())
					break;
				else if (ship.get(i) >= box.get(ind)) {
					box.remove(ind);
					i++;
				} else {
					ind++;
				}
			}
			result++;
		}

		System.out.println(result);

	}

}