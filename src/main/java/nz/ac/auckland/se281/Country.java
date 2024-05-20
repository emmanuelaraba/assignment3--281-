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

  /**
   * method to calculate the hash code of the country
   *
   * @return the hash code of the country
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((continent == null) ? 0 : continent.hashCode());
    return result;
  }

  /**
   * mehtod to check if to countries are equal
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
}
