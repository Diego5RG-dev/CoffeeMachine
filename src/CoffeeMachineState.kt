interface ICoffeeMachineState {
    fun onEnter(stateMachine: Estado)
}
sealed class CoffeeMachineState {
    object Idle : CoffeeMachineState(){
        val timestamp: Long = System.currentTimeMillis()
        fun onEnter(stateMachine: Estado) {
            println("[Idle] Entrando en estado Idle a las $timestamp")
            println("[Idle] La máquina está lista para hacer café.")
        }

        init{
            println("[Idle] ejecutando init")
        }
    } /* como es de espera le coloco object*/
    data class CoffeeSelection(val coffeeType: String, val sugarLevel: Int) : CoffeeMachineState()
    object MakingCoffee : CoffeeMachineState()
    object ServingCoffee: CoffeeMachineState()
    data class CoffeeFinal (val cambio: Double) : CoffeeMachineState()

    /*En este caso he optado por poner "data class a serving coffee, final y seleccion porque dependiendo del caso estos
    van a reciibir parametros, final, el cambio que va a recibir, selection, la seleccion del cafe, y el nivel de azucar
     */

}