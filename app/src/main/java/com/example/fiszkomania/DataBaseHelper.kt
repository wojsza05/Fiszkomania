package com.example.fiszkomania

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import java.security.AccessControlContext

object TableInfo: BaseColumns
{
    const val TABLE_NAME = "slowka"
    const val TABLE_COLUMN_ANG = "angielski"
    const val TABLE_COLUMN_POL = "polski"
}

object BasicCommand
{
    const val SQL_CREATE_TABLE =
        "CREATE TABLE ${TableInfo.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
            "${TableInfo.TABLE_COLUMN_ANG} TEXT NOT NULL," +
            "${TableInfo.TABLE_COLUMN_POL} TEXT NOT NULL)"

    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS ${TableInfo.TABLE_NAME}"
}

class DataBaseHelper(context: Context): SQLiteOpenHelper(context, TableInfo.TABLE_NAME, null, 1)
{
    override fun onCreate(db: SQLiteDatabase?)
    {
        db?.execSQL(BasicCommand.SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
    {
        db?.execSQL(BasicCommand.SQL_DELETE_TABLE)
        onCreate(db)
    }
}
