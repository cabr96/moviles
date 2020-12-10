package com.example.json

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adaptador_custom(var context: Context, items : ArrayList<Auto>):
    RecyclerView.Adapter<Adaptador_custom.ViewHolder>() {

     var items:ArrayList<Auto>?
    init {
        this.items=items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val vista = LayoutInflater.from(context).inflate(R.layout.template_autos,parent, false)
        var viewHolder = ViewHolder(vista)
        return  viewHolder
    }

    override fun getItemCount(): Int {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return this.items?.count()!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val item =items?.get(position)

        holder?.id?.text=item?.id.toString()
        holder?.placas?.text=item?.placas.toString()
        holder?.modelo?.text=item?.modelo
        holder?.marca?.text=item?.marca
        holder?.estado?.text=item?.estado.toString()
        holder?.ensabladora?.text=item?.ensabladora.toString()

    }



    class ViewHolder (vista : View) : RecyclerView.ViewHolder(vista){
        var vista= vista

        var id : TextView? = null
        var placas : TextView? = null
        var modelo : TextView? = null
        var marca : TextView? = null
        var estado : TextView? = null
        var ensabladora : TextView? = null


        init {
                id= vista.findViewById(R.id.mostrar_id)
            placas= vista.findViewById(R.id.mostrar_placas)
            modelo= vista.findViewById(R.id.mostrar_modelo)
            marca= vista.findViewById(R.id.mostrar_marca)
            estado= vista.findViewById(R.id.mostrar_estado)
            ensabladora= vista.findViewById(R.id.mostrar_ensabladora)
        }
    }
}