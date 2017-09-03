import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/*	Program to find the combinations of dishes from a menu  	
 * 	for a fixed targer_price
 * */
public class MenuPuzzle {

	private static double TARGET_PRICE = 0.0;
	private static int count = 0;
	static Logger logger = Logger.getLogger(MenuPuzzle.class);
	public static void main(String[] args){
		
		try {
			String log4jConfigFile = System.getProperty("user.dir")
	                + File.separator + "log4j.properties";
	        PropertyConfigurator.configure(log4jConfigFile);
	        logger.info("the main method is invoked");
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader("menu.csv"));
			String targetLine = br.readLine();
			
			/*	Parsing the string value to double by eliminating all the other characters
			 * 	Variable Target_price to find the combination of dishes
			 * */
			TARGET_PRICE = Double.parseDouble(targetLine.split(",")[1].trim().substring(1, targetLine.split(",")[1].trim().length()-1));
			String lines;
			
			/*	loggers to display the logs in case of any information or warnings to be thrown
			 * */
			logger.info("menu details has been extracted from the csv file");
			
			//	HashMap to store all the menu details i.e. name and price of the dish
	        HashMap<Double, String> menuDetails = new HashMap<>();
	        logger.warn("make sure to split the line and delete unnecessary characters");
	        
	        /*	@param: menuItems to store the dish name
	         * 	@param: menuPrices to store the dish price  
	         * */
	        while((lines = br.readLine()) != null){
	        	String menuItems = lines.split(",")[0].trim().replace("\"", "");
	        	double menuPrices = Double.parseDouble(lines.split(",")[1].trim().substring(1, lines.split(",")[1].trim().length()-1));
	        	menuDetails.put(menuPrices, menuItems);
	        }
	        generateComboDishes(menuDetails);
			
		}
		
		/*	Exception to be thrown in case of no file or input exceptions
		 * */
		catch(Exception e) {
			logger.warn(e);
		}
	}

	/*	Function to generate the combination of dishes
	 * 	@param: currentPrice is used to keep updating the menu prices for each dish
	 * 	@param: count -  counter is used to show in case of no combinations
	 * 	
	 * */
	private static void generateComboDishes(HashMap<Double, String> menuDetails) {
		ArrayList<Double> priceTags =  new ArrayList<>();
		for(Double d: menuDetails.keySet())
			priceTags.add(d);
		ArrayList<Double> comboDishStack = new ArrayList<Double>();
    	double currentPrice = 0.0;
    	count = calculateComboDishPrice(priceTags, 0, priceTags.size(), comboDishStack, currentPrice, menuDetails);
    	if(count == 0)
    		System.out.println("No combination of dishes");
	}
	
	private static int calculateComboDishPrice(ArrayList<Double> priceTags, int fromIndex, int endIndex, 
			ArrayList<Double> comboDishStack, double currentPrice, HashMap<Double, String> menuDetails) {
		ArrayList<String> comboDishes = new ArrayList<>();
		/*
        * Check if sum of elements stored in list is equal to the TARGET_PRICE
        * 
        * If so, it will print all the combinations of dishes and store it in the list.
        */
		try {
			if (currentPrice == TARGET_PRICE) {
	        	count++;
	        	for(int i=0;i<comboDishStack.size();i++) {
	        		comboDishes.add(menuDetails.get(comboDishStack.get(i)));
	        	}
	       		System.out.println(comboDishes);
	        }

	        for (int i = fromIndex; i < endIndex; i++) {

	            if (currentPrice + priceTags.get(i) <= TARGET_PRICE) {
	                comboDishStack.add(priceTags.get(i));
	                currentPrice += priceTags.get(i);
	                currentPrice = Math.round(currentPrice * 100.0) / 100.0;
	                /*
	                * Make the currentIndex + 1, and then use recursion procedure to check all the
	                * combinations of the dishes.
	                * It will pop out or remove one by one from the list if no matching combo found
	                */
	                calculateComboDishPrice(priceTags, i + 1, endIndex, comboDishStack, currentPrice, menuDetails);
	                currentPrice -= priceTags.get(i);
	                comboDishStack.remove(priceTags.get(i));
	            }
	        }

		}
		catch(Exception e) {
			logger.warn(e);
		}
        return count;
    }
}
