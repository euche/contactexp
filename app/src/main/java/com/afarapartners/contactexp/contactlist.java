package com.afarapartners.contactexp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class contactlist extends RecyclerView.Adapter<contactlist.dcontactListviewHolder> {

    private Context c;
    private ArrayList<uContact> al ;


    public contactlist(Context c, ArrayList<uContact> al) {
        this.c = c;
        this.al = al;
    }



    public class dcontactListviewHolder extends RecyclerView.ViewHolder{

        TextView cname,cphonenumber;

        RadioButton selectTab;



        public dcontactListviewHolder( View itemView) {

            super(itemView);

            cname = itemView.findViewById(R.id.contact_name);
            cphonenumber = itemView.findViewById(R.id.contact_telephone);
            selectTab = itemView.findViewById(R.id.select_feature);

        }


    }



    @Override
    public contactlist.dcontactListviewHolder onCreateViewHolder( ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item,parent,false);
        return new contactlist.dcontactListviewHolder(v);


    }

    @Override
    public void onBindViewHolder( contactlist.dcontactListviewHolder holder, int position) {

        final uContact result = al.get(position);
        holder.cname.setText(result.getName());
        holder.cphonenumber.setText(result.getNumber());
        holder.selectTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(c, "Response "+result.getName()+" was selected ", Toast.LENGTH_LONG).show();
                Toast.makeText(c, "Response "+result.getNumber()+" was selected ", Toast.LENGTH_LONG).show();

                Log.e("showselect1",result.getName()+" "+result.getNumber());

                String a = result.getName();
                String b = result.getNumber();

                Intent i = new Intent(c,show_select_contact.class);
                i.putExtra("contactname",a);
                i.putExtra("contactphoneno",b);
                c.startActivity(i);





            }
        });




    }

    @Override
    public int getItemCount() {
        return al.size();
    }






}
