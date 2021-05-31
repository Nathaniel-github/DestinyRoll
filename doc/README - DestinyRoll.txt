Destiny Roll

**Authors**: Nathaniel Thomas, Jay Paek

**Revision**: 5/24/2021

**<span style="text-decoration:underline;">Introduction</span>**: 

Destiny Roll: Final Wish is a turn based role playing game (RPG) implemented with a gacha character rolling system. The game will have a story mode. Players will be able to choose 3 starting characters from an initial 20 to build their first team.

Each character will have unique abilities and stats (which will be explained and listed in features list). 

The main stats are as follows:



*   Health points (amount of health points)
*   Mana points (amount of mana points)
*   Starting mana (amount of starting mana)
*   Mana per charge (amount of mana restored per “charge”)
*   Basic atk (amount of damage done by basic atk)
*   Special atk/heal damage (amount of damage/healing done by special move)
*   Ultimate atk/heal damage (amount of damage/healing done by ultimate move)
*   Basic attack range (character’s abilities to attack only the front of the 3 characters or the back)
*   Special attack range
*   Ultimate attack range
*   Basic atk Defense (damage reduction for basic atks)
*   Special defense (damage reduction for special/ultimate attacks)

Each character unit has five possible actions: block, basic atk (rq 1 mana), special ability (rq 2-5 mana), ultimate ability (rq 4+ mana), charge (+1 mana), or do nothing. Players can set up their teams with 3 different characters with different abilities. Each character can cast one action per turn towards 1 or more targets. Basic attack range is implemented so that squishy damage dealign carry revolutionaries can not be one shot.

Players can select 3 characters, or revolutionaries (name assigned for in-game), to fight against peons, minions, and bosses. All sprites will either be low frame rate gifs from open source sprites or real people.Each sprites will have 3 animation states, attack animation, idle animation, and a flinch animation (when taking damage). 

Every levels will have a mini-boss or boss, which is just a climax in the game (nothing special other than big damage and flashy sprites)

Another main part of the game is the character gacha rolling system. This will be the main way in which players can obtain new characters. Players can use in-game currency to get a chance to obtain another character. Character drawing is based on complete RNG, which will induce rage from players not drawing the character they want. 

**<span style="text-decoration:underline;">Instructions</span>**:

