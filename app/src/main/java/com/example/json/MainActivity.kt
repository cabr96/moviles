package com.example.json

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.json.R
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var listar_autos : ArrayList<Auto>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }


    fun comprobarConexion(){
        if (Network.hayRed(this)){
                solicitarJSON_object();
        }else{
            Toast.makeText(this,"Error de conexion",Toast.LENGTH_SHORT).show()
        }
    }





    fun solicitarJSON_object(){
        val queue = Volley.newRequestQueue(this)
        val url = "https://automovil-moviles.000webhostapp.com/"

        val JsonObjectRequest = JsonObjectRequest(url, null,
            Response.Listener { response ->
                try {
                    val json : JSONObject
                    json =response
                    generar_array(json)

                }catch (e:JSONException){
                    Log.d("msg_error",e.localizedMessage)
                }
                Log.i("JSON_OBJECT", "response is $response")
            },
            Response.ErrorListener { error ->
                error.printStackTrace()

            }
        )
            queue.add(JsonObjectRequest)
    }

        fun generar_array(json :JSONObject){
            listar_autos = ArrayList()

            val json =json
            val  autos =json.getJSONArray("Autos")
            for (i in 0.. autos.length()-1){
                val id = autos.getJSONObject(i).getInt("id");
                val placas = autos.getJSONObject(i).getInt("placas")
                val modelo = autos.getJSONObject(i).getString("modelo")
                val marca = autos.getJSONObject(i).getString("marca")
                val estado = autos.getJSONObject(i).getString("estado")
                val ensabladora = autos.getJSONObject(i).getString("ensambladora")
                listar_autos.add(
                    Auto(
                        id,
                        placas,
                        modelo,
                        marca,
                        estado,
                        ensabladora
                    )
                )
                Log.d("onCreate", listar_autos.count().toString())

                mandar_lista(listar_autos);
            }
    }


    fun btn_mostrar(view: View) {
        comprobarConexion()
    }


        fun mandar_lista(lista_alumnos : ArrayList<Auto>){
            val intent : Intent = Intent(applicationContext, Listar::class.java)
            intent.putParcelableArrayListExtra("lista_autos",listar_autos as java.util.ArrayList<out Parcelable>)
            startActivity(intent)

        }

}
