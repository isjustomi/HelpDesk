package RecyclerViewHelpers

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import thomas.galdamez.crudactividad.R

class ViewHolder(view:View): RecyclerView.ViewHolder(view) {

    //En el viewholder mando a llamar a los elementos de la card
    val txtNombreCard = view.findViewById<TextView>(R.id.txtTickets)
}