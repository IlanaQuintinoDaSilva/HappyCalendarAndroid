package br.com.iq.happycalendarandroid.domain.api

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import br.com.iq.happycalendarandroid.data.DatabaseHelper
import br.com.iq.happycalendarandroid.data.TodosContract

class CategoryService : ContentProvider(){

    private var helper: DatabaseHelper? = null
    private var category: String = ""

    override fun onCreate(): Boolean {
        helper = DatabaseHelper(context)
        return true
    }

    override fun insert(uri: Uri?, values: ContentValues?): Uri? {
        return null
    }

    override fun query(uri: Uri?, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(uri: Uri?, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(uri: Uri?, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getType(uri: Uri?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun addCategory(category: String){
        var db = helper?.writableDatabase
        var values = ContentValues().apply {
            put(TodosContract.CategoriesEntry.COLUMN_DESCRIPTION, category)
        }

        val newRowId = db?.insert(TodosContract.CategoriesEntry.TABLE_NAME, null, values)

        db?.close()

    }
}