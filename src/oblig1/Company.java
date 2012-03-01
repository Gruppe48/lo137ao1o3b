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

class Company extends AbstractOwner {
  protected int companyID; //Company number
  
  public Company (String name, String address, int companyID ) {
    super (name,address);
    this.companyID = companyID;
  }
  
  @Override
  public int getOwnerID() {
    return companyID;
  }
  
  @Override
  public String toString() {
    String res = super.toString() + "Firma nummer: " + companyID + "\n\n";
    if (vehicles != null) {
      res += vehicles.list();
    }
    res += "\n----------\n";
    return res;  
  }
}