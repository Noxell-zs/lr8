package ru.sfu.entity;

/** The entity of the coffee machine */
public class CoffeeMachine {
  private int id = -1;
  private int year;
  private int reservoir;
  private String name;
  private String country;
  private String description;
  private boolean bought = false;

  /** Default constructor */
  public CoffeeMachine() {}
  /**
   * Constructor without id
   * @param name Trade name
   * @param country Country of manufacture
   * @param year Year of manufacture
   * @param reservoir Volume of the coffee reservoir
   * @param description Product description
   */
  public CoffeeMachine(String name, String country,
      int year, int reservoir, String description) {
    setName(name);
    setCountry(country);
    setYear(year);
    setReservoir(reservoir);
    setDescription(description);
  }
  /**
   * Constructor with all fields
   * @param id Number in the table
   * @param name Trade name
   * @param country Country of manufacture
   * @param year Year of manufacture
   * @param reservoir Volume of the coffee reservoir
   * @param description Product description
   */
  public CoffeeMachine(int id, String name, String country,
      int year, int reservoir, String description) {
    this(name, country, year, reservoir, description);
    setId(id);
  }
  /** @param id Number in the table */
  public void setId(int id) {
    this.id = id;
  }
  /** @param name Trade name */
  public void setName(String name) {
    this.name = name;
  }
  /** @param country Country of manufacture */
  public void setCountry(String country) {
    this.country = country;
  }
  /** @param year Year of manufacture */
  public void setYear(int year) {
    this.year = year;
  }
  /** @param reservoir Volume of the coffee reservoir */
  public void setReservoir(int reservoir) {
    this.reservoir = reservoir;
  }
  /** @param description Product description */
  public void setDescription(String description) {
    this.description = description;
  }
  /** @param bought Purchase status */
  public void setBought(boolean bought) {
    this.bought =  bought;
  }
  /** @return id Number in the table */
  public int getId() {
    return id;
  }
  /** @return Trade name */
  public String getName() {
    return name;
  }
  /** @return Country of manufacture */
  public String getCountry() {
    return country;
  }
  /** @return Year of manufacture */
  public int getYear() {
    return year;
  }
  /** @return Volume of the coffee reservoir */
  public int getReservoir() {
    return reservoir;
  }
  /** @return Product description */
  public String getDescription() {
    return description;
  }
  /** @return Purchase status */
  public boolean getBought() {
    return bought;
  }
  /** @return String representation */
  @Override
  public String toString() {
    return "CoffeeMachine(" +
        "id: " + id +
        ", name: " + name +
        ", year: " + year +
        ", reservoir: " + reservoir +
        " gr, country: " + country +
        ", description: " + description + ')';
  }
}
