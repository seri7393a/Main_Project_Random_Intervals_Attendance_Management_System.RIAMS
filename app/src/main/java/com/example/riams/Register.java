package com.example.riams;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.riams.databinding.ActivityMain2Binding;
import com.example.riams.response.Regresponse;
import com.example.riams.response.courseresponse;
import com.example.riams.response.deptresponse;
import com.example.riams.rest.Api;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Register extends AppCompatActivity {
    ActivityMain2Binding bind;
    String name;
    String admissionno;
    String selecteddepartment;
    String selectedcourse;
    String selectedsem;
    String password;


    List<deptresponse> departmentresponse;
    List<courseresponse> courseresponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main2);


        getdepartment();
        getcourse();
        bind.Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                reg();


            }
        });

    }


    private void reg() {


        final ProgressDialog progressDialog = new ProgressDialog(Register.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();


        name = bind.Name.getText().toString().trim();
        admissionno = bind.admn.getText().toString().trim();
        //selecteddepartment = bind.spinnerdept.getText().toString().trim();
        // selectedcourse = bind.spinnercourse.getText().toString().trim();
        // selectedsem =bind.spinnersemester.getText().toString().trim();
        password = bind.password.getText().toString().trim();

        Api.getClient().register(name, admissionno, selectedcourse, selecteddepartment, selectedsem, password, new Callback<Regresponse>() {
            @Override
            public void success(Regresponse regresponse, Response response) {

                progressDialog.dismiss();


                if (regresponse.getMsg().equals("RegistrationSuccess")) {
                    Intent i = new Intent(getApplicationContext(), Login.class);
                    startActivity(i);

                } else if (regresponse.getMsg().equals("RegistrationFailed")) {
                    Intent i = new Intent(getApplicationContext(), Register.class);
                    startActivity(i);
                }
            }


            @Override
            public void failure(RetrofitError error) {

            }
        });
    }


    private void getdepartment(){
        final ProgressDialog progressDialog = new ProgressDialog(Register.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        Api.getClient().getdepartment(new Callback<List<deptresponse>>() {
            @Override
            public void success(List <deptresponse> regresponse, Response response) {

                progressDialog.dismiss();

                departmentresponse=regresponse;

                Log.i("Response","Length of array=="+departmentresponse.toString());

                setDataInSpinnerDepartment();

             //   Log.i("Response","Department=="+regresponse.);


            }


            @Override
            public void failure(RetrofitError error) {

            }
        });


    }


    public void setDataInSpinnerDepartment(){
        // Creating adapter for spinner
        ArrayAdapter<courseresponse> dataAdapter = new ArrayAdapter<courseresponse>(getApplicationContext(), android.R.layout.simple_spinner_item, courseresponse);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        bind.spinnercourse.setAdapter(dataAdapter)


        ;

    }
    private void getcourse(){
        final ProgressDialog progressDialog = new ProgressDialog(Register.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        Api.getClient().getcourse(new Callback<List<courseresponse>>() {
            @Override
            public void success(List <courseresponse> regresponse, Response response) {

                progressDialog.dismiss();

                courseresponse=regresponse;

                Log.i("Response","Length of array=="+courseresponse.toString());

                setDataInSpinnerCourse();

                //   Log.i("Response","Course=="+regresponse.);


            }


            @Override
            public void failure(RetrofitError error) {

            }
        });


    }


    public void setDataInSpinnerCourse(){
        // Creating adapter for spinner
        ArrayAdapter<courseresponse> dataAdapter = new ArrayAdapter<courseresponse>(getApplicationContext(), android.R.layout.simple_spinner_item, courseresponse);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        bind.spinnercourse.setAdapter(dataAdapter)


        ;

    }

}




