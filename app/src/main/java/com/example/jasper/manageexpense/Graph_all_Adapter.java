package com.example.jasper.manageexpense;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;


public class Graph_all_Adapter extends BaseAdapter {
    Context context;
    private List<Graph_all_List> lists;


    public Graph_all_Adapter (Context context, List<Graph_all_List> lists){
        this.context = context;
        this.lists = lists;

    }


    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lists.get(position).getId();
    }

    public double getTotal() {
        DBHelper db = new DBHelper(this.context);
        SQLiteDatabase sql = db.getReadableDatabase();
        String query = "SELECT SUM(amount) AS total FROM Add_Expense";
        Cursor c = sql.rawQuery(query, null);
        c.moveToFirst();
        double a=c.getInt(0);

        //DecimalFormat precision = new DecimalFormat("0.00 Rs");
        //total.setText(precision.format(c.getInt(0)));
        return a;
    }

    @Override
    public View getView(final int position, View itemView, ViewGroup parent) {

        View view = View.inflate(context, R.layout.piegraph_list, null);

        TextView txtName = (TextView)view.findViewById(R.id.piegraph_name);
        TextView txtAmount = (TextView) view.findViewById(R.id.piegraph_amount);
        TextView txtPercent = (TextView) view.findViewById(R.id.piegraph_percent);

        txtName.setText(lists.get(position).getName());
        txtAmount.setText(String.valueOf(lists.get(position).getAmount()));
        if(!txtAmount.toString().equals("")){
            double amount = Double.parseDouble(txtAmount.getText().toString());
            double totalammount=getTotal();
            double res = ((amount*100.0)/totalammount);
            txtPercent.setText(String.format("%.2f",res)+"%");
        }else {

        }

        return view;
    }


}
