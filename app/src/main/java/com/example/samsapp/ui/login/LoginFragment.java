package com.example.samsapp.ui.login;

import android.app.Activity;
import android.content.Context;
import android.app.Application;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;;

import com.example.samsapp.DatabaseHelper;
import com.example.samsapp.GlobalClass;
import com.example.samsapp.MainActivity;
import com.example.samsapp.Students;
import com.example.samsapp.databinding.LoginBinding;

import java.util.ArrayList;

public class LoginFragment extends Fragment {
    private LoginViewModel loginViewModel;
    private LoginBinding binding;
    DatabaseHelper STConnector;
    Context mContext;
    SimpleCursorAdapter scAdapter;
    Cursor cursor;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = LoginBinding.inflate(inflater, container, false);

        View root = binding.getRoot();
        EditText user = binding.username;
        EditText password = binding.password;
        Button button = binding.login;
        STConnector = new DatabaseHelper(getContext());

        button.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(1);
                if(!user.getText().toString().equals("") && !password.getText().toString().equals("")) {
                    if(STConnector.exist(user.getText().toString())){
                        if(STConnector.select(user.getText().toString()).getHashed_password().equals(String.valueOf(password.getText().toString().hashCode()))){
                            System.out.println(222);
                            boolean im = true;
                            System.out.println(((GlobalClass) getActivity().getApplicationContext()).isAuthorized());
                            ((GlobalClass) getActivity().getApplicationContext()).setAuthorized(im);
                            System.out.println(((GlobalClass) getActivity().getApplicationContext()).isAuthorized());
                            Navigation.findNavController(v).navigateUp();
                        }
                        else
                            Toast.makeText(getContext(), "Не верный пароль", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(getContext(), "Не верный ник или пароль", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}