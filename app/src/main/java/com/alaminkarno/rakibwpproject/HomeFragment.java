package com.alaminkarno.rakibwpproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class HomeFragment extends Fragment {

    EditText nameET,phoneET,counterET,destinationET;
    Spinner seatsSP;
    Button submitBTN;

    DatabaseHelper databaseHelper;

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        nameET = view.findViewById(R.id.nameET);
        phoneET = view.findViewById(R.id.phoneET);
        counterET = view.findViewById(R.id.counterET);
        destinationET = view.findViewById(R.id.destinationET);

        seatsSP = view.findViewById(R.id.seatsSP);
        submitBTN = view.findViewById(R.id.submitBTN);

        databaseHelper = new DatabaseHelper(getContext());


        submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = nameET.getText().toString();
                String phone = phoneET.getText().toString();
                String counter = counterET.getText().toString();
                String destination = destinationET.getText().toString();
                String seats = seatsSP.getSelectedItem().toString();


                if(name.isEmpty()){

                    Toast.makeText(getContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
                }
                else if(phone.isEmpty()){

                    Toast.makeText(getContext(), "Please enter your phone", Toast.LENGTH_SHORT).show();
                }
                else if(counter.isEmpty()){

                    Toast.makeText(getContext(), "Please enter counter name", Toast.LENGTH_SHORT).show();
                }
                else if(destination.isEmpty()){

                    Toast.makeText(getContext(), "Please enter your destination", Toast.LENGTH_SHORT).show();
                }
                else if(seats.equals("Select how many seats needed")){

                    Toast.makeText(getContext(), "Please enter how many seats needed", Toast.LENGTH_SHORT).show();
                }
                else {

                   long id = databaseHelper.insertData(name,phone,seats,counter,destination);

                   if(id < 0){
                       Toast.makeText(getContext(), "Data not inserted", Toast.LENGTH_SHORT).show();
                   }
                   else {
                       Toast.makeText(getContext(), "Data inserted and ID is "+id, Toast.LENGTH_SHORT).show();

                       nameET.setText("");
                       phoneET.setText("");
                       destinationET.setText("");
                       counterET.setText("");
                       seatsSP.setSelection(0);
                   }
                }
            }
        });

        return view;
    }
}