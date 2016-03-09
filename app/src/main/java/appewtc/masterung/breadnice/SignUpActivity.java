package appewtc.masterung.breadnice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
        } else {
        }

    }   // clickSignUp

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
