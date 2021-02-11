package edu.byui.cit.techcompare.model;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import edu.byui.cit.techcompare.R;
import edu.byui.cit.techcompare.model.DevicesDB;
import java.util.Objects;
public class DevicesDAO {
    // device object

    //database reference
    private static final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static final DatabaseReference dbDevices = database.getReference("/devices");



    public static void insertDevice(DevicesDB device){
        DatabaseReference node = dbDevices.push();
        node.setValue(device);


    }
// todo: add more functions like update, remove a single device, wipe DB (Delete all)
    public static void updateDevice(DevicesDB device){
        
        //get reference to specific node by device name
       dbDevices.child(device.getName()).setValue(device);
       
       dbDevices.child(device.getName());
    }

    public static void deleteDevice(DevicesDB device){
        //get reference to specific node by device name and delete it
        dbDevices.child(device.getName()).removeValue();
    }

    public static void deleteAll(DevicesDB device){
        //get reference to all nodes by node and delete them
        dbDevices.removeValue();
    }





}
