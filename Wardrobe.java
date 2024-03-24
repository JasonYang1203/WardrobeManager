import java.util.Arrays;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * An instantiable class that represents a Wardrobe. A Wardrobe object contains and manages Clothing.
 * For use in the Wardrobe Manager project.
 */
public class Wardrobe {

  //data fields
  private Clothing[] wardrobe;
  private int wardrobeSize;

  //constructor

  /**
   * Creates a new Wardrobe object that is empty with the given capacity.
   *
   * @param capacity - the number of clothing that the wardrobe can fit
   * @throws IllegalArgumentException - with a descriptive message if the capacity is non-positive
   *                                   (less than or equal to 0)
   */
  public Wardrobe(int capacity) {
    //check if the capacity is non-positive
    if (capacity <= 0) {
      throw new IllegalArgumentException("Invalid information about capacity: " + capacity);
    }
    wardrobe = new Clothing[capacity];
  }

  /**
   * Getter for the capacity of this wardrobe.
   *
   * @return the number of pieces of clothing this wardrobe can potentially hold
   */
  public int capacity() {
    return this.wardrobe.length;
  }

  /**
   * Getter for the size of this wardrobe.
   *
   * @return the number of pieces of clothing in this wardrobe
   */
  public int size() {
    return this.wardrobeSize;
  }

  /**
   * Gets the array that contains all the Clothing in the wardrobe.
   *
   * @return the wardrobe array
   */
  protected Clothing[] getArray() {
    return this.wardrobe;
  }

  /**
   * Adds a piece of clothing at the end of the wardrobe. If the wardrobe does not have room for the piece of clothing,
   * the wardrobe expands by doubling in capacity. Then adds the new piece of clothing.
   *
   * @param toAdd - the piece of clothing to add to the wardrobe
   * @throws IllegalArgumentException - with a descriptive message if toAdd is already in the wardrobe
   */
  public void addClothing(Clothing toAdd) throws IllegalArgumentException {
    // check if toAdd is already in the wardrobe
    for (int i = 0; i < wardrobe.length; i++) {
      if (wardrobe[i] != null && wardrobe[i].equals(toAdd)) {
        throw new IllegalArgumentException("The item is already in the wardrobe.");
      }
    }
    // toAdd is not in the wardrobe, then try to add it to the wardrobe
    //check if there is any more space to add clothing
    if (this.size() < this.capacity()) {
      //add the clothing to the end of the wardrobe
      for (int i = 0; i < this.capacity(); i++) {
        if (wardrobe[i] == null) {
          wardrobe[i] = toAdd;
          this.wardrobeSize++;
          break; //exit the loop after adding the clothing successfully
        }
      }
      //there is no more space to add clothing
    } else {
      //double the capacity of the original wardrobe and add it to the end
      wardrobe = Arrays.copyOf(wardrobe, wardrobeSize * 2);
      wardrobe[wardrobeSize] = toAdd;
      this.wardrobeSize++;
    }
  }

  /**
   * Finds and returns the piece of clothing with the matching description and brand.
   * The comparisons are CASE INSENSITIVE.
   *
   * @param description - the description of the piece of clothing to find
   * @param brand - the brand of the piece of clothing to find
   * @return - the Clothing object in the Wardrobe that matches the given description and brand
   * @throws NoSuchElementException - with descriptive message if the clothing does not exist in the wardrobe
   */
  public Clothing getClothing(String description, String brand) {
    for (Clothing cloth : wardrobe) {
      //make sure the cloth we are getting is not null
      if (cloth != null) {
        //search the cloth that matches the description and brand
        if (cloth.getDescription().equalsIgnoreCase(description)
            && cloth.getBrand().equalsIgnoreCase(brand)) {
          return cloth;
        }
      }
    }
    throw new NoSuchElementException("The clothing does not exist in the wardrobe.");
  }

  /**
   * Wears the piece of Clothing in this Wardrobe equal to the provided Clothing on the given date.
   *
   * @param toWear - the piece of clothing in the Wardrobe that we want to wear
   * @param year - the year that it will be worn
   * @param month - the month that it will be worn
   * @param day - the day that it will be worn
   * @throws IllegalArgumentException - with a descriptive message if the year is less than 1,
   *                                    or the month is outside the range [1,12]
   */
  public void wearClothing(Clothing toWear, int year, int month, int day) {
    //check the validity of year and month information
    if (year < 1 || month < 1 || month > 12) {
      throw new IllegalArgumentException("Invalid year or month: " + year + ", " + month);
    }

    //find the clothing and wear it
    for (Clothing cloth : wardrobe) {
      if (cloth != null && cloth.equals(toWear)) {
        cloth.wearClothing(year, month, day);
      }
    }
  }


