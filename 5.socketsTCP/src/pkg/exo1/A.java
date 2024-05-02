package pkg.exo1;
import java.io.*;
import java.util.*;
public class A {
  public static void main(String args[]) throws IOException {
    HashMap<String, Integer> map = new HashMap<>();
      try (Scanner a = new Scanner(new File("./src/symptomes.txt"))) {
          while (a.hasNextLine()) ajouter(map, a.nextLine());  
      }
        map.forEach((k, v) -> System.out.println(k + " " + v));
  }
  static void ajouter(Map<String, Integer> map, String symptome) {  
   map.compute(symptome, (key, value) -> (value == null) ? 1 : value + 1);
  }
}
