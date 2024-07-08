package thomas.galdamez.crudactividad

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import modelo.ClaseConexion
import java.util.UUID

class tickets : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tickets)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rcvTickets)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        //1- mando a llamar a todos los elementos que tengo en la vista
        val txtTitulo = findViewById<EditText>(R.id.txtTitulo)
        val txtDescripcion = findViewById<EditText>(R.id.txtDescipcion)
        val txtResponsable = findViewById<EditText>(R.id.txtResponsable)
        val txtEmail = findViewById<EditText>(R.id.txtEmail)
        val txtTelefono = findViewById<EditText>(R.id.txtTelefono)
        val txtUbicacion = findViewById<EditText>(R.id.txtUbicacion)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)
        val btnVer = findViewById<Button>(R.id.btnVer)

        btnVer.setOnClickListener {
            val intent = Intent(this, visualizar_tickets::class.java)
            startActivity(intent)
        }

        //2- Programar el boton agregar
        btnGuardar.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                //1-Crear un objeto de la clase conexion

                val objConexion = ClaseConexion().cadenaConexion()

                //2-Crear una variable que contenga un preparestatement

                val addTickets = objConexion?.prepareStatement("insert into tbTickets (uuid, TITULO, DESCRIPCION, RESPONSABLE, EMAIL, TELEFONO, UBICACION) values (?,?,?,?,?,?,?)")!!
                addTickets.setString(1,UUID.randomUUID().toString())
                addTickets.setString(2,txtTitulo.text.toString())
                addTickets.setString(3,txtDescripcion.text.toString())
                addTickets.setString(4,txtResponsable.text.toString())
                addTickets.setString(5,txtEmail.text.toString())
                addTickets.setString(6,txtTelefono.text.toString())
                addTickets.setString(7,txtUbicacion.text.toString())

            }
        }


    }


}