  /**
   * Removes the piece of clothing from the wardrobe that has a matching description and brand.
   *
   * @param description - the description of the piece of clothing to remove
   * @param brand - the brand of the piece of clothing to remove
   * @throws IllegalStateException - with a descriptive message if the wardrobe is empty
   * @throws NoSuchElementException - with a descriptive message if the piece of clothing is not in the wardrobe
   */
  public void removeClothing(String description, String brand) {
    //check if the wardrobe is empty
    if (wardrobeSize == 0) {
      throw new IllegalStateException("The wardrobe is empty");
    }
    boolean found=false;

    //try to find the piece of clothing that matches description and brand,then remove it
    Clothing targetClothing = getClothing(description, brand);
    for (int i = 0; i < wardrobeSize; i++) {
      if (wardrobe[i] != null && wardrobe[i].equals(targetClothing)) {
        found=true;
        // Shift all elements down by one position
        for (int j = i; j < wardrobeSize - 1; j++) {
          wardrobe[j] = wardrobe[j + 1];
        }
        // Set the last element to null
        wardrobe[wardrobeSize - 1] = null;
        // Decrease the size of the wardrobe
        wardrobeSize--;
        break;
      }
    }
    if(! found){throw new NoSuchElementException("No such Clothing in the wardrobe");}
  }

  /**
   * remove all piece of clothing from the wardrobe whole last worn date is Before the given day, month, year
   *
   * @param year - the year of the date to use to remove clothing
   * @param month - month to use to remove cloth
   * @param day - day to use to remove cloth
   */
  public void removeAllClothingWornBefore(int year, int month, int day) {
    LocalDate date = LocalDate.of(year, month, day);
    for (int i = wardrobeSize - 1; i >= 0; i--) {
      if (wardrobe[i] != null && wardrobe[i].getLastWornDate() == null || wardrobe[i].getLastWornDate().isBefore(date)){
        removeClothing(wardrobe[i].getDescription(), wardrobe[i].getBrand());
      }
    }
  }

  /**
   * remove all pieces of clothing from the wardrobe who have been worn fewer times than the given threshold
   *
   * @param threshold the upperbound(exclusive) of number of times worn
   */
  public void removeAllClothingWornNumTimes(int threshold) {
    for (int i = wardrobeSize - 1; i >= 0; i--) {
      if (wardrobe[i].getNumOfTimesWorn() < threshold) {
        removeClothing(wardrobe[i].getDescription(), wardrobe[i].getBrand());
      }
    }
  }

  /**
   * Creates and returns a string representation of this Wardrobe object. Each piece of clothing in the wardrobe should
   * be printed in order on a new line enclosed in [] brackets. The last line should NOT have a new line character.
   *
   * @return the String representation of this Wardrobe object
   */
  @Override
  public String toString() {
    String result = "";
    for (int i = 0; i < wardrobeSize; i++) {
      if (wardrobe[i] != null) {
        result += "[" + wardrobe[i].toString() + "]";
        if (i < wardrobeSize - 1) {
          result += "\n"; // Add newline character for all but the last item.
        }
      }
    }
    return result;
  }

