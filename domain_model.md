@startuml
object Game {
    winCondition
}
object Player {
}
object Room {
    name
}
object Guard{
}
object Museum{
}
object Tool{
     name
}
object Artwork{
     name
     value
     type
}
object Timer{
}
object Backpack{
     weight
}

Game "1" *-- "1" Player
Game "1" *-- "1" Museum
Game "1" *-- "1" Guard
Game "1" *-- "1" Timer
Player "1" --> "1" Backpack : owns
Player "1" --> "0..1" Tool : uses
Player "1" --> "0..*" Artwork : steals
Player "0..1" -- "1" Room
Artwork "*" --* "1" Room : contains
Artwork "*" --> "1" Tool : requests
Artwork "0..*" <-- "1" Backpack : contains
Room --> Room : connected to 
Room "1" <-- "0..1" Guard : patrols
Room "12" --* "1" Museum : is composed by



@enduml
