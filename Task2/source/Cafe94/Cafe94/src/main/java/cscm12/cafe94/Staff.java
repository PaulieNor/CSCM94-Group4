package cscm12.cafe94;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * [ManageStaff]
 * Manage staff class is responsible for being the blank slate for staff information.
 * It receives and fills itself with SQL database for staff objects.
 * The data is used by @getManageStaffTable and @getStaffTable in the Controller class.
 *
 * @author Sumi Sunuwar
 * @version 1.0*/
public class Staff{
    private SimpleStringProperty staffFName;
    private SimpleStringProperty staffLName;
    private SimpleStringProperty staffType;
    private SimpleIntegerProperty hoursToWork;
    private SimpleStringProperty staffUsername;
    private SimpleStringProperty staffPassword;

    /**
     * [ManageStaff]
     * Constructor to create staff objects from the api or manipulate them.*/
    public Staff(String staffFName, String staffLName, String staffType, int hoursToWork,
                       String staffUsername, String staffPassword) {
        this.staffFName = new SimpleStringProperty(staffFName);
        this.staffLName = new SimpleStringProperty(staffLName);
        this.staffType = new SimpleStringProperty(staffType);
        this.hoursToWork = new SimpleIntegerProperty(hoursToWork);
        this.staffUsername = new SimpleStringProperty(staffUsername);
        this.staffPassword = new SimpleStringProperty(staffPassword);
    }

    /**
     * [Getters and Setters]
     * Used to fill up the constructor as ManageStaffs field variables are private.*/
    public String getStaffFName() {
        return staffFName.get();
    }

    public void setStaffFName(String fName) {
        staffFName.set(fName);
    }

    public SimpleStringProperty staffFNameProperty() {
        return staffFName;
    }

    public String getStaffLName() {
        return staffLName.get();
    }

    public void setStaffLName(String lName) {
        staffLName.set(lName);
    }

    public SimpleStringProperty staffLNameProperty() {
        return staffLName;
    }

    public String getStaffType() {
        return staffType.get();
    }

    public void setStaffType(String type) {
        staffType.set(type);
    }

    public SimpleStringProperty staffTypeProperty() {
        return staffType;
    }

    public int getHoursToWork() {
        return hoursToWork.get();
    }

    public void setHoursToWork(int hours) {
        hoursToWork.set(hours);
    }

    public SimpleIntegerProperty staffHoursToWorkProperty() {
        return hoursToWork;
    }

    public String getStaffUsername() {
        return staffUsername.get();
    }

    public void setStaffUsername(String username) {
        staffUsername.set(username);
    }

    public SimpleStringProperty staffUsernameProperty() {
        return staffUsername;
    }

    public String getStaffPassword() {
        return staffPassword.get();
    }

    public void setStaffPassword(String password) {
        staffPassword.set(password);
    }

    public SimpleStringProperty staffPasswordProperty() {
        return staffPassword;
    }
}
