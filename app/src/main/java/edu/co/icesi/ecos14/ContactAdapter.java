package edu.co.icesi.ecos14;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import edu.co.icesi.ecos14.model.Contact;

//El adaptador se encarga de representar gráficamente los contactos según
//el diseño creado
public class ContactAdapter extends BaseAdapter {

    //La información que va a ser mostrada
    private ArrayList<Contact> contacts;


    public ContactAdapter(){
        contacts = new ArrayList<>();
    }


    //Es un metodo que permite agregar un contacto al arreglo
    public void addContact(Contact contact){
        contacts.add(contact);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    //3 datos, por lo tanto, 3 veces se ejecuta el getView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Contact -> View ()
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //XML-> View
        View item = inflater.inflate(R.layout.contact, null);

        //Referenciar
        TextView nameContact = item.findViewById(R.id.nameContact);
        TextView telContact = item.findViewById(R.id.telContact);

        nameContact.setText( contacts.get(position).getNombre() );
        telContact.setText( contacts.get(position).getTelefono() );



        return item;
    }

    public void clear() {
        contacts.clear();
        notifyDataSetChanged();
    }
}
