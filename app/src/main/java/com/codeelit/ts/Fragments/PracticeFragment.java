package com.codeelit.ts.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.codeelit.ts.R;
import com.codeelit.ts.practice;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class PracticeFragment extends Fragment {

    private Button msendButton;
    private Button mrecieveButton;
    private Button submit;
    private FirebaseFirestore db;
    private TextView tv;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private RadioButton rb;
    private String rightAnswer;
    private RadioGroup rg;

    public PracticeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_practice, container, false);

        msendButton = (Button) view.findViewById(R.id.button);
        mrecieveButton = (Button) view.findViewById(R.id.button2);
        submit = (Button) view.findViewById(R.id.submit);
        tv = (TextView) view.findViewById(R.id.question);
        rb1 = (RadioButton) view.findViewById(R.id.option1);
        rb2 = (RadioButton) view.findViewById(R.id.option2);
        rb3 = (RadioButton) view.findViewById(R.id.option3);
        rb4 = (RadioButton) view.findViewById(R.id.option4);
        rg = (RadioGroup) view.findViewById(R.id.radiog);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg.getCheckedRadioButtonId();
                rb = (RadioButton) view.findViewById(selectedId);
                if (rb.getText().equals(rightAnswer)) {


                } else {



                }

            }
        });
        mrecieveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference contact = db.collection("practice1").document("question1");
                contact.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot doc = task.getResult();
                        StringBuilder data = new StringBuilder("");
                        tv.setText(doc.getString("question"));
                        rb1.setText(doc.getString("optionA"));
                        rb2.setText(doc.getString("optionB"));
                        rb3.setText(doc.getString("optionC"));
                        rb4.setText(doc.getString("optionD"));
                        rightAnswer = doc.getString("correctAnswer");
                    }
                });
            }
        });
        msendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writedata();

            }
        });


        return view;
    }

    private void writedata() {
        db = FirebaseFirestore.getInstance();
        Map<String, Object> newContact = new HashMap<>();
        newContact.put("question", "How");
        newContact.put("optionA", "some");
        newContact.put("optionB", "thats how");
        newContact.put("optionC", "like that");
        newContact.put("optionD", "I see");
        newContact.put("correctAnswer", "thats how");
        newContact.put("explain", "because");


        db.collection("practice1").document("question1").set(newContact).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Error", e.getMessage());
            }
        });
    }

}
