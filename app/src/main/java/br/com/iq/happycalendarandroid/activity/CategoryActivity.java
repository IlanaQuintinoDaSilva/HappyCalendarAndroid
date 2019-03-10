package br.com.iq.happycalendarandroid.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import br.com.iq.happycalendarandroid.R;
import br.com.iq.happycalendarandroid.data.DatabaseHelper;
import br.com.iq.happycalendarandroid.data.TodosContract;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        //CreateTodo();
        //CreateCategory();
        readDataCategory();
        readData();
    }

    private void CreateTodo(){
        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        /*String query = "INSERT INTO todos ("
                + TodosContract.TodosEntry.COLUMN_TEXT + ","
                + TodosContract.TodosEntry.COLUMN_CATEGORY + ","
                + TodosContract.TodosEntry.COLUMN_CREATED + ","
                + TodosContract.TodosEntry.COLUMN_EXPIRED + ","
                + TodosContract.TodosEntry.COLUMN_DONE + ")"
                + " VALUES (\"Go to the gym\", 1, \"2016-01-01\", \"\", 0";
        db.execSQL(query);*/

        ContentValues values = new ContentValues();
        values.put(TodosContract.TodosEntry.COLUMN_TEXT, "Testando");
        values.put(TodosContract.TodosEntry.COLUMN_CATEGORY, "Família");
        values.put(TodosContract.TodosEntry.COLUMN_SPRINT, "2016-01-02");
        values.put(TodosContract.TodosEntry.COLUMN_DONE, 0);
        long todo_id = db.insert(TodosContract.TodosEntry.TABLE_NAME, null, values);

        todo_id = db.insert(TodosContract.TodosEntry.TABLE_NAME, null, values);
    }

    private void readData(){
        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] projection = {TodosContract.TodosEntry.COLUMN_TEXT,
                TodosContract.TodosEntry.COLUMN_SPRINT,
                TodosContract.TodosEntry.COLUMN_DONE,
                TodosContract.TodosEntry.COLUMN_CATEGORY};
        //String selection = TodosContract.TodosEntry.COLUMN_CATEGORY + " = ?";
        //String[] selectionArgs = {"1"};
        Cursor c = db.query(TodosContract.TodosEntry.TABLE_NAME,
                projection, null, null, null, null, null);
        int i = c.getCount();
        Log.d("Record Count", String.valueOf(i));

        String rowContent = "";
        while (c.moveToNext()){
            for(i=0; i<4; i++){
                rowContent += c.getString(i) + " - ";
            }
            Log.i("Todo Row " + String.valueOf(c.getPosition()), rowContent);
            rowContent = "";
        }
        c.close();
    }

    private void CreateCategory(){
        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(TodosContract.CategoriesEntry.COLUMN_DESCRIPTION, "Casa");
        long todo_id = db.insert(TodosContract.CategoriesEntry.TABLE_NAME, null, values);

    }

    private void readDataCategory(){
        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] projection = {TodosContract.CategoriesEntry.COLUMN_DESCRIPTION};
        String selection = TodosContract.TodosEntry.COLUMN_CATEGORY + " = ?";
        String[] selectionArgs = {"1"};
        Cursor c = db.query(TodosContract.CategoriesEntry.TABLE_NAME,
                projection, null, null, null, null, null);
        int i = c.getCount();
        Log.d("Categories Count", String.valueOf(i));
        String rowContent = "";
        while (c.moveToNext()){
            rowContent += c.getString(0) + " - ";
            Log.i("Category Row " + String.valueOf(c.getPosition()), rowContent);
            rowContent = "";
        }
        c.close();
    }


}
