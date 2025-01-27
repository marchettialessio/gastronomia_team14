@startuml

title move(direction)

skinparam backgroundColor #f9f9f9
skinparam participantBackgroundColor #e0f7fa
skinparam participantBorderColor #00796b
skinparam sequenceArrowColor #00796b
skinparam sequenceBoxBackgroundColor #b2dfdb
skinparam sequenceBoxBorderColor #004d40
skinparam criticalBackgroundColor #ffcdd2
skinparam criticalBorderColor #b71c1c
skinparam altBackgroundColor #d1c4e9
skinparam altBorderColor #512da8

actor Player
participant Controller
participant Game

Player -> Controller: move(direction)
Controller --> Controller: movePlayer(direction)
Controller --> Controller : showPlayer(updatePosition)
Controller --> Controller : calculate coordinates (centerX, centerY)
Controller --> Controller : setLayoutX(centerX)
Controller --> Controller : setLayoutY(centerY)
Controller --> Controller : checkGuardAndPlayer()
alt Guard and Player in same position
    Controller -> Game : game.get_timer().decrement(WASTED_TIME)
    Game --> Player: alert "La guardia ti ha trovato, hai perso 5 secondi"
end
alt updatePosition is true
    Controller -> Game : game.updatePosition(player.get_x_coord(), player.get_y_coord())
end
Controller --> Player: showUpdatedImage()

@enduml



@startuml

title timerFinished()

skinparam backgroundColor #f9f9f9
skinparam participantBackgroundColor #e0f7fa
skinparam participantBorderColor #00796b
skinparam sequenceArrowColor #00796b
skinparam sequenceBoxBackgroundColor #b2dfdb
skinparam sequenceBoxBorderColor #004d40
skinparam criticalBackgroundColor #ffcdd2
skinparam criticalBorderColor #b71c1c
skinparam altBackgroundColor #d1c4e9
skinparam altBorderColor #512da8


actor Player
participant Controller
participant Timer
participant Game


alt timer ran out
Controller -> Timer: timerFinished()
Controller --> Player: alert "Hai perso. Il tempo è scaduto"
Controller --> Player: alert "Vuoi chiudere l'applicazione(sì) o continuare (no)?"
Controller -> Game: setDecisionButtons()
Game --> Player: showDecisionButtons()
alt yes
    Controller -> Game: closeGame()
    Controller --> Player: exitGame()
else no
    Controller -> Game: initializeGame()
    Controller --> Player: newGame()
end


end
@enduml



@startuml

title timerFinished()

skinparam backgroundColor #f9f9f9
skinparam participantBackgroundColor #e0f7fa
skinparam participantBorderColor #00796b
skinparam sequenceArrowColor #00796b
skinparam sequenceBoxBackgroundColor #b2dfdb
skinparam sequenceBoxBorderColor #004d40
skinparam criticalBackgroundColor #ffcdd2
skinparam criticalBorderColor #b71c1c
skinparam altBackgroundColor #d1c4e9
skinparam altBorderColor #512da8


actor Player
participant Controller
participant Timer
participant Game


alt timer ran out
Controller -> Timer: timerFinished()
Controller --> Player: alert "Hai perso. Il tempo è scaduto"
Controller --> Player: alert "Vuoi chiudere l'applicazione(sì) o continuare (no)?"
Controller -> Game: setDecisionButtons()
Game --> Player: showDecisionButtons()
alt yes
    Controller -> Game: closeGame()
    Controller --> Player: exitGame()
else no
    Controller -> Game: initializeGame()
    Controller --> Player: newGame()
end


end
@enduml


@startuml

title initialize()

skinparam backgroundColor #f9f9f9
skinparam participantBackgroundColor #e0f7fa
skinparam participantBorderColor #00796b
skinparam sequenceArrowColor #00796b
skinparam sequenceBoxBackgroundColor #b2dfdb
skinparam sequenceBoxBorderColor #004d40
skinparam criticalBackgroundColor #ffcdd2
skinparam criticalBorderColor #b71c1c
skinparam altBackgroundColor #d1c4e9
skinparam altBorderColor #512da8

actor Player
participant Controller
participant Game

Player -> Controller : initialize()
alt playerImageView != null
    Controller --> Controller : remove(playerImageView)
    Controller --> Controller : playerImageView = null
end
Controller -> Game: setConfiguration(false)
Controller --> Player: showPlayer(true)
Controller --> Player: showGuard()
Controller --> Player: startGame()
@enduml
