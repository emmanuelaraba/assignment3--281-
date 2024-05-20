package nz.ac.auckland.se281;

import java.util.*;

public class RiskMap {
  private Map<Country, ArrayList<Country>> map;

  public RiskMap() {
    this.map = new HashMap<>();
  }

  public void addCountry(Country country) {
    map.putIfAbsent(country, new ArrayList<>());
  }

  public void addAdjacency(Country country1, Country country2) {
    addCountry(country1);
    addCountry(country2);
    map.get(country1).add(country2);
    map.get(country2).add(country1);
  }

  public void removeCountry(Country country) {
    map.remove(country);
    // iterating through the map to remove the country from all the adjacency lists
    map.forEach((key, value) -> value.remove(country));
  }

  public void removeAdjacency(Country country1, Country country2) {
    map.get(country1).remove(country2);
    map.get(country2).remove(country1);
  }
}
