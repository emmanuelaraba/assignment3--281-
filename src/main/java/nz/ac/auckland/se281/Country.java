package nz.ac.auckland.se281;

public class Country {
  private String name;
  private String continent;
  private double taxFees;

  public Country(String name, String continent, double taxFees) {
    this.name = name;
    this.continent = continent;
    this.taxFees = taxFees;
  }

  public int HashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((continent == null) ? 0 : continent.hashCode());
    return result;
  }
}
