import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
public class CopyOnWriteListTest {

   public static void main(String args[]) {

      // create an array list
      List<String> al = new CopyOnWriteArrayList<String>();
	   
	  //List<String> al = new ArrayList<String>();
      System.out.println("Initial size of al: " + al.size());

      // add elements to the array list
      al.add("C");
      al.add("A");
      al.add("E");
      al.add("B");
      al.add("D");
      al.add("F");
      al.add(1, "A2");
      System.out.println("Size of al after additions: " + al.size());

      // display the array list
      System.out.println("Contents of al: " + al);

      // Remove elements from the array list
      al.remove("F");
      al.remove(2);
      System.out.println("Size of al after deletions: " + al.size());
      System.out.println("Contents of al: " + al);

      try{
         Iterator<String> iterator = al.iterator();
         while(iterator.hasNext()) {
            iterator.remove();
         }
      }catch(UnsupportedOperationException e) {
         System.out.println("Method not supported:");
      }
      System.out.println("Size of al: " + al.size());
   }
}