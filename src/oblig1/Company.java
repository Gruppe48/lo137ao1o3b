/*
 */
package oblig1;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @studnr 180212
 * @date Feb 28, 2012
 */
class Company extends AbstractOwner {
  protected int companyID; //Company number
  
  public Company (String name, String address, VehicleList vehicles, int companyID ) {
    super (name,address,vehicles);
    companyID = companyID;
  }
  
  @Override
  public int getOwnerID() {
    return companyID;
  }
  
  @Override
  public String toString() {
    String res = super.toString() + "Firma nummer: " + companyID + "\n";
    if (vehicles != null) {
      res += vehicles.list();
    }
    return res;  
  }
}