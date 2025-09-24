object CoffeeMachine {
    private var CurrentState: CoffeeMachineState = CoffeeMachineState.Idle

    fun MakeCoffee() {
        println("Estado actual: $CurrentState")

        when (CurrentState) {
            is CoffeeMachineState.Idle -> {
                println("La maquina esta en espera, seleccione su cafe")
                CurrentState = CoffeeMachineState.CoffeeSelection("Espresso", 2)
                selectionCafe()
                pago()

                CurrentState = CoffeeMachineState.MakingCoffee
                println("Preparando su cafe...")
                Thread.sleep(2000)

                CurrentState = CoffeeMachineState.ServingCoffee
                println("Sirviendo su cafe...")
                Thread.sleep(2000)
                CurrentState = CoffeeMachineState.CoffeeFinal(0.50)
                println("Su cafe esta listo, aqui tiene su cambio")
            }

            is CoffeeMachineState.CoffeeSelection -> {}
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
        println("Escoge el cafe;")
        for ((nombre, precio) in listaDeCafes) {
            println("- $nombre: \$$precio")
            print("Introduce el nombre de tu café: ")
        }

        val opcion = readLine()?.trim()
        val cafeSeleccionado = listaDeCafes.find { it.first.equals(opcion, ignoreCase = true) }
        if (cafeSeleccionado != null) {
            val (nombre, precio) = cafeSeleccionado
            println("Has elegido: $nombre - €$precio")
        } else {
            println("Opción no válida.")
        }
    }

    fun pago() {
        println("Por favor, inserte el dinero")
        val dineroInsertado = readLine()?.toDoubleOrNull() ?: 0.0
        if (dineroInsertado >= 1.20) {
            val cambio = dineroInsertado - 1.20
            println("Pago aceptado. Su cambio es: €$cambio")
        } else {
            println("Dinero insuficiente. Por favor, inserte al menos €1.20")
        }
    }
}

