package com.example.tasknine;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity implements FragmentCommunicationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onMessageSent(String message) {
        Fragment secondFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container_2);

        if (secondFragment instanceof SecondFragment && secondFragment.isAdded()) {
            ((SecondFragment) secondFragment).displayInterfaceMessage(message);
        }
    }
}