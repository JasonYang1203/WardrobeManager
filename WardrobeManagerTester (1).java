import java.text.ParseException;
import java.time.LocalDate;
import java.util.NoSuchElementException;

//////////////// FILE HEADER  //////////////////////////
//
// Title:    P04 Wardrobe Manager 2.0
// Course:   CS 300 Spring 2024
//
// Author:   Jiaming Yang, Yiming Yan
// Email:    jyang766@wisc.edu, yyan227@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Yiming Yan
// Partner Email:   yyan227@wisc.edu
// Partner Lecturer's Name: Hobbes LeGault
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   X Write-up states that pair programming is allowed for this assignment.
//   X We have both read and understand the course Pair Programming Policy.
//   X We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//NONE
///////////////////////////////////////////////////////////////////////////////

/**
 * A tester class for the Wardrobe Manager project. It contains various tests
 * to check the correctness of the Wardrobe and Clothing classes.
 */
public class WardrobeManagerTester {

  /**
   * Tests both of the Clothing constructors and all getters for correctness.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   * @author Michelle
   */
  public static boolean testClothingConstructorAndGetters() {

    // in case we get an unexpected exception from a broken implementation
    // we handle it with a try-catch to avoid our tester from crashing
    try {
      // test the 2-argument constructor
      Clothing c = new Clothing("black t-shirt", "Gildan");

      // check that the four instance data fields have been initialized correctly
      // using the getters to do this we are also checking their correctness
      // in a bad implementation either 1) the constructor didn't intialize a data field correctly
      // OR 2) the getter doesn't return the correct value
      if (!c.getDescription().equals("black t-shirt")) return false;
      if (!c.getBrand().equals("Gildan")) return false;
      if (c.getNumOfTimesWorn() != 0) return false;
      if (c.getLastWornDate() != null) return false;

      // test the 4 argument constructor
      // same idea as the previous test case
      LocalDate date = LocalDate.of(2024, 2, 14); // create a date object for last worn
      c = new Clothing("jeans", "Levi", 3, date);
      if (!c.getDescription().equals("jeans")) return false;
      if (!c.getBrand().equals("Levi")) return false;
      if (c.getNumOfTimesWorn() != 3) return false;
      if (!c.getLastWornDate().equals(date)) return false;

    } catch (Exception e) { // we encounter an exception when we should not, it is a bad implementation
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Tests that both of the Clothing constructors throw the correct type of exception(s)
   * with a message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   * @author Michelle and Hobbes
   */
  public static boolean testClothingConstructorExceptions() {
    // Here we call constructors with input that should lead to an IllegalArgumentException
    // on a correct (good) implementation. ALL of these cases SHOULD throw exceptions,
    // so we test them in separate try-catch statements to verify that each individual
    // case throws an exception.

    try {
      // test the 2 argument constructor with a blank description
      new Clothing(" ", "Gildan");

      return false; // no exception was thrown when it should have been; it's a broken implementation
    } catch (IllegalArgumentException e) {
      // check if the exception has any message; if there is NO message it's a broken implementation
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good, it's a broken implementation
      e.printStackTrace();
      return false;
    }

    try {
      // and make sure a blank brand will also throw an exception
      new Clothing("black t-shirt", "  ");

      return false; // no exception was thrown
    } catch (IllegalArgumentException e) {
      // check if the exception has a message
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    try {
      // test the 4 argument constructor with a blank description
      LocalDate date = LocalDate.of(2021, 12, 25);
      new Clothing(" ", "Gildan", 4, date);

      return false; // no exception was thrown
    } catch (IllegalArgumentException e) {
      // check if the exception has a message
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    try {
      // and verifying that a blank brand will also throw an exception
      LocalDate date = LocalDate.of(2021, 12, 25);
      new Clothing("black t-shirt", "  ", 6, date);

      return false; // no exception was thrown
    } catch (IllegalArgumentException e) {
      // check if the exception has a message,
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    // passed all the tests!
    return true;
  }

  /**
   * Tests for the correctness of the Clothing classes' wearClothing() method.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testClothingWear() {
    //try valid inputs with no exception is going to be thrown
    try {
      Clothing c = new Clothing("black t-shirt", "Lv");
      LocalDate expectedDate = LocalDate.of(2024, 6, 25);
      c.wearClothing(2024, 6, 25);

      if (!c.getLastWornDate().equals(expectedDate)) return false;
      if (c.getNumOfTimesWorn() != 1) return false;

    } catch (Exception e) { // we encounter an exception when we should not, it is a bad implementation
      e.printStackTrace();
      return false;
    }

    //try invalid input for year
    try {
      Clothing c = new Clothing("blue t-shirt", "Lv");
      c.wearClothing(-5, 6, 25);
      return false; // no exception was thrown
    } catch (IllegalArgumentException e) {
      // check if the exception has a message,
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    }

    //try invalid input for month
    try {
      Clothing c = new Clothing("blue t-shirt", "Lv");
      c.wearClothing(2024, 13, 25);
      return false; // no exception was thrown
    } catch (IllegalArgumentException e) {
      // check if the exception has a message,
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    }

    return true; // passed all the tests!
  }

  /**
   * Tests the Wardrobe constructor and all getters for correctness.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */

  public static boolean testWardrobeConstructorAndGetters() {
    // in case we get an unexpected exception from a broken implementation
    // we handle it with a try-catch to avoid our tester from crashing
    try {
      // test the  constructor
      Wardrobe wardrobe = new Wardrobe(10);

      // check that the two instance data fields have been initialized correctly
      // using the getters to do this we are also checking their correctness
      // in a bad implementation
      if (wardrobe.capacity() != 10) return false;
      if (wardrobe.size() != 0) return false;

      // Call the getArray method
      Clothing[] array = wardrobe.getArray();
      if (array == null || array.length != 10) {
        return false;
      }

    } catch (Exception e) { // we encounter an exception when we should not, it is a bad implementation
      e.printStackTrace();
      return false;
    }

    //try if the capacity is non-positive
    try {
      new Wardrobe(-5);
      return false; // no exception was thrown
    } catch (IllegalArgumentException e) {
      // check if the exception has a message,
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }
    return true; // all tests passed

  }

  /**
   * Tests that the Wardrobe constructor throws the correct type of exception(s)
   * with a message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testWardrobeConstructorExceptions() {
    try {
      // test the constructor with invalid capacity
      new Wardrobe(-10);
      return false; // no exception was thrown when it should have been; it's a broken implementation
    } catch (IllegalArgumentException e) {
      // check if the exception has any message; if there is NO message it's a broken implementation
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good, it's a broken implementation
      e.printStackTrace();
      return false;
    }
    return true; // all tests passed
  }

  /**
   * Tests that the Wardrobe's addClothing() method throws the correct type of exception(s)
   * with a message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testAddClothingExceptions() {
    try {
      Wardrobe wardrobe = new Wardrobe(10);
      wardrobe.addClothing(new Clothing("Blue t-shirt", "Dior"));
      wardrobe.addClothing(new Clothing("Green t-shirt", "Lv"));
      wardrobe.addClothing(new Clothing("Blue t-shirt", "Dior"));
      return false; // no exception was thrown when the item is already in the wardrobe
    } catch (IllegalArgumentException e) {
      // check if the exception has any message; if there is NO message it's a broken implementation
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }
    return true; // all tests passed
  }

  /**
   * Tests the Wardrobe's addClothing() method for correctness.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testAddClothing() {
    //try there is more space to add the clothing
    try {
      Wardrobe wardrobe = new Wardrobe(6);
      wardrobe.addClothing(new Clothing("Blue t-shirt", "Dior"));
      wardrobe.addClothing(new Clothing("Green t-shirt", "Lv"));
      wardrobe.addClothing(new Clothing("Black t-shirt", "Dior"));
      wardrobe.addClothing(new Clothing("Black jeans", "Dior"));
      if (wardrobe.size() != 4) return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    //try there is no more space to add the clothing, then should double the capacity to add it
    try {
      Wardrobe wardrobe = new Wardrobe(3);
      wardrobe.addClothing(new Clothing("Blue t-shirt", "Dior"));
      wardrobe.addClothing(new Clothing("Green t-shirt", "Lv"));
      wardrobe.addClothing(new Clothing("Black t-shirt", "Dior"));
      wardrobe.addClothing(new Clothing("Black jeans", "Dior"));
      if (wardrobe.capacity() != 6) return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    return true; // all tests passed
  }


  /**
   * Tests the Wardrobe's getClothing() method for correctness.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testGetClothing() {
    try {
      Wardrobe wardrobe = new Wardrobe(4);
      wardrobe.addClothing(new Clothing("Blue t-shirt", "Dior"));
      wardrobe.addClothing(new Clothing("Green t-shirt", "Lv"));
      wardrobe.addClothing(new Clothing("Black t-shirt", "Dior"));
      wardrobe.addClothing(new Clothing("Black jeans", "Dior"));

      Clothing targetClothing = new Clothing("Green t-shirt", "Lv");
      Clothing gotClothing = wardrobe.getClothing("Green t-shirt", "Lv");
      if (!gotClothing.equals(targetClothing)) {
        return false;
      }
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    return true; //all tests passed
  }


  /**
   * Tests that the Wardrobe's getClothing() method throws the correct type of exception(s)
   * with a message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testGetClothingExceptions() {
    try {
      Wardrobe wardrobe = new Wardrobe(4);
      wardrobe.addClothing(new Clothing("Blue t-shirt", "Dior"));
      wardrobe.addClothing(new Clothing("Green t-shirt", "Lv"));
      wardrobe.addClothing(new Clothing("Black t-shirt", "Dior"));
      wardrobe.addClothing(new Clothing("Black jeans", "Dior"));

      wardrobe.getClothing("Pink t-shirt", "Lv");
      return false;  // no exception was thrown when the item is already in the wardrobe
    } catch (NoSuchElementException e) {
      // check if the exception has any message; if there is NO message it's a broken implementation
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Tests that the Wardrobe's removeClothing() method throws the correct type of exception(s)
   * with a message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveClothingExceptions() {
    try {
      Wardrobe wardrobe = new Wardrobe(10);
      wardrobe.removeClothing("Black t-shirt", "Lv");
      return false; // no exception was thrown
    } catch (IllegalStateException e) {
      // check if the exception has any message; if there is NO message it's a broken implementation
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    }

    try {
      Wardrobe wardrobe = new Wardrobe(5);
      wardrobe.addClothing(new Clothing("Black t-shirt", "Lv"));
      wardrobe.removeClothing("Black", "L");
      return false; // no exception was thrown
    } catch (NoSuchElementException e) {
      // check if the exception has any message; if there is NO message it's a broken implementation
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    }


    return true;
  }

  /**
   * Tests the Wardrobe's removeClothings() method for correctness.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveClothing() {
    try {
      Wardrobe wardrobe = new Wardrobe(4);
      wardrobe.addClothing(new Clothing("Blue t-shirt", "Dior"));
      wardrobe.addClothing(new Clothing("Green t-shirt", "Lv"));
      wardrobe.addClothing(new Clothing("Black t-shirt", "Dior"));
      wardrobe.addClothing(new Clothing("Black jeans", "Dior"));
      wardrobe.removeClothing("Blue t-shirt", "Dior");
      if (wardrobe.size() != 3) return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    return true; //all tests passed
  }

  /**
   * Tests the Wardrobe's removeAllClothingWornBefore() method for correctness.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveAllClothingWornBefore() {
    try {
      Wardrobe wardrobe = new Wardrobe(10);
      wardrobe.addClothing(new Clothing("Blue t-shirt", "Dior", 5,
                                         LocalDate.of(2024, 12, 3)));
      wardrobe.addClothing(new Clothing("Green t-shirt", "Lv", 6,
                                         LocalDate.of(2023, 1, 12)));
      wardrobe.addClothing(new Clothing("Black t-shirt", "Dior", 7,
                                         LocalDate.of(2022, 12, 20)));
      wardrobe.addClothing(new Clothing("Black jeans", "Dior", 2,
                                         LocalDate.of(2022, 6, 1)));
      wardrobe.removeAllClothingWornBefore(2023, 5, 20);
      if (wardrobe.size() != 1) return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    return true; // all tests passed
  }

  /**
   * Tests the Wardrobe's removeAllClothingWornNumTimes() method for correctness.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveAllClothingWornNumTimes() {
    try {
      Wardrobe wardrobe = new Wardrobe(10);
      wardrobe.addClothing(new Clothing("Blue t-shirt", "Dior", 5,
                                         LocalDate.of(2024, 12, 3)));
      wardrobe.addClothing(new Clothing("Green t-shirt", "Lv", 6,
                                         LocalDate.of(2023, 1, 12)));
      wardrobe.addClothing(new Clothing("Black t-shirt", "Dior", 7,
                                         LocalDate.of(2022, 12, 20)));
      wardrobe.addClothing(new Clothing("Black jeans", "Dior", 2,
                                         LocalDate.of(2022, 6, 1)));
      wardrobe.removeAllClothingWornNumTimes(6);
      if (wardrobe.size() != 2) return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    return true; //all tests passed
  }

  /**
   * Tests that the Wardrobe's parseClothing() method throws the correct type of exception(s)
   * with a message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testParseClothingExceptions() {
    //check if it throws an exception when there are not exactly four parts from the original string
    try {
      Wardrobe.parseClothing("Black jeans,Dior,06/01/2024,2,35");
      return false; // no exception was thrown
    } catch (ParseException e) {
      // check if the exception has any message; if there is NO message it's a broken implementation
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    //check if it throws an exception when the third part of the string can not be parsed into LocalDate datatype
    try {
      Wardrobe.parseClothing("Black jeans,Dior,2021/2,2");
      return false; // no exception was thrown
    } catch (ParseException e) {
      // check if the exception has any message; if there is NO message it's a broken implementation
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    //check if it throws an exception when the fourth part of the string can not be parsed into int datatype
    try {
      Wardrobe.parseClothing("Black jeans,Dior,06/01/2023,hello");
      return false; // no exception was thrown
    } catch (ParseException e) {
      // check if the exception has any message; if there is NO message it's a broken implementation
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }
    return true; // all tests passed
  }

  /**
   * Tests the Wardrobe's parseClothing method for correctness.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testParseClothing() {
    Clothing expected = new Clothing("Black jeans", "Dior", 2,
                                      LocalDate.of(2022, 6, 1));
    try {
      Clothing actual = Wardrobe.parseClothing("Black jeans,Dior,06/01/2022,2");
      if (!expected.equals(actual)) return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    return true; // all tests passed
  }

  /**
   * Runs all testing methods and prints out their results.
   *
   * @return true if and only if all the tests return true, false otherwise
   */
  public static boolean runAllTests() {
    boolean test1 = testClothingConstructorExceptions();
    System.out.println("testClothingConstructorExceptions(): " + (test1 ? "pass" : "FAIL"));

    boolean test2 = testClothingConstructorAndGetters();
    System.out.println("testClothingConstructorAndGetters(): " + (test2 ? "pass" : "FAIL"));

    boolean test3 = testClothingWear();
    System.out.println("testClothingWear(): " + (test3 ? "pass" : "FAIL"));

    boolean test4 = testWardrobeConstructorAndGetters();
    System.out.println("testWardrobeConstructorAndGetters(): " + (test4 ? "pass" : "FAIL"));

    boolean test5 = testWardrobeConstructorExceptions();
    System.out.println("testWardrobeConstructorExceptions(): " + (test5 ? "pass" : "FAIL"));

    boolean test6 = testAddClothingExceptions();
    System.out.println("testAddClothingExceptions(): " + (test6 ? "pass" : "FAIL"));

    boolean test7 = testAddClothing();
    System.out.println("testAddClothing(): " + (test7 ? "pass" : "FAIL"));

    boolean test8 = testGetClothing();
    System.out.println("testGetClothing(): " + (test8 ? "pass" : "FAIL"));

    boolean test9 = testGetClothingExceptions();
    System.out.println("testGetClothingExceptions(): " + (test9 ? "pass" : "FAIL"));

    boolean test10 = testRemoveClothing();
    System.out.println("testRemoveClothing(): " + (test10 ? "pass" : "FAIL"));

    boolean test11 = testRemoveClothingExceptions();
    System.out.println("testRemoveClothingExceptions(): " + (test11 ? "pass" : "FAIL"));

    boolean test12 = testRemoveAllClothingWornBefore();
    System.out.println("testRemoveAllClothingWornBefore(): " + (test12 ? "pass" : "FAIL"));

    boolean test13 = testRemoveAllClothingWornNumTimes();
    System.out.println("testRemoveAllClothingWornNumTimes(): "
        + (test13 ? "pass" : "FAIL"));

    boolean test14 = testParseClothingExceptions();
    System.out.println("testParseClothingExceptions(): " + (test14 ? "pass" : "FAIL"));

    boolean test15 = testParseClothing();
    System.out.println("testParseClothing(): " + (test15 ? "pass" : "FAIL"));

    return test1 && test2 && test3 && test4 && test5 && test6 && test7 && test8 && test9 && test10
        && test11 && test12 && test13 && test14 && test15;
  }

  public static void main(String[] args) {
    System.out.println("runAllTests(): " + runAllTests());
  }
}
