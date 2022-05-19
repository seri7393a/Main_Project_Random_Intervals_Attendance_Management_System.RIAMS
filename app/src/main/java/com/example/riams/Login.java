package com.example.riams;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.riams.databinding.ActivityMainBinding;
import com.example.riams.response.Regresponse;
import com.example.riams.rest.Api;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Login extends AppCompatActivity {
    ActivityMainBinding bind;
    String admnnumber;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bind = DataBindingUtil.setContentView(this, R.layout.activity_main);


        bind.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                login();
            }

            private void login() {
                // display a progress dialog
                final ProgressDialog progressDialog = new ProgressDialog(Login.this);
                progressDialog.setCancelable(false); // set cancelable to false
                progressDialog.setMessage("Please Wait"); // set message
                progressDialog.show(); // show progress dialog

                admnnumber = bind.admnnumber.getText().toString().trim();
                password = bind.pswd.getText().toString().trim();

                Api.getClient().login(admnnumber, password, new Callback<Regresponse>() {
                    @Override
                    public void success(Regresponse regresponse, Response response) {


                        if (regresponse.getMsg().equals("LoginSuccessfull")) {
                            Intent i = new Intent(getApplicationContext(), Homepage.class);
                            startActivity(i);

                        } else {
                            Intent i = new Intent(getApplicationContext(), Login.class);
                            startActivity(i);
                        }


                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }

                });

            }
        });
            bind.signuplink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(getApplicationContext(),Register.class);
                    startActivity(i);


                }

            });




    }
}