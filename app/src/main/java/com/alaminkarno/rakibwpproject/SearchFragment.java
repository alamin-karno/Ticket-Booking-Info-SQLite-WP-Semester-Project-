package com.alaminkarno.rakibwpproject;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchFragment extends Fragment {

    TextView nameTV,phoneTV,seatsTV,counterTV,destinationTV;
    EditText search_ID;
    Button searchBTN;
    DatabaseHelper databaseHelper;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        nameTV = view.findViewById(R.id.nameTV);
        phoneTV = view.findViewById(R.id.phoneTV);
        seatsTV = view.findViewById(R.id.seatsTv);
        counterTV = view.findViewById(R.id.counterTV);
        destinationTV = view.findViewById(R.id.destinationTV);
        search_ID = view.findViewById(R.id.search_idET);
        searchBTN = view.findViewById(R.id.searchBTN);

        databaseHelper = new DatabaseHelper(getContext());

        searchBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = search_ID.getText().toString();

                if(id.isEmpty()){

                    Toast.makeText(getContext(), "Enter ID for search", Toast.LENGTH_SHORT).show();
                }
                else {

                   Cursor cursor = databaseHelper.searchData(Integer.parseInt(id));

                   if(cursor.getCount() <= 0){

                       Toast.makeText(getContext(), "No data in this ID", Toast.LENGTH_SHORT).show();

                       nameTV.setText("No Data Added");
                       phoneTV.setText("No Data Added");
                       seatsTV.setText("No Data Added");
                       counterTV.setText("No Data Added");
                       destinationTV.setText("No Data Added");
                   }
                   else {
                       while (cursor.moveToNext()){

                           String name = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_NAME));
                           String phone = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_PHONE));
                           String seats = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_SEATS));
                           String counter = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_COUNTER));
                           String destination = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_Destination));

                           nameTV.setText("Name: "+name);
                           phoneTV.setText("Phone: "+phone);
                           seatsTV.setText("Total Seats: "+seats);
                           counterTV.setText("Counter Name: "+counter);
                           destinationTV.setText("Destination: "+destination);

                       }
                   }
                }
            }
        });

        return view;
    }
}