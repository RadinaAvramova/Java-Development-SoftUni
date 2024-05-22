import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class CollectResources {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		String[] inputResources = scanner.nextLine().split(" ");
		Integer numberOfPaths = Integer.parseInt(scanner.nextLine());
		ArrayList<Integer> quantities = new ArrayList<Integer>();
		
		Integer sumedResources = 0;
		
		for (int i = 0; i < numberOfPaths; i++) {
			String[] arguments = scanner.nextLine().split(" ");
			Integer startIndex = Integer.parseInt(arguments[0]);
			Integer step = Integer.parseInt(arguments[1]);
			String[] currentResources = inputResources.clone();
			
			for (int j = startIndex; true; j = (j + step) % currentResources.length) {

				String[] resource = currentResources[j].split("_");
				if(resource[0].contains("@")){
					break;
				}
				
				if(resource[0].equals("wood") || resource[0].equals("gold") || resource[0].equals("stone") || resource[0].equals("food")){
					if(resource.length < 2){
						currentResources[j] = "@" + currentResources[j];
						sumedResources += 1;
					}
					else{
						currentResources[j] = "@" + currentResources[j];
						sumedResources += Integer.parseInt(resource[1]);
					}
				}
			}
			
			quantities.add(sumedResources);
			sumedResources = 0;
		}
		
		System.out.println(Collections.max(quantities));
	}
}