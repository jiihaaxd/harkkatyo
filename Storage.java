package harkkatyo;

import java.util.ArrayList;
import java.util.*;


public class Storage {
	
	ArrayList<Package> packages = new ArrayList<>();
	ArrayList<Package> items = new ArrayList<Package>();

	     public void addItem(Package p) {
	         items.add(p);
	     }

	public Storage() {
		Package o1 = new FirstClass("Laatikko", false, 100, 100000);
		items.add(o1);
		Package o2 = new SecondClass("Purkki", true, 200, 20000);
		items.add(o2);
		Package o3 = new ThirdClass("Paketti", false, 200, 30000);
		items.add(o3);
		Package o4 = new FirstClass("Kirje", false, 100, 1000);
		items.add(o4);		
		
	}
	
	public List<String> getlist() {
		List<String> list = new ArrayList<String>();
		for(Package i : items) {
            list.add(i.getName());
        }
		return list;
	}

	public ArrayList<Package> getList() {
		return items;
	}
	
	public ArrayList<Package> getPackageList() {
		return packages;
	}
	
	public boolean getBreakvalue(String s) {
		boolean b = false;
		for (Package p : items) {
			if(s.equals(p.getName())) {
				b = p.getBreakable();
			}
		}
		return b;
	}
	
	public int getSizevalue(String s) {
		int sv = 0;
		for (Package p : items) {
			if(s.equals(p.getName())) {
				sv = p.getSize();
			}
		}
		return sv;
	}
	
	public void addReady(String n, boolean b, double d, int s, String st, String en) {
		Package p = new ReadyPackage(n, b, d, s, st, en);
        packages.add(p);
    }
	
	public boolean CreatePackage(String item, String ct) {
		Package result = null;
		for(Package a : items) {
			if (item.equals(a.getName())) {
				result = a;
			}
		}
		
		if (ct.equals("1") == true) {
			if (result.getDistance() < 150 && result.getBreakable() == false) {
				return true;
			} else {
				return false;
			}
			
		} else if (ct.equals("2") == true) {
			if (result.getBreakable() == true) {
				return true;
			} else {
				return false;
			}
			
		} else if (ct.equals("3") == true) {
			if (result.getBreakable() == false) {
				return true;
			} else {
				return false;
			}
			
		} else {
			System.out.println("ei sovi luokkaan");
			return false;
		}
	}
	
	public boolean CreatePackage2(String name, int size,  String ct, double distance, boolean breakvalue) {
		
		if (ct.equals("1") == true) {
			if ( distance < 150 && breakvalue == false) {
				return true;
			} else {
				return false;
			}
			
		} else if (ct.equals("2") == true) {
			if (size < 27000) {
				return true;
			} else {
				return false;
			}
			
		} else if (ct.equals("3") == true) {
			if (breakvalue == false) {
				return true;
			} else {
				return false;
			}
			
		} else {
			System.out.println("ongelma");
			return false;
		}
	}
	
	public double distance(double lat1, double lat2, double lon1,
	        double lon2, double el1, double el2) {

	    final int R = 6371;

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000;

	    double height = el1 - el2;

	    distance = Math.pow(distance, 2) + Math.pow(height, 2);

	    return Math.sqrt(distance);
	}
}

