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

Player -> Game: move(direction)
Game --> Controller: movePlayer(direction)
Controller -> Game : showPlayer(updatePosition)
Game --> Game : calculate coordinates (centerX, centerY)
Game --> Game : setLayoutX(centerX)
Game --> Game : setLayoutY(centerY)
Game --> Controller : checkGuardAndPlayer()
alt Guard and Player in same position
    Controller -> Game : game.get_timer().decrement(WASTED_TIME)
    Game --> Player: alert "La guardia ti ha trovato, hai perso 5 secondi"
end
alt updatePosition is true
    Controller -> Game : game.updatePosition(player.get_x_coord(), player.get_y_coord())
end
Game --> Player: showUpdatedImage()

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
participant Game
participant Controller


alt timer ran out
Controller -> Game: timerFinished()
activate Controller
Game --> Player: alert "Hai perso. Il tempo è scaduto"
Game --> Player: alert "Vuoi chiudere l'applicazione(sì) o continuare (no)?"
Controller --> Game: setDecisionButtons()
alt sì
    Controller --> Game: closeGame()
    Game --> Player: exitGame()
else no
    Controller --> Game: initializeGame()
    Game --> Player: newGame()
    deactivate Controller
end


end
@enduml



@startuml

title setConfiguration(isFromLoadCommand)

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
participant Game
participant Controller
participant Timer
participant CloudHandler
participant JacksonService

Player -> Controller: load() OR initialize()
alt game != null
    Controller --> Game : stopTimer()
end
Controller -> CloudHandler : getAvailableConfigurationList()
alt availableConfiguration is null or empty
    Controller --> Player: alert "Non hai configurazioni salvate"
    Controller -> JacksonService : deserializeGame(JSON_CONFIGURATION_PATH)
    Game -> Game : setGame()
end
alt availableConfiguration is not null and not empty
    Controller --> Player : loadNewConfiguration = showConfirmationAlert()
    alt loadNewConfiguration is true OR isFromLoadCommand is true
        Controller -> CloudHandler : loadGame(selectedConfiguration)
        Game -> Game : setGame()
    else
        Controller -> JacksonService : deserializeGame(JSON_CONFIGURATION_PATH)
        Game -> Game : setGame()
    end
end
Controller -> Timer : startTimer()
Controller --> Player: startGame()
@enduml
