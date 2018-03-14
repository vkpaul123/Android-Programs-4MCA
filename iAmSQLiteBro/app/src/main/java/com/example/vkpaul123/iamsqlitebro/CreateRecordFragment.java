package com.example.vkpaul123.iamsqlitebro;

import android.content.ContentValues;
import android.content.Context;
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
import android.widget.Toast;

import static com.example.vkpaul123.iamsqlitebro.StudentContract.FeedEntry.col2;
import static com.example.vkpaul123.iamsqlitebro.StudentContract.FeedEntry.col3;
import static com.example.vkpaul123.iamsqlitebro.StudentContract.FeedEntry.tableName;

public class CreateRecordFragment extends Fragment {

    EditText studName;
    EditText studClass;
    Button btnIns;

    DBHelper dbHelper;

    String sName;
    String sClass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_record, container, false);

        dbHelper = new DBHelper(getContext());

        studName = (EditText) view.findViewById(R.id.editTextName);
        studClass = (EditText) view.findViewById(R.id.editTextClass);
        btnIns = (Button) view.findViewById(R.id.buttonInsert);

        btnIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sName = studName.getText().toString();
                sClass = studClass.getText().toString();

                SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put(col2, sName);
                contentValues.put(col3, sClass);

                long result = sqLiteDatabase.insert(tableName, null, contentValues);

                if(result == -1)
                    Toast.makeText(getContext(), "Data Not Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(),"DATA  INSERTED "+result,Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Create Student Record");
    }

    @Override
    public void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
