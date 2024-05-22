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

  public List<Country> findShortestPath(Country country1, Country country2) {
    Queue<Country> queue = new LinkedList<>();
    Map<Country, Country> parent = new HashMap<>();
    Set<Country> visited = new HashSet<>();
    queue.add(country1);
    visited.add(country1);
    // BFS traversal
    while (!queue.isEmpty()) {
      Country current = queue.poll();
      if (current.equals(country2)) {
        // stopping early if we reach the destination node
        break;
      }
      // going through all the adjacencies for the current country
      for (Country neighbour : map.get(current)) {
        // if it hasnt been visited yet
        if (!visited.contains(neighbour)) {
          queue.add(neighbour);
          visited.add(neighbour);
          // put the current node and its previous node in the parent map
          parent.put(neighbour, current);
        }
      }
    }
    // reconstructing the path
    List<Country> path = new ArrayList<>();
    Country current = country2;
    while (current != null) {
      path.add(current);
      current = parent.get(current);
    }
    Collections.reverse(path);
    return path;
  }
}
