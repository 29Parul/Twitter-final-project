package com.example.parul.twsearch;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by parul on 24/07/16.
 */
public class Login extends AppCompatActivity implements View.OnClickListener {

        EditText _passwordText, email_text;
        Button _loginButton, login_Fb;
        TextView _signupLink;
        private static final int REQUEST_SIGNUP = 0;
        private static final String PREF_UNAME = "Username";
        private static final String PREF_PASSWORD = "Password";
        private final String defaultUnameValue = "";
        private String unameValue;
        private final String defaultPasswordValue = "";
        private String passwordValue;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.login);


            email_text = (EditText) findViewById(R.id.input_email);
            _passwordText = (EditText) findViewById(R.id.input_password);
            _loginButton = (Button) findViewById(R.id.btn_login);
            _signupLink = (TextView) findViewById(R.id.link_signup);


            _loginButton.setOnClickListener(this);
            _signupLink.setOnClickListener(this);





        }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.link_signup:
                Intent intent = new Intent(Login.this, com.example.parul.twsearch.SignupActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                break;
            default:
                break;
        }
    }
    public void login() {
        Log.d("Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }
        _loginButton.setEnabled(false);
        final ProgressDialog progressDialog = new ProgressDialog(Login.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = email_text.getText().toString();
        String password = _passwordText.getText().toString();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        onLoginSuccess();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
       // moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        saveLoginPreferences();
        finish();
        Intent intent = new Intent(this, com.example.parul.twsearch.MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }
    public boolean validate() {

        boolean valid = true;

        String email = email_text.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            email_text.setError("enter a valid email address");
            valid = false;
        } else {
            email_text.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
    static boolean checkInternetConnection(Context context) throws Exception {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        // test for connection
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            Toast.makeText(context,"Internet connection not found.Please enable it.",Toast.LENGTH_LONG).show();

            Log.e("Internet connection not found.");
            throw new IllegalStateException("Internet connection not found.");

        }
    }


    private void saveLoginPreferences() {

        try {
            SharedPreferences settings = getSharedPreferences(com.example.parul.twsearch.Constants.LOGIN_PREFS_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();

            unameValue = email_text.getText().toString();
            passwordValue = _passwordText.getText().toString();

            editor.putString(PREF_UNAME, unameValue);
            editor.putString(PREF_PASSWORD, passwordValue);
            editor.putBoolean(com.example.parul.twsearch.Constants.LOGIN_ENABLE, true);
            editor.commit();
            Log.d("Login Details saved savePreferences - " + unameValue + " " + passwordValue);

        } catch (Exception e) {
            Log.d("Exeption in LOGIN_PREFS_NAME :" + e);

        }
    }


      /*  protected void loadPreferences() {

            SharedPreferences settings = getSharedPreferences(Constants.LOGIN_PREFS_NAME,
                    Context.MODE_PRIVATE);
            // Get value
            unameValue = settings.getString(PREF_UNAME, defaultUnameValue);
            passwordValue = settings.getString(PREF_PASSWORD, defaultPasswordValue);
            Log.d("Login Details retrive from loadPreferences- " + unameValue + " " + passwordValue);
        }
*/
    public static boolean isLoginEnabled(Context context) {
        try {
            SharedPreferences Prefs = context.getSharedPreferences(
                    com.example.parul.twsearch.Constants.LOGIN_PREFS_NAME, Context.MODE_PRIVATE);
            if (Prefs != null && !Prefs.equals(null))
                if (Prefs.contains(com.example.parul.twsearch.Constants.LOGIN_ENABLE))
                    return Prefs.getBoolean(com.example.parul.twsearch.Constants.LOGIN_ENABLE, false);

        } catch (Exception e) {
            Log.e("Exception occurred while enabling sdk", e);

        }
        return false;
    }

}