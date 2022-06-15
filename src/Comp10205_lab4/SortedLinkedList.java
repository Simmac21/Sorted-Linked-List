package Comp10205_lab4;

/**
 * Generic Linked List class that always keeps the elements in order 
 * @author mark.yendt
 */
public class SortedLinkedList<T extends Comparable>
{
  /**
   * The Node class stores a list element and a reference to the next node.
   */
  private final class Node<T extends Comparable>
  {
    T value;
    Node next;

    /**
     * Constructor.
     * @param val The element to store in the node.
     * @param n The reference to the successor node.
     */
    Node(T val, Node n)
    {
      value = val;
      next = n;
    }

    /**
     * Constructor.
     *
     * @param val The element to store in the node.
     */
    Node(T val)
    {
      // Call the other (sister) constructor.
      this(val, null);
    }
  }
  private Node first;  // list head

  /**
   * Constructor.
   */
  public SortedLinkedList()
  {
    first = null;
  }

  /**
   * The isEmpty method checks to see if the list is empty.
   *
   * @return true if list is empty, false otherwise.
   */
  public boolean isEmpty()
  {
    return first == null;
  }

  /**
   * The size method returns the length of the list.
   * @return The number of elements in the list.
   */
  public int size()
  {
    int count = 0;
    Node p = first;
    while (p != null) {
      // There is an element at p
      count++;
      p = p.next;
    }
    return count;
  }

  /**
   * The add method adds an element at a position.
   * @param element The element to add to the list in sorted order.
   */
  public void add(T element)
  {
    Node node = new Node(element);
    if (isEmpty()) {
      first = node;
      return;
    } else if (first.next == null) {
      if (element.compareTo(first.value) < 0) {
        node.next = first;
        first = node;
      } else {
        first.next = node;
      }
      return;
    }
    Node c, t = first;
    c = first.next;
    while (c.next != null) {
      if (element.compareTo(c.value) < 0) {
        node.next = c;
        t.next = node;
        return;
      }
      t = t.next;
      c = c.next;
    }
    c.next = node;
  }

  /**
   * The toString method computes the string representation of the list.
   * @return The string form of the list.
   */
  public String toString()
  {
    System.out.println(size() + ":\tSize");
    String listOfItems = "";

    // TODO: Iterate through the list and append items to end of listOfItems
    Node f = first;
    while (f != null) {
      listOfItems += "\t" + f.value;
      f = f.next;
    }

    return listOfItems;
  }

  /**
   * The remove method removes an element.
   * @param element The element to remove.
   * @return true if the remove succeeded, false otherwise.
   */
  public boolean remove(T element)
  {
    Node removeNode = new Node(element);
    if (isEmpty()) {
      first = removeNode;
      return true;
    }
    Node c = first;
    Node previous;
    if (c.value.equals(removeNode.value)) {
      first = c.next;
      return true;
    }
    previous = c;
    c = c.next;
    boolean check;
    while (c.next != null) {
      check = c.value.equals(removeNode.value);
      if (check) {
        previous.next = c.next;
        return true;
      }
      c = c.next;
      previous = previous.next;
    }
    return false;
  }
}