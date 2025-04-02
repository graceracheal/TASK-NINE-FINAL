package com.example.tasknine;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class FirstFragment extends Fragment {

    private EditText editTextMessage;
    private Button buttonSendBundle;
    private Button buttonSendInterface;
    private Button buttonSendViewModel;

    private FragmentCommunicationListener communicationListener;
    private SharedViewModel sharedViewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            communicationListener = (FragmentCommunicationListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement FragmentCommunicationListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        editTextMessage = view.findViewById(R.id.editTextMessage);
        buttonSendBundle = view.findViewById(R.id.buttonSendBundle);
        buttonSendInterface = view.findViewById(R.id.buttonSendInterface);
        buttonSendViewModel = view.findViewById(R.id.buttonSendViewModel);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        buttonSendBundle.setOnClickListener(v -> {
            String message = editTextMessage.getText().toString().trim();
            if (!message.isEmpty()) {
                sendMessageViaBundle(message);
                clearInput();
            } else {
                showToast("Please enter a message");
            }
        });
        buttonSendInterface.setOnClickListener(v -> {
            String message = editTextMessage.getText().toString().trim();
            if (!message.isEmpty()) {
                sendMessageViaInterface(message);
                clearInput();
            } else {
                showToast("Please enter a message");
            }
        });
        buttonSendViewModel.setOnClickListener(v -> {
            String message = editTextMessage.getText().toString().trim();
            if (!message.isEmpty()) {
                sendMessageViaViewModel(message);
                clearInput();
            } else {
                showToast("Please enter a message");
            }
        });
    }
    private void sendMessageViaBundle(String message) {
        if (getActivity() != null) {
            SecondFragment secondFragment = new SecondFragment();
            Bundle bundle = new Bundle();
            bundle.putString(SecondFragment.ARG_BUNDLE_MESSAGE, message);
            secondFragment.setArguments(bundle);

            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_2, secondFragment)
                    .commit();

            showToast("Message sent via Bundle");
        }
    }
    private void sendMessageViaInterface(String message) {
        communicationListener.onMessageSent(message);
        showToast("Message sent via Interface");
    }
    private void sendMessageViaViewModel(String message) {
        sharedViewModel.setMessage(message);
        showToast("Message sent via ViewModel");
    }

    private void clearInput() {
        editTextMessage.setText("");
    }

    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}