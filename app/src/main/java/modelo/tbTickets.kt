package modelo

data class tbTickets(
    val uuid: String,
    val TITULO : String,
    val DESCRIPCION: String,
    val RESPONSABLE: String,
    val EMAIL: String,
    val TELEFONO: Int,
    val UBICACION: String
)
