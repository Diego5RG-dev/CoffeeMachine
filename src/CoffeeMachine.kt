import kotlin.text.toInt
import kotlin.times

object CoffeeMachine {
    private var CurrentState: CoffeeMachineState = CoffeeMachineState.Idle
    var precioCafeSeleccionado: Double = 0.0
    var cafeSeleccionado: String = ""
    var nivelAzucar: Int = 0
    var cambio: Double = 0.0

    fun MakeCoffee() {
        while (true) {
            println("Estado actual: $CurrentState")
            when (CurrentState) {
                is CoffeeMachineState.Idle -> {
                    println("La maquina está en espera, seleccione su café")
                    selectionCafe()
                    if (cafeSeleccionado.isNotEmpty()) {
                        selectionSugar()
                        CurrentState = CoffeeMachineState.CoffeeSelection(cafeSeleccionado, nivelAzucar)
                    } else {
                        continue // Vuelve a pedir selección si no es válida
                    }
                }
                is CoffeeMachineState.CoffeeSelection -> {
                    if (pago()) {
                        CurrentState = CoffeeMachineState.MakingCoffee
                    } else {
                        continue // Vuelve a pedir pago si es insuficiente
                    }
                }
                is CoffeeMachineState.MakingCoffee -> {
                    println("Preparando su café...")
                    Thread.sleep(2000)
                    CurrentState = CoffeeMachineState.ServingCoffee
                }
                is CoffeeMachineState.ServingCoffee -> {
                    println("Sirviendo su café...")
                    Thread.sleep(2000)
                    CurrentState = CoffeeMachineState.CoffeeFinal(cambio)
                }
                is CoffeeMachineState.CoffeeFinal -> {
                    println("Su café está listo, aquí tiene su cambio: €$cambio")
                    CurrentState = CoffeeMachineState.Idle
                    break // Termina el ciclo para preguntar si quiere otro café
                }
            }
        }
    }

    fun iniciarMaquina() { /*bucle para reiniciar la maquina si el usuario quiere otro cafe*/
        do {
            MakeCoffee()
            print("¿Desea comprar otro café? (s/n): ")
            val respuesta = readLine()?.trim()?.lowercase()
        } while (respuesta == "s")
        println("¡Gracias por usar la máquina de café!")

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

    fun selectionSugar() {
        print("¿Cuánta azúcar quieres? (0-5): ")  /*el usuario puede elegir el nivel de azucar desedo*/
        nivelAzucar = readLine()?.toIntOrNull()?.coerceIn(0, 5) ?: 0
        println("Nivel de azúcar seleccionado: $nivelAzucar")  /*coerceIn limita el valor entre 0 y 5*/
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
