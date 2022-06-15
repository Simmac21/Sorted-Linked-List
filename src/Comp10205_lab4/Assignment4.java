/**
 * COMP10205 - Lab#4 Starting Code
 */
package Comp10205_lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Assignment4
{
  public static void main(String[] args)
  {
    final int NUMBER_OF_NAMES = 18756;
    final String filename = "resources/bnames.txt";
    final String[] names = new String[NUMBER_OF_NAMES];
    
     // May be useful for selecting random words to remove
    Random random = new Random(); 
    
    // Read in all of the names 
    try {
      Scanner fin = new Scanner(new File(filename));
      for (int i=0; i<NUMBER_OF_NAMES; i++)
          names[i] = fin.next();
      fin.close();
    } catch (FileNotFoundException e) {
      System.out.println("Exception caught: " + e.getMessage());
      System.exit(-1);
    }
 
    // Use the system to build the linked List
    
    // 1. Create the linkedList and add all items in Array
    SortedLinkedList<String> linkedList_String = new SortedLinkedList<>();
    long start = System.nanoTime();
    for (int i=0; i<NUMBER_OF_NAMES;i++)
        linkedList_String.add(names[i]);
    System.out.printf("The time required to add %d elements to a Linked List = %d us\n", NUMBER_OF_NAMES, (System.nanoTime() - start) / 1000);
    System.out.println(linkedList_String);

    // 2. Remove half the items and time the code.
    long start_time = System.nanoTime();
    int random_index;
    for (int i=0; i< NUMBER_OF_NAMES / 2;i++){
      random_index = random.nextInt(linkedList_String.size() - 1);
      linkedList_String.remove(names[random_index]);
    }
    System.out.printf("The time required to remove %d elements from a Linked List = %d us\n", NUMBER_OF_NAMES / 2, (System.nanoTime() - start_time) / 1000);
    System.out.println(linkedList_String);

    ArrayList<String> arrayList_String = new ArrayList<>();
    long start_time_for_array = System.nanoTime();
    try {
      Scanner read = new Scanner(new File(filename));
      while (read.hasNext()){
        arrayList_String.add(read.next());
        // sorting array after adding each element
        arrayList_String.sort((o1, o2) -> o1.compareToIgnoreCase(o2));
      }
      read.close();
    } catch (FileNotFoundException e) {
      System.out.println("Exception caught: " + e.getMessage());
      System.exit(-1);
    }
    System.out.println("Time to read and sort " + arrayList_String.size() + " elements from file in ArrayList: " + (System.nanoTime() - start_time_for_array) / 1000);
    arrayList_String.forEach(name -> {
      System.out.print(name + "\t");
    });
    //    Remove the half the elements and time again.
    long start_remove_time = System.nanoTime();
    int remove_index, remove_count = 0;
    for (int i = 0; i < (arrayList_String.size()) / 2; i++) {
      remove_index = random.nextInt(arrayList_String.size() - 1);
      arrayList_String.remove(remove_index);
      remove_count++;
    }
    System.out.println("Time required to remove " + remove_count + " elements from ArrayList: " + (System.nanoTime() - start_remove_time) / 1000);
    arrayList_String.forEach(name -> {
      System.out.print(name + "\t");
    });
  }
  
}
