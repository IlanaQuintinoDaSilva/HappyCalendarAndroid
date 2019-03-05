/*
package br.com.iq.happycalendarandroid.data

import android.net.Uri
import android.provider.BaseColumns

class TodosContract {

    fun concatContent(path: String): String {
        return "content://$path"
    }

    class TodosEntry : BaseColumns {
        companion object {
            val CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_TODOS)

            // Table name
            val TABLE_NAME = "todos"
            //column (field) names
            val _ID = BaseColumns._ID
            val COLUMN_TEXT = "text"
            val COLUMN_CREATED = "created"
            val COLUMN_EXPIRED = "expired"
            val COLUMN_DONE = "done"
            val COLUMN_CATEGORY = "category"
        }
    }

    class CategoriesEntry : BaseColumns {
        companion object {
            val CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_CATEGORIES)

            // Table name
            val TABLE_NAME = "categories"
            //column names
            val _ID = BaseColumns._ID
            val COLUMN_DESCRIPTION = "description"
        }
    }

    companion object {
        //URI section
        val CONTENT_AUTHORITY = "com.example.todos.todosprovider"
        val PATH_TODOS = "todos"
        val PATH_CATEGORIES = "categories"
        val BASE_CONTENT_URI = Uri.parse("content://$CONTENT_AUTHORITY")
    }
}
*/