package com.login.samplelogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;

public class RegistrationActivity extends AppCompatActivity
{
    EditText mInputFacId,mInputFacName,mInputFacDept,mInputFacDes,mInputFacMob,mInputFacPassword;
    Button mbtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initParams();
        mbtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perforSignUp();
            }
        });
    }

    private void initParams()
    {
        mInputFacId=(EditText)findViewById(R.id.fac_id);
        mInputFacName=(EditText)findViewById(R.id.fac_name);
        mInputFacMob=(EditText)findViewById(R.id.fac_mob);
        mInputFacDept=(EditText)findViewById(R.id.fac_dept);
        mInputFacDes=(EditText)findViewById(R.id.fac_des);
        mInputFacPassword=(EditText)findViewById(R.id.password);
        mbtnRegister=(Button)findViewById(R.id.reg_btn);
    }

    private void perforSignUp()
    {
        ApiService service=APIClient.getClient().create(ApiService.class);
      Call<Response> sigupCall= service.signUp(mInputFacId.getText().toString().trim(),mInputFacName.getText().toString().trim(),mInputFacMob.getText().toString().trim(),
                        mInputFacDept.getText().toString().trim(),mInputFacDes.getText().toString().trim(),mInputFacPassword.getText().toString().trim());
        sigupCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.body().getSuccess()==1)
                {
                    Toast.makeText(RegistrationActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }

                else {
                    Toast.makeText(RegistrationActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(RegistrationActivity.this, "Something went Wrong", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
