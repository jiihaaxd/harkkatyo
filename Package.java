package harkkatyo;


abstract class Package {
	
	protected String startSmartPost;
	protected String endSmartPost;
	
	protected String name;
	protected boolean breakable;
	protected double distance;
	protected int size;
	
	
	protected String getName() {
		return name;
	}
	
	protected boolean getBreakable() {
		return breakable;
	}
	
	protected double getDistance() {
		return distance;
	}
	
	protected int getSize() {
		return size;
	}
}

class FirstClass extends Package {
	
	public FirstClass(String n, boolean b, int d, int s) {
		
		this.name = n;
		this.breakable = b;
		this.distance = d;
		this.size = s;
	}	
}

class SecondClass extends Package {
	public SecondClass(String n, boolean b, int d, int s) {
		this.name = n;
		this.breakable = b;
		this.distance = d;
		this.size = s;
	}
}

class ThirdClass extends Package {
	public ThirdClass(String n, boolean b, int d, int s) {
		this.name = n;
		this.breakable = b;
		this.distance = d;
		this.size = s;
	}
}

class ReadyPackage extends Package {
	public ReadyPackage(String n, boolean b, double d, int s, String st, String en) {
		this.name = n;
		this.breakable = b;
		this.distance = d;
		this.size = s;
		this.startSmartPost = st;
		this.endSmartPost = en;
	}
}

