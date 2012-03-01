
package oblig1;

import java.io.Serializable;

public class Vehicle implements Serializable {
  private String regNbr;  // registration number
  private String make; // Vehicle make (Toyota, Ford, and so on)
  private String model; // Model, obviously.
  private int regYear; // First time registered.
  
  public Vehicle (String regNumber, String make, String model, int regYear) {
    this.regNbr = regNumber;
    this.make = make;
    this.model = model;
    this.regYear = regYear;
  }
  
  public String getMake() {
    return make;
  }

  public String getModel() {
    return model;
  }

  public int getRegyear() {
    return regYear;
  }
  
  public String getRegNr() {
    return regNbr;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public void setRegNbr(String regNbr) {
    this.regNbr = regNbr;
  }

  public void setRegyear(int regyear) {
    this.regYear = regyear;
  }
  
  
  @Override
  public String toString() {
    return "RegNr: " + regNbr + "\nModell: " + make + " " + model +  "\nÅr: " 
            + regYear + "\n";
  }
  
  
  
}
