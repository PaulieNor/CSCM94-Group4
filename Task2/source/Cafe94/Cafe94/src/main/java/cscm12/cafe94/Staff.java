package cscm12.cafe94;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**[ManageStaff]
 * Manage staff class is responsible for being the blank slate for staff information.
 * @author Sumi Sunuwar
 * @version 1.1 */
public class Staff{
    private final SimpleStringProperty staffFName, staffLName,
            staffType, staffUsername, staffPassword;
    private final SimpleIntegerProperty hoursToWork;

    /**[ManageStaff]
     * Constructor to create staff objects from the api or manipulate them.
     * @param staffFName Staff's first name.
     * @param staffLName Staff's last name.
     * @param staffType Staff's work position.
     * @param hoursToWork Staff's weekly hours to work.
     * @param staffUsername Staff's login username.
     * @param staffPassword Staff's login password. */
    public Staff(String staffFName, String staffLName, String staffType, int hoursToWork,
                       String staffUsername, String staffPassword) {
        this.staffFName = new SimpleStringProperty(staffFName);
        this.staffLName = new SimpleStringProperty(staffLName);
        this.staffType = new SimpleStringProperty(staffType);
        this.hoursToWork = new SimpleIntegerProperty(hoursToWork);
        this.staffUsername = new SimpleStringProperty(staffUsername);
        this.staffPassword = new SimpleStringProperty(staffPassword);
    }

    /**[Getters and Setters]
     * Used to fill up the constructor as ManageStaffs field variables are private.*/
    public SimpleStringProperty staffFNameProperty() {
        return staffFName;
    }

    public SimpleStringProperty staffLNameProperty() {
        return staffLName;
    }

    public SimpleStringProperty staffTypeProperty() {
        return staffType;
    }

    public SimpleIntegerProperty staffHoursToWorkProperty() {
        return hoursToWork;
    }

    public SimpleStringProperty staffUsernameProperty() {
        return staffUsername;
    }

    public SimpleStringProperty staffPasswordProperty() {
        return staffPassword;
    }
}
