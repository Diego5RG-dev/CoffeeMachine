# Máquina de Café - Estados

Este proyecto simula una máquina de café con los siguientes estados:

- **Idle**: La máquina está en espera y solicita al usuario que seleccione un café.
- **CoffeeSelection**: El usuario ha elegido el tipo de café y el nivel de azúcar. Se solicita el pago.
- **MakingCoffee**: La máquina prepara el café seleccionado.
- **ServingCoffee**: El café se sirve al usuario.
- **CoffeeFinal**: El usuario recibe su café y el cambio. La máquina vuelve al estado de espera.

Cada transición de estado depende de la interacción del usuario (selección, pago, etc.) y asegura un flujo claro y controlado en la preparación del café.

Asi viendose que se pasa de un estado a otro a dependencia de acciones o tiempo si se quisiera 

```mermaid
stateDiagram-v2

    Idle --> CoffeeSelection
    CoffeeSelection --> Idle
    CoffeeSelection --> MakingCafe
    MakingCafe --> ServingCoffee
    ServingCoffee --> Coffeefinal
    Coffeefinal --> Idle

  


