package br.com.iq.happycalendarandroid.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "todosapp.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_CATEGORIES_CREATE=
            "CREATE TABLE " + TodosContract.CategoriesEntry.TABLE_NAME + " (" +
                    TodosContract.CategoriesEntry._ID + " INTEGER PRIMARY KEY, " +
                    TodosContract.CategoriesEntry.COLUMN_DESCRIPTION + " TEXT " +
                    ")";
    private static final String TABLE_TODOS_CREATE =
            "CREATE TABLE " + TodosContract.TodosEntry.TABLE_NAME + " (" +
                    TodosContract.TodosEntry._ID + " INTEGER PRIMARY KEY, " +
                    TodosContract.TodosEntry.COLUMN_TEXT + " TEXT, " +
                    TodosContract.TodosEntry.COLUMN_SPRINT + " TEXT default CURRENT_TIMESTAMP, " +
                    TodosContract.TodosEntry.COLUMN_DONE + " INTEGER, " +
                    TodosContract.TodosEntry.COLUMN_CATEGORY + " TEXT " + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CATEGORIES_CREATE);
        db.execSQL(TABLE_TODOS_CREATE);
        ContentValues values = new ContentValues();
        values.put (TodosContract.CategoriesEntry.COLUMN_DESCRIPTION, "Work");
        long idCat = db.insert(TodosContract.CategoriesEntry.TABLE_NAME, null, values);
        values.clear();
        values.put(TodosContract.TodosEntry.COLUMN_CATEGORY, String.valueOf(idCat));
        values.put(TodosContract.TodosEntry.COLUMN_TEXT, "Welcome to Todos!");
        long idTodo = db.insert(TodosContract.TodosEntry.TABLE_NAME, null, values);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TodosContract.TodosEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TodosContract.CategoriesEntry.TABLE_NAME);
        onCreate(db);
    }
}
