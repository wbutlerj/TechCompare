package edu.byui.cit.techcompare.controller;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.Objects;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import edu.byui.cit.techcompare.R;

public class ViewDeviceFrag extends Fragment {
    private DatabaseReference dbDevices;
    private TextView deviceName;
    private Bundle args;
    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstState) {
        View view = null;
        try {
            // Load the xml file that corresponds to this Java file.
            view = inflater.inflate(R.layout.frag_view_device, container, false);


            // grabs the bundle from the previous fragment

            args = getArguments();
            assert args != null;

            deviceName = view.findViewById(R.id.deviceID);
            deviceName.setText(args.getString("deviceID"));

            deviceName = view.findViewById(R.id.device_name_view);
            deviceName.setText(args.getString("deviceName"));

            deviceName = view.findViewById(R.id.device_type_1);
            deviceName.setText(args.getString("deviceType"));

            deviceName = view.findViewById(R.id.cpu_1);
            deviceName.setText(args.getString("CPU"));

            deviceName = view.findViewById(R.id.ram_1);
            deviceName.setText(args.getString("RAM"));

            deviceName = view.findViewById(R.id.screen_size_1);
            deviceName.setText(args.getString("screenSize"));

            deviceName = view.findViewById(R.id.resolution_1);
            deviceName.setText(args.getString("screenResolution"));

            deviceName = view.findViewById(R.id.battery_1);
            deviceName.setText(args.getString("Battery"));

            deviceName = view.findViewById(R.id.price_1);

            NumberFormat formatter = NumberFormat.getInstance();
            String price = formatter.format(args.getDouble("Price"));
            deviceName.setText(price);

            // add Image
            String url = args.getString("image");

            ImageView DeviceImage = view.findViewById(R.id.device_image_1);
            Picasso.get().load(url).into(DeviceImage);

            // adds the back button
            Button back_button=view.findViewById(R.id.backButton);
            back_button.setOnClickListener(new BackHandler());

            // delete btn
            Button delete_button = view.findViewById(R.id.delete_device);
            delete_button.setOnClickListener(new DeleteDeviceHandler(args.getString("deviceID")));

            // compare btn
            Button compare_button = view.findViewById(R.id.compareButton);
            compare_button.setOnClickListener(new CompareChooseHandler());

            // sends bundle to the next frag



        }
        catch (Exception ex) {
            Log.e(MainActivity.TAG, ex.toString());
        }
        return view;
    }

    private final class DeleteDeviceHandler implements View.OnClickListener{

        String deviceKey;

        public DeleteDeviceHandler(String deviceKey){
            this.deviceKey = deviceKey;
        }
        @Override
        public void onClick(View v){
            // Delete Device Btn
            try {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                dbDevices = database.getReference("/devices");
                dbDevices.child(deviceKey).removeValue();
                Log.d(MainActivity.TAG, "Delete Device Key: " + deviceKey);

                // TODO: Display a menssage that this device was deleted and go back to MainFrag


            }
            catch (Exception ex) {
                Log.e(MainActivity.TAG, ex.getMessage());
            }
        }
    }

    private final class BackHandler implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            // TODO: It is not going back to MainFrag
            // Programmatically press the back arrow
            Objects.requireNonNull(getActivity()).onBackPressed();

        }
    }

    private final class CompareChooseHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // cast the Activity to a MainActivity
            MainActivity act = (MainActivity)getActivity();

            // Create the main fragment and place it
            // as the first fragment in this activity.
            Fragment frag = new CompareChooseFrag();
            frag.setArguments(args);
            assert act != null;
            FragmentTransaction trans =
                    act.getSupportFragmentManager().beginTransaction();
            trans.addToBackStack(null);
            trans.replace(R.id.fragMain, frag);
            trans.commit();
        }
    }

}

