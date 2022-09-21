package com.example.fiszkomania

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_baza.*

class BazaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baza)
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
                val id = cursor.getInt(0)
                val ang = cursor.getString(1)
                val pol = cursor.getString(2)
                val slowo = ang + "#" + pol + "@" + id.toString()
                listaSlowek.tablicaSlowek2.add(slowo)
                cursor.moveToNext()
            }
        }
        cursor.close()

        listaSlowek.tablicaSlowek2.sort()

        val slowa = ArrayList<Slowo>()

        var it = 0
        while (it < listaSlowek.tablicaSlowek2.size)
        {
            var slowo = Slowo()

            var czy1: Boolean = false
            var czy2: Boolean = false

            val fiszka = listaSlowek.tablicaSlowek2[it]
            var ang = ""
            var pol = ""
            var id = ""

            for (i in fiszka)
            {
                if (czy1 == false && i != '#')
                    ang += i
                if (i == '#')
                    czy1 = true
                if (czy1 == true && i != '#' && i != '@' && czy2 == false)
                    pol += i
                if (i == '@')
                    czy2 = true
                if (czy2 == true && i != '@')
                    id += i
            }

            slowo.id = id.toInt()
            slowo.ang = ang
            slowo.pol = pol

            slowa.add(slowo)

            it++
        }

        RV_slowka.layoutManager = LinearLayoutManager(applicationContext)
        RV_slowka.adapter = MyAdapter(applicationContext, db, slowa)

        var dl = listaSlowek.tablicaSlowek2.size
        if (dl == 0)
        {
            var dodaj_Toast = Toast.makeText(applicationContext, "Nie posiadasz rzadnych słówek \n               Dodaj słówka", Toast.LENGTH_LONG)
            dodaj_Toast.show()
        }
        else
        {
            var dodaj_Toast = Toast.makeText(applicationContext, "Przytrzymaj słowo, aby je usunąć", Toast.LENGTH_LONG)
            dodaj_Toast.show()
        }
    }

    fun menu3 (view: View)
    {
        var aktywnoscMenu = Intent(applicationContext, MainActivity::class.java)
        startActivity(aktywnoscMenu)
    }
}