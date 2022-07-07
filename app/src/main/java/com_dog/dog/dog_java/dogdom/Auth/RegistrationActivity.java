package com_dog.dog.dog_java.dogdom.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

import com_dog.dog.dog_java.dogdom.FirebaseModules.User;
import com_dog.dog.dog_java.dogdom.R;
import com_dog.dog.dog_java.dogdom.ui.Dashboard.Main2Activity;

public class RegistrationActivity extends AppCompatActivity {

    private User user;
    FirebaseAuth mAuth;
    private TextInputEditText phoneEditText, emailEditText, nameEditText;
    private String verificationID;
    private MaterialButton regMoveBtn, verifyBtn;
    private PinView pinViewReg;
    private ImageView backImg;
    private long maxId = 0;
    private DatabaseReference myRef;
    private String emailStr, nameStr, phoneStr;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
            final String code = credential.getSmsCode();
            if (code != null) {
                verifycode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(RegistrationActivity.this, "Verification Failed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(@NonNull String s,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {
            super.onCodeSent(s, token);
            verificationID = s;
            Toast.makeText(RegistrationActivity.this, "Code sent", Toast.LENGTH_SHORT).show();
            verifyBtn.setVisibility(View.VISIBLE);
            regMoveBtn.setVisibility(View.GONE);
            pinViewReg.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("users");
        mAuth = FirebaseAuth.getInstance();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxId = (long)snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        regMoveBtn = findViewById(R.id.btn_reg_move);
        verifyBtn = findViewById(R.id.btn_verify);
        pinViewReg = findViewById(R.id.pin_view);
        backImg = findViewById(R.id.img_back);
        phoneEditText = findViewById(R.id.phone_edit_text);
        nameEditText = findViewById(R.id.name_edit_text);
        emailEditText = findViewById(R.id.email_edit_text);

        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(pinViewReg.getText().toString())) {
                    Toast.makeText(RegistrationActivity.this, "Wrong OTP Entered", Toast.LENGTH_SHORT).show();
                } else
                    verifycode(pinViewReg.getText().toString());
            }
        });

        regMoveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(phoneEditText.getText().toString())) {
                    Toast.makeText(RegistrationActivity.this, "Enter Valid Phone No.", Toast.LENGTH_SHORT).show();
                } else {
                    String number = phoneEditText.getText().toString();
                    sendVerificationCode(number);
                }
            }
        });
    }

    private void sendVerificationCode(String phoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+7" + phoneNumber)  // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void verifycode(String Code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID, Code);
        signinbyCredentials(credential);
    }

    private void signinbyCredentials(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegistrationActivity.this, "Register Successful", Toast.LENGTH_SHORT).show();

                            identifyText();

                            user = new User();
                            user.setEmail(emailStr);
                            user.setName(nameStr);
                            user.setPhone(phoneStr);


                            myRef.child(firebaseAuth.getUid()).setValue(user);
                            startActivity(new Intent(RegistrationActivity.this, Main2Activity.class));
                        }

                    }
                });
    }

    private void identifyText(){
         emailStr = emailEditText.getText().toString().trim();
         nameStr = nameEditText.getText().toString().trim();
         phoneStr = "+7" + phoneEditText.getText().toString().trim();
    }
}