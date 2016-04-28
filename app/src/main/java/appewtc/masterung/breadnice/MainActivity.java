package appewtc.masterung.breadnice;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private EditText userEditText, passwordEditText;
    private String userString, passwordString, passwordTrueString;
    private String[] myUserStrings, myPasswordStrings;
    private boolean userABoolean = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        userEditText = (EditText) findViewById(R.id.editText5);
        passwordEditText = (EditText) findViewById(R.id.editText6);

    }   // Main Method

    public class ConnectedJSON extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url("http://swiftcodingthai.com/bic/get_user_nice.php").build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                return null;
            }

        }   // doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {

                JSONArray jsonArray = new JSONArray(s);
                myUserStrings = new String[jsonArray.length()];
                myPasswordStrings = new String[jsonArray.length()];

                for (int i=0;i<jsonArray.length();i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    myUserStrings[i] = jsonObject.getString("User");
                    myPasswordStrings[i] = jsonObject.getString("Password");

                    if (userString.equals(myUserStrings[i])) {
                        //UserTrue
                        userABoolean = true;
                        passwordTrueString = myPasswordStrings[i];
                    }

                }   // for

                if (userABoolean) {

                    if (passwordString.equals(passwordTrueString)) {
                        Toast.makeText(MainActivity.this, "Welcome",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Password False", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "User False", Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }   // onPost
    }   // Connected Class

    public void clickSignIn(View view) {

        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        if (userString.equals("") || passwordString.equals("")) {
            Toast.makeText(this, "Have Space", Toast.LENGTH_SHORT).show();
        } else {
            //No Space
            ConnectedJSON connectedJSON = new ConnectedJSON();
            connectedJSON.execute();
        }


    }   // clickSignIn

    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
    }

}   // Main Class
