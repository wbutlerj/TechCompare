package edu.byui.cit.techcompare.controller;

import android.content.Context;
import android.content.res.Resources;
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

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import edu.byui.cit.techcompare.R;
import edu.byui.cit.techcompare.model.DevicesDB;


public class CompareChooseFrag extends Fragment {
    RecyclerView recyclerView;
    Query query1;
    private DatabaseReference dbDevices;
    FirebaseRecyclerAdapter<DevicesDB, MainFrag.BlogViewHolder> firebaseRecyclerAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        try {

            super.onCreateView(inflater, container, savedInstanceState);
            // Load the xml file that corresponds to this Java file.
            view = inflater.inflate(R.layout.frag_compare_choose, container, false);

            if (dbDevices == null) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                dbDevices = database.getReference("/devices");
            }

            recyclerView = view.findViewById(R.id.recyclerViewGirdView);

            // Make the query to get all objects on the DB
            super.onStart();
            query1 = FirebaseDatabase.getInstance().getReference().child("devices");
            FirebaseRecyclerOptions<DevicesDB> options =
                    new FirebaseRecyclerOptions.Builder<DevicesDB>()
                            .setQuery(query1, DevicesDB.class)
                            .build();

            Log.d("Options"," data : "+options);

            // Create RecyclerView and Populate with date from Firebase
            firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<DevicesDB, MainFrag.BlogViewHolder>(options) {
                // Call Image URL of Firebase Storage todo: need to implemented
                @Override
                protected void onBindViewHolder(@NonNull MainFrag.BlogViewHolder blogViewHolder, final int i, @NonNull DevicesDB product_get_set_v) {

                    blogViewHolder.setname(product_get_set_v.getName());
                    blogViewHolder.setimage(product_get_set_v.getImage());

                    // Add link to card on click go to the device frag.
                    blogViewHolder.itemView.setOnClickListener(v -> {
                        final String deviceID=getRef(i).getKey();
                        Log.d(MainActivity.TAG," data : "+deviceID);


                        assert deviceID != null;
                        dbDevices.child(deviceID).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                Context appCtx = Objects.requireNonNull(getActivity()).getApplicationContext();
                                // cast the Activity to a MainActivity
                                MainActivity act = (MainActivity)getActivity();

                                // Create the main fragment and place it
                                // as the first fragment in this activity.
                                Fragment frag = new ComparisonFrag();

                                Bundle args = getArguments();
                                Resources res = act.getResources();

                                // Passing Device Info for ViewDeviceFrag
                                args.putString("deviceName2", product_get_set_v.getName());
                                args.putString("deviceType2", product_get_set_v.getType());
                                args.putString("CPU2", product_get_set_v.getCpu());
                                args.putString("RAM2", product_get_set_v.getRam());
                                args.putString("screenSize2", product_get_set_v.getScreen_size());
                                args.putString("screenResolution2", product_get_set_v.getResolution());
                                args.putString("Battery2", product_get_set_v.getBattery());
                                args.putDouble("Price2", product_get_set_v.getPrice());
                                args.putString("image2", product_get_set_v.getImage());
                                args.putString("deviceID2", getRef(i).getKey());
                                frag.setArguments(args);

                                FragmentTransaction trans =
                                        act.getSupportFragmentManager().beginTransaction();
                                trans.addToBackStack(null);
                                trans.replace(R.id.fragMain, frag);
                                trans.commit();

                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }
                        });
                    });
                }

                @NonNull
                @Override
                public MainFrag.BlogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.card_layout, parent, false);
                    return new MainFrag.BlogViewHolder(view);
                }
            };
            firebaseRecyclerAdapter.startListening();
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(firebaseRecyclerAdapter);

            // Get the back button to simulate a back arrow press
            Button btnback = view.findViewById(R.id.back);
            btnback.setOnClickListener(new CompareChooseFrag.BackHandler());


        } catch (Exception ex) {
            Log.e(MainActivity.TAG, ex.toString());
        }
        return view;
    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder
    {
        View mView;

        public BlogViewHolder(View itemView)
        {
            super(itemView);
            mView=itemView;
        }
        public void setname(String name)
        {
            TextView cardDeviceName= mView.findViewById(R.id.device_name);
            cardDeviceName.setText(name.toUpperCase());
        }

        public String setimage(String url)
        {
            ImageView cardDeviceImage = mView.findViewById(R.id.device_image);
            Picasso.get().load(url).into(cardDeviceImage);
            return url;
        }
    }

    private final class BackHandler implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            // Programmatically press the back arrow
            Objects.requireNonNull(getActivity()).onBackPressed();

        }
    }

    private final class ComparisonHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // cast the Activity to a MainActivity
            MainActivity act = (MainActivity)getActivity();

            // Create the main fragment and place it
            // as the first fragment in this activity.
            Fragment frag = new ComparisonFrag();
            assert act != null;
            FragmentTransaction trans =
                    act.getSupportFragmentManager().beginTransaction();
            trans.addToBackStack(null);
            trans.replace(R.id.fragMain, frag);
            trans.commit();
        }
    }

}

