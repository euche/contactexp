package com.afarapartners.contactexp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Button donebutton;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 100 ;
    RecyclerView contactlist;
    private ArrayList<uContact> arrayList = new ArrayList<>();
    private contactlist contactsid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        donebutton = findViewById(R.id. button1);
        contactlist = findViewById(R.id.my_contacts_list);
        contactlist.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        contactlist.setHasFixedSize(true);
        contactsid = new contactlist(MainActivity.this,arrayList);

        permisssionsRequest();
        loadcontactlist();

    }



    private void permisssionsRequest() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if(checkSelfPermission(Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED){



            }else{

                if(shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)){

                    Toast.makeText(getApplicationContext(), "Permisssion to access Contacts is needed. ", Toast.LENGTH_LONG).show();

                }else{

                    requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},MY_PERMISSIONS_REQUEST_READ_CONTACTS);


                }




            }
        }




    }



    private void loadcontactlist() {



//ContactsContract.Contacts.CONTENT_URI

//ContactsContract.CommonDataKinds.Phone.NUMBER

        Cursor contactcursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);





        if(contactcursor.getCount() > 0){


            while(contactcursor.moveToNext()){


                String id = contactcursor.getString(contactcursor.getColumnIndex(ContactsContract.Contacts._ID));

                String name = contactcursor.getString(contactcursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                String number = contactcursor.getString(contactcursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));


//                ContactsContract.Data.CONTENT_URI
//                Cursor c = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID+ "=?",new String[] {id},null);


                if(number.length()> 0){

                    Cursor c = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID+ "=?",new String[] {id},null);


                    if(c.getCount()>0){


                        while(c.moveToNext()){


                            String phonenumberValue = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));


                            uContact getContactuser = new uContact(name,phonenumberValue);

                            arrayList.add(getContactuser);



                        }



                    }


                    c.close();


                }




            }


/////////////work to done
            contactlist.setAdapter(contactsid);

        }else{


            Toast.makeText(getApplicationContext(), "No Contacts Found ", Toast.LENGTH_LONG).show();


        }


       contactcursor.close();

    }





    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == MY_PERMISSIONS_REQUEST_READ_CONTACTS){

            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                loadcontactlist();

            }else{

//                movetoupdatecircledetails();



            }

        }


    }

}
