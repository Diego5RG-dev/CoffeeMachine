sealed class CoffeeMachineState {
    object Idle : CoffeeMachineState() /* como es de espera le coloco object*/
    data class CoffeeSelection(val coffeeType: String, val sugarLevel: Int) : CoffeeMachineState()
    object MakingCoffee : CoffeeMachineState()
    data class ServingCoffee(val coffeeType: String) : CoffeeMachineState()
    data class CoffeeFinal (val cambio: Double) : CoffeeMachineState()

    /*En este caso he optado por poner "data class a serving coffee, final y seleccion porque dependiendo del caso estos
    van a reciibir parametros, final, el cambio que va a recibir, selection, la seleccion del cafe, y serving un tipo de preparacion,
    las dos otras no deberian recibir datos
     */
}