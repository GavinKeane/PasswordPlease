/*
	Utility methods
*/
import java.util.concurrent.TimeUnit;
public class Game{

	//print text character by character
	public static void print(String text){
		char[] chars = text.toCharArray();
		for (char ch : chars) {
			try{
				System.out.print(ch);
				//pause between each character
				TimeUnit.MICROSECONDS.sleep(100);
			} catch (InterruptedException e) {
        System.err.format("IOException: %s%n", e);
      }
		}
	}
	
	//print with newline at the end
	public static void println(String text){
		char[] chars = text.toCharArray();
		for (char ch : chars) {
			try{
				System.out.print(ch);
				//pause between each character
				TimeUnit.MICROSECONDS.sleep(100);
			} catch (InterruptedException e) {
        System.err.format("IOException: %s%n", e);
      }
		}
		System.out.println("");
	}
	
}
