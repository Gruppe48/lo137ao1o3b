/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @studnr 180212

 * @author Even Augdal <even.augdal@gmail.com>
 * @studnr 181091
 * 
 * @author Tommy Nyrud <s180487@stud.hioa.no>
 * @studnr 180487
 * 
 * @date Feb 20, 2012
 */

package oblig1;

import java.io.Serializable;

public abstract class AbstractOwner implements Serializable {
  protected String name;
  protected String address;
  protected VehicleList vehicles;
  
  // Iterators
  protected AbstractOwner next;
  protected AbstractOwner previous;
  
  public AbstractOwner(String name, String address) {
    this.name = name;
    this.address = address;
    this.vehicles = new VehicleList();
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public VehicleList getVehicles() {
    return vehicles;
  }

  public void setVehicles(VehicleList vehicles) {
    this.vehicles = vehicles;
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






