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

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Manager extends JFrame {
  JRadioButton rbPerson, rbCompany;
  JTextField textOwnerID, textOwnerName, textOwnerAddress;
  JTextField textVehicleRegNumber, textVehicleMake, textVehicleModel, textVehicleRegistrationYear;
  JButton buttonRegisterVehiacle, buttonRegisterOwner, buttonDeleteVehicle, buttonDeleteOwner, buttonChangeOwner, buttonShowAll, buttonShowOwner;
  JTextArea display;
  
  OwnerList registry = new OwnerList();
  
  public static final int NON_SELECTED = -1;
  public static final int PRIVATE      = 1;
  public static final int COMPANY      = 2;
  
  public Manager() {
    super("BilPark");
    
    try {
      load();
    } catch (IOException ex) {
      Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
    } 
    
    // Create RadioButtons
    rbPerson  = new JRadioButton("Privat");
    rbCompany = new JRadioButton("Firma");
    ButtonGroup rbGroup = new ButtonGroup();
    rbGroup.add(rbPerson);
    rbGroup.add(rbCompany);
   
    // Create Textfields
    textOwnerID      = new JTextField(10);
    textOwnerName    = new JTextField(10);
    textOwnerAddress = new JTextField(10);
    
    textVehicleRegNumber        = new JTextField(10);
    textVehicleMake             = new JTextField(10);
    textVehicleModel            = new JTextField(10);
    textVehicleRegistrationYear = new JTextField(4);
    
    // Create Buttons
    buttonRegisterVehiacle      = new JButton("Reg bil");
    buttonRegisterOwner         = new JButton("Reg eier");
    buttonDeleteVehicle         = new JButton("Slett bil");
    buttonDeleteOwner           = new JButton("Slett eier");
    buttonChangeOwner           = new JButton("Skift eier");
    buttonShowOwner             = new JButton("Vis eier");
    buttonShowAll               = new JButton("Vis alle");
    
    // Create Display area
    display = new JTextArea(15, 45);
    display.setEditable(false);
    JScrollPane scroll = new JScrollPane(display);
    
    // Add ActionListeners
    BtnListener listener = new BtnListener();
    buttonRegisterVehiacle.addActionListener(listener);
    buttonRegisterOwner.addActionListener(listener);
    buttonDeleteVehicle.addActionListener(listener);
    buttonDeleteOwner.addActionListener(listener);
    buttonChangeOwner.addActionListener(listener);
    buttonShowOwner.addActionListener(listener);
    buttonShowAll.addActionListener(listener);
    
    
    Container c = getContentPane();
    c.setLayout( new FlowLayout() );
    
    // Add RadioButtons
    c.add(rbPerson);
    c.add(rbCompany);
    
    // Add TextFields
    c.add(new JLabel("EierID:"));
    c.add(textOwnerID);
    c.add(new JLabel("Eier Navn:"));
    c.add(textOwnerName);
    c.add(new JLabel("Eier Adresse:"));
    c.add(textOwnerAddress);
    c.add(new JLabel("RegistreringsNr:"));
    c.add(textVehicleRegNumber);
    c.add(new JLabel("Bilmerke:"));
    c.add(textVehicleMake);
    c.add(new JLabel("Bilmodell:"));
    c.add(textVehicleModel);
    c.add(new JLabel("Registreringsår"));
    c.add(textVehicleRegistrationYear);
    
    // Add Buttons
    c.add(buttonRegisterVehiacle);
    c.add(buttonDeleteVehicle);
    c.add(buttonRegisterOwner);
    c.add(buttonDeleteOwner);
    c.add(buttonChangeOwner);
    c.add(buttonShowOwner);
    c.add(buttonShowAll);
    
    // Add Display Area
    c.add(scroll);
    
    setSize(870, 400);
    setVisible(true);
    
    addWindowListener(new WindowAdapter() {

                  @Override
                  public void windowClosing(WindowEvent e) {
                    try {
                      save();
                      System.out.println("Lagret!");
                    } catch (IOException ex) {
                      Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.exit(0);
                  }
                });
  }
  
  void save() throws IOException {
    try {
      /*< Åpne riktig filtype.
      Gjennomløp lista og skriv objekt for objekt til fil
      vha skrivObjektTilFil-metoden i Bok-klassen >*/
      ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ownerlist.dat"));
      
      out.writeObject(registry);
    } catch (FileNotFoundException ex) {
      Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private void load() throws IOException {
    try {
      /*< Åpne riktig filtype.
      Gjennomløp lista og skriv objekt for objekt til fil
      vha skrivObjektTilFil-metoden i Bok-klassen >*/
      FileInputStream fileHandle = new FileInputStream("ownerlist.dat");
      
      ObjectInputStream in = new ObjectInputStream(fileHandle);
      
      registry = (OwnerList) in.readObject();
      
    } catch (FileNotFoundException ex) {
      System.out.println("Filen finnes ikke, oppretter!");
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
    } catch (EOFException ex) {
      System.out.println("Ferdig lastet!");
    }
  }
  
  private class BtnListener implements ActionListener { 
    @Override
    public void actionPerformed(ActionEvent e) {
      
      if(e.getSource() == buttonRegisterVehiacle) {
        int rbStatus = checkRadioButtons();
        if(rbStatus == PRIVATE) {
          registerVehiclePrivate();
        }
        else if (rbStatus == COMPANY) {
          registerVehicleCompany();
        }
      }
      else if (e.getSource() == buttonDeleteVehicle) {
        deleteVehicle();
      }
      else if (e.getSource() == buttonRegisterOwner) {
        int rbStatus = checkRadioButtons();
        if(rbStatus == PRIVATE) {
          registerPerson();
        }
        else if (rbStatus == COMPANY) {
          registerCompany();
        }
      }
      else if (e.getSource() == buttonDeleteOwner) {
        deleteOwner();
      }
      else if (e.getSource() == buttonChangeOwner) {
        changeOwner();
      }
      else if (e.getSource() == buttonShowOwner) {
        showOwner();
      }
      else if (e.getSource() == buttonShowAll) {
        showAll();
      }
    }
  }
  
  public void registerVehiclePrivate() {
    try {
      String regNumber = textVehicleRegNumber.getText();
      String make = textVehicleMake.getText();
      String model = textVehicleModel.getText();
      int regYear = Integer.parseInt(textVehicleRegistrationYear.getText());
      int ownerID = Integer.parseInt(textOwnerID.getText());
      
      if (!regNumber.equals("") && !make.equals("") && !model.equals("")) {
        Vehicle v = new Vehicle(regNumber,make,model,regYear);
        if (registry.registerVehicle(ownerID, v)) {
          display.setText("Kjøretøy registert!");
        }
        else {
          display.setText("Kjøretøyet kunne ikke registeres fordi det finnes et kjøretøy med samme regNr");
        }
      }
      else {
        display.setText("Noen felter er tomme!");
      }
      
    } catch (NumberFormatException e) {
      display.setText("Noen felter er tomme!");
    }

  }
  
  public void registerVehicleCompany() {
    try {
      int ownerID = Integer.parseInt(textOwnerID.getText());
      
      String regNumber  = textVehicleRegNumber.getText();
      String make       = textVehicleMake.getText();
      String model      = textVehicleModel.getText();
      int regYear       = Integer.parseInt(textVehicleRegistrationYear.getText());
      
      
      if (!regNumber.equals("") && !make.equals("") && !model.equals("")) {
        Vehicle v = new Vehicle(regNumber,make,model,regYear);
        if (registry.registerVehicle(ownerID, v)) {
          display.setText("Kjøretøy registert!");
        }
        else {
          display.setText("Kjøretøyet kunne ikke registeres fordi det finnes et kjøretøy med samme regNr");
        }
      }
      else {
        display.setText("Noen felter er tomme!");
      }
      
    } catch (NumberFormatException e) {
      display.setText("Noen felter er tomme!");
    }

  }
  
  public void deleteVehicle() {
    String regNr = textVehicleRegNumber.getText();
    
    if (!regNr.equals("")) {
      if(registry.removeVehicle(regNr)) {
        display.setText("Bilen: " + regNr + " er nå slettet\n");
      }
      else {
        display.setText("Bilen: " + regNr + " kan ikke slettes."
                + "Enten finnes den ikke, eller så er det noen som eier den");
      }
    }
  }
  
  // * Register private owner
  public void registerPerson() {
    try {
      int ownerID = Integer.parseInt(textOwnerID.getText());
      String ownerName = textOwnerName.getText();
      String address = textOwnerAddress.getText();
      
      if (!ownerName.equals("") && !address.equals("")) {
        Person owner = new Person(ownerName, address, null, ownerID);
        if (registry.addOwner(owner)) {
          display.setText("Eier registert!");
        }
        else {
          display.setText("Det finnes en eier med samme ID fra før! Vennligst benytt et annet ID");
        }
      }
      else {
        display.setText("Noen felter er tomme!");
      }
    } catch (NumberFormatException e) {
      display.setText("Noen felter er tomme!");
    }
  }
  
  // * Register company owner
  public void registerCompany() {
    try {
      int ownerID = Integer.parseInt(textOwnerID.getText());  
      String companyName = textOwnerName.getText();
      String address = textOwnerAddress.getText();
      
      if (!companyName.equals("") && !address.equals("")) {
        Company owner = new Company (companyName, address, null, ownerID);
      
        if (registry.addOwner(owner)) {
          display.setText("Eier registert!");
        }
        else {
          display.setText("Det finnes en eier med samme ID fra før! Vennligst benytt et annet ID");
        }
      }
      else {
        display.setText("Noen felter er tomme!");
      }
    } catch (NumberFormatException e) {
      display.setText("Noen felter er tomme!");
    }
  }
  
  public void deleteOwner() {
    try {
      int ownerID = Integer.parseInt(textOwnerID.getText());
      int status = registry.removeOwner(ownerID);
      switch(status) {
        case 1: display.setText("Eieren er nå fjernet.");
          break;
        case 0: display.setText("Eier finnes ikke.");
          break;
        case -1: display.setText("Kan ikke slette eier fordi han eier et kjøretøy.");
          break;
        case -2: display.setText("Lista er tom, så det er ingen eiere å slette.");
          break;
      }
    }
    
    catch (NumberFormatException e) {
      display.setText("Noen felter er tomme!");
    }
  }
          
          
  public void changeOwner() {
    try {
      int ownerID = Integer.parseInt(textOwnerID.getText());
      String regNr = textVehicleRegNumber.getText();
      
      if (!regNr.equals("")) {
        registry.changeOwner(regNr,ownerID);
        display.setText("Kjøretøyet har blitt flyttet!");
      }
      else {
        display.setText("Noen felter er tomme!");
      }
    }
    catch (NumberFormatException e) {
      display.setText("Noen felter er tomme!");
    }
  }
  
  public void showOwner() {
    String regNumber = textVehicleRegNumber.getText();
    if (!regNumber.equals("")) {
      String result = registry.findOwner(regNumber);
      display.setText(result);
    }
    else {
      display.setText("Noen felter er tomme!");
    }
  }
  
  public void showAll() {
    display.setText(registry.printRegistry());
  }

  
  public int checkRadioButtons() { 
    if(rbPerson.isSelected()) {
      return PRIVATE;
    }
    else if (rbCompany.isSelected()) {
      return COMPANY;
    }
    display.setText("Du må velge privat- eller firmaeier");
    return NON_SELECTED;
  }
}