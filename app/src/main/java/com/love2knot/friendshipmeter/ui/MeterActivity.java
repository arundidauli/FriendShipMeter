package com.love2knot.friendshipmeter.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.love2knot.friendshipmeter.R;
import com.love2knot.friendshipmeter.databinding.ActivityMainBinding;
import com.love2knot.friendshipmeter.databinding.ActivityMeterBinding;

public class MeterActivity extends AppCompatActivity {

    ActivityMeterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMeterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.heart1.setProgress(67);
        binding.pulsator.start();
    }
}