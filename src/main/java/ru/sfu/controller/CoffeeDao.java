package ru.sfu.controller;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import ru.sfu.entity.CoffeeMachine;

/** Data access object */
@Controller
public class CoffeeDao {
  JdbcTemplate jdbcTemplate;

  /** @param dataSource Object to connect to the data source */
  @Autowired
  public void setDataSource(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  /**
   * Deleting an item
   * @param id Number of the item to be deleted
   * @return Number of deleted item
   */
  public int delete(int id) {
    return jdbcTemplate.update(
        "delete from coffee_machine where id=?", id);
  }

  /**
   * Buying an item
   * @param id Number of the item to be bought
   * @return Number of bought item
   */
  public int buy(int id) {
    return jdbcTemplate.update(
        "update coffee_machine set bought=true where id=?", id);
  }

  /**
   * Searching for an item by ID
   * @param id Number if table
   * @return Required item
   */
  public CoffeeMachine findById(int id) {
    try {
      return jdbcTemplate.queryForObject(
          "select * from coffee_machine where id=?",
          new BeanPropertyRowMapper<>(CoffeeMachine.class),
          id);
    } catch (EmptyResultDataAccessException e) {
      return null;
    } catch (Exception e) {
      System.err.println(e.getMessage());
      return null;
    }
  }

  /** @return List of all items */
  public List<CoffeeMachine> findAll() {
    return jdbcTemplate.query("select * from coffee_machine",
        new BeanPropertyRowMapper<>(CoffeeMachine.class));
  }

  /**
   * Search for items with a year greater than the specified one
   * @param minYear Minimal Year
   * @return List of required items
   */
  public List<CoffeeMachine> findByYear(int minYear) {
    return jdbcTemplate.query(
        "select * from coffee_machine where year>=?",
        new BeanPropertyRowMapper<>(CoffeeMachine.class), minYear);
  }

  /**
   * Modifying an item
   * @param coffeeMachine Modified items
   * @return Number of modified items
   */
  public int update(CoffeeMachine coffeeMachine) {
    return jdbcTemplate.update("update coffee_machine set "
            + "name=?, country=?, year=?, reservoir=?, description=? "
            + "where id=?",
        coffeeMachine.getName(), coffeeMachine.getCountry(),
        coffeeMachine.getYear(), coffeeMachine.getReservoir(),
        coffeeMachine.getDescription(), coffeeMachine.getId());
  }

  /**
   * Adding an item
   * @param coffeeMachine Item to add
   * @return Number of added items
   */
  public int add(CoffeeMachine coffeeMachine) {
    return jdbcTemplate.update("insert into coffee_machine"
            + "(name, country, year, reservoir, description) values (?,?,?,?,?)",
        coffeeMachine.getName(), coffeeMachine.getCountry(), coffeeMachine.getYear(),
        coffeeMachine.getReservoir(), coffeeMachine.getDescription());
  }
}
