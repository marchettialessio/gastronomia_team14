@startuml
class Game {
    - winCondition: double
    + checkWinCondition(): boolean
    + start(): void
    + updateGuardPositions(): void
}
class Player {
    + move(direction: String): void
    + viewMap(): void
    + viewRoomArtefacts(): void
    + viewRequiredTools(): void
    + depositArtefacts(): void
    + checkTime(): void
    + escapeGuard(): boolean
}
class Room {
    - name: String
    + getConnectedRoom(direction: String): Room
}
class Guard{
    - currentRoom: Room
    + moveRandomly(): void
}
class Museum{
    + getRoomByName(name: String): Room
}
class Tool{
    - name: String
}
class Artwork{
    - name: String
    - value: double
    - type: String
    + canBeStolen(tool: String): boolean
}
class Timer{
}
class Backpack{
    - weight: Integer
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
