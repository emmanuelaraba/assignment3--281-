package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** This class is the main entry point. */
public class MapEngine {
  private final RiskMap countryMap = new RiskMap();

  public MapEngine() {
    // add other code here if you want
    loadMap(); // keep this method invocation
  }

  /** invoked one time only when constructing the MapEngine class. */
  private void loadMap() {
    // loading the countries and the adjacencies into the countryMap
    loadCountries();
    loadAdjacencies();
  }

  /** method to load all the countries into the map. */
  private void loadCountries() {
    List<String> countries = Utils.readCountries();
    List<String> splitCountries = new ArrayList<>();

    // loading the countries into the map
    for (String country : countries) {
      String[] split =
          country.split("\n+"); // Splitting by whitespace, adjust the regex as per your requirement
      splitCountries.addAll(Arrays.asList(split));
    }

    // add the split countries to the map
    for (String splitCountry : splitCountries) {
      String[] country = splitCountry.split(",");
      Country newCountry = new Country(country[0], country[1], Integer.parseInt(country[2]));
      countryMap.addCountry(newCountry);
    }
  }

  /** method to load all the adjacencies into the map. */
  private void loadAdjacencies() {
    List<String> adjacencies = Utils.readAdjacencies();

    // loading the adjacencies into the map
    for (String adjEntry : adjacencies) {
      String[] adjElements = adjEntry.split(",");
      for (int i = 1; i < adjElements.length; i++) {
        try {
          countryMap.addAdjacency(
              countryMap.getCountry(adjElements[0]), countryMap.getCountry(adjElements[i]));
        } catch (InvalidCountryName e) {
          break;
        }
      }
    }
  }

  /**
   * method to capitalize the first letter of each word.
   *
   * @param input the string to capitalize.
   * @return the string with the first letter of each word capitalized.
   */
  public String captilizeFirstLetterOfEachWord(String input) {
    return Utils.capitalizeFirstLetterOfEachWord(input);
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {

    boolean validCountry = false;
    while (!validCountry) {
      MessageCli.INSERT_COUNTRY.printMessage();
      String countryInput = captilizeFirstLetterOfEachWord(Utils.scanner.nextLine());
      try {
        Country newCountry = countryMap.getCountry(countryInput);
        MessageCli.COUNTRY_INFO.printMessage(
            newCountry.getName(),
            newCountry.getContinent(),
            Integer.toString(newCountry.getTaxFees()));
        validCountry = true;
      } catch (InvalidCountryName e) {
        MessageCli.INVALID_COUNTRY.printMessage(countryInput);
      }
    }
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {
    // insert the source and destination countries
    Country origin = null;
    boolean validOrigin = false;
    // making corresponding string to country object
    String originString;
    // checking if the country is valid
    while (!validOrigin) {
      MessageCli.INSERT_SOURCE.printMessage();
      originString = captilizeFirstLetterOfEachWord(Utils.scanner.nextLine());
      try {
        origin = countryMap.getCountry(originString);
        validOrigin = true;
      } catch (InvalidCountryName e) {
        MessageCli.INVALID_COUNTRY.printMessage(originString);
        MessageCli.INSERT_SOURCE.printMessage();
      }
    }

    Country destination = null;
    String destinationString;
    boolean validDestination = false;
    while (!validDestination) {
      MessageCli.INSERT_DESTINATION.printMessage();
      destinationString = captilizeFirstLetterOfEachWord(Utils.scanner.nextLine());
      try {
        destination = countryMap.getCountry(destinationString);
        validDestination = true;
      } catch (InvalidCountryName e) {
        MessageCli.INVALID_COUNTRY.printMessage(destinationString);
        MessageCli.INSERT_DESTINATION.printMessage();
      }
    }

    List<Country> path = countryMap.findShortestPath(origin, destination);
    String pathString = Arrays.toString(path.toArray());
    List<String> continentPath = countryMap.getContinentList(path);
    String continentPathString = Arrays.toString(continentPath.toArray());
    String taxTotal = Integer.toString(countryMap.getTaxTotal(path));

    if (path.size() > 1) {
      MessageCli.ROUTE_INFO.printMessage(pathString);
    }
    MessageCli.CONTINENT_INFO.printMessage(continentPathString);
    if (taxTotal.equals("0")) {
      MessageCli.NO_CROSSBORDER_TRAVEL.printMessage();
    } else {
      MessageCli.TAX_INFO.printMessage(taxTotal);
    }
  }
}
