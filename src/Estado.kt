object Estado {
    public var currentState: CoffeeMachineState = CoffeeMachineState.Idle
    fun setState(newState: CoffeeMachineState) {
        if (isValidTransition(currentState, newState)) {
            currentState = newState
            updateState()
        } else {
            println("Transición inválida de $currentState a $newState")
        }
    }

    private fun isValidTransition(from: CoffeeMachineState, to: CoffeeMachineState): Boolean {
        return when (from) {
            is CoffeeMachineState.Idle -> to == CoffeeMachineState.CoffeeSelection("", 0)
            is CoffeeMachineState.CoffeeSelection -> to == CoffeeMachineState.MakingCoffee
            is CoffeeMachineState.MakingCoffee -> to == CoffeeMachineState.ServingCoffee
            is CoffeeMachineState.ServingCoffee -> to == CoffeeMachineState.CoffeeFinal(0.0)
            is CoffeeMachineState.CoffeeFinal -> to == CoffeeMachineState.Idle
        }
    }

    fun getState(): CoffeeMachineState {
        return currentState
    }

    fun updateState() {
        println("[StateMachine] Estado actual: $currentState")
        currentState.onEnter(this)
    }

}