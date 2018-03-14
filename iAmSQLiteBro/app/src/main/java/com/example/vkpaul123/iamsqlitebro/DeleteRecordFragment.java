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

public class DeleteRecordFragment extends Fragment {

    EditText studRegNo;
    Button retBtn;
    Button delBtn;

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
        View view = inflater.inflate(R.layout.fragment_delete_record, container, false);

        studRegNo = (EditText) view.findViewById(R.id.editTextRegNoFindToDelete);
        retBtn = (Button) view.findViewById(R.id.buttonFindToDelete);
        delBtn = (Button) view.findViewById(R.id.buttonDelte);
        dbHelper = new DBHelper(getContext());

        ssRegNo = (TextView) view.findViewById(R.id.textViewRegNoDelete);
        ssName = (TextView) view.findViewById(R.id.textViewNameDelete);
        ssClass = (TextView) view.findViewById(R.id.textViewClassDelete);

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

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sRegNo = studRegNo.getText().toString();

                SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

                int result = sqLiteDatabase.delete(tableName, col1 + "=" + sRegNo, null);

                if(result == -1)
                    Toast.makeText(getContext(), "Delete Failed!", Toast.LENGTH_LONG).show();
                else {
                    ssRegNo.setText("");
                    ssName.setText("");
                    ssClass.setText("");
                    studRegNo.setText("");
                    Toast.makeText(getContext(), "Delete Success. " + result, Toast.LENGTH_LONG).show();
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
