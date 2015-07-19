package com.example.ykzhao.passwordmanager;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setSubmitState();
        EditText txtTags = this.getTxtTags();
        txtTags.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.this.setSubmitState();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        EditText txtPassword = this.getTxtPassword();
        txtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.this.setSubmitState();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        CheckBox chkShowClearPwd = this.getChkShowClearPwd();
        chkShowClearPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                MainActivity.this.setSubmitState();
            }
        });

        Button btnSubmit = this.getSubmitButton();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MainActivity.this.setSubmitState();
            }
        });
    }

    public Button getSubmitButton(){
        return (Button)this.findViewById(R.id.btnSubmit);
    }

    public EditText getTxtTags(){
        return (EditText)this.findViewById(R.id.txtTags);
    }

    public EditText getTxtPassword(){
        return (EditText)this.findViewById(R.id.txtPassword);
    }

    public CheckBox getChkShowClearPwd() { return (CheckBox) this.findViewById(R.id.chkShowClearPwd);}

    public void setSubmitState() {
        EditText mTxtTags = (EditText) this.findViewById(R.id.txtTags);
        String tags = mTxtTags.getText().toString().trim();
        EditText mTxtPassword = (EditText) this.findViewById(R.id.txtPassword);
        String password = mTxtPassword.getText().toString();
        Button btnSubmit = (Button) this.findViewById(R.id.btnSubmit);
        boolean isEnabled = tags.length() > 0 || password.length() > 0;
        boolean isFind = !isEnabled || tags.length() > 0 && password.length() == 0;

        String submitText = isFind ?
                getResources().getString(R.string.findPassword) :
                getResources().getString(R.string.savePassword);
        btnSubmit.setText(submitText);
        btnSubmit.setEnabled(isEnabled);

        CheckBox chkShowClearPwd = this.getChkShowClearPwd();
        if (chkShowClearPwd.isChecked()) {
            mTxtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

        } else {
            mTxtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
