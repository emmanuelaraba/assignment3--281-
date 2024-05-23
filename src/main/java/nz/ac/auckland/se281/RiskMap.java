package nz.ac.auckland.se281;

import java.util.*;

public class RiskMap {
  private Set<Country> countrySet = new LinkedHashSet<>();
  private Map<Country, LinkedHashSet<Country>> map;

  public RiskMap() {
    this.map = new HashMap<>();
  }

  public void addCountry(Country country) {
    countrySet.add(country);
    map.putIfAbsent(country, new LinkedHashSet<>());
  }

  public void addAdjacency(Country country1, Country country2) {
    map.get(country1).add(country2);
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

  public List<String> getContinentList(List<Country> countries) {
    List<String> continentsList = new ArrayList<>();
    Set<String> continentsSet = new LinkedHashSet<>();
    for (Country country : countries) {
      String continent = country.getContinent();
      continentsSet.add(continent);
    }
    continentsList.addAll(continentsSet);
    return continentsList;
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

  public List<Country> findShortestPath(Country origin, Country destination) {
    Queue<Country> queue = new LinkedList<>();
    queue.add(origin);

    Map<Country, Country> parent = new HashMap<>();
    parent.put(origin, null);

    Set<Country> visited = new LinkedHashSet<>();
    visited.add(origin);

    // BFS traversal
    while (!queue.isEmpty()) {
      Country current = queue.poll();
      // going through all the adjacencies for the current country
      for (Country neighbour : map.get(current)) {
        // if it hasnt been visited yet
        if (!visited.contains(neighbour)) {
          visited.add(neighbour);
          queue.add(neighbour);
          // put the current node and its previous node in the parent map
          parent.put(neighbour, current);
        }
      }
    }
    // reconstructing the path
    Country current = destination;
    List<Country> path = new ArrayList<Country>();
    // starting from the destination and going back to the origin
    while (current != null) {
      path.add(current);
      current = parent.get(current);
    }
    Collections.reverse(path);
    return path;
  }
}
