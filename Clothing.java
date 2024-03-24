import java.time.LocalDate;

/**
 * An instantiable class that represents a piece of Clothing. For use in the Wardrobe Manager project.
 */
public class Clothing {

  //data fields
  private String brand;
  private String description;
  private LocalDate lastWornDate;
  private int timesWorn;

  //constructors

  /**
   * Creates a new clothing object with the given description and brand. This piece of clothing has been never worn,
   * so its lastWornDate will be null.
   *
   * @param description - the description for this piece of clothing
   * @param brand - the brand of this piece of clothing
   * @throws IllegalArgumentException - with a descriptive message if the description or brand is a blank
   */
  public Clothing(String description, String brand) {
    //check if the description is blank
    if (description == null || description.isBlank()) {
      //throw an exception with a descriptive message
      throw new IllegalArgumentException("No information provided for description.");
    }
    this.description = description;

    //check if the description is blank
    if (brand == null || brand.isBlank()) {
      //throw an exception with a descriptive message
      throw new IllegalArgumentException("No information provided for brand.");
    }
    this.brand = brand;

    this.lastWornDate = null;

  }

  /**
   * Creates a new clothing object with the given description, brand, timesWorn and lastWorn date values.
   *
   * @param description - the description for this piece of clothing
   * @param brand - the brand of this piece of clothing
   * @param timesWorn - the number of times this piece of clothing has been worn
   * @param lastWornDate - the date that this piece of clothing was last worn
   * @throws IllegalArgumentException - with a descriptive message if the description or brand is a blank
   */
  public Clothing(String description, String brand, int timesWorn, LocalDate lastWornDate) {
    //check if the description is blank
    if (description == null || description.isBlank()) {
      //throw an exception with a descriptive message
      throw new IllegalArgumentException("No information provided for description.");
    }
    this.description = description;

    //check if the description is blank
    if (brand == null || brand.isBlank()) {
      //throw an exception with a descriptive message
      throw new IllegalArgumentException("No information provided for brand.");
    }
    this.brand = brand;

    this.timesWorn = timesWorn;
    this.lastWornDate = lastWornDate;
  }

  /**
  * Getter for the brand of this piece of clothing.
  *
  * @return this clothing's brand
  */
  public String getBrand() { return this.brand; }

  /**
  * Getter for the description of this piece of clothing.
  *
  * @return this clothing's description
  */
  public String getDescription() {
    return this.description;
  }

  /**
   * Getter for the date that this piece of clothing was last worn.
   *
   * @return this clothing's last worn date
   */
  public LocalDate getLastWornDate() {
    return this.lastWornDate;
  }

  /**
   * Getter for the number of times this piece of clothing has been worn.
   *
   * @return this clothing's number of times worn
   */
  public int getNumOfTimesWorn() {
    return this.timesWorn;
  }

  /**
   * Checks if Object o equals this clothing object, that is the current instance of Clothing.
   *
   * @param o - the object to check if it equal to this piece of clothing
   * @return true if and only if 1) o is of type Clothing
   *                             2) the brands match ignoring case
   *                             3) the descriptions match ignoring case
   *                             otherwise return false
   */
  @Override
  public boolean equals(Object o) {
    // two pieces of clothing are considered the “same” if the
    // brand and description match, ignoring case.

    // Check if the object is the same as the current instance
    if (this == o) return true;

    // Check if the object is null or not of the Clothing class
    if (o == null || getClass() != o.getClass()) return false;

    // Cast the object to Clothing since we've checked its type
    Clothing clothing = (Clothing) o;

    // Compare brands and descriptions, ignoring case sensitivity
    return clothing.getBrand().equalsIgnoreCase(this.brand)
        && clothing.getDescription().equalsIgnoreCase(this.description);
  }

  /**
   * Creates and returns a string representation of this Clothing object.
   *
   * @return the String representation of this Clothing object
   */
  @Override
  public String toString() {
    String formattedDate = "";
    if (this.lastWornDate == null) {
      formattedDate = null;
    } else {
      int month = lastWornDate.getMonthValue();
      int year = lastWornDate.getYear();
      int day = lastWornDate.getDayOfMonth();
      formattedDate = String.format("%02d/%02d/%d", month, day, year);
    }

    return this.description + "," + this.brand + "," + formattedDate + "," + this.timesWorn;
  }

  /**
   * Updates the number of times this piece of clothing has been worn and the last worn date
   *
   * @param year - the year of the new last worn date
   * @param month - the month of the new last worn date
   * @param day - the day of the last worn date
   * @throws IllegalArgumentException - with a descriptive message if the year is less than 1,
   *                                    or the month is outside the range [1,12]
   */
  public void wearClothing(int year, int month, int day) throws IllegalArgumentException {
    //declare three variables to store the new information
    int newLastWornYear;
    int newLastWornMonth;
    int newLastWornDay;

    //check if the information for year is wrong
    if (year < 1) {
      throw new IllegalArgumentException("Invalid information about year: " + year);
    } else {
      newLastWornYear = year;
    }

    //check if the information for month is wrong
    if (month < 1 || month > 12) {
      throw new IllegalArgumentException("Invalid information about month: " + month);
    } else {
      newLastWornMonth = month;
    }

    //assign the newLastWornDay with new information
    newLastWornDay = day;

    this.lastWornDate = LocalDate.of(newLastWornYear, newLastWornMonth, newLastWornDay);
    this.timesWorn++;

  }
}
