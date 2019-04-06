package br.com.iq.happycalendarandroid.domain.api

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.util.Log
import br.com.iq.happycalendarandroid.HappyCalendarApplication
import br.com.iq.happycalendarandroid.data.DatabaseHelper
import br.com.iq.happycalendarandroid.data.TodosContract
import br.com.iq.happycalendarandroid.domain.Category

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

    fun addCategory(category: String, helper: DatabaseHelper){
        var db = helper.writableDatabase
        var values = ContentValues().apply {
            put(TodosContract.CategoriesEntry.COLUMN_DESCRIPTION, category)
        }

        val newRowId = db?.insert(TodosContract.CategoriesEntry.TABLE_NAME, null, values)

        db?.close()

    }

    fun getCategories(helper: DatabaseHelper):List<Category> {
        val categories = mutableListOf<Category>()
        //val helper = DatabaseHelper(context)
        val db = helper.readableDatabase
        val projection = arrayOf(TodosContract.CategoriesEntry.COLUMN_DESCRIPTION)
        val selection = TodosContract.TodosEntry.COLUMN_CATEGORY + " = ?"
        val selectionArgs = arrayOf("1")
        val c = db.query(TodosContract.CategoriesEntry.TABLE_NAME,
                projection, null, null, null, null, null)
        val i = c.count
        Log.d("Categories Count", i.toString())
        var rowContent = ""
        while (c.moveToNext()) {
            var ct = Category()
            ct.name = c.getString(0)
            categories.add(ct)
        }
        c.close()
        return categories
    }

}