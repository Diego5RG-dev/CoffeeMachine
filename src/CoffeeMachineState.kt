import CoffeeMachine.cafeSeleccionado
import CoffeeMachine.cambio
import CoffeeMachine.nivelAzucar
import CoffeeMachine.precioCafeSeleccionado
import CoffeeMachineState.Idle.timestamp

interface ICoffeeMachineState {
    fun onEnter(stateMachine: Estado)
}
sealed class CoffeeMachineState: ICoffeeMachineState {
    object Idle : CoffeeMachineState(){
        val timestamp: Long = System.currentTimeMillis()
        override fun onEnter(stateMachine: Estado) {
            println("[Idle] Entrando en estado Idle a las $timestamp")
            println("[Idle] La máquina está lista para hacer café.")
        }

        init{
            println("[Idle] ejecutando init")
        }
    } /* como es de espera le coloco object*/
    data class CoffeeSelection(val coffeeType: String, val sugarLevel: Int) : CoffeeMachineState() {
        override fun onEnter(stateMachine: Estado) {
            TODO("Not yet implemented")

        }
        fun selectionSugar() {
            print("¿Cuánta azúcar quieres? (0-5): ")  /*el usuario puede elegir el nivel de azucar desedo*/
            nivelAzucar = readLine()?.toIntOrNull()?.coerceIn(0, 5) ?: 0
            println("Nivel de azúcar seleccionado: $nivelAzucar")  /*coerceIn limita el valor entre 0 y 5*/
        }
        fun selectionCafe() {
            val listaDeCafes = listOf(
                "Espresso" to 0.70,
                "Americano" to 0.80,    /*he creado una listad de pares (nombre, precio) para los cafes*/
                "Cappuccino" to 1.20,
                "Latte" to 1.10,
                "Macchiato" to 1.20,
                "Mocha" to 1.30
            )
            println("Escoge el cafe (escribe el nombre):")
            for ((nombre, precio) in listaDeCafes) {
                println("- $nombre: €$precio")
            }
            print("Introduce el nombre de tu café: ")

            val opcion = readLine()?.trim()
            val cafe = listaDeCafes.find { it.first.equals(opcion, ignoreCase = true) }
            if (cafe != null) {
                val (nombre, precio) = cafe
                precioCafeSeleccionado = precio
                cafeSeleccionado = nombre
                println("Has elegido: $nombre - €$precio")
            } else {
                println("Opción no válida.")
                precioCafeSeleccionado = 0.0
                cafeSeleccionado = ""
            }
        }
        fun pago(): Boolean {
            println("Por favor, inserte el dinero")
            val dineroInsertado = readLine()?.toDoubleOrNull() ?: 0.0       /*el usuario inserta el dinero*/
            return if (dineroInsertado >= precioCafeSeleccionado) {
                cambio = ((dineroInsertado - precioCafeSeleccionado) * 100).toInt() / 100.0
                println("Pago aceptado. Su cambio es: €${"%.2f".format(cambio)}")   /*calculo del cambio*/
                true
            } else {
                println("Dinero insuficiente. Por favor, inserte al menos €$precioCafeSeleccionado") /*si el dinero es insuficiente*/
                false
            }
        }

    }

    object MakingCoffee : CoffeeMachineState() {
        override fun onEnter(stateMachine: Estado) {
            TODO("Not yet implemented")
        }
    }

    object ServingCoffee: CoffeeMachineState() {
        override fun onEnter(stateMachine: Estado) {
            TODO("Not yet implemented")
        }
    }

    data class CoffeeFinal (val cambio: Double) : CoffeeMachineState() {
        override fun onEnter(stateMachine: Estado) {
            TODO("Not yet implemented")
        }
    }

    /*En este caso he optado por poner "data class a serving coffee, final y seleccion porque dependiendo del caso estos
    van a reciibir parametros, final, el cambio que va a recibir, selection, la seleccion del cafe, y el nivel de azucar
     */

}