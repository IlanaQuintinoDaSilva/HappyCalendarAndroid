package br.com.iq.happycalendarandroid.domain.api

import android.content.ContentValues
import android.util.Log
import br.com.iq.happycalendarandroid.HappyCalendarApplication
import br.com.iq.happycalendarandroid.R.string.category
import br.com.iq.happycalendarandroid.data.DatabaseHelper
import br.com.iq.happycalendarandroid.data.TodosContract
import br.com.iq.happycalendarandroid.domain.Category
import br.com.iq.happycalendarandroid.domain.ToDo
import br.com.iq.happycalendarandroid.utils.DateUtil
import java.util.*


class ToDoService{

    private var helper: DatabaseHelper? = null

    fun getToDos(helper: DatabaseHelper, isBacklog: String):List<ToDo> {
        val todos = mutableListOf<ToDo>()
        val db = helper.readableDatabase
        val projection = arrayOf(TodosContract.TodosEntry.COLUMN_TEXT, TodosContract.TodosEntry.COLUMN_CATEGORY,
                TodosContract.TodosEntry._ID, TodosContract.TodosEntry.COLUMN_BACKLOG, TodosContract.TodosEntry.COLUMN_DONE)
        val selection =  TodosContract.TodosEntry.COLUMN_BACKLOG + " = ?"
        val selectionArgs = arrayOf(isBacklog)
        val c = db.query(TodosContract.TodosEntry.TABLE_NAME,
                projection, selection, selectionArgs, null, null, null)
        val i = c.count
        while (c.moveToNext()) {
            var td = ToDo()
            //if(c.getLong(4).equals(0)){
                td.description = c.getString(0)
                td.category = c.getString(1)
                td.id = c.getLong(2)
                td.backlog = c.getLong(3)
                //td.done = c.getInt(4)
                todos.add(td)
            //}
        }
        c.close()
        return todos
    }

    fun addToDo(description: String, category: String, helper: DatabaseHelper){
        var db = helper.writableDatabase
        var values = ContentValues().apply {
            put(TodosContract.TodosEntry.COLUMN_TEXT, description)
            put(TodosContract.TodosEntry.COLUMN_CATEGORY, category)
            put(TodosContract.TodosEntry.COLUMN_DONE, 0)
            put(TodosContract.TodosEntry.COLUMN_BACKLOG, 1)
        }

        val newRowId = db?.insert(TodosContract.TodosEntry.TABLE_NAME, null, values)

        db?.close()

    }

    fun updateDone(id: Long) {
        helper = HappyCalendarApplication.dbHelper
        var db = helper?.writableDatabase
        var values = ContentValues().apply {
            put(TodosContract.TodosEntry.COLUMN_DONE, 1)
        }
        val selectionArgs = arrayOf(id.toString())
        var numRows = db?.update(TodosContract.TodosEntry.TABLE_NAME,values,
                TodosContract.TodosEntry._ID + "=?", selectionArgs)

        //int id = 2;
        //String[] args = {String.valueOf(id)};
        //ContentValues values = new ContentValues();
        //values.put(TodosContract.TodosEntry.COLUMN_TEXT, "Call Mr Clark Kent");
        //int numRows = getContentResolver().update(TodosContract.TodosEntry.CONTENT_URI, values,
        //TodosContract.TodosEntry._ID + "=?", args);
        //Log.d("Update Rows ", String.valueOf(numRows));
    }

    fun updateBacklog(id: Long) {
        helper = HappyCalendarApplication.dbHelper
        var db = helper?.writableDatabase
        var values = ContentValues().apply {
            put(TodosContract.TodosEntry.COLUMN_BACKLOG, 0)
        }
        val selectionArgs = arrayOf(id.toString())
        var numRows = db?.update(TodosContract.TodosEntry.TABLE_NAME,values,
                TodosContract.TodosEntry._ID + "=?", selectionArgs)

        //int id = 2;
        //String[] args = {String.valueOf(id)};
        //ContentValues values = new ContentValues();
        //values.put(TodosContract.TodosEntry.COLUMN_TEXT, "Call Mr Clark Kent");
        //int numRows = getContentResolver().update(TodosContract.TodosEntry.CONTENT_URI, values,
        //TodosContract.TodosEntry._ID + "=?", args);
        Log.d("Update Rows ", numRows.toString());
    }

}
