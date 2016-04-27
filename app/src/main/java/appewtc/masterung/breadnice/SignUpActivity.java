package appewtc.masterung.breadnice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class SignUpActivity extends AppCompatActivity {

    //Explicit
    private EditText nameEditText, userEditText, passwordEditText, idCardEditText;
    private String nameString, userString, passwordString, idCardString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Bind Widget
        bindWidget();

    }   // Main Method



    public void clickSignUpSign(View view) {

        nameString = nameEditText.getText().toString().trim();
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString();
        idCardString = idCardEditText.getText().toString().trim();

        //CheckSpace
        if (checkSpace()) {
            Toast.makeText(this, "มีช่องว่าง", Toast.LENGTH_SHORT).show();
        } else {
            //No Space
            updateToServer();
        }

    }   // clickSignUp

    private void updateToServer() {

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("Name", nameString)
                .add("User", userString)
                .add("Password", passwordString)
                .add("ID_Card", idCardString)
                .build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.url("http://swiftcodingthai.com/bic/php_add_user_nice.php").post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.d("27April", "myError ==> " + e.toString());
            }

            @Override
            public void onResponse(Response response) throws IOException {

                try {
                    finish();
                } catch (Exception e) {
                    Log.d("27April", "myError ==> " + e.toString());
                    e.printStackTrace();
                }

            }
        });


    }   // updateToServer

    private boolean checkSpace() {
        boolean result = true;
        result = nameString.equals("") || userString.equals("") ||
                passwordString.equals("") || idCardString.equals("");
        return result;
    }


    private void bindWidget() {
        nameEditText = (EditText) findViewById(R.id.editText);
        userEditText = (EditText) findViewById(R.id.editText2);
        passwordEditText = (EditText) findViewById(R.id.editText3);
        idCardEditText = (EditText) findViewById(R.id.editText4);
    }

}   // Main Class
