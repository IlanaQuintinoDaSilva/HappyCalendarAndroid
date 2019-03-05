/*package br.com.iq.happycalendarandroid.data

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import android.util.Log
import br.com.iq.happycalendarandroid.data.TodosContract.Companion.CONTENT_AUTHORITY
import br.com.iq.happycalendarandroid.data.TodosContract.Companion.PATH_CATEGORIES
import br.com.iq.happycalendarandroid.data.TodosContract.Companion.PATH_TODOS

import com.example.todos.data.TodosContract.CONTENT_AUTHORITY
import com.example.todos.data.TodosContract.CategoriesEntry
import com.example.todos.data.TodosContract.PATH_CATEGORIES
import com.example.todos.data.TodosContract.PATH_TODOS
import com.example.todos.data.TodosContract.TodosEntry

class TodosProviderTmp : ContentProvider() {

    private var helperTmp: DatabaseHelperTmp? = null
    override fun onCreate(): Boolean {
        helperTmp = DatabaseHelperTmp(context)
        return true
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, orderBy: String?): Cursor? {
        var selection = selection
        var selectionArgs = selectionArgs
        //get db
        val db = helperTmp!!.readableDatabase
        //cursor
        val cursor: Cursor
        //our integer
        val match = uriMatcher.match(uri)
        //intables
        val inTables = (TodosContract.TodosEntry.TABLE_NAME
                + " inner join "
                + TodosContract.CategoriesEntry.TABLE_NAME
                + " on " + TodosContract.TodosEntry.COLUMN_CATEGORY + " = "
                + TodosContract.CategoriesEntry.TABLE_NAME + "." + TodosContract.CategoriesEntry._ID)
        val builder: SQLiteQueryBuilder
        when (match) {
            TODOS -> {
                builder = SQLiteQueryBuilder()
                builder.tables = inTables
                cursor = builder.query(db, projection, selection, selectionArgs, null, null, orderBy)
            }
            TODOS_ID -> {
                builder = SQLiteQueryBuilder()
                builder.tables = inTables
                selection = TodosContract.TodosEntry._ID + "=?"
                selectionArgs = arrayOf(ContentUris.parseId(uri).toString())
                cursor = builder.query(db, projection, selection, selectionArgs, null, null, orderBy)
            }
            CATEGORIES -> cursor = db.query(TodosContract.CategoriesEntry.TABLE_NAME,
                    projection, null, null, null, null, orderBy)
            CATEGORIES_ID -> {
                selection = TodosContract.CategoriesEntry._ID + "=?"
                selectionArgs = arrayOf(ContentUris.parseId(uri).toString())
                cursor = db.query(TodosContract.CategoriesEntry.TABLE_NAME,
                        projection, selection, selectionArgs, null, null, orderBy)
            }
            else -> throw IllegalArgumentException("Query unknown URI: $uri")
        }

        cursor.setNotificationUri(context!!.contentResolver, uri)
        return cursor
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, contentValues: ContentValues?): Uri? {
        val match = uriMatcher.match(uri)
        when (match) {
            TODOS -> return insertRecord(uri, contentValues, TodosContract.TodosEntry.TABLE_NAME)
            CATEGORIES -> return insertRecord(uri, contentValues, TodosContract.CategoriesEntry.TABLE_NAME)
            else -> throw IllegalArgumentException("Insert unknown URI: $uri")
        }
    }

    private fun insertRecord(uri: Uri, values: ContentValues?, table: String): Uri? {
        //this time we need a writable database
        val db = helperTmp!!.writableDatabase
        val id = db.insert(table, null, values)
        if (id == -1) {
            Log.e("Error", "insert error for URI $uri")
            return null
        }
        context!!.contentResolver.notifyChange(uri, null)
        return ContentUris.withAppendedId(uri, id)
    }


    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        var selection = selection
        val match = uriMatcher.match(uri)
        when (match) {
            TODOS -> return deleteRecord(uri, null, null, TodosContract.TodosEntry.TABLE_NAME)
            TODOS_ID -> return deleteRecord(uri, selection, selectionArgs, TodosContract.TodosEntry.TABLE_NAME)
            CATEGORIES -> return deleteRecord(uri, null, null, TodosContract.CategoriesEntry.TABLE_NAME)
            CATEGORIES_ID -> {
                val id = ContentUris.parseId(uri)
                selection = TodosContract.CategoriesEntry._ID + "=?"
                val sel = arrayOfNulls<String>(1)
                sel[0] = id.toString()
                return deleteRecord(uri, selection, sel,
                        TodosContract.CategoriesEntry.TABLE_NAME)
            }

            else -> throw IllegalArgumentException("Insert unknown URI: $uri")
        }
    }

    private fun deleteRecord(uri: Uri, selection: String?, selectionArgs: Array<String>?, tableName: String): Int {
        //this time we need a writable database
        val db = helperTmp!!.writableDatabase
        val id = db.delete(tableName, selection, selectionArgs)
        if (id == -1) {
            Log.e("Error", "delete unknown URI $uri")
            return -1
        }
        context!!.contentResolver.notifyChange(uri, null)
        return id
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        val match = uriMatcher.match(uri)
        when (match) {
            TODOS -> return updateRecord(uri, values, selection, selectionArgs, TodosEntry.TABLE_NAME)
            CATEGORIES -> return updateRecord(uri, values, selection, selectionArgs, CategoriesEntry.TABLE_NAME)
            else -> throw IllegalArgumentException("Update unknown URI: $uri")
        }
    }

    private fun updateRecord(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?, tableName: String): Int {
        //this time we need a writable database
        val db = helperTmp!!.writableDatabase
        val id = db.update(tableName, values, selection, selectionArgs)
        if (id == 0) {
            Log.e("Error", "update error for URI $uri")
            return -1
        }
        return id
    }

    companion object {
        //constants for the operation
        private val TODOS = 1
        private val TODOS_ID = 2
        private val CATEGORIES = 3
        private val CATEGORIES_ID = 4
        //urimatcher
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(CONTENT_AUTHORITY, PATH_TODOS, TODOS)
            uriMatcher.addURI(CONTENT_AUTHORITY, PATH_TODOS + "/#", TODOS_ID)
            uriMatcher.addURI(CONTENT_AUTHORITY, PATH_CATEGORIES, CATEGORIES)
            uriMatcher.addURI(CONTENT_AUTHORITY, PATH_CATEGORIES + "/#", CATEGORIES_ID)
        }
    }
}
*/