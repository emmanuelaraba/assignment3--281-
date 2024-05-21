package nz.ac.auckland.se281;

import java.util.*;

public class RiskMap {
  private Set<Country> countrySet = new HashSet<>();
  private Map<Country, Set<Country>> map;

  public RiskMap() {
    this.map = new HashMap<>();
  }

  public void addCountry(Country country) {
    countrySet.add(country);
    map.putIfAbsent(country, new HashSet<>());
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
  public Country getCountry(String name) throws InvalidCountryName {
    for (Country country : countrySet) {
      if (country.getName().equals(name)) {
        return country;
      }
    }
    throw new InvalidCountryName("Country not found");
  }

  public Set<Country> getCountries() {
    return countrySet;
  }

  public void getAdjacencies() {
    for (Country country : map.keySet()) {
      System.out.println(country.getName() + " " + map.get(country).size());
    }
  }
}
