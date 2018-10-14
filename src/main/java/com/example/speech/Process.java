package com.example.speech;

import java.util.HashMap;

public class Process{
	static HashMap <String, Integer> dataTable;
	// 1- In the dataTable, dynamicKeys map to index 1
	static HashMap <String, Integer> dynamicKeys;
	// 2- In the dataTable, staticKeys map to index 2 
	static HashMap <String, String> staticKeys;
	// 3- In the dataTable, variables map to index 3 
	HashMap <String, String> variables;
	// 4- In the dataTable, functions map to index 4  
	HashMap <String, Function> functions;
	
	static int indentation = 0; 
	/**
	 * This function processes the raw array of text received from GCP.
	 * @param currentFile
	 * @param newStr
	 * @return an array of strings for processed java commands
	 */
	public static String[] process(String[] currentFile, String newStr) {
		dataTable = new HashMap <String, Integer>();
		dynamicKeys = new HashMap <String, Integer>();
		staticKeys = new HashMap <String, String>();
		
		String line = newStr.trim();
		initMap();
		initDynamicMap();
		initStaticMap();
		indentation = 0; 
		
		int numCom = 0;
		String [] commands = line.split("pumpkin");
		numCom = commands.length;
		
		if(!newStr.endsWith("pumpkin")) {
			numCom--;
		}
		
		for(int i = 0; i<numCom; i++ ) {
			System.out.println(commands[i]);
		}
		
		String [] fin = new String[numCom];
		for(int i = 0; i<numCom;i++)
		{
			fin[i]=analyze(commands[i]);
		}
		
		return fin;
	}
	
	 /**
	 * This function maps the keys to corresponding static 
	 * and dynamic indices while adding them to the HashMap:dataTable
	 */
	private static void initMap() {
		dataTable.put("create", 1);
		dataTable.put("create class", 1);
		dataTable.put("create main", 1);
		
		dataTable.put("body", 1);
		dataTable.put("print", 1);
		dataTable.put("end", 1);
		dataTable.put("end body", 2);
		dataTable.put("and", 1);
		dataTable.put("and body", 2);
	}

	/**
	 * This function maps the dynamic keys to indices
	 * in the HashMap:dynamicKeys
	 */
	private static void initDynamicMap() {
		dynamicKeys.put("create class", 1);
		dynamicKeys.put("create main", 2);
		dynamicKeys.put("body", 3);
		dynamicKeys.put("print", 4);
	}
	
	/**
	 * This function maps the static keys to indices
	 * in the HashMap:staticKeys
	 */
	private static void initStaticMap() {
		staticKeys.put("end body", "}");
		staticKeys.put("and body", "}");
	}
	
	/**
	 * This function creates a class, a main method and prints a
	 * statement dynamically taking input from the user speech.
	 * @param num
	 * @param left
	 * @return
	 */
	private static String switchDyn(int num, String [] left) {
		String result = "";
		switch(num) {
		case 1: result = createClass(left);
			break;
		case 2: result = createMain(left);
			break;
		case 3:
			break;
		case 4: 
			result = print(left);
			break;
		default: result = "";
		}
		
		return result;
	}
	
	
	/**
	 * This function analyzes the input string and classifies them into
	 * static or dynamic keywords,further analyzing their sub-types in 
	 * static/dynamic HashMaps and passes them to the apt functions.
	 * @param currStr
	 * @return a string processed code
	 */
	private static String analyze(String currStr) {
		currStr = currStr.trim();
		String [] words = currStr.split(" ");
		String result = "";
		int num = dataTable.get(words[0]);
	
		String command = words[0];
		int structure = 0;
		int i = 1;
		
		/**If the number is 1, see if the first AND second word are in map.
		If they are in the map, and the number is still 1, repeat for words 1-3 */
		
		for(i = 1; i < words.length; i++) {
			while(dataTable.containsKey(command) && i<words.length) {
				structure = dataTable.get(command);
				
				if(null!= dataTable.get(command + " " + words[i])) {
					int val = dataTable.get(command + " " + words[i]);
					switch(val) {
						case 1:
							command = command + " "+ words[i];
							structure = 1;
						break;
						case 2: 
							command = command + " "+ words[i];
							structure = 2;
						break;
						case 3: 
							command= command + " "+ words[i];
							structure = 3;
						break;
						case 4: 
							command = command + " "+ words[i];
							structure = 4;
						break;
					}
					i++;
				}
				else
					break;
			}
			break;
		}

		//Creating a leftover array, containing the rest of the words (if keyword is just one word)
		String [] left = new String[words.length-i];
	
		for(int j = i; j < words.length-i+1; j++) {
			left[j-i] = words[j];
		}
		System.out.print(command+" comm\n");
		
		switch(structure) {
			case 1:
				System.out.print(dynamicKeys.get(command.trim())+ "\n");
				result = switchDyn(dynamicKeys.get(command.trim()), left);
				break;
			case 2:
				result = staticKeys.get(command);
				if(result=="}")
					indentation--;
				String interm = "";
				for(int k = 0; k< indentation; k++) {//---------------------<=?
					interm+="\t";
				}
				result = interm + result+"\n";
				break;
		}
		System.out.print(result);
		return result;
	}
	
	/**
	 * This function creates class header and adds indentation to the 
	 * new class being created. 
	 * TODO implement code for: extends, interface, abstract, etc.
	 * @param leftovers
	 * @return code block for class header
	 */
	private static String createClass(String[] leftovers) {
		String result ="";
		for(int i = 0; i< indentation; i++) {//---------------------<=?
			result+="\t";
		}
		result +="public class ";
		
		for(int i = 0; i < leftovers.length - 1; i++) {
			String cap = leftovers[i].toString();
			cap=cap.substring(0, 1).toUpperCase() + cap.substring(1, cap.length());
			result += cap;
		}
		result += " {\n";
		indentation++;
		return result;
		
	}
	
	/**
	 * This function creates main function and proceeds to the body part
	 * from text input.
	 * @param leftovers
	 * @return code block for main method
	 */
	private static String createMain(String [] leftovers) {
		String result ="";
		for(int i = 0; i< indentation; i++) {//---------------------<=?
			result+="\t";
		}
		result += "public static void main(String[] args)";
		result += "{\n"; //For 'body'
		indentation++;
		return result;
	}
	
	/**
	 * This function creates print statements for the String array passed.
	 * @param leftovers
	 * @return code block for a print statement
	 */
	private static String print(String[] leftovers) {
		String result ="";
		for(int i = 0; i< indentation; i++) {//---------------------<=?
			result+="\t";
		}
		result += "System.out.print(\"";
		for(int i = 0; i < leftovers.length; i++) {
			result += leftovers[i];
			if(i+1!= leftovers.length)
				result+=" ";
		}
		result += "\");\n";
		return result;
	}
	
	
}
