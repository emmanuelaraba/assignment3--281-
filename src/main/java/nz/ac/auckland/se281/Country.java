package nz.ac.auckland.se281;

public class Country {
  private String name;
  private String continent;
  private int taxFees;

  public Country(String name, String continent, int taxFees) {
    this.name = name;
    this.continent = continent;
    this.taxFees = taxFees;
  }

  /**
   * method to calculate the hash code of the country
   *
   * @return the hash code of the country
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  public int getTaxFees() {
    return taxFees;
  }

  public void setTaxFees(int taxFees) {

    this.taxFees = taxFees;
  }

  /**
   * method to check if to countries are equal
   *
   * @param obj the object to compare with
   * @return true if the countries are equal, false otherwise
   */
  @Override
  public boolean equals(Object obj) {
    // method to check if to countries are equal
    if (this == obj) {
      return true;
    }

    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }

    Country other = (Country) obj;

    if (continent == null) {
      if (other.continent != null) {
        return false;
      }
    } else if (!continent.equals(other.continent)) {
      return false;
    }
    return true;
  }

  /**
   * method to get the name of the country
   *
   * @return the name of the country, along witht the continent and the tax fees
   */
  @Override
  public String toString() {

    return name + " => continent: " + continent + ", tax fees: " + taxFees;
  }

  public String getName() {
    return this.name;
  }
}
