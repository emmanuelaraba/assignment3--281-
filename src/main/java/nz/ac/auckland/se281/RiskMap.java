package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/** This class represents the map of the Risk game. */
public class RiskMap {
  private Set<Country> countrySet = new LinkedHashSet<>();
  private Map<Country, LinkedHashSet<Country>> map;

  public RiskMap() {
    this.map = new HashMap<>();
  }

  /**
   * Adds a country to the map.
   *
   * @param country
   */
  public void addCountry(Country country) {
    // adds country if absent in the list

    countrySet.add(country);
    map.putIfAbsent(country, new LinkedHashSet<>());
  }

  /**
   * Adds an adjacency between two countries.
   *
   * @param country1 the first country
   * @param country2 the second country
   */
  public void addAdjacency(Country country1, Country country2) {
    map.get(country1).add(country2);
  }

  /**
   * Returns the list of continents traversed in a given list
   *
   * @param countries
   * @return the list of continents traversed
   */
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
   * Returns the total tax fees of the countries in the list.
   *
   * @param countries
   * @return the total tax fees of the countries
   */
  public int getTaxTotal(List<Country> countries) {
    int taxTotal = 0;
    for (int i = 1; i < countries.size(); i++) {
      taxTotal += countries.get(i).getTaxFees();
    }
    return taxTotal;
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
