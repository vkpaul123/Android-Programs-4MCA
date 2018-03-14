package com.example.vkpaul123.myconference;

import android.content.Intent;
import android.hardware.camera2.TotalCaptureResult;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner1;
    private EditText name, phone, email, pass, address, dob, time;
    private Switch member;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button register;

    private String toastText = "";

    private String[] spinItems = {"(Select Age Group)", "15 and Below", "16-20", "21-30", "31-50", "51-70", "71 and Above"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addItemOnSpinner();
        addListenerOnButton();
    }

    public void addListenerOnButton() {
        radioGroup = (RadioGroup) findViewById(R.id.genderGroup);

        register = (Button) findViewById(R.id.button4);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameRegEx = "^[\\p{L} .'-]+$", phoneRegEx= "\\d+", emailRegEx="^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", dateRegEx="^\\d{2}/\\d{2}/\\d{4}$", timeRegEx="^\\d{2}:\\d{2}$" , passRegEx="^((?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})$";
                Pattern namePattern = Pattern.compile(nameRegEx, Pattern.CASE_INSENSITIVE), phonePattern = Pattern.compile(phoneRegEx, Pattern.CASE_INSENSITIVE), emailPattern = Pattern.compile(emailRegEx, Pattern.CASE_INSENSITIVE), datePattern = Pattern.compile(dateRegEx, Pattern.UNICODE_CASE), timePattern = Pattern.compile(timeRegEx, Pattern.CASE_INSENSITIVE), passPattern = Pattern.compile(passRegEx, Pattern.CASE_INSENSITIVE);

                try {
                    name = (EditText) findViewById(R.id.editText4);
                    phone = (EditText) findViewById(R.id.editText5);
                    email = (EditText) findViewById(R.id.editText6);
                    pass = (EditText) findViewById(R.id.editText2);
                    address = (EditText) findViewById(R.id.editText7);
                    dob = (EditText) findViewById(R.id.editText8);
                    time = (EditText) findViewById(R.id.editText9);
                    spinner1 = (Spinner) findViewById(R.id.ageGroup);
                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(selectedId);
                    member = (Switch) findViewById(R.id.memberCheck);
                    pass = (EditText) findViewById(R.id.editText2);

                    if(name.getText().length() < 3)
                        name.setError("Name is Too Short.");
                    else if(! namePattern.matcher(name.getText()).matches())
                        name.setError("Invalid Name");

                    else if(phone.getText().length() < 6)
                        phone.setError("Phone Number is Too Short");
                    else if(! phonePattern.matcher(phone.getText()).matches())
                        phone.setError("Invalid Phone Number");

                    else if(email.getText().length() < 5)
                        email.setError("Email is too short.");
                    else if(! emailPattern.matcher(email.getText()).matches())
                        email.setError("Invalid Email");

                    else if(pass.getText().length() < 3)
                        pass.setError("Password is too Short!");

//                    else if(! passPattern.matcher(pass.getText()).matches())
//                        pass.setError("Please add a Symbol, Capital Letter, Small Letter, and a Number.");

                    else if(address.getText().length() < 4)
                        address.setError("Address is Too Short.");

                    else if(dob.getText().length() != 10 || !datePattern.matcher(dob.getText()).matches())
                        dob.setError("Invalid DOB!");

                    else if(time.getText().length() != 5 || !timePattern.matcher(time.getText()).matches())
                        time.setError("Invalid Suitable Time!");

                    else if(spinner1.getSelectedItem().toString().equals(new String("(Select Age Group)")))
                        Toast.makeText(MainActivity.this, "Please Select a Valid Age Group.", Toast.LENGTH_LONG).show();

                    else {
                        toastText += "Name: " + name.getText() + "\n";
                        toastText += "Ph No: " + phone.getText() + "\n";
                        toastText += "Email: " + email.getText() + "\n";
                        toastText += "Address: " + address.getText() + "\n";
                        toastText += "DOB: " + dob.getText() + "\n";
                        toastText += "Suitable Time: " + time.getText() + "\n";
                        toastText += "Age Group: " + spinner1.getSelectedItem().toString() + "\n";
                        toastText += "Gender: " + radioButton.getText() + "\n";

                        if(member.isActivated())
                            toastText += "Member: Yes\n";
                        else
                            toastText += "Member: No\n";

                        Toast.makeText(MainActivity.this, toastText, Toast.LENGTH_LONG).show();
                        String []sendArray = {name.getText().toString(), phone.getText().toString(), email.getText().toString()};
                        Intent in = new Intent(getApplicationContext(), Main2Activity.class);
                        in.putExtra("sendArray", sendArray);
                        startActivity(in);
                        finish();

                    }
                }
                catch (Exception e) {
                    Toast.makeText(MainActivity.this,"Error in Submission!\nPlease Fill Complete Values!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addItemOnSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.ageGroup);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinItems);

        spinner.setAdapter(aa);
    }
}
