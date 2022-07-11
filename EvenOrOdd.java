import java.util.Scanner;

public class EvenOrOdd {

	public static void main(String[] args) {
		System.out.print("Enter a number: ");
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		
		String evenorodd = EvenOrOdd.evenOrOdd(Integer.parseInt(input));
		System.out.println("That number is " + evenorodd);
		scan.close();
	}
	
	static String evenOrOdd(int number) {
	
		if (number%2 == 1) {
			return "odd.";
		}
		else {
			return "even.";
		}
	}
}
