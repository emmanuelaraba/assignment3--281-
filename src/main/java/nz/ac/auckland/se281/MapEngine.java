package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.List;

/** This class is the main entry point. */
public class MapEngine {
  private RiskMap countryMap = new RiskMap();

  public MapEngine() {
    // add other code here if you want
    loadMap(); // keep this method invocation
  }

  /** invoked one time only when constructing the MapEngine class. */
  private void loadMap() {
    loadCountries();
    loadAdjacencies();
  }

  private void loadCountries() {
    List<String> countries = Utils.readCountries();
    List<String> splitCountries = new ArrayList<>();

    // loading the countries into the map
    for (String country : countries) {
      String[] split =
          country.split("\n+"); // Splitting by whitespace, adjust the regex as per your requirement
      for (String part : split) {
        splitCountries.add(part);
      }
    }

    // add the split countries to the map
    for (String splitCountry : splitCountries) {
      String[] country = splitCountry.split(",");
      Country newCountry = new Country(country[0], country[1], Integer.parseInt(country[2]));
      countryMap.addCountry(newCountry);
    }
  }

  private void loadAdjacencies() {
    List<String> adjacencies = Utils.readAdjacencies();

    // loading the adjacencies into the map
    for (String adjEntry : adjacencies) {
      String[] adjElements = adjEntry.split(",");
      for (int i = 1; i < adjElements.length; i++) {
        countryMap.addAdjacency(
            countryMap.getCountry(adjElements[0]), countryMap.getCountry(adjElements[i]));
      }
    }
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {
    countryMap.getAdjacencies();
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {}
}
