package com_dog.dog.dog_java.dogdom.ProfileStructure;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com_dog.dog.dog_java.dogdom.R;

public class ChangeDataActivity extends AppCompatActivity {

    private TextInputEditText changeNameEditText, changeEmailEditText, changePhoneEditText;
    private MaterialButton changeBtn;
    private ImageView backImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_data);

        init();

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersRef = rootRef.child("users");
        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!changeNameEditText.getText().toString().isEmpty()){
                    usersRef.child(currentFirebaseUser.getUid()).child("name").setValue(changeNameEditText.getText().toString().trim());
                }if(!changeEmailEditText.getText().toString().isEmpty()){
                    usersRef.child(currentFirebaseUser.getUid()).child("email").setValue(changeEmailEditText.getText().toString().trim());
                }if(!changePhoneEditText.getText().toString().isEmpty()){
                    usersRef.child(currentFirebaseUser.getUid()).child("phone").setValue("+7" + changeEmailEditText.getText().toString().trim());
                }if(changeNameEditText.getText().toString().isEmpty() & changeEmailEditText.getText().toString().isEmpty() & changePhoneEditText.getText().toString().isEmpty()){
                    Toast.makeText(ChangeDataActivity.this, "No data were changed", Toast.LENGTH_SHORT).show();
                }


                onBackPressed();
            }
        });


        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });


    }

    private void init(){
        backImg = findViewById(R.id.img_back);
        changeNameEditText = findViewById(R.id.change_name_edit_text);
        changeEmailEditText = findViewById(R.id.change_email_edit_text);
        changePhoneEditText = findViewById(R.id.change_phone_edit_text);
        changeBtn = findViewById(R.id.btn_change_data);
    }

}