  /**
   * Creates a new Clothing object based on the given String formatted "description,brand,lastWornDate,timesWorn".
   *
   * @param str - the String parse to make a Clothing object
   * @return a Clothing object with the pieces of information in the given string
   * @throws ParseException - with a descriptive message if the string does not have the 4 required pieces of
   *                      information OR if there was an issue converting pieces of information to an int or Date object
   */
  public static Clothing parseClothing(String str) throws ParseException {
    //split the string into four parts
    String[] parts = str.split(",");
    //check if there are four parts from the original string
    if (parts.length != 4) {
      throw new ParseException("Incorrect number of fields", 0);
    }

    String description = parts[0]; //first part is description information
    String brand = parts[1]; //second part is brand information

    // try to make the third part be parsed into LocalDate datatype
    LocalDate lastWornDate = null;
    if (!"null".equals(parts[2]) && !parts[2].isEmpty()) { // Check if the date string is not "null" or empty
      String[] dateParts = parts[2].split("/"); // Split the date string into month, day, and year
      if (dateParts.length == 3) { // Ensure the date string is split into exactly three parts: month, day, year
        String isoDate = dateParts[2] + "-" + dateParts[0] + "-" + dateParts[1]; // Convert to yyyy-MM-dd format
        try {
          lastWornDate = LocalDate.parse(isoDate); // Parse the ISO-8601 formatted date string
        } catch (Exception e) {
          throw new ParseException("Error parsing the lastWornDate", 0);
        }
      } else {
        throw new ParseException("Date format should be MM/dd/yyyy", 0);
      }
    }


    //try to make the fourth part be parsed into int type
    int timesWorn = 0;
    if (parts[3] != null) {
      try {
        timesWorn = Integer.parseInt(parts[3]);
      } catch (Exception e) {
        throw new ParseException("Times worn is not an integer", 0);
      }
    }

    return new Clothing(description, brand, timesWorn, lastWornDate);
  }

  /**
   * Validates if the provided string represents a date in the format MM/DD/YYYY.
   * The method uses regular expression to check if the input string conforms to the expected date format.
   * This includes validating that the month ranges from 01 to 12, the day ranges from 01 to 31,
   * and the year consists of four digits.
   *
   * @param s - The string representing the date to be validated.
   * @return true if the string matches the MM/DD/YYYY format, otherwise returns false.
   */
  private boolean dateMatch(String s) {
    String regex="^(0[1-9]|1[012])/(0[1-9]|[1,2][0-9]|3[01])/\\d{4}$"; //check month(01-12), day(01-31),year(4 digit)
    return s.matches(regex);

  }

  /**
   * Loads all pieces of clothing into this wardrobe from the designated file. Each piece of clothing in the Wardrobe
   * is written on its own line, formatted as description,brand,lastWornDate,timesWorn.
   * The date must be formatted MM/DD/YYYY.
   * If a line is NOT properly formatted
   * 1) the String "Cannot parse line to Clothing object" should be printed out to the console
   * 2) will be skipped, the method should continue to read the remaining lines.
   *
   * @param saveFile - the File that the information should be read from
   * @return true if ANY of the lines from the file were parsed successfully into Clothing for this Wardrobe,
   *         false otherwise
   */
  public boolean loadFromFile(File saveFile) {
    boolean found = false;
    try {
      Scanner scnr = new Scanner(saveFile);

      while (scnr.hasNextLine()) {
        String line = scnr.nextLine();//string of line of input
        if (line.split(",").length == 4) { // if have 4 element
          if (dateMatch(line.split(",")[2])) { //whether the 3rd element match style of mm/dd/yyyy
            try { //try to add to the wardrobe of this clothing
              Clothing toadd = parseClothing(line); //create clothing to add
              addClothing(toadd); //add into wardrobe
              found = true;
            } catch (ParseException e) {
              System.out.print("Cannot parse line to Clothing object: " + e.getErrorOffset());
            }
          } else {
            System.out.print("Cannot parse line to Clothing objec");
          } // if 3rd element (lat-worn-date) dont match style, print information
        }
      }
      scnr.close();
    } catch (FileNotFoundException e) {
      System.out.print("No such file found"); // if cannot convert into scnr, print no such file exist
    }
    return found; //update whether successfully load to file
  }

  /**
   * Saves all pieces of clothing in this wardrobe to the designated file.
   * Each piece of clothing in the Wardrobe is written on its own line,
   * formatted as description,brand,lastWornDate,timesWorn. The date must be formatted MM/DD/YYYY.
   *
   * @param saveFile - the File that the information should be written to
   * @return true if the file saved successfully, false otherwise
   */
  public boolean saveToFile(File saveFile) {
    boolean save = false;// boolean indicate whether write successfully

    try {
      FileWriter fileWrite = new FileWriter(saveFile);// create new FileWriter object to write
      for (Clothing c : wardrobe) {// for each clothing
        fileWrite.write(c.toString() + "\n");// using Clothing method .toString()
        save = true;
      }
      fileWrite.close();
    } catch (IOException e) {
      e.printStackTrace();

    }
    return save;
  }
}
