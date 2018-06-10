package tops.com.barberapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button loginButton;
    EditText etUsername, etPassword;
    TextView tvNewUser;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context=this;
        loginButton=findViewById(R.id.btnLogin);
        etUsername=findViewById(R.id.etUsername);
        etPassword=findViewById(R.id.etPassword);
        tvNewUser=findViewById(R.id.tvNewUser);
        // Anonymous Event Handling
        loginButton.setOnClickListener(v -> {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                if (username.equals(""))
                {
                    etUsername.setError("Required");
                    return;
                }
                if(password.equals(""))
                {
                    etPassword.setError("Required");
                    return;
                }
                Toast.makeText(context, username+":::"+password, Toast.LENGTH_SHORT).show();

        });

        tvNewUser.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(context,RegistrationActivity.class));
    }
}
