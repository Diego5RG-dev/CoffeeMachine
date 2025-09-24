object CoffeeMachine {
    private var CurrentState : CoffeeMachineState = CoffeeMachineState.Idle

    fun MakeCoffee(){
        println("Estado actual: $CurrentState")

        when(CurrentState){
            is CoffeeMachineState.Idle -> {}
            is CoffeeMachineState.CoffeeSelection -> {}
            is CoffeeMachineState.MakingCoffee -> {}
            is CoffeeMachineState.ServingCoffee -> {}
            is CoffeeMachineState.CoffeeFinal -> {}

        }

        fun selectionCafe(){
            val listaDeCafes = listOf("Espresso", "Americano", "Cappuccino", "Latte", "Macchiato", "Mocha")
            println(listaDeCafes + "/n Escoge el cafe")
        }
    }
}