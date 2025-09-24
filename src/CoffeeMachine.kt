object CoffeeMachine {
    private var CurrentState: CoffeeMachineState = CoffeeMachineState.Idle
    var precioCafeSeleccionado: Double = 0.0
    var cafeSeleccionado: String = ""
    var nivelAzucar: Int = 0

    fun MakeCoffee() {
        println("Estado actual: $CurrentState")

        when (CurrentState) {
            is CoffeeMachineState.Idle -> {
                println("La maquina esta en espera, seleccione su cafe")
                selectionCafe()
                selectionSugar()
                CurrentState = CoffeeMachineState.CoffeeSelection(cafeSeleccionado, nivelAzucar)
                if (pago()) {
                    CurrentState = CoffeeMachineState.MakingCoffee
                    println("Preparando su cafe...")
                    Thread.sleep(2000)

                    CurrentState = CoffeeMachineState.ServingCoffee
                    println("Sirviendo su cafe...")
                    Thread.sleep(2000)
                    CurrentState = CoffeeMachineState.CoffeeFinal(0.50)
                    println("Su cafe esta listo, aqui tiene su cambio")
                }
            }
            is CoffeeMachineState.CoffeeSelection -> {
                println("Sirviendo su cafe...")
                Thread.sleep(2000)
            }
            is CoffeeMachineState.MakingCoffee -> {}
            is CoffeeMachineState.ServingCoffee -> {}
            is CoffeeMachineState.CoffeeFinal -> {}
        }
    }

    fun selectionCafe() {
        val listaDeCafes = listOf(
            "Espresso" to 0.70,
            "Americano" to 0.80,
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
        print("¿Cuánta azúcar quieres? (0-5): ")
        nivelAzucar = readLine()?.toIntOrNull()?.coerceIn(0, 5) ?: 0
        println("Nivel de azúcar seleccionado: $nivelAzucar")
    }

    fun pago(): Boolean {
        println("Por favor, inserte el dinero")
        val dineroInsertado = readLine()?.toDoubleOrNull() ?: 0.0
        return if (dineroInsertado >= precioCafeSeleccionado) {
            val cambio = dineroInsertado - precioCafeSeleccionado
            println("Pago aceptado. Su cambio es: €$cambio")
            true
        } else {
            println("Dinero insuficiente. Por favor, inserte al menos €$precioCafeSeleccionado")
            false
        }
    }
}
