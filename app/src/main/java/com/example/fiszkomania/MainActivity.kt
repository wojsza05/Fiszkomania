package com.example.fiszkomania

import android.content.Intent
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.provider.BaseColumns
import android.provider.ContactsContract
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun dodaj (view: View)
    {
        var aktywnoscDodaj = Intent(applicationContext, DodajActivity::class.java)
        startActivity(aktywnoscDodaj)
    }

    fun uczSie (view: View)
    {
        var aktywnoscUczSie = Intent(applicationContext, UczSieActivity::class.java)
        startActivity(aktywnoscUczSie)
    }

    fun baza (view: View)
    {
        var aktywnoscBaza = Intent(applicationContext, BazaActivity::class.java)
        startActivity(aktywnoscBaza)
    }
}