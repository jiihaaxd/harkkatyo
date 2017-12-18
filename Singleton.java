/*
for helping to control lists
*/

package harkkatyo;

import java.util.ArrayList;


public class Singleton {
	private static Singleton singleton = new Singleton( );
		ArrayList<String> list = new ArrayList<>();

	   private Singleton() { }
	   public static Singleton getInstance( ) {
	      return singleton;
	   }
	   
	   public void addtolist(String s) {
		   list.add(s);
	   }
	   
	   public ArrayList<String> getfromlist(){
		   return list;
	   }
}
