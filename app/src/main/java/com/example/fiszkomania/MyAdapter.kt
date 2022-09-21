package com.example.fiszkomania

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.poj_slowo.view.*
import java.security.AccessControlContext
import java.text.FieldPosition

class MyAdapter(val context: Context, val db: SQLiteDatabase, var slowa: ArrayList<Slowo>): RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(viewGrop: ViewGroup, p1: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(viewGrop.context)
        val slowo = layoutInflater.inflate(R.layout.poj_slowo, viewGrop, false)
        return MyViewHolder(slowo)
    }

    override fun getItemCount(): Int {
        return slowa.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val slowko = holder.view.slowo_cardView
        val angSl = holder.view.angSlowo
        val polSl = holder.view.polSlowo

        angSl.setText(slowa[holder.adapterPosition].ang)
        polSl.setText(slowa[holder.adapterPosition].pol)

        slowko.setOnLongClickListener(object: View.OnLongClickListener
        {
            override fun onLongClick(v: View?): Boolean
            {
                db.delete(TableInfo.TABLE_NAME,
                    BaseColumns._ID + "=?",
                    arrayOf(slowa[holder.adapterPosition].id.toString()))

                slowa.removeAt(holder.adapterPosition)
                notifyItemRemoved(holder.adapterPosition)

                return true
            }
        })
    }
}

class MyViewHolder(val view: View): RecyclerView.ViewHolder(view)