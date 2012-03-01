//Usikker på om denne trengs, eller om vi skal la alt være i CarRegistry. 

package oblig1;

import java.io.Serializable;


public class OwnerList implements Serializable {
  
  public static final int SUCCESS       = 1;  // Returns 1 on success
  public static final int UNKNOWN       = 0;  // Returns 0 if ownerID doesn't exists
  public static final int HAS_VEHICLE  = -1; // Returns -1 if owner do have a vehicle
  public static final int EMPTY_LIST    = -2; // Returns -2 if the list of owners is empty   
  
  private AbstractOwner first;
  
  public OwnerList() {
    first = null;
  }
  
  // Double linked list. Not that we use it though, really.
  public boolean addOwner(AbstractOwner owner) {
    
    if (!ownerExists(owner.getOwnerID())) {
      if (first == null) {
        first = owner;
        return true;
      }

      else {
        AbstractOwner current = first;
        while (current.next != null) {
          current = current.next;
        }
        current.next = owner;
        current.next.previous = current;
        return true;
      }
    }
    return false;
  }
  public boolean vehicleExists(String regNr) {
     if (first == null) {
      return false;
    }
    
    AbstractOwner current = first;
    while(current != null) {
      if (current.next == null) {
        return false;
      }
      if (current.vehicle != null) {
        if (current.vehicle.getRegNr().equals(regNr)) {
          return true;
        }
      }
      current = current.next;    
    }
    return false; 
  }
  public boolean ownerExists(int ownerID) {
    AbstractOwner owner = find(ownerID);
    if (owner != null) {
      return true;
    }
    else {
      return false;
    }
  }
  public boolean registerVehicle(int ownerID, Vehicle vehicle) {
    if (!vehicleExists(vehicle.getRegNr())) {
      
      AbstractOwner owner = find(ownerID);
      if (owner != null && vehicle != null) {
        // Owner by that name exists and we have a non-null vehicle.
        if (owner.vehicle == null) {
          owner.vehicle = vehicle;
          return true;
        }
        else {
          // already owns a vehicle.
          return false;
        }
      }
    }
    return false;
  }
  
  public String printRegistry() {
    String result = "";
    AbstractOwner current = first;
    
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
  
  public boolean removeVehicle(String regNr) {
    if (first == null) {
      return false;
    }
    
    AbstractOwner current = first;
    while(current != null && current.vehicle != null && !current.vehicle.getRegNr().equals(regNr)) {
      if (current.next == null) {
        return false;
      }
      current = current.next;
    }
    System.out.println("Current owner:" + current.getName());
    current.setVehicle(null);
    return true;
  }
  
  public int removeOwner(int ownerID) {
    
    if (first == null) {
      return EMPTY_LIST;
    }
    
    if(ownerID == first.getOwnerID()) {
      if(first.vehicle == null ) {
        first = first.next;
        return SUCCESS;
      }
      else {
        return HAS_VEHICLE;
      }
    }
    else {
      AbstractOwner current = first;
      while(current.next != null) {
        if(current.next.getOwnerID() == ownerID) {
          if(current.next.vehicle == null) {
            if(current.next.next != null) {
              current.next = current.next.next;
              return SUCCESS;
            }
            current.next = null;
            return SUCCESS;
          }
          return HAS_VEHICLE;
        }
        current.next = current.next.next;
      }
    }
    return UNKNOWN;
  }
  
  
  public String findOwner(String regNr) {
    if (first == null) {
      return "Registeret er tomt.";
    }
    
    AbstractOwner current = first;
    while(current != null) {
      if (current.vehicle != null && current.vehicle.getRegNr().equals(regNr)) {
        return current.toString();
      }
      current = current.next;
      
    }
    return "Dette kjøretøyet finnes ikke.";
  }
  public AbstractOwner getOwner(String regNr) {
    if (first == null) {
      return null;
    }
    
    AbstractOwner current = first;
    while(current != null) {
      if (current.vehicle != null && current.vehicle.getRegNr().equals(regNr)) {
        return current;
      }
      current = current.next;
    }
    return null;
  }

  private AbstractOwner find(int ownerID) {
    if (first == null) {
      return null;
    }
    
    AbstractOwner current = first;
    while(current != null && current.getOwnerID() != ownerID) {
      if (current.next == null) {
        return null;
      }
      current = current.next;
    }
    return current;
  }
  
  public boolean changeOwner(String regNumber, int ownerID) {
    AbstractOwner owner = getOwner(regNumber);
    if (owner != null) {
      // The vehicle has a owner.
      AbstractOwner newOwner = find(ownerID);
      if (newOwner != null) {
        // The new owner also exists
        if (newOwner.vehicle == null) {
          newOwner.vehicle = owner.vehicle;
          owner.vehicle = null;
        }
        else {
          // He already owns a vehicle
          return false;
        }
      }
    }
    // The owner is null or the new owner is null.
    return false;
  }
  
}
