package ru.sfu.apps;
import java.util.Scanner;

/** A set of methods for user input */
public final class Enter {
  /**
   * Entering an integer
   * @param message Message to the user
   * @return Entered number
   */
  public static int intNum(String message){
    if (message != null)
      System.out.print(message);
    Scanner scan = new Scanner(System.in);
    while(!scan.hasNextInt())
      scan.nextLine();
    return scan.nextInt();
  }

  /**
   * Entering a fractional number
   * @param message Message to the user
   * @return Entered number
   */
  public static double doubleNum(String message) {
    if (message != null)
      System.out.print(message);
    Scanner scan = new Scanner(System.in);
    while (!scan.hasNextDouble())
      scan.nextLine();
    return scan.nextDouble();
  }

  /**
   * Entering a character string
   * @param message Message to the user
   * @return Entered string
   */
  public static String string(String message){
    if (message != null)
      System.out.print(message);
    Scanner scan = new Scanner(System.in);
    return scan.nextLine();
  }
}