[Explain how to use the program. This needs to be **specific**: 

Which keyboard keys will do what? 

Where will you need to click? 

Will you have menus that need to be navigated? What will they look like? 

Do actions need to be taken in a certain order?

Opening Screen:

	The opening screen will have some trailer animations with some music. When the player presses any key or mouse button, the opening screen will change to the home screen.

Home Screen:

	The home screen will consist of 3 buttons: Play, Collection, and Options. (subject to change in the course of development

	Clicking on the Play button will lead to the GameSelection Screen.

	Clicking on the Collection button will lead to the Collection Screen.

	Clicking on the Options button or pressing the [esc] key will summon a small Panel with options to adjust game volume or watch the Tutorial

GameSelection Screen:

	The Game Selection Screen will have 1 main button: Story Mode. 

	Clicking on the Story Mode button will lead to the LevelSelection Screen.

	It will also have a button to navigate back to the home screen.

	The options hotkey is still usable

LevelSelection

	LevelSelection Screen will be an array of square, each square being a button that will navigate to the BattlePreparation Screen

	It will also have a button to navigate back to the GameSelection screen.

BattlePreparation

	Players will be able to select 3 revolutionaries they would like to fight in battle. Click and holding on a revolutionary’s profile will invoke a popup showing the stats of that revolutionary. When 1 or more revolutionaries are selected, the player can press the confirm button that will lead them to the Battle Screen

Battle Screen

	Players will engage in battle with enemies. 

Prep Phase:

A player will be able to invoke one action per character, a maximum of 3 actions. Players can select the action via the interactive user interface below the battlefield. Character HP and MP bars will be present throughout the battle. After the player select an action for each character and target enemy, he/she can lock in their choices and proceed to the Battle Phase

Battle Phase:

All damage, healing, and mana regeneration is calculated here. It will also invoke attack animations from the characters.

Closing Phase:

By analyzing the changes in health, the game will proceed to despawn enemies or revolutionaries to declare them as “dead.” After characters die, it will check if either side has no enemies standing. If both sides have characters alive, the game will proceed to the next turn. Otherwise, it will determine the victor. Victory screen will display the rewards and loot won from the battle, then present a button to go back to the Level Selection screen. Otherwise, the screen will dim, fade and then redirect to the Level Selection Screen.



Collection Screen:

	The Collection page will just display an Array of revolutionaries. Each unlocked revolutionary will have a color profile, and locked revolutionaries will have a dark silhouette on the profile. Users can access the stats for unlocked characters by clicking on the revolutionary profile but not for locked characters. There will be a button to return to the home page.

Overall, there will not be many hotkeys to navigate, though they may be implemented later



**<span style="text-decoration:underline;">Features List (THE ONLY SECTION THAT CANNOT CHANGE LATER)</span>**:

**Must-have Features**:

[These are features that we agree you will _definitely_ have by the project due date. A good final project would have all of these completed. At least 5 are required. Each feature should be fully described (at least a few full sentences for each)]



*   Story Mode: \
There will not be a real story, but levels in this mode would be a classic “harder as you go” with bosses every few stages. 
*   Levels:

    Level information will be loaded via db queries. Players will earn rewards for clearing each stage relative to the number of stars they achieve in the level. Maximum completion (or three stars) would be clearing the stage in a certain number of turns. Some levels will result in a loss if not completed in a certain number of turns.

*   Character selection system \
Right before entering battle, the user will be able to select up to 3 desired characters to fight in battle. By holding the character profile, the user can see the stats of the character.
*   RPG battle system: \
The main game will be a turn based RPG battle system similar to the popular hand-game fireball. There will be five actions: block, basic atk (rq 1 mana), special ability (rq 2-5 mana), ultimate ability (rq 4+ mana), charge (+1 mana), or do nothing. Players can set up their teams with 3 different characters with different abilities. There will be a schematic background with 3 revolutionaries and 3 enemies drawn in the upper side of the screen called the battlefield. Then there will be an interactive UI for the player to select character actions.
*   In game currency system: \
Players can earn in game currency by clearing story mode levels, completing quests, or leveling up. They can spend in game currency on claiming more characters, leveling up characters, [or unlocking new items]
*   Gacha Roll System: \
This will be the main way in which players can obtain new characters. Players can use in-game currency to get a chance to obtain another character.
*   Character Gallery (Collection):  \
There will be a page where players can view their characters and view character stats

    There are 7 tiers:  \
Common, Uncommon, Rare, Super Rare, Ultra Rare, Legendary, Divine

*   Settings:  \
Settings/option will be in game to disable sound and music
*   Character gif rendering for 20 characters
*   Energy system:  \
Players will have a certain amount of energy which would be expended by playing the main game. Energy can recharge over time or by in game purchases. This will prevent the player from speedrunning the entire game and maintain game retention.
*   Online database:

    Mongo db database to store and access info easily


**Want-to-have Features**:

[These are features that you would like to have by the project due date, but you’re unsure whether you’ll hit all of them. A good final project would have perhaps half of these completed. At least 5 are required. Again, fully describe each.



*   Mini games:  \
Mini games would be simple games, such as outer space shooting, run-jump platformer, or racing, implemented as a side game for players. Players can entertain themselves in a simple way to earn small rewards and more energy to further play the game.
*   Daily rewards: \
Every day, players can earn small rewards/equivalent to the “free chest” in clash royale. Players will have a miniscule chance to win a new character, but mostly it will be in game currency or more energy
*   Items and item management (item enhance): \
By using in game currency, players can upgrade their revolutionaries to improve the stats. This will increase the survivability and damage output for later phases in the game. Characters can only equip a certain amount of items to boost their stats
*   Character management (level up features): \
By using in game currency, players can upgrade their revolutionaries to improve the stats. This will increase the survivability and damage output for later phases in the game.
*   More characters
*   Crowd control effects:

    Effects on skills such as stun/petrify/freeze that prevent the enemy from acting next turn

*   Actual story dialogue:

    Before or after each level, there will be cutscenes with textbox dialogue to progress the game’s storyline.

*   Endless mode: \
Endless mode will be a game mode where there will be waves of enemies, each wave getting stronger than the previous. Every few waves, there will be a boss. Players will use their team comp to fight through the enemy waves and make high scores. Each damage dealt to the enemy will be one point. Every 1000 points or so will provide the player with a one time prize.
*   Data encryption: \
Encrypt game data so that players can not inspect code and change values to cheat.
*   Tutorial: \
Implement a tutorial video at the opening screen. If the player does not interact with the opening screen for more than 10 seconds, then it will play the tutorial video. Additionally, players can watch the tutorial video in the options menu.


**Stretch Features**:

[These are features that we agree a _fully complete version of this program would have, but that you probably will not have time to implement_. A good final project does not necessarily need to have any of these completed at all. At least 3 are required. Again, fully describe each.]



*   Advanced Character management (skill tree - leveling skills to unlock abilities):

    Each character will have unique abilities that you can unlock using resources such as exp and materials. These skills allow for abilities like insta-heal or increased damage to be passively activated during battle.

*   Different skins for characters:

    Each character will start with the standard default look but some may be able to have a special skin/look that can be unlocked and equipped. Can be obtained by insane amounts of in game currency


**<span style="text-decoration:underline;">Class List</span>**:

[This section lists the Java classes that make up the program and very briefly describes what each represents. It’s totally fine to put this section in list format and not to use full sentences.]



*   Assets
    *   Constants.java - A class to hold some values that are constant throughout runtime
    *   Character.java - A class that represents a character and holds stats and image data about them
    *   RippleCursor.java - A class that handles all the functionality of a ripple that will follow the user's cursor around
    *   Character.java - A class that represents a revolutionary. It holds their stats, image, and other info
    *   Enemy.java - A class that represents an Enemy
    *   Player.java - A class that represents this user. It keeps track of general resources and data of this player
    *   StatsBox.java - A class that represents a statistics box for a character. It can be drawn to a PApplet
*   Core
    *   ClickEvent.java - An interface used to allow the EventHandler trigger anything that relies on a click event 
    *   DragEvent.java - An interface used to allow the EventHandler trigger anything that relies on a drag event 
    *   Event.java - This is the class that is passed into any triggered events. It contains necessary information about the game state during the time of the event.
    *   EventHandler.java - This class handles all events that are triggered in the PApplet and filters them to any listeners on those events
    *   FadeGif.java - This class uses the existing PGif class and adds fading functionality to it
    *   FadeImage.java - This class uses the PImage class from processing and adds fading capability to it
    *   FadeVideo.java - This class uses the Movie class from processing and adds fading capability to it
    *   Fader.java - This is the abstract class all faders extend from, it gives the backbone functionality of tinting the window and ticking the fade along with other functionalities
    *   PButton.java - This class represents a button that can be added to a PApplet. It requires the EventHandler in order for its events to be triggered
    *   PGif.java - This class uses the OpenImaging GifDecoder to make a gif that is drawable to a PApplet
    *   Screen.java - This is the interface that must be implemented by all Screens that are added to the ScreenManager
    *   ScreenManager.java - This class manages all the screens in the game and decides which one should be drawn and whenlibraries handle
    *   SoundPlayer.java - A class that plays wav files because that is all java supports
*   Net
    *   ### NOT USED ### ClientPacket.java - This class holds all the data that the client sends to the server ### NOT USED ###
    *   ### NOT USED ### MyListener.java - A class that listens on the port and forwards data received ### NOT USED ###
    *   ### NOT USED ### PacketHandler.java - This class handles all incoming and outgoing packets between client and server ### NOT USED ###
    *   ### NOT USED ### ServerPacket.java - This class holds all data that the server sends to the client ### NOT USED ###
    *   MongoHandler.java - This class allows you to easily make mongo queries that are necessary for the code to run
*   Panels
    *   HomeScreen.java - A class that represents the initial home screen that all users start on when loading into the game
    *   OpeningScreen.java - This class represents the opening/startup screen for the game
    *   BattlePrepScreen.java - This class represents the prep screen for battle
    *   BattleScreen.java - This class represents the actual battle screen
    *   LevelSelectScreen.java - This class represents the screen that allows you to select the level you want to play
    *   MainScreen.java - This class represents the main screen that users see when playing the game
    *   Window.java - This class represents the window that the game is displayed in. It extends from PApplet and forwards all necessary events to the EventHandler and ScreenManager
    *   GachaScreen.java - Gacha Screen is a class that draws everything related to the banner and handles anything related to drawing characters
    *   GachaResultsScreen.java - GachaResultsScreen is the panel that handles drawing everything that shows once you draw a character. It is also in charge of handling all events that can take place on this screen
    *   GalleryScreen.java - GalleryScreen is the panel that draws and handles all events related to the character gallery
*   Main
    *   Main.java - Main method to run the PApplet

**<span style="text-decoration:underline;">Credits</span>**:

[Gives credit for project components. This includes both internal credit (your group members) and external credit (other people, websites, libraries). To do this:



*   List the group members and describe how each member contributed to the completion of the final program. This could be classes written, art assets created, leadership/organizational skills exercises, or other tasks. Initially, this is _how you plan on splitting the work_.
*   Nathaniel Thomas:
    *   Server sided code and database management
    *   Library classes and backend programming
*   Jay Paek: 
    *   Client sided graphics and user interfaces
    *   Project resources and code flow
*   Give credit to all outside resources used. This includes downloaded images or sounds, external java libraries, parent/tutor/student coding help, etc.]
*   Kryonet
*   Processing
*   [https://www.spriters-resource.com/](https://www.spriters-resource.com/)
*   MongoDB
*	[https://www.youtube.com/channel/UCpQUBJrRfcpWLro4Oziff7Q](https://www.youtube.com/channel/UCpQUBJrRfcpWLro4Oziff7Q)
*	[https://www.youtube.com/channel/UCyW-leqPXUunrXXxFjpZ7VA](https://www.youtube.com/channel/UCyW-leqPXUunrXXxFjpZ7VA)
*	https://genshin.mihoyo.com/
*	https://www.google.com/searchq=settings+button&sxsrf=ALeKk00H3ETJJ5VBctOIP8tXtCvwvrTS_w:1621817463242&source=lnms&tbm=isch&sa=X&ved=2ahUKEwjivaXujOHwAhWaJTQIHYVTCWQQ_AUoAXoECAEQAw&biw=1920&bih=918#imgrc=BLOXP2oQtUFN3M

https://www.google.com/search?q=star+png&sxsrf=ALeKk01h1Ef-NeX_fdOh_x62qFLs80fqBw:1621891219545&source=lnms&tbm=isch&sa=X&ved=2ahUKEwiU-ITQn-PwAhVRDzQIHU0eDZYQ_AUoAXoECAEQAw&biw=1920&bih=975#imgrc=ta9naw4eid5UBM

https://www.walpaperlist.com/2020/01/anime-wallpaper-gif-4k.html

https://wallpapercave.com/chill-anime-pc-wallpapers
