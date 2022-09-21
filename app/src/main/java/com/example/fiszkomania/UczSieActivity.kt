package com.example.fiszkomania

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_ucz_sie.*
import kotlin.random.Random

class UczSieActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ucz_sie)
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

        nastepne(View(applicationContext))
    }

    fun nastepne (view: View)
    {
        var dl = listaSlowek.tablicaSlowek2.size
        if (dl == 0)
        {
            var dodaj_Toast = Toast.makeText(applicationContext, "Nie posiadasz rzadnych słówek \n               Dodaj słówka", Toast.LENGTH_LONG)
            dodaj_Toast.show()
        }
        else
        {
            var liczba = Random.nextInt(0, dl)
            var slowo = listaSlowek.tablicaSlowek2[liczba]
            var pol = ""
            var ang = ""

            var czy1 = true
            for (i in slowo)
            {
                if (czy1 == true && i != '#')
                    ang += i
                if (i == '#')
                    czy1 = false
                if (czy1 == false && i != '#')
                    pol += i
            }

            angSlowo.text = ang
            polSlowo.text = pol
        }
    }

    fun menu2 (view: View)
    {
        var aktywnoscDodaj = Intent(applicationContext, MainActivity::class.java)
        startActivity(aktywnoscDodaj)
    }
}