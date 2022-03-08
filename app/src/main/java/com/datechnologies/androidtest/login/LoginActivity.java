package com.datechnologies.androidtest.login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.datechnologies.androidtest.MainActivity;
import com.datechnologies.androidtest.R;
import com.datechnologies.androidtest.api.RetrofitClient;
import com.datechnologies.androidtest.api.apiInterface.UserClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A screen that displays a login prompt, allowing the user to login to the D & A Technologies Web Server.
 *
 */
public class LoginActivity extends AppCompatActivity {

    //==============================================================================================
    // Static Class Methods
    //==============================================================================================

    public static void start(Context context)
    {
        Intent starter = new Intent(context, LoginActivity.class);
        context.startActivity(starter);
    }

    //==============================================================================================
    // Lifecycle Methods
    //==============================================================================================

    private EditText username;
    private EditText password;
    private Button login;
//
//    private static Retrofit.Builder builder = new Retrofit.Builder()
//            .baseUrl("https://dev.rapptrlabs.com/Tests/scripts/")
//            .addConverterFactory(GsonConverterFactory.create());
//    public static Retrofit retrofit = builder.build();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Login");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        username = (EditText)findViewById(R.id.login_userName);
        password = (EditText)findViewById(R.id.login_password);
        login = (Button)findViewById(R.id.login_button);

        username.setText("info@rapptrlabs.com");

        login.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sendMessage(username.getText().toString(), password.getText().toString());
                }
            }
        );



        // Make the UI look like it does in the mock-up.
        // Allow for horizontal screen rotation.
        // Add a ripple effect when the buttons are clicked
        // Save screen state on screen rotation, inputted username and password should not disappear on screen rotation

        // The only valid login credentials are:
        // email: info@rapptrlabs.com
        // password: Test123

    }

    //  When you receive a response from the login endpoint, display an AlertDialog.
    //  The AlertDialog should display the 'code' and 'message' that was returned by the endpoint.
    //  The AlertDialog should also display how long the API call took in milliseconds.
    //  When a login is successful, tapping 'OK' on the AlertDialog should bring us back to the MainActivity
    private void sendMessage(String username, String password){
        UserClient userClient = RetrofitClient.getRetrofit().create(UserClient.class);

        Call<ResponseBody> call = userClient.sendCred(
                username,
                password
        );
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                long api_executionTime = response.raw().receivedResponseAtMillis() - response.raw().sentRequestAtMillis();
                AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
                    alert.setTitle("Login");
                    alert.setMessage("Code: " +response.code()  +
                            "\n" + "Message: " + response.message() +
                            "\n" + "Execution Time: " + api_executionTime+"ms");
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            onBackPressed();
                        }
                    });
                alert.create().show();

            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
                    alert.setTitle("Failed to Signin");
                    alert.setMessage(throwable.getMessage());
                alert.create().show();
                Toast.makeText(LoginActivity.this, "fail", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
