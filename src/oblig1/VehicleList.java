/*
 */
package oblig1;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @studnr 180212
 * @date Mar 1, 2012
 */
public class VehicleList {
  protected Vehicle first;
  
  public static final int SUCCESS       = 1;  // Returns 1 on success
  public static final int UNKNOWN       = 0;  // Returns 0 if ownerID doesn't exists
  public static final int HAS_VEHICLE  = -1; // Returns -1 if owner do have a vehicle
  public static final int EMPTY_LIST    = -2; // Returns -2 if the list of owners is empty   
 
  public boolean add(Vehicle vehicle) {
    if (!exists(vehicle.getRegNr())) {
      if (first == null) {
        first = vehicle;
        return true;
      }

      else {
        Vehicle current = first;
        while (current.next != null) {
          current = current.next;
        }
        current.next = vehicle;
        return true;
      }
    }
    return false;
  }
  //TODO: Lag denne metoden?
  public boolean remove(Vehicle v) {
    return false;
  }
  public int remove(String regNumber) {
     if (first == null) {
      return EMPTY_LIST;
    }
    
    if(regNumber.equals(first.getRegNr())) {
      first = first.next;
      return SUCCESS;
    }
    else {
      Vehicle current = first;
      while(current.next != null) {
        if(current.next.getRegNr().equals(regNumber)) {
          if(current.next.next != null) {
            current.next = current.next.next;
            return SUCCESS;
          }
          current.next = null;
          return SUCCESS;
        }
      }
      current.next = current.next.next;
    }
    return UNKNOWN;
  }
  public boolean exists(String regNumber) {
    if (get(regNumber) != null) {
      return true;
    }
    return false;
  }
  public Vehicle get(String regNumber) {
    if (first == null) {
      return null;
    }
    
    Vehicle current = first;
    while(current != null && !current.getRegNr().equals(regNumber)) {
      if (current.next == null) {
        return null;
      }
      current = current.next;
    }
    return current;
  }
  public String list(){
    String result = "Kjøretøy:\n";
    Vehicle current = first;
    
    while (current != null) {
      result += current.toString() + "\n";
      current = current.next;
    }
    
    if (!result.equals("")) {
      return result;
    }
    
    else {
      return "Listen er tom";
    }
  }
}
