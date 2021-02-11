package edu.byui.cit.techcompare.controller;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.text.NumberFormat;
import java.util.Objects;

import edu.byui.cit.techcompare.R;
import edu.byui.cit.techcompare.model.DevicesDB;
import edu.byui.cit.techcompare.model.DevicesDAO;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static edu.byui.cit.techcompare.controller.MainActivity.TAG;

public class AddDeviceFrag extends Fragment{
    private EditText deviceName;
    private EditText deviceType;
    private EditText CPU;
    private EditText RAM;
    private EditText screenSize;
    private EditText screenResolution;
    private EditText Battery;
    private EditText Price;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstState) {
        View view = null;
        try {
            // Load the xml file that corresponds to this Java file.
            view = inflater.inflate(R.layout.frag_add_device, container, false);


            // adds the back button
            Button back_button=view.findViewById(R.id.back_button);
            back_button.setOnClickListener(new BackHandler());

            // adds the add device button
            Button add_new_button =view.findViewById(R.id.add_new_button);
            add_new_button.setOnClickListener(new AddHandler());


            // Upload Image button
            Button upload_button = view.findViewById(R.id.upload_button);
            upload_button.setOnClickListener(new UploadHandler());

            // connect all of the input fields
            deviceName = view.findViewById(R.id.deviceName);
            deviceType = view.findViewById(R.id.device_type);
            CPU = view.findViewById(R.id.cpu);
            RAM = view.findViewById(R.id.ram);
            screenSize = view.findViewById(R.id.screen_size);
            screenResolution = view.findViewById(R.id.resolution);
            Battery = view.findViewById(R.id.battery);
            Price = view.findViewById(R.id.price);
        }
        catch (Exception ex) {
            Log.e(MainActivity.TAG, ex.toString());
        }
        return view;
    }

    private final class BackHandler implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            // Programmatically press the back arrow
            Objects.requireNonNull(getActivity()).onBackPressed();

        }
    }

    private  final class UploadHandler implements View.OnClickListener{
        public void onClick(View v){
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference deviceImage = storage.getReference().child("deviceImages");


        }
    }


    private final class AddHandler implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            try {
                // get the data
                String name = deviceName.getText().toString();
                String d_type = deviceType.getText().toString();
                String d_cpu = CPU.getText().toString();
                String d_ram = RAM.getText().toString();
                String d_screenSize = screenSize.getText().toString();
                String d_resolution = screenResolution.getText().toString();
                String d_battery = Battery.getText().toString();
                NumberFormat formatter = NumberFormat.getInstance();
                double d_price = Objects.requireNonNull(formatter.parse(Price.getText().toString())).doubleValue();
                String d_image = null; // todo: link to Firebase Storage API image URL
                String d_link = null; // todo: link to individual page (??)

                // create new project object
                DevicesDB device = new DevicesDB(name, d_type, d_cpu, d_ram, d_screenSize, d_resolution, d_battery, d_price, d_link, d_image);

                // Call devices dao function to insert into the database
                DevicesDAO.insertDevice(device);

                // Toast confirming insertion of a new device
                Context appCtx = Objects.requireNonNull(getActivity()).getApplicationContext();
                Toast.makeText(appCtx, "Device Inserted", Toast.LENGTH_LONG).show();

                // Go back to other screen
                Objects.requireNonNull(getActivity()).onBackPressed();

            } catch (Exception ex) {
                Log.e(MainActivity.TAG, ex.toString());
            }
        }
    }
}
