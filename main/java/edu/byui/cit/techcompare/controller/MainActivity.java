package edu.byui.cit.techcompare.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import edu.byui.cit.techcompare.R;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "tecCompare";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            if (savedInstanceState == null) {
                // Create the main fragment and place it
                // as the first fragment in this activity.
                Fragment frag = new MainFrag();
                FragmentTransaction trans =
                        getSupportFragmentManager().beginTransaction();
                trans.add(R.id.fragMain, frag);
                trans.commit();
            }

        }
        catch (Exception ex) {
            Log.e(TAG, ex.getMessage(),ex);
        }
    }
}