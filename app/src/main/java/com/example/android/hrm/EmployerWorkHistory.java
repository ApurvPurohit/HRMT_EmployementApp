package com.example.android.hrm;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android.hrm.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EmployerWorkHistory extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("Employer_Work_History");
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        String userid=user.getUid();
        Query checkUser = reference.child(userid);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    final TextView[] tv = new TextView[(Integer.parseInt(String.valueOf(snapshot.getChildrenCount())))];
                    int k = 0;
                    int t=1;
                    final LinearLayout rl = findViewById(R.id.linear_layout);
                    for (DataSnapshot data : snapshot.getChildren()) {
                        Employer_requirement_send user = data.getValue(Employer_requirement_send.class);
                        tv[k] = new TextView(getApplicationContext());
                        assert user != null;
                        tv[k].setText((t++)+".\nकाम: " + user.getJob() + "\nविवरण: " + user.getJob_desp() + "\nश्रमिकों की संख्या: " + user.getNlab() + "\nदिनों की संख्या: " + user.getNdays());
                        tv[k].setTextSize((float) 20);
                        tv[k].setBackgroundColor(Color.parseColor("#f8fcee"));
                        tv[k].setPadding(20, 20, 20, 20);
                        rl.addView(tv[k]);
                        TextView border = new TextView(getApplicationContext());
                        border.setText("\n");
                        border.setBackgroundColor(Color.parseColor("#4FB5E6"));
                        rl.addView(border);
                        k++;
                    }
                } else {
                    final LinearLayout rl = findViewById(R.id.linear_layout);
                    TextView tv = new TextView(getApplicationContext());
                    tv.setText("कोई इतिहास नहीं मिला");
                    tv.setTextSize((float) 20);
                    tv.setPadding(20, 20, 20, 20);
                    rl.addView(tv);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        findViewById(R.id.button222).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
