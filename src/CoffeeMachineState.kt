sealed class CoffeeMachineState {
    object Idle : CoffeeMachineState()
    data class CoffeeMachineSelection(val coffeeType: String, val sugarLevel: Int) : CoffeeMachineState()
    object MakingCoffee : CoffeeMachineState()
    data class ServingCoffee(val coffeeType: String) : CoffeeMachineState()
    data class CoffeeFinal (val cambio: Double) : CoffeeMachineState()
}