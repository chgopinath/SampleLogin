package com.login.samplelogin;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity
{
    EditText mInputEmpName,mInputEmpPassword;
    Button mBtnLogin,mBtnRe;
    TextView mLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        try {
            initParams();
            mBtnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    performLogin();
                }
            });
            mBtnRe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,RegistrationActivity.class));
                }
            });
            mLabel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(MainActivity.this,RegistrationActivity.class));
                }
            });
        }catch (Exception e)
        {
            AlertDialog ad=new AlertDialog.Builder(this)
                    .setTitle("Exception")
                    .setMessage(""+e)
                    .create();
            ad.show();
        }


    }
  public void initParams()
    {
        mInputEmpName=(EditText)findViewById(R.id.emp_id_input);
        mInputEmpPassword=(EditText)findViewById(R.id.password_input);
        mBtnLogin=(Button)findViewById(R.id.login_btn);
        mLabel=(TextView)findViewById(R.id.textview);
        mBtnRe=(Button)findViewById(R.id.regist);
    }

    private void performLogin()
    {
        ApiService service=APIClient.getClient().create(ApiService.class);
        Call<Response> loginCall=service.performLogin(mInputEmpName.getText().toString().trim(),mInputEmpPassword.getText().toString().trim());

            loginCall.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response)
                {
                    if (response.body().success==0)
                    {
                        Toast.makeText(MainActivity.this,""+response.body().message,Toast.LENGTH_SHORT).show();
                    }
                    else if (response.body().success==1)
                    {
                        Toast.makeText(MainActivity.this, ""+response.body().message, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, ""+response.body().message, Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {

                }
            });
    }

    public void gotoRegistration()
    {
        startActivity(new Intent(MainActivity.this,RegistrationActivity.class));
    }
}
