package nz.ac.auckland.se281;

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
    List<String> countries = Utils.readCountries();
    List<String> adjacencies = Utils.readAdjacencies();
    // add code here to create your data structures
    // making new country objects and adding them to the map
    for (String country : countries) {
      String[] countryData = country.split("\n");
      Country newCountry =
          new Country(countryData[0], countryData[1], Integer.parseInt(countryData[2]));
      map.addCountry(newCountry);
    }

    // adding adjacencies to the map
    for (String adjacency : adjacencies) {
      String[] adjacencyData = adjacency.split(",");
      // add code here
    }
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {
    // add code here
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {}
}
