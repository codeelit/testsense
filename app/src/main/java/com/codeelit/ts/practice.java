package com.codeelit.ts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class practice extends AppCompatActivity {
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
    private  String rightAnswer;
    private RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
          msendButton=(Button)findViewById(R.id.button);
          mrecieveButton=(Button)findViewById(R.id.button2);
          submit=(Button)findViewById(R.id.submit);
          tv=(TextView) findViewById(R.id.question);
        rb1=(RadioButton) findViewById(R.id.option1);
        rb2=(RadioButton) findViewById(R.id.option2);
        rb3=(RadioButton) findViewById(R.id.option3);
        rb4=(RadioButton) findViewById(R.id.option4);
        rg=(RadioGroup)findViewById(R.id.radiog);
submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        int selectedId = rg.getCheckedRadioButtonId();
        rb = (RadioButton) findViewById(selectedId);
        if(rb.getText().equals(rightAnswer)){
            Toast.makeText(practice.this,"Correct Answer",Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(practice.this,"Wrong Answer",Toast.LENGTH_SHORT).show();


        }

    }
});
          mrecieveButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  DocumentReference contact=db.collection("practice1").document("question1");
                  contact.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                      @Override
                      public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                          DocumentSnapshot doc= task.getResult();
                          StringBuilder data=new StringBuilder("");
                          tv.setText(doc.getString("question"));
                          rb1.setText(doc.getString("optionA"));
                          rb2.setText(doc.getString("optionB"));
                          rb3.setText(doc.getString("optionC"));
                          rb4.setText(doc.getString("optionD"));
                          rightAnswer=doc.getString("correctAnswer");
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
    }
    private void writedata(){
        db=FirebaseFirestore.getInstance();
        Map<String,Object> newContact=new HashMap<>();
        newContact.put("question","How");
        newContact.put("optionA","some");
        newContact.put("optionB","thats how");
        newContact.put("optionC","like that");
        newContact.put("optionD","I see");
        newContact.put("correctAnswer","thats how");
        newContact.put("explain","because");


        db.collection("practice1").document("question1").set(newContact).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(practice.this,"Added question",Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Error",e.getMessage());
            }
        });
    }

    }

