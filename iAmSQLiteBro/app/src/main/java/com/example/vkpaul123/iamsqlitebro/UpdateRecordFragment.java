package com.example.vkpaul123.iamsqlitebro;

import android.content.ContentValues;
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
import android.widget.Toast;

import static com.example.vkpaul123.iamsqlitebro.StudentContract.FeedEntry.col1;
import static com.example.vkpaul123.iamsqlitebro.StudentContract.FeedEntry.col2;
import static com.example.vkpaul123.iamsqlitebro.StudentContract.FeedEntry.col3;
import static com.example.vkpaul123.iamsqlitebro.StudentContract.FeedEntry.tableName;

public class UpdateRecordFragment extends Fragment {

    EditText studRegNo;

    EditText studName;
    EditText studClass;
    Button btnFind, btnUpdate;

    DBHelper dbHelper;

    String sRegNo, ssRegNo;
    String sName, ssName;
    String sClass, ssClass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_record, container, false);

        studRegNo = (EditText) view.findViewById(R.id.editTextRegNoFindUpdate);
        btnFind = (Button) view.findViewById(R.id.buttonFindToUpdate);

        studName = (EditText) view.findViewById(R.id.editTextNameUpdate);
        studClass = (EditText) view.findViewById(R.id.editTextClassUpdate);

        btnUpdate = (Button) view.findViewById(R.id.buttonUpdate);

        dbHelper = new DBHelper(getContext());

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sRegNo = studRegNo.getText().toString();

                SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
                Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + tableName + " WHERE " + col1 + "=" + sRegNo, null);

                if(cursor != null) {
                    if(cursor.moveToFirst()) {
                        studName.setText(cursor.getString(cursor.getColumnIndex(col2)));
                        studClass.setText(cursor.getString(cursor.getColumnIndex(col3)));
                    }
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sRegNo = studRegNo.getText().toString();
                ssName = studName.getText().toString();
                ssClass = studClass.getText().toString();

                SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put(col2, ssName);
                contentValues.put(col3, ssClass);

                int result = sqLiteDatabase.update(tableName, contentValues, col1+" = " + sRegNo, null);

                if(result == -1) {
                    Toast.makeText(getContext(), "Update Failed.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "Update Success."+result, Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Update Record");
    }

    @Override
    public void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
