package thomas.galdamez.crudactividad

import RecyclerViewHelpers.Adaptador
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.ClaseConexion
import modelo.tbTickets
import kotlin.coroutines.coroutineContext

class visualizar_tickets : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_visualizar_tickets)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rcvTickets)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rcvTickets = findViewById<RecyclerView>(R.id.rcvTickets)

        rcvTickets.layoutManager = LinearLayoutManager(this)

        fun obtenerTickets(): List<tbTickets> {

            val objConxion = ClaseConexion().cadenaConexion()

            val statement = objConxion?.createStatement()
            val resultSet = statement?.executeQuery("SELECT * FROM tbTickets")!!

            val listaTickets = mutableListOf<tbTickets>()

            while (resultSet.next()){
                val uuid = resultSet.getString("uuid")
                val TITULO = resultSet.getString("TITULO")
                val DESCRIPCION = resultSet.getString("DESCRIPCION")
                val RESPONSABLE = resultSet.getString("RESPONSABLE")
                val EMAIL = resultSet.getString("EMAIL")
                val TELEFONO = resultSet.getInt("TELEFONO")
                val UBICACION = resultSet.getString("UBICACION")

                val valoresJuntos = tbTickets(uuid, TITULO, DESCRIPCION, RESPONSABLE, EMAIL, TELEFONO, UBICACION)

                listaTickets.add(valoresJuntos)
            }
            return listaTickets
        }
        CoroutineScope(Dispatchers.IO).launch {
            val ticketsDB = obtenerTickets()
            withContext(Dispatchers.Main){
            val adaptador = Adaptador(ticketsDB)
            rcvTickets.adapter = adaptador
            }
        }
    }
}