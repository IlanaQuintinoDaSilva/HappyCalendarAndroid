/*import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.com.iq.happycalendarandroid.data.TodosContract

package br.com.iq.happycalendarandroid.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelperTmp(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(TABLE_CATEGORIES_CREATE)
        db.execSQL(TABLE_TODOS_CREATE)
        val values = ContentValues()
        values.put(TodosContract.CategoriesEntry.COLUMN_DESCRIPTION, "Work")
        val idCat = db.insert(TodosContract.CategoriesEntry.TABLE_NAME, null, values)
        values.clear()
        values.put(TodosContract.TodosEntry.COLUMN_CATEGORY, idCat.toString())
        values.put(TodosContract.TodosEntry.COLUMN_TEXT, "Welcome to Todos!")
        val idTodo = db.insert(TodosContract.TodosEntry.TABLE_NAME, null, values)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TodosContract.TodosEntry.TABLE_NAME)
        db.execSQL("DROP TABLE IF EXISTS " + TodosContract.CategoriesEntry.TABLE_NAME)
        onCreate(db)
    }

    companion object {

        private val DATABASE_NAME = "todosapp.db"
        private val DATABASE_VERSION = 1
        private val TABLE_CATEGORIES_CREATE = "CREATE TABLE " + TodosContract.CategoriesEntry.TABLE_NAME + " (" +
                TodosContract.CategoriesEntry._ID + " INTEGER PRIMARY KEY, " +
                TodosContract.CategoriesEntry.COLUMN_DESCRIPTION + " TEXT " +
                ")"
        private val TABLE_TODOS_CREATE = "CREATE TABLE " + TodosContract.TodosEntry.TABLE_NAME + " (" +
                TodosContract.TodosEntry._ID + " INTEGER PRIMARY KEY, " +
                TodosContract.TodosEntry.COLUMN_TEXT + " TEXT, " +
                TodosContract.TodosEntry.COLUMN_CREATED + " TEXT default CURRENT_TIMESTAMP, " +
                TodosContract.TodosEntry.COLUMN_EXPIRED + " TEXT, " +
                TodosContract.TodosEntry.COLUMN_DONE + " INTEGER, " +
                TodosContract.TodosEntry.COLUMN_CATEGORY + " INTEGER, " +
                " FOREIGN KEY(" + TodosContract.TodosEntry.COLUMN_CATEGORY + ") REFERENCES " +
                TodosContract.CategoriesEntry.TABLE_NAME +
                "(" + TodosContract.CategoriesEntry._ID + ") " + ")"
    }
}
*/