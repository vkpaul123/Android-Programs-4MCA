package com.example.vkpaul123.iamsqlitebro;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.vkpaul123.iamsqlitebro.StudentContract.FeedEntry.col1;
import static com.example.vkpaul123.iamsqlitebro.StudentContract.FeedEntry.col2;
import static com.example.vkpaul123.iamsqlitebro.StudentContract.FeedEntry.col3;
import static com.example.vkpaul123.iamsqlitebro.StudentContract.FeedEntry.tableName;

public class RetrieveRecordFragment extends Fragment {

    EditText studRegNo;
    Button retBtn, retAll;

    DBHelper dbHelper;

    TextView ssRegNo;
    TextView ssName;
    TextView ssClass;

    String sRegNo;

    ArrayList<String> results;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_retrieve_record, container, false);

        studRegNo = (EditText) view.findViewById(R.id.editTextRegNoFind);
        retBtn = (Button) view.findViewById(R.id.buttonRetrieve);
        dbHelper = new DBHelper(getContext());
        retAll = (Button) view.findViewById(R.id.buttonRetrieveAll);

        ssRegNo = (TextView) view.findViewById(R.id.textViewRegNo);
        ssName = (TextView) view.findViewById(R.id.textViewName);
        ssClass = (TextView) view.findViewById(R.id.textViewClass);


        results = new ArrayList<>();

        retBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sRegNo = studRegNo.getText().toString();

                SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
                Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + tableName + " WHERE " + col1 + "=" + sRegNo, null);

                if(cursor != null) {
                    if(cursor.moveToFirst()) {
                        ssRegNo.setText(cursor.getString(cursor.getColumnIndex(col1)));
                        ssName.setText(cursor.getString(cursor.getColumnIndex(col2)));
                        ssClass.setText(cursor.getString(cursor.getColumnIndex(col3)));
                    }
                }
            }
        });

        retAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
                Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + tableName, null);

                String allRec = "";

                if(cursor != null) {
                    if(cursor.moveToFirst()) {
                        do {
                            allRec += cursor.getString(cursor.getColumnIndex(col1));
                            allRec += "\n";
                            allRec += cursor.getString(cursor.getColumnIndex(col2));
                            allRec += "\n";
                            allRec += cursor.getString(cursor.getColumnIndex(col3));
                            allRec += "\n\n";

                        } while ((cursor.moveToNext()));
                    }
                }

                Toast.makeText(getContext(), allRec, Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Retrieve Record");
    }

    @Override
    public void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
