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
}
