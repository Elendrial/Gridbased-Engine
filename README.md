# Gridbased-Engine
This is a simple, easy to use, independant game engine written in Java.
There are no physics implemented, so if you're looking for more complex things than you're willing to write yourself, then I'm afraid this engine isn't for you!
This is designed for only one player, although it would probably take very little work to change that.

## How it works (roughly) :
###### _The information below will not be up to date until V3 is considered nearing completion._
### Hierarchy:
* The [Game Controller](../master/src/me/hii488/controllers/GameController.java) class is the top level class. It is where the game is started and ended from.
  * The [Player](../master/src/me/hii488/objects/entities/Player.java) object.
    * KeyListener and MouseListener for the active [Window](../master/src/me/hii488/gameWindow/Window.java)..
  * The active [Window](../master/src/me/hii488/gameWindow/Window.java).
    * This [Display](../master/src/me/hii488/gameWindow/Display.java) class, which is the "top" level of the render call, _and will handle GUIs_.
  * The [Containers](../master/src/me/hii488/gameWorld/baseTypes/GeneralWorldContainer.java).
    * A list of [Entities](../master/src/me/hii488/objects/entities/GeneralEntity), including the Player entity if the container is loaded.
    * A [Grid](../master/src/me/hii488/gameWorld/baseTypes/Grid.java), which contains [Tile](../master/src/me/hii488/gameWorld/baseTypes/Tile.java)s, which each have a [TileType](../master/src/me/hii488/objects/tileTypes/BaseTileType.java)
    * Has an ID so calling the correct Container is possible.

### Ticking:
- The game is updated every tick by the [TickController](../master/src/me/hii488/gameWorld/TickController.java).
- The TickController only starts when startGame() method is called.
- The tick rate is defined in the [Settings](../master/src/me/hii488/general/Settings.java) class
- The tick rate will automatically be lowered if problems start occuring, it **does not** automatically raise itself when lowered.
- Will only tick what has been added to the World class, and additional ITickable's added to the ArrayList in the TickController.

### Rendering:
- Each object that needs rendering has it's own render() method.
- A proper Gui handler is not implemented yet, but will be shortly (if I'm prodded enough).
- Is threaded seperately from Ticking.

### Initilisation:
- This is mainly for large projects
- To add a class to the Initilisation queue, it must extend [IInitilisation](../master/src/me/hii488/gameWorld/initilisation/IInitilisation.java) and added to [WorldInitilisation](../master/src/me/hii488/gameWorld/initilisation/WorldInitilisation.java)'s list

## Examples:
[Space Invaders](https://github.com/hii488/Space-Invaders)
######If you make something with this engine, send me a message and I'll add it here :)
