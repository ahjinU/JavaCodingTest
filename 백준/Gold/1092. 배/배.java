import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static int N, M, result;
	static List<Integer> ship = new ArrayList<>();
	static List<Integer> box = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		for (int i = 0; i < N; i++) {
			ship.add(sc.nextInt());
		}
		
		M = sc.nextInt();

		for (int i = 0; i < M; i++) {
			box.add(sc.nextInt());
		}

		Collections.sort(ship, Collections.reverseOrder());
		Collections.sort(box, Collections.reverseOrder());

		if (box.get(0) > ship.get(0)) {
			System.out.println(-1);
			return;
		}

		int shipSize = ship.size();

		while(!box.isEmpty()) {
            int idx =0;
            for(int i=0; i< N; ) {
                if(idx == box.size()) break;
                else if(ship.get(i) >= box.get(idx)) {
                    box.remove(idx);
                    i++;
                }else idx++;
            }
            result++;
        }

		System.out.println(result);

	}

}