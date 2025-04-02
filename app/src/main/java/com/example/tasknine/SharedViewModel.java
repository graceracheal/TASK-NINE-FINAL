package com.example.tasknine;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> message = new MutableLiveData<>();
    public LiveData<String> getMessage() {
        return message;
    }
    public void setMessage(String input) {
        message.setValue(input);
    }
}
