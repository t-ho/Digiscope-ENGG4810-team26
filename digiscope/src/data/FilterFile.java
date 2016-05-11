package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ToanHo
 */
public class FilterFile {
	private ArrayList<Double> firstColumn;
	private ArrayList<Double> secondColumn;
	private int type;
	private boolean isValid;
	
	public FilterFile() {
		firstColumn = new ArrayList<Double>();
		secondColumn = new ArrayList<Double>();
		type = Integer.MAX_VALUE;
		isValid = false;
	}
	
	/**
	 * Load CSV file 
	 * @param absolutePath the absolute path of the csv file
	 * @return true if file is loaded successful and the data is valid. Otherwise, false.
	 */
	public boolean loadCsvFile(String absolutePath) {
		File csvFile = new File(absolutePath);
		if(csvFile.exists()) {
			Scanner scanner = null;
			int nOfColumns = 0;
			try {
				scanner = new Scanner(csvFile);
				if(scanner.hasNextLine()) {
					String[] line = scanner.nextLine().split(",");
					if(line.length == 1) {
						this.type = Constant.FIR;
						nOfColumns = 1;
						try {
							Double number = parseDouble(line[0].trim());
							firstColumn.add(number);
						} catch(NumberFormatException nfe) {
							isValid = false;
							return false;
						}
					} else if(line.length == 2) {
						this.type = Constant.IIR;
						nOfColumns = 2;
						try {
							Double number = parseDouble(line[0].trim());
							firstColumn.add(number);
							number = parseDouble(line[1].trim());
							secondColumn.add(number);
						} catch(NumberFormatException nfe) {
							isValid = false;
							return false;
						}
					} else {
						isValid = false;
						return false;
					}
				} else {
					isValid = false;
					return false;
				}
				while(scanner.hasNextLine()) {
					String[] line = scanner.nextLine().split(",");
					if (line.length == nOfColumns) {
						if (type == Constant.FIR) {
							try {
								Double number = parseDouble(line[0].trim());
								firstColumn.add(number);
							} catch (NumberFormatException nfe) {
								isValid = false;
								return false;
							}
						} else { // type == Constant.IIR
							try {
								Double number = parseDouble(line[0].trim());
								firstColumn.add(number);
								number = parseDouble(line[1].trim());
								secondColumn.add(number);
							} catch (NumberFormatException nfe) {
								isValid = false;
								return false;
							}
						}
					} else {
						isValid = false;
						return false;
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				isValid = false;
				return false;
			} finally {
				if(scanner != null) {
					scanner.close();
				}
			}
			isValid = true;
			return true;
		} else {
			isValid = false;
			return false;
		}
	}
	
	/**
	 * Return a new double represented by the specified string
	 * @param numberString the string to be parsed
	 * @return double value represented by the string argument
	 */
	private Double parseDouble(String numberString) throws NumberFormatException {
		Double result = Double.NaN;
		if (numberString.equals("e")) {
			result = Math.E;
		} else if (numberString.equals("pi")) {
			result =  Math.PI;
		} else {
				result = Double.parseDouble(numberString);
		}
		return result;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isValid() {
		return isValid;
	}
	
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public ArrayList<Double> getFirstColumn() {
		return firstColumn;
	}

	public void setFirstColumn(ArrayList<Double> firstColumns) {
		this.firstColumn = firstColumns;
	}

	public ArrayList<Double> getSecondColumn() {
		return secondColumn;
	}

	public void setSecondColumn(ArrayList<Double> secondColumns) {
		this.secondColumn = secondColumns;
	}

}
