package edu.co.icesi.ecos14;

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

import edu.co.icesi.ecos14.model.Contact;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db;
    private ListView contactList;
    private ContactAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactList = findViewById(R.id.contactList);

        adapter = new ContactAdapter();
        contactList.setAdapter(adapter);



        db = FirebaseDatabase.getInstance();

        //Desde el principio
        loadContacts();
    }

    //Este método carga la información de firebase y arma los elementos visual para pasarlos al ListView
    public void loadContacts(){
        db.getReference().child("contactos").addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot data) {

                        adapter.clear();
                        for(DataSnapshot child : data.getChildren()){
                            Contact contact = child.getValue(Contact.class);
                            adapter.addContact(contact);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                }
        );
    }

}