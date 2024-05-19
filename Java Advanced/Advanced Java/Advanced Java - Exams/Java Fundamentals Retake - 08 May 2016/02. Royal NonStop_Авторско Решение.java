import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

public class _02_RoyalNonStop {
	
	//METHOD FOR PARSING STRING ARRAY INTO INTEGER ARRAY
	//IT IS A SNIPPET I CREATED
	//USED FOR PARSING SPLITTED INPUT
	private static Integer[] parseArgsToInteger(String[] inputArgs) {
		Integer[] parsedResults = new Integer[inputArgs.length];

		for (int i = 0; i < inputArgs.length; i++) {
			parsedResults[i] = Integer.parseInt(inputArgs[i]);
		}

		return parsedResults;
	}
	
	//METHOD FOR PARSING STRING ARRAY INTO DOUBLE ARRAY
	private static Double[] parseArgsToDouble(String[] inputArgs) {
		Double[] parsedResults = new Double[inputArgs.length];

		for (int i = 0; i < inputArgs.length; i++) {
			parsedResults[i] = Double.parseDouble(inputArgs[i]);
		}

		return parsedResults;
	}
	
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		
		//THE DIMENSIONS OF THE MATRIX PARSED WITH THE METHOD
		Integer[] dimensions = parseArgsToInteger(scanner.nextLine().split(" "));
		Integer maxRow = dimensions[0];
		Integer maxColumn = dimensions[1];
		
		//THE PRICES OF THE PRODUCTS PARSED WITH THE METHOD
		//STORED IN AN ARRAY FOR A CERTAIN PURPOUSE YOU'LL SEE BELOW
		Double[] productPrices = parseArgsToDouble(scanner.nextLine().split(" "));
		
		//BIGDECIMAL USE, WHY ? BECAUSE ACCOARDING TO THE CONSTRAINTS, THE WORST CASE IS 2^64 * 10000 * 10000 WHICH IS ALOT
		//AND IT CAN BE DONE MANY TIMES
		BigDecimal totalMoney = BigDecimal.ZERO;
		//TOTAL CUSTOMERS COUNTER
		Integer totalCustomers = 0;
		
		String inputLine = scanner.nextLine();
		//MAIN LOOP
		while(!inputLine.equals("Royal Close")) {
			
			//DECLARING A CURRENTSUM VARIABLE
			BigDecimal currentSum = BigDecimal.ZERO;
			
			//PARSING THE CURRENT CUSTOMER ARGUMENTS
			Integer[] customerArguments = parseArgsToInteger(inputLine.split(" "));	
			Integer initialRow = customerArguments[0];
			Integer initialColumn = customerArguments[1];
			
			//CHECK IF THE ROW IS BIGGER THAN THE COLUMN
			if(initialRow >= initialColumn) {
				//IF SO WE FIRST GO LEFT
				for (Integer currentColumn = initialColumn; currentColumn >= 0; currentColumn--) {
					//WE TAKE THE ROW AND COLUMN 
					BigDecimal rowValue = new BigDecimal(initialRow + 1);
					BigDecimal colValue = new BigDecimal(currentColumn + 1);
					
					//NOW, IN THE productPrices ARRAY WE STORE THE LUKANKA VALUE AT INDEX 0 AND THE RAKIJA AT 1
					//BY PERFOMRING A MOD WITH THE INITIAL ROW AND 2 WE GET IF THE INITIAL ROW IS EVEN OR ODD
					//IF IT IS EVEN IT WOULD RETURN 0 AT WHICH INDEX THE LUKANKA IS
					//IF IT IS ODD IT WOULD RETURN 1 AT WHICH INDEX THE RAKIJA IS
					//THAT WAY WE AVOID UNNECESSARY IF AND ELSE
					BigDecimal productPrice = new BigDecimal(productPrices[initialRow % 2]);
					
					BigDecimal productSum = rowValue.multiply(colValue).multiply(productPrice);
					
					currentSum = currentSum.add(productSum);
				}
				
				//THEN WE GO UP
				for (Integer currentRow = initialRow - 1; currentRow > 0; currentRow--) {
					//SAME THINGS HERE
					BigDecimal rowValue = new BigDecimal(currentRow + 1);
					BigDecimal productPrice = new BigDecimal(productPrices[currentRow % 2]);
					
					//THE COLUMN IS 1, SO NO MULTIPLICATION IS REQUIRED
					BigDecimal productSum = rowValue.multiply(productPrice);
					
					currentSum = currentSum.add(productSum);
				}
			}
			else {
				//IF THE COLUMN IS MORE THAN THE ROW
				//FIRST WE GO UP
				for (Integer currentRow = initialRow; currentRow >= 0; currentRow--) {
					//SAME THINGS HERE
					BigDecimal rowValue = new BigDecimal(currentRow + 1);
					BigDecimal colValue = new BigDecimal(initialColumn + 1);
					
					BigDecimal productPrice = new BigDecimal(productPrices[currentRow % 2]);
					
					BigDecimal productSum = rowValue.multiply(colValue).multiply(productPrice);
					
					currentSum = currentSum.add(productSum);			
				}
				
				//THEN WE GO LEFT
				for (Integer currentColumn = initialColumn - 1; currentColumn > 0; currentColumn--) {
					//SAME THINGS HERE
					BigDecimal colValue = new BigDecimal(currentColumn + 1);
					
					//THE LUKANKA PRICE
					//WE DID NOT DIVIDE BECAUSE IF THE INITIAL ROW IS ODD, IT MIGHT RETURN BAD VALUES
					BigDecimal productPrice = new BigDecimal(productPrices[0]);
					
					//THE ROW IS 1, SO NO MULTIPLICATION IS REQUIRED
					BigDecimal productSum = colValue.multiply(productPrice);
					
					currentSum = currentSum.add(productSum);
				}
			}
			
			//WE ADD THE CURRENTSUM
			totalMoney = totalMoney.add(currentSum);
			//WE INCREMENT THE CUSTOMERS
			totalCustomers++;
			
			//READ THE NEXT LINE
			inputLine = scanner.nextLine();
		}
		
		//PRINTING THE TOTALMONEY ROUNDED THE DEFAULT WAY, TO THE 6TH DIGIT
		System.out.println(totalMoney.setScale(6, RoundingMode.HALF_EVEN));
		System.out.println(totalCustomers);
		//NOTICE THAT WE DID NOT USE A MATRIX
		//IT WAS NOT NEEDED FOR THE CURRENT PROBLEM
	}
}
