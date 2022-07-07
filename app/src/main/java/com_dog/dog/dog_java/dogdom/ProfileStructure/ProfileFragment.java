package com_dog.dog.dog_java.dogdom.ProfileStructure;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import com_dog.dog.dog_java.dogdom.Auth.LoginActivity;
import com_dog.dog.dog_java.dogdom.Modules.ItemFAQ;
import com_dog.dog.dog_java.dogdom.R;

public class ProfileFragment extends Fragment implements BottomSheetDialogLogOutFragment.ItemClickListener{

    private ArrayList<ItemFAQ> arrayFAQ;
    private RecyclerView rvFAQ;
    private TextView nameText, phoneText, emailText;
    private AdapterFAQ adapterFAQ;
    private FloatingActionButton logoutBtn;
    private MaterialButton feedbackBtn, changeBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        logoutBtn = view.findViewById(R.id.btn_logout);
        emailText = view.findViewById(R.id.text_email);
        changeBtn = view.findViewById(R.id.btn_change);
        nameText = view.findViewById(R.id.text_name);
        phoneText = view.findViewById(R.id.text_phone_num);
        rvFAQ = view.findViewById(R.id.rv_faq);
        feedbackBtn = view.findViewById(R.id.btn_feedback);

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        buildRecyclerViewFAQ();
        setDataFAQ();

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersRef = rootRef.child("users");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = (String) snapshot.child(currentFirebaseUser.getUid()).child("name").getValue();
                String phone = (String) snapshot.child(currentFirebaseUser.getUid()).child("phone").getValue();
                String email = (String) snapshot.child(currentFirebaseUser.getUid()).child("email").getValue();
                nameText.setText(String.valueOf(name));
                emailText.setText(String.valueOf(email));
                phoneText.setText(String.valueOf(phone));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        usersRef.addValueEventListener(valueEventListener);

        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ChangeDataActivity.class));
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheet(view);
            }
        });

        feedbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                String UriText = "mailto:" +
                        Uri.encode("freshart666@gmail.com") + "?subject=" +
                        Uri.encode("Feedback") + "=" + Uri.encode("");
                Uri uri = Uri.parse(UriText);
                intent.setData(uri);
                startActivity(Intent.createChooser(intent, "send email"));
            }
        });

        return view;
    }

    private void buildRecyclerViewFAQ() {
        rvFAQ.setHasFixedSize(true);
        rvFAQ.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void showBottomSheet(View view) {
        BottomSheetDialogLogOutFragment addPhotoBottomDialogFragment =
                BottomSheetDialogLogOutFragment.newInstance();
        addPhotoBottomDialogFragment.show(getFragmentManager(),
                BottomSheetDialogLogOutFragment.TAG);
    }



    private void setDataFAQ() {
        arrayFAQ = new ArrayList<>();
        arrayFAQ.add(new ItemFAQ("В чем суть вашего проекта, как вы собираетесь его продвигать?", "Ну попробуй догадаться, если умеешь думать, значит до тебя допрет, что к чему, придурь..."));
        arrayFAQ.add(new ItemFAQ("В чем суть вашего проекта, как вы собираетесь его продвигать?", "Ну попробуй догадаться, если умеешь думать, значит до тебя допрет, что к чему, придурь..."));
        arrayFAQ.add(new ItemFAQ("В чем суть вашего проекта, как вы собираетесь его продвигать?", "Ну попробуй догадаться, если умеешь думать, значит до тебя допрет, что к чему, придурь..."));
        arrayFAQ.add(new ItemFAQ("В чем суть вашего проекта, как вы собираетесь его продвигать?", "Ну попробуй догадаться, если умеешь думать, значит до тебя допрет, что к чему, придурь..."));
        arrayFAQ.add(new ItemFAQ("В чем суть вашего проекта, как вы собираетесь его продвигать?", "Ну попробуй догадаться, если умеешь думать, значит до тебя допрет, что к чему, придурь..."));
        adapterFAQ = new AdapterFAQ(arrayFAQ);
        rvFAQ.setAdapter(adapterFAQ);
    }

    @Override
    public void onItemClick(String item) {
    }
}