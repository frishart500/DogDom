package com_dog.dog.dog_java.dogdom.Onboarding;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;

import com_dog.dog.dog_java.dogdom.Auth.LoginActivity;
import com_dog.dog.dog_java.dogdom.Auth.RegistrationActivity;
import com_dog.dog.dog_java.dogdom.R;


public class ChoiseFragment extends Fragment {

    private MaterialButton loginBtn, registerBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choise, container, false);

        init(view);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), RegistrationActivity.class));
            }
        });

        return view;
    }

    private  void init(View view){
        loginBtn = view.findViewById(R.id.btn_login);
        registerBtn = view.findViewById(R.id.btn_register);
    }

}