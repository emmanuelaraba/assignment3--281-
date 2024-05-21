package nz.ac.auckland.se281;

import java.util.*;

public class RiskMap {
  private Map<Country, List<Country>> map;

  public RiskMap() {
    this.map = new HashMap<>();
  }

  public Map<Country, List<Country>> getMap() {
    return map;
  }

  public void setAdjacencies(Country country, ArrayList<Country> adjacencies) {
    map.put(country, adjacencies);
  }

  public void addCountry(Country country) {
    map.put(country, new ArrayList<>());
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

  /**
   * Returns the country object that shares the name we are looking for.
   *
   * @param name the country we are looking for
   * @return the country object that shares the name we are looking for.
   */
  public Country returnCountry(String name) {
    for (Country country : map.keySet()) {
      if (country.getName().equals(name)) {
        return country;
      }
    }
    return null;
  }

  public void getCountries() {
    for (Country country : map.keySet()) {
      System.out.println(country.getName());
    }
  }

  public int getNumCountries() {
    return map.size();
  }
}
