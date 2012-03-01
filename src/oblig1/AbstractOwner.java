
package oblig1;

import java.io.Serializable;

public abstract class AbstractOwner implements Serializable {
  protected String name;
  protected String address;
  protected Vehicle vehicle;
  
  // Iterators
  protected AbstractOwner next;
  protected AbstractOwner previous;
  
  public AbstractOwner(String name, String address, Vehicle vehicle) {
    this.name = name;
    this.address = address;
    this.vehicle = vehicle;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle car) {
    this.vehicle = car;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public AbstractOwner getNext() {
    return next;
  }

  public void setNext(AbstractOwner next) {
    this.next = next;
  }
  
  public AbstractOwner getPrevious() {
    return previous;
  }

  public void setPrevious(AbstractOwner previousOwner) {
    this.previous = previousOwner;
  }
  
  abstract int getOwnerID();
  
  @Override
  public String toString() {
    return "Eier:\n " + "Navn: " + name + "\nAdresse: " + address + "\n";
  }
}






