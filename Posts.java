/*
post class for smartpost data
*/

package harkkatyo;

public class Posts {
		 private String code;
		 private String city;
		 private String address;
		 private String availability;
		 private String postoffice;
	     private String lat;
	     private String lng;

	     public Posts(String code, String city, String address, String availability, String postoffice, String lat, String lng) {
	         this.code = code;
	    	 this.city = city;
	          this.address = address;
	          this.availability = availability;
	          this.postoffice = postoffice;
	          this.lat = lat;
	          this.lng = lng;
	     }

	     @Override
	     public String toString() {
	          return code + city + ", " + address + ", "+ availability + ", " + postoffice + ", " + lat + ", " + lng;
	     }
	     
	     public String getCity() {
	    	 return city;
	     }
	     
	     public String getPostoffice() {
	    	 return postoffice;
	     }
	     
	     public String getAddress() {
	    	 return address;
	     }
	     
	     public String getAvailability() {
	    	 return availability;
	     }
	     
	     public String getLat() {
	    	 return lat;
	     }
	     
	     public String getLng() {
	    	 return lng;
	     }
	     
	     public String getCode() {
	    	 return code;
	     }


}
