package com.example.jasper.manageexpense;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorTreeAdapter;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class History extends Fragment {
    ListView list;
    TabHistory_Week_Adapter adapter;
    List<TabHistory_Week_List> lists;

    public History()

    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tabhistory_week, container, false);
        list = (ListView) view.findViewById(R.id.listview_week);

        DBHelper db = new DBHelper(getContext());
        lists = db.getHistoryWeek();
        adapter = new TabHistory_Week_Adapter(getContext(), (ArrayList<TabHistory_Week_List>) lists);
        list.setAdapter(adapter);

        return view;
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getActivity().getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.editText) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}