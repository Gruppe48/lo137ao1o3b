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
  
  public Company (String n, String a, Vehicle c, int cn ) {
    super (n,a,c);
    companyID = cn;
  }
  
  @Override
  public int getOwnerID() {
    return companyID;
  }
  
  @Override
  public String toString() {
    String res = super.toString() + "Firma nummer: " + companyID + "\n";
    if (vehicle != null) {
      res += vehicle.toString();
    }
    return res;  
  }
}