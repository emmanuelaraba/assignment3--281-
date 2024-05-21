package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.List;

/** This class is the main entry point. */
public class MapEngine {

  public MapEngine() {
    // add other code here if you want
    loadMap(); // keep this method invocation
  }

  /** invoked one time only when constructing the MapEngine class. */
  private void loadMap() {
    RiskMap map = new RiskMap();
    loadCountries();
    List<String> adjacencies = Utils.readAdjacencies();
    List<String> splitAdjacencies = new ArrayList<>();
  }

  /** method to loadCountries into Map */
  private void loadCountries() {
    List<String> countries = Utils.readCountries();
    // Create a new list to store the split country names
    List<String> splitCountries = new ArrayList<>();

    for (String country : countries) {
      String[] split =
          country.split("\n+"); // Splitting by whitespace, adjust the regex as per your requirement
      for (String part : split) {
        splitCountries.add(part);
      }
    }

    // Print the split country names
    for (String splitCountry : splitCountries) {
      String[] country = splitCountry.split(",");
      Country newCountry = new Country(country[0], country[1], Integer.parseInt(country[2]));
    }
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {
    List<String> countries = Utils.readCountries();
    // Create a new list to store the split country names
    List<String> splitCountries = new ArrayList<>();

    for (String country : countries) {
      String[] split =
          country.split("\n+"); // Splitting by whitespace, adjust the regex as per your requirement
      for (String part : split) {
        splitCountries.add(part);
      }
    }

    // Print the split country names
    for (String splitCountry : splitCountries) {
      String[] country = splitCountry.split(",");
      System.out.println(
          "country:" + country[0] + " continent:" + country[1] + " tax:" + country[2]);
    }
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {}
}
