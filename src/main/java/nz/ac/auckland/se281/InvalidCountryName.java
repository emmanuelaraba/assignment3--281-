package nz.ac.auckland.se281;

/** This is an exception class that is thrown when the country name is invalid. */
public class InvalidCountryName extends Exception {

  public InvalidCountryName(String message) {
    super(message);
  }
}
