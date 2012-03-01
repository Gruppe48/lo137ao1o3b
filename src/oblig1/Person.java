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

class Person extends AbstractOwner {
  protected int ssn; //Social Security number 
  
  public Person (String name, String address, int ssn) {
    super (name,address);
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