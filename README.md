# Gridbased-Engine
This is a simple, easy to use, independent game engine written in Java.
There are no physics implemented, so if you're looking for more complex physics than you're willing to write yourself, then this engine isn't for you!
This is designed for only one player, although it would probably take very little work to change that.

## How it works (roughly) :
### Controllers:
##### Classes which directly control the engine
 * The [Game Controller](../master/src/me/hii488/controllers/GameController.java) class is the 'top level' class. It is where the game is started and ended from, the order of starting methods are:
    * setupEngine()
    * <Any init classes (see below) and setting changes>
    * loadWindow()   <can be done with either (title, width, height), or a premade [Window](../master/src/me/hii488/graphics/Window.java)>
    * startGame().
  
  
 * The [Initialisation Controller](../master/src/me/hii488/controllers/InitialisationController.java) which is discussed below.
 
 
 * The [Update Controller](../master/src/me/hii488/controllers/UpdateController.java) which helps synchronise the [Tick](../master/src/me/hii488/controllers/TickController.java) and [Render](../master/src/me/hii488/controllers/RenderController.java) controllers. None of these need to be touched.

### Handlers:
##### Classes which only do anything when the game 'ontop' requests it to be done.
 * The [InputHandler](../master/src/me/hii488/handlers/InputHandler.java) class is arguably a controller, in that it acts on its own to give all registered IInputUsers updates as soon as they come.
 
 
 * The [Entity Handler](../master/src/me/hii488/handlers/EntityHandler.java) class is mostly for internal use. None of the methods modify passed arguments.
 
 
 * The [Container Handler](../master/src/me/hii488/handlers/ContainerHandler.java) class handles the loading of levels (ie [BaseContainer](../master/src/me/hii488/objects/containers/BaseContainer.java)s).
    * Use addContainer to add a container without loading it
    * If you pass loadNewContainer a container which is not already added to the list of containers it is added automatically
    * The Render Container methods are more for internal use, and can be safely ignored for the most part.
    
    
 * The [File Handler](../master/src/me/hii488/handlers/FileHandler.java) class.
    * It has two different methods for reading from/writing to files.
    * serialize() and deserialize() need to be passed objections which implement Serializable
    * readFile() and writeFile() read/write the exact strings given.
    * If writing data which you don't wish to be modified by the average user, the serialized versions are recommended, although they do not stop a 'tech savvy' user from modifying data.
    
    
 * The [TextureHandler](../master/src/me/hii488/handlers/TextureHandler.java) class handles all the loading and storing of images.
    * To load an image, a path, image name, object calling the method, and key must be given.
    * The object calling the method is unimportant, but helps with error messages
    * The key given is what you call the image when calling getTexture(String key)
    * If an image with key provided is not loaded, a default image is returned.
    * Any images which fail to load have their details stored so they can be accessed easily at a later time.
    
### Updating:
 * The game is updated by the [UpdateController](../master/src/me/hii488/controllers/UpdateController.java), this syncs and controls when tick() and render() are called.
 * Updating only starts once startGame() in the game controller is called.
 * The update rate is defined in the [Settings](../master/src/me/hii488/general/Settings.java) class as TargetTPS. (Standard is 30 updates/second.)
 * The update rate will automatically be lowered by 5% if problems start occurring, it **does not** automatically raise itself when lowered.

##### Ticking:
 * Only the loaded container is ticked, all others (including the render container) are ignored.
 * Any extra classes which need to be ticked should implement ITickable and be added to either ArrayList in the TickController. (Early ticking means before the container, late is after)

##### Rendering:
 * Only objects in the render container are rendered, the loaded container is ignored to avoid attempting to render things halfway through an update.
 * When entities are copied from the loaded container to the render container, they are converted into a [RenderEntity](../master/src/me/hii488/objects/entities/RenderEntity.java), and so any overrides of their render methods will do nothing, instead you must override the createRenderEntity() method extended from [BaseEntity](../master/src/me/hii488/objects/entities/RenderEntity.java) and possibly provide a subclass of RenderEntity.
 * Tiles are rendered from their render() method, this may change to match the Entities. 
 * A GUI handler is implemented, if you want to make a simple button game screw the rest of the engine, use these.
    * All elements of a GUI must extend GUIElement
    * All elements automatically receives a notification when clicked (onClick()) or a key is typed (onKeyTyped()).
    * Use GUIPriority to set the order of overlapping elements
    * Each element can be hidden/shown separately and will not receive notifications when hidden.

### Initialisation:
- To get the engine to load your code, you need to initialise it
- To add a class to [InitilisationController](../master/src/me/hii488/controllers/InitialisationController.java)'s queue, it must extend [IInitilisation](../master/src/me/hii488/interfaces/IInitilisation.java)
- Initialisers must be added <i>before</i> the engine is started.
- All Entities and Tiles must be instantiated in some way to ensure they are added to their registers.

## Examples:
- [Space Invaders](https://github.com/hii488/Space-Invaders)
- [Scrolling Shooter](https://github.com/hii488/Scrolling-Shooter)
- [Volcano Rush](https://github.com/hii488/VolcanoRush)

##### Volcano Rush uses the most recent version of the engine, it is probably the best "large scale" example.
######If you make something with this engine, send me a message and I'll add it here!


TODO:
- Improve how registering is done.
- Add a way to make layouts of containers with images of some kind? (v low priority)
- Sounds