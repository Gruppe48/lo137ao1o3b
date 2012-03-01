/*
 */
package oblig1;


/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @studnr 180212
 * @date Feb 28, 2012
 */
class Person extends AbstractOwner {
  protected int ssn; //Social Security number 
  
  public Person (String name, String address, VehicleList vehicles, int ssn) {
    super (name,address,vehicles);
    this.ssn = ssn;
  }
  
  @Override
  public int getOwnerID() {
    return ssn;
  }
  
  @Override
  public String toString() {
    String res = super.toString() + "Personnr: " + ssn + "\n";
    if (vehicles != null) {
      res += "Bil:\n" + vehicles.list();
    }
    return res;  
  }
  
}