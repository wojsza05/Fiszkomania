package com.example.fiszkomania

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_dodaj.*

class DodajActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dodaj)
    }

    override fun onResume() {
        super.onResume()

        val dbHelper = DataBaseHelper(applicationContext)
        val db = dbHelper.writableDatabase

        listaSlowek.tablicaSlowek2.clear()

        val cursor = db.query(TableInfo.TABLE_NAME, null, null, null, null, null, null)

        if (cursor.count > 0)
        {
            cursor.moveToFirst()
            while (!cursor.isAfterLast)
            {
                val ang = cursor.getString(1)
                val pol = cursor.getString(2)
                val slowo = ang + "#" + pol
                listaSlowek.tablicaSlowek2.add(slowo)
                cursor.moveToNext()
            }
        }
        cursor.close()
    }

    fun dodawanie_fiszek (v: View) {
        val dbHelper = DataBaseHelper(applicationContext)
        val db = dbHelper.writableDatabase

        var angielski = napisAngielski.text.toString()
        var polski = polSlowo.text.toString()

        var czy1 = 0
        var ang1 = ""
        for (i in angielski) {
            if (i != ' ') {
                czy1 = 1
                ang1 += i
            } else if (czy1 == 1) {
                czy1 = 1
                ang1 += i
            }
        }

        var ang2 = ang1.reversed()
        var ang3 = ""

        czy1 = 0
        for (i in ang2) {
            if (i != ' ') {
                czy1 = 1
                ang3 += i
            } else if (czy1 == 1) {
                czy1 = 1
                ang3 += i
            }
        }

        var czy2 = 0
        var pol1 = ""
        for (i in polski) {
            if (i != ' ') {
                czy2 = 1
                pol1 += i
            } else if (czy2 == 1) {
                czy2 = 1
                pol1 += i
            }
        }

        var pol2 = pol1.reversed()
        var pol3 = ""

        czy2 = 0
        for (i in pol2) {
            if (i != ' ') {
                czy2 = 1
                pol3 += i
            } else if (czy2 == 1) {
                czy2 = 1
                pol3 += i
            }
        }

        ang3 = ang3.reversed()
        pol3 = pol3.reversed()

        var slowo = ang3 + "#" + pol3

        var czy3 = listaSlowek.tablicaSlowek2.find { s -> s == slowo }

        if (ang3.length == 0 || pol3.length == 0) {
            var dodaj_Toast = Toast.makeText(applicationContext, "Niepoprawne słowo", Toast.LENGTH_SHORT)
            dodaj_Toast.show()
        } else if (czy3 != null) {
            var dodaj_Toast = Toast.makeText(applicationContext, "Słowo już znajduje się w bazie", Toast.LENGTH_SHORT)
            dodaj_Toast.show()
        } else {
            var value = ContentValues()
            value.put(TableInfo.TABLE_COLUMN_ANG, ang3)
            value.put(TableInfo.TABLE_COLUMN_POL, pol3)

            db.insertOrThrow(TableInfo.TABLE_NAME, null, value)
            listaSlowek.tablicaSlowek2.add(slowo)

            var dodaj_Toast = Toast.makeText(applicationContext, "Dodano", Toast.LENGTH_SHORT)
            dodaj_Toast.show()

            napisAngielski.setText("")
            polSlowo.setText("")
        }
    }

    fun menu1 (view: View)
    {
        var aktywnoscMenu = Intent(applicationContext, MainActivity::class.java)
        startActivity(aktywnoscMenu)
    }
}