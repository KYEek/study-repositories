package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {

		// Scanner sc = new Scanner(System.in);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {

			String number;
			try {
				number = reader.readLine();

				if (number != null) {

					String[] num_arr = number.split(" ");
					if (num_arr.length == 2) {
						int result = Integer.parseInt(num_arr[0]) + Integer.parseInt(num_arr[1]);
						sb.append(result + "\n");
					}

				}
				else {
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println(sb.toString());

	}

}
