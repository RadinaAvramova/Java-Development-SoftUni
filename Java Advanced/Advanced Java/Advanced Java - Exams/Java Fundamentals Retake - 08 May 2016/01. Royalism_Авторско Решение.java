import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class _01_Royalism {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);

		//COLLECTIONS FOR THE ROYALISTS AND NON-ROYALISTS
		ArrayList<String> royalists = new ArrayList<String>();
		ArrayList<String> nonRoyalists = new ArrayList<String>();
		//ARRAY - REFERENCE TYPE, SO IT CAN BE USED IN LAMBDAS, FROM OUTSIDE
		//BECAUSE I LIKE WRITTING LAMBDAS
		int[] currentSum = new int[1];
		
		//SPLITTING THE INPUT LINE AND MAKING IT A LIST SO IT CAN BE STREAMED
		//THEN FOREACH-ING EVERY NAME IN IT
		Arrays.asList(scanner.nextLine().split(" ")).stream().forEach((name) -> {
			
			//SPLITTING THE NAME INTO CHARACTERS
			//EMPTY SPLIT SPLITS EVERYTHING
			//TURNING IT INTO A LIST, STREAMING, AND FOREACH-INT IT
			Arrays.asList(name.split("")).stream().forEach((character) -> {
				//ADDING EVERY CHARACTER TO THE PREVIOUS SUM
				//AGAIN, ARRAY IS A REFERENCE TYPE AND IT CAN BE USED IN LAMBDAS FROM OUTSIDE,
				//THAT IS WHY AN ARRAY OF EXACTLY 1 ELEMENT WAS CREATED
				//WHEN THE ADDITION ENDS, MOD THE SUM BY 26 (SAME AS SUBTRACTING 26 UNTIL IT BECOMES LESS OR EQUAL TO 26)
				currentSum[0] = (currentSum[0] + (int)character.charAt(0)) % 26;
			});
			
			//CHECKING IF CURRENTSUM IS 18 AND IF SO, ADDING THE NEW ROYALIST TO THE ROYALISTS COLLECTION
			if(currentSum[0] == 18){
				royalists.add(name);
				//RESETTING THE SUM
				currentSum[0] = 0;
			}
			else {
				currentSum[0] = 0;
				//IF NOT, ADDING THE CURRENT NAME TO THE NON-ROYALIST COLLECTION
				nonRoyalists.add(name);
			}
		});
		
		//THE CHECK IF THE ROYALISTS ARE MORE OR EQUAL TO THE NON-ROYALISTS
		//CHECK FOR 0 IS FOR OPTIMIZATION, NOT NEEDED 
		if(royalists.size() > 0 && royalists.size() >= nonRoyalists.size()) {
			//THE PRINTING IS DONE WITH PLACEHOLDERS, SEPARATED BY NEW LINE SYMBOL
			//FIRST THE ROYALISTS SIZE, THEN THE JOINED COLLECTION OF ROYALISTS, AND AT THE LAST LINE THE MESSAGE
			System.out.println(String.format("Royalists - %s\n%s\n%s", royalists.size(), String.join("\n", royalists), "All hail Royal!"));
		}
		else{
			//SAME PRINTING FORMAT
			System.out.println(String.format("%s\n%s", String.join("\n", nonRoyalists), "Royalism, Declined!"));
		}
	}
}
