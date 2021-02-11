package edu.byui.cit.techcompare.controller;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Objects;

import edu.byui.cit.techcompare.R;

public class ComparisonFrag extends Fragment {
    private DatabaseReference dbDevices;
    private TextView deviceName;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstState) {


        View view = null;
        try {
            // Load the xml file that corresponds to this Java file.
            view = inflater.inflate(R.layout.frag_comparison, container, false);

            // grabs the bundle from the previous fragment

            Bundle args = getArguments();
            assert args != null;


            deviceName = view.findViewById(R.id.device_name_view);
            deviceName.setText(args.getString("deviceName"));

            deviceName = view.findViewById(R.id.device_name_2);
            deviceName.setText(args.getString("deviceName2"));

            deviceName = view.findViewById(R.id.device_type_1);
            deviceName.setText(args.getString("deviceType"));

            deviceName = view.findViewById(R.id.device_type_2);
            deviceName.setText(args.getString("deviceType2"));

            deviceName = view.findViewById(R.id.cpu_1);
            deviceName.setText(args.getString("CPU"));

            deviceName = view.findViewById(R.id.cpu_2);
            deviceName.setText(args.getString("CPU2"));

            deviceName = view.findViewById(R.id.ram_1);
            deviceName.setText(args.getString("RAM"));

            deviceName = view.findViewById(R.id.ram_2);
            deviceName.setText(args.getString("RAM2"));

            deviceName = view.findViewById(R.id.screen_size_1);
            deviceName.setText(args.getString("screenSize"));

            deviceName = view.findViewById(R.id.screen_size_2);
            deviceName.setText(args.getString("screenSize2"));

            deviceName = view.findViewById(R.id.resolution_1);
            deviceName.setText(args.getString("screenResolution"));

            deviceName = view.findViewById(R.id.resolution_2);
            deviceName.setText(args.getString("screenResolution2"));

            deviceName = view.findViewById(R.id.battery_1);
            deviceName.setText(args.getString("Battery"));

            deviceName = view.findViewById(R.id.battery_2);
            deviceName.setText(args.getString("Battery2"));

            deviceName = view.findViewById(R.id.price_1);

            NumberFormat formatter = NumberFormat.getInstance();
            String price = formatter.format(args.getDouble("Price"));
            deviceName.setText(price);

            deviceName = view.findViewById(R.id.price_2);

            NumberFormat formatter2 = NumberFormat.getInstance();
            String price2 = formatter.format(args.getDouble("Price2"));
            deviceName.setText(price2);

            // add Images
            String url = args.getString("image");

            ImageView DeviceImage = view.findViewById(R.id.device_image_1);
            Picasso.get().load(url).into(DeviceImage);

            String url2 = args.getString("image2");

            ImageView DeviceImage2 = view.findViewById(R.id.device_image_2);
            Picasso.get().load(url2).into(DeviceImage2);

            // adds the back button
            Button back_button=view.findViewById(R.id.backbutton);
            back_button.setOnClickListener(new ComparisonFrag.BackHandler());


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
}
