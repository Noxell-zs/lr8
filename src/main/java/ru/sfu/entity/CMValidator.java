package ru.sfu.entity;

/** Object for validation of entity  properties */
public class CMValidator {
  private final CoffeeMachine coffeeMachine;
  private final static int minYear = 1800, maxYear = 2023;

  /**
   * Constructor
   * @param coffeeMachine Entity for validation
   */
  public CMValidator(CoffeeMachine coffeeMachine) {
    this.coffeeMachine = coffeeMachine;
  }

  /**
   * Validation of the year
   * @return Result of checking
   */
  public boolean invalidYear() {
    final int year = coffeeMachine.getYear();
    return year < minYear || year > maxYear;
  }

  /**
   * Validation of the name
   * @return Result of checking
   */
  public boolean invalidName() {
    final String name = coffeeMachine.getName();
    return name == null || name.equals("");
  }

  /**
   * Validation of all specified properties
   * @return Result of checking
   */
  public boolean invalid() {
    return coffeeMachine == null || invalidYear() || invalidName();
  }
}
