package br.com.iq.happycalendarandroid.data

import android.provider.BaseColumns

object CategoryContract {

    object CategoryEntry : BaseColumns {
        const val TABLE_NAME = "category"
        const val COLUMN_NAME_CATEGORY = "categoryname"
    }
}