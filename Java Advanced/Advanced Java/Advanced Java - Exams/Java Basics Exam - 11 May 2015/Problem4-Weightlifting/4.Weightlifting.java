import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;




public class Weightlifting {

	public static void main (String[] args) {
		
		Scanner read = new Scanner(System.in);
		int n = read.nextInt();
		read.nextLine();
		
		// Filling up our TreeMap with nested LinkedHashMap
		TreeMap<String, TreeMap<String, Long>> weightlifting = new TreeMap<String, TreeMap<String, Long>>();
		for (int i = 0; i < n; i++) {
			String line = read.nextLine(); 
			String[] inputs = line.split(" ");
			String name = inputs[0];
			String exercise = inputs[1];
			Long weights = Long.parseLong(inputs[2]);
			//Checking if our Maps contain the current keys(name and exercise)
			if(!weightlifting.containsKey(name)){
				weightlifting.put(name,new TreeMap<String, Long>()); 
				weightlifting.get(name).put(exercise, weights);
			} else {
				if(!weightlifting.get(name).containsKey(exercise)){
					weightlifting.get(name).put(exercise, weights);
				} else {
					long oldWeights = weightlifting.get(name).get(exercise);
					weightlifting.get(name).put(exercise,weights + oldWeights);
				}
				
			}
		}
		// Keeping our keys in a new variable, so we can iterate through the TreeMap
		Set<String> keys = weightlifting.keySet();
		
		for (String name : keys) {
			TreeMap<String, Long> results = weightlifting.get(name);
			System.out.printf("%s : ",name);
			// Keeping our keys in a new variable, so we can iterate through the LinkedHashMap
			Set<String> exercises = results.keySet();
			//Setting up a boolean variable, so we can put the right number of commas
			boolean isFirst = true;
			for (String exercise : exercises) {
				long totalWeights = results.get(exercise);
				if (!isFirst) {
					System.out.print(", ");
				}
				
				isFirst = false;
				System.out.printf("%s - %d kg",exercise, totalWeights);

				
			}
			System.out.println();
		}
		
	}
}
