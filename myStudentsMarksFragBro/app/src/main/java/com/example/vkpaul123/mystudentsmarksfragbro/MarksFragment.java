package com.example.vkpaul123.mystudentsmarksfragbro;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.vkpaul123.mystudentsmarksfragbro.StudentContract.FeedEntry.col1;
import static com.example.vkpaul123.mystudentsmarksfragbro.StudentContract.FeedEntry.col2;
import static com.example.vkpaul123.mystudentsmarksfragbro.StudentContract.FeedEntry.col4;
import static com.example.vkpaul123.mystudentsmarksfragbro.StudentContract.FeedEntry.col5;
import static com.example.vkpaul123.mystudentsmarksfragbro.StudentContract.FeedEntry.col6;
import static com.example.vkpaul123.mystudentsmarksfragbro.StudentContract.FeedEntry.col7;
import static com.example.vkpaul123.mystudentsmarksfragbro.StudentContract.FeedEntry.col8;
import static com.example.vkpaul123.mystudentsmarksfragbro.StudentContract.FeedEntry.col9;
import static com.example.vkpaul123.mystudentsmarksfragbro.StudentContract.FeedEntry.tableName;


public class MarksFragment extends Fragment {
    DBHelper dbHelper;

    TextView sName, sSub1, sSub2, sSub3, sSub4, sTotal, sPerc;

    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_marks, container, false);
        dbHelper = new DBHelper(getContext());

        sName = view.findViewById(R.id.textViewStudName);
        sSub1 = view.findViewById(R.id.textViewSub1);
        sSub2 = view.findViewById(R.id.textViewSub2);
        sSub3 = view.findViewById(R.id.textViewSub3);
        sSub4 = view.findViewById(R.id.textViewSub4);
        sTotal = view.findViewById(R.id.textViewSubTotal);
        sPerc = view.findViewById(R.id.textViewSubPercentage);


        String sRegNo = getArguments().getString("studID");
        Toast.makeText(getActivity().getApplicationContext(), sRegNo, Toast.LENGTH_LONG).show();

        if(sRegNo.length() != 0) {
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + tableName + " WHERE " + col1 + "=" + sRegNo, null);

            if(cursor != null) {
                if(cursor.moveToFirst()) {
                    sName.setText(cursor.getString(cursor.getColumnIndex(col2)));
                    sSub1.setText(cursor.getString(cursor.getColumnIndex(col4)));
                    sSub2.setText(cursor.getString(cursor.getColumnIndex(col5)));
                    sSub3.setText(cursor.getString(cursor.getColumnIndex(col6)));
                    sSub4.setText(cursor.getString(cursor.getColumnIndex(col7)));
                    sTotal.setText(cursor.getString(cursor.getColumnIndex(col8)) + " /400");
                    sPerc.setText(cursor.getString(cursor.getColumnIndex(col9)) + " %");
                }
            }
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.buttonBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


                RegisterFragment blankFragment = new RegisterFragment();

                fragmentTransaction.replace(R.id.fragment_container, blankFragment);
                fragmentTransaction.commit();
            }
        });
    }
}
