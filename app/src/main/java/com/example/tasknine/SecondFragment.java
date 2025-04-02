package com.example.tasknine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class SecondFragment extends Fragment {

    public static final String ARG_BUNDLE_MESSAGE = "bundle_message";

    private TextView textViewBundleMessage;
    private TextView textViewInterfaceMessage;
    private TextView textViewViewModelMessage;

    private SharedViewModel sharedViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        textViewBundleMessage = view.findViewById(R.id.textViewBundleMessage);
        textViewInterfaceMessage = view.findViewById(R.id.textViewInterfaceMessage);
        textViewViewModelMessage = view.findViewById(R.id.textViewViewModelMessage);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            String bundleMessage = getArguments().getString(ARG_BUNDLE_MESSAGE);
            if (bundleMessage != null && !bundleMessage.isEmpty()) {
                textViewBundleMessage.setText(bundleMessage);
            }
        }

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.getMessage().observe(getViewLifecycleOwner(), message -> {
            if (message != null && !message.isEmpty()) {
                textViewViewModelMessage.setText(message);
            }
        });
    }
    public void displayInterfaceMessage(String message) {
        if (textViewInterfaceMessage != null) {
            textViewInterfaceMessage.setText(message);
        }
    }
}