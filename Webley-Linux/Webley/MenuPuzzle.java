//package chai.webley.puzzle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/*	
 * 
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
			BufferedReader br = new BufferedReader(new FileReader("menu3.csv"));
			String targetLine = br.readLine();
			/*	Parsing the string value to double by eliminating all the other characters
			 * 	Variable Target_price to find the combination of dishes
			 * */
			TARGET_PRICE = Double.parseDouble(targetLine.split(",")[1].trim().substring(1, targetLine.split(",")[1].trim().length()-1));
			String lines;
			/*	
			 * */
			logger.info("menu details has been extracted from the csv file");
	        HashMap<Double, String> menuDetails = new HashMap<>();
	        logger.warn("make sure to split the line and delete unnecessary characters");
	        while((lines = br.readLine()) != null){
	        	String menuItems = lines.split(",")[0].trim().replace("\"", "");
	        	double menuPrices = Double.parseDouble(lines.split(",")[1].trim().substring(1, lines.split(",")[1].trim().length()-1));
	        	menuDetails.put(menuPrices, menuItems);
	        }
	        menu(menuDetails);
			
		}
		catch(Exception e) {
			logger.warn(e);
		}
	}

	private static void menu(HashMap<Double, String> menuDetails) {
		ArrayList<Double> priceTags =  new ArrayList<>();
		for(Double d: menuDetails.keySet())
			priceTags.add(d);
		Stack<Double> comboDishStack = new Stack<Double>();
    	double currentPrice = 0.0;
    	count = generateComboMenuItems(priceTags, 0, priceTags.size(), comboDishStack, currentPrice, menuDetails);
    	if(count == 0)
    		System.out.println("No combination of dishes");
	}
	
	private static int generateComboMenuItems(ArrayList<Double> priceTags, int fromIndex, int endIndex, 
			Stack<Double> comboDishStack, double currentPrice, HashMap<Double, String> menuDetails) {
		ArrayList<String> comboDishes = new ArrayList<>();
		/*
        * Check if sum of elements stored in Stack is equal to the expected
        * target sum.
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
	                comboDishStack.push(priceTags.get(i));
	                currentPrice += priceTags.get(i);
	                currentPrice = Math.round(currentPrice * 100.0) / 100.0;
	                /*
	                * Make the currentIndex +1, and then use recursion to proceed
	                * further.
	                */
	                generateComboMenuItems(priceTags, i + 1, endIndex, comboDishStack, currentPrice, menuDetails);
	                currentPrice -= comboDishStack.pop();
	            }
	        }

		}
		catch(Exception e) {
			logger.warn(e);
		}
        return count;
    }
}
