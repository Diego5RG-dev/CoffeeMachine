class testErrores {
    fun testPagoError() {
        CoffeeMachine.precioCafeSeleccionado = 1.0
        // Simula dinero insuficiente
        CoffeeMachine.cambio = 0.0
        val resultado = CoffeeMachine.pago() // Requiere entrada manual
        assert(!resultado) { "El pago debería fallar si el dinero es insuficiente" }
        println("Test de error de pago completado")
    }

    fun testSelectionCafeError() {
        // Simula selección inválida
        CoffeeMachine.cafeSeleccionado = ""
        CoffeeMachine.precioCafeSeleccionado = 0.0
        CoffeeMachine.selectionCafe() // Requiere entrada manual
        assert(CoffeeMachine.cafeSeleccionado == "") { "La selección de café inválida debe dejar el nombre vacío" }
        println("Test de error de selección de café completado")
    }

    fun testSelectionSugarError() {
        // Simula nivel de azúcar fuera de rango
        CoffeeMachine.nivelAzucar = -1
        CoffeeMachine.selectionSugar() // Requiere entrada manual
        assert(CoffeeMachine.nivelAzucar in 0..5) { "El nivel de azúcar debe estar entre 0 y 5" }
        println("Test de error de selección de azúcar completado")
    }
}

fun main() {
    val tester = testErrores()
    tester.testPagoError()
    tester.testSelectionCafeError()
    tester.testSelectionSugarError()
}
