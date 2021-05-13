package edu.co.icesi.ecos14;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    FirebaseDatabase db;
    private ListView contactList;
    private ArrayList<Contact> contacts;
    private ArrayAdapter<Contact> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactList = findViewById(R.id.contactList);
        contacts = new ArrayList<>();
        adapter = new ArrayAdapter<>(this,
                                            android.R.layout.simple_list_item_1,
                                            contacts);
        contactList.setAdapter(adapter);



        db = FirebaseDatabase.getInstance();

        //Desde el principio
        loadContacts();
    }

    public void loadContacts(){
        db.getReference().child("contactos").addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot data) {
                        for(DataSnapshot child : data.getChildren()){
                            Contact contact = child.getValue(Contact.class);
                            Log.e(">>>",contact.getNombre());
                            contacts.add(contact);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                }
        );
    }

}