package ru.sfu.apps;

import java.util.ArrayList;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import ru.sfu.entity.CoffeeMachine;

/** RESTful client application */
public final class Client {
  private final static int EXIT=0, CREATE=1, READ=2, UPDATE=3, DELETE=4, READ_ALL=5;
  private static String port = "8081";

  /**
   * Entry point
   * @param args Command Line Arguments
   */
  public static void main(String[] args) {
    if (args.length > 0 && args[0].matches("[0-9]+")) {
      port = args[0];
    }

    System.out.printf(
        "\n%d. Create\n%d. Read\n%d. Update\n%d. Delete\n%d. Read all\n%d. Exit\n",
        CREATE, READ, UPDATE, DELETE, READ_ALL, EXIT
    );

    int action;
    int id, year, reservoir;
    String name, country, description;
    CoffeeMachine coffeeMachine;

    while ((action=Enter.intNum("\nEnter the action: ")) != EXIT) {
      switch (action) {
        case CREATE:
          year = Enter.intNum("Enter the year: ");
          reservoir = Enter.intNum("Enter the volume of reservoir: ");
          name = Enter.string("Enter the name: ");
          country = Enter.string("Enter the country: ");
          description = Enter.string("Enter the description: ");
          coffeeMachine = new CoffeeMachine(name, country, year, reservoir, description);
          create(coffeeMachine);
          break;
        case READ:
          id = Enter.intNum("Enter the ID: ");
          read(id);
          break;
        case UPDATE:
          id = Enter.intNum("Enter the ID: ");
          year = Enter.intNum("Enter the year: ");
          reservoir = Enter.intNum("Enter the volume of reservoir: ");
          name = Enter.string("Enter the name: ");
          country = Enter.string("Enter the country: ");
          description = Enter.string("Enter the description: ");
          coffeeMachine = new CoffeeMachine(id, name, country, year, reservoir, description);
          update(coffeeMachine);
          break;
        case DELETE:
          id = Enter.intNum("Enter the ID: ");
          delete(id);
          break;
        case READ_ALL:
          readAll();
          break;
      }
    }
  }

  /** Sending 'GET' query for all items */
  public static void readAll() {
    try {
      ArrayList<Object> list = new RestTemplate().getForObject(
          "http://localhost:"+port+"/coffee-machine",
          ArrayList.class);

      if (list == null) {
        System.out.println("Not found");
        return;
      }

      for (Object item: list) {
        System.out.println(item);
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  /** Sending 'GET' query for one item */
  public static void read(int id) {
    String result;

    try {
      result = new RestTemplate().getForObject(
          "http://localhost:"+port+"/coffee-machine/{id}",
          String.class, id);
    } catch (Exception e) {
      System.err.println(e.getMessage());
      return;
    }

    if (result==null)
      result = "Not found";

    System.out.println(result);
  }

  /** Sending 'POST' query */
  public static void create(CoffeeMachine coffeeMachine) {
    HttpEntity<CoffeeMachine> request = new HttpEntity<>(coffeeMachine);

    try {
      System.out.println(new RestTemplate().postForObject(
          "http://localhost:"+port+"/coffee-machine",
          request, String.class));
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  /** Sending 'DELETE' query */
  public static void delete(int id) {
    try {
      System.out.println(new RestTemplate().exchange(
          "http://localhost:"+port+"/coffee-machine/{id}",
          HttpMethod.DELETE, null, String.class, id
      ).getBody());
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  /** Sending 'PUT' query */
  public static void update(CoffeeMachine coffeeMachine) {
    HttpEntity<CoffeeMachine> request = new HttpEntity<>(coffeeMachine);

    try {
      System.out.println(new RestTemplate().exchange(
          "http://localhost:"+port+"/coffee-machine/{id}",
          HttpMethod.PUT, request, String.class, coffeeMachine.getId()
      ).getBody());
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
