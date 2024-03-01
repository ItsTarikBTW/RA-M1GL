/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.exo4;

/**
 *
 * @author tarik
 */
import java.io.*;
import java.util.*;
public class A {
  public static void main(String args[]) throws IOException {
    /*
    HashMap<String, Integer> map = new HashMap<>();
      try (Scanner a = new Scanner(new File("./src/symptomes.txt"))) {
          while (a.hasNextLine()) ajouter(map, a.nextLine());  
      }
    map.forEach((k, v) -> System.out.println(k + " : " + v));
    */
    //with arraylist
    ArrayList<ArrayListSI> list = new ArrayList<>();
    try (Scanner a = new Scanner(new File("./src/symptomes.txt"))) {
          while (a.hasNextLine()) ajouter(list, a.nextLine());  
      }
    //sort
    list.sort((ArrayListSI a, ArrayListSI b) -> b.occurence - a.occurence);

    list.forEach((arrayListSI) -> System.out.println(arrayListSI.symptome + " : " + arrayListSI.occurence));
  }
  static void ajouter(Map<String, Integer> map, String symptome) {  
     // compute() prend en paramètre une clé (symptome dans ce cas), 
      // ainsi qu'une fonction à appliquer sur la clé et la valeur correspondante
      // (la fonction lambda (key, value) -> (value == null) ? 1 : value + 1) dans ce cas)
   map.compute(symptome, (key, value) -> (value == null) ? 1 : value + 1);
  }
  static void ajouter(ArrayList<ArrayListSI> list, String symptome) {  
    boolean found = false;
    for (ArrayListSI arrayListSI : list) {
      if(arrayListSI.symptome.equals(symptome)){
        arrayListSI.occurence++;
        
        found = true;
        break;
      }
    }
    if(!found){
      ArrayListSI arrayListSI = new ArrayListSI();
      arrayListSI.symptome = symptome;
      arrayListSI.occurence = 1;
      list.add(arrayListSI);
    }
    
  }

}
class ArrayListSI{
  public String symptome;
  public int occurence;
}

