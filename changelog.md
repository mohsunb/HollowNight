<<<<<<< HEAD
=======
## Update 7-May-2022: v0.3.6
* Fixed a bug where shade would spawn inside hazards and die immediately if the player also died to the same hazard previously
* Fixed a bug where shade would spawn inside walls
* Fixed a bug where shade could still do contact damage in death animation
* Fixed a bug where shade could attack during death
* Added shade absorption animation
* Improved soul animation
* Reimplemented map loading system to depend on "GameFlags"
* Player can now respawn lying on ground (without benches)
* Fixed a bug where camera would flicker briefly after respawning
* Fixed a bug where maximum soul amount would still be cut even after the shade was killed
* Particle effect of taking damage is now displayed according to facing direction
* Death particle effect is now rendered above the world
* Generic breakable walls are now not climbable
* Fixed animation speed of player's slashes
* Fixed a bug where you could stop superdash during room transitions
* Added ability to mirror images through code
* Added superdash animation
* Added particle effect for hitting walls and enemies
* Added particle effect for collecting geo
* Added particle effect for hitting spikes
* Reduced player slash active hitbox duration

## Update 5-May-2022: v0.3.5
* Fixed a bug with pogo system where you would pogo twice
* Added Shade enemy (no spell attacks)
* Improved death animation
* Implemented "GameFlags" class
* Added soul animation for the soul vessel
* Added particle effect for getting hit while having lifeblood
* Fixed a vertical camera lock in King's Pass
* Interactable items are now rendered in front of the player
* Generalized the method of player getting hit

## Update 2-May-2022: v0.3.4
* Added nail and shell props to death animation
* Added shade spawning animation upon death

## Update 28-Apr-2022: v0.3.3
* Adjusted low hp particle effects
* Adjusted shadow dash animation
* Added double jump animation
* Adjusted double jump timing

## Update 27-Apr-2022: v0.3.2
* Added void particles to shadow dash

## Update 27-Apr-2022: v0.3.1
* Added shadow dashing animation (incomplete, lacks particle effects)

## Update 27-Apr-2022: v0.3.0
* Added dashing animation (regular only)

## Update 26-Apr-2022: v0.2.9
* Added void particles to the player at low health
* Reduced geo horizontal bounce

## Update 26-Apr-2022: v0.2.8
* Added death animation (incomplete, lacks post props)

## Update 25-Apr-2022: v0.2.7
* Fixed a bug with the engine where the window wouldn't scale properly if the "Display Scaling" setting in the OS is not 100%
* Decreased screen shake to more sane levels
* Added "Perpetua" and "Trajan Pro" fonts
* Added mouse cursor
* Added window icon
* Added respawn system (no animation)

## Update 24-Apr-2022: v0.2.6
* Added benches (incomplete)

## Update 24-Apr-2022: v0.2.5
* Added slashing animation to the player model (vertical)

## Update 24-Apr-2022: v0.2.4
* Added slashing animation to the player model (horizontal only)

## Update 24-Apr-2022: v0.2.3
* Added walking animation (🤩)

## Update 23-Apr-2022: v0.2.2
* Fixed the timing of damage dealt by hazards
* Added room transition visual effects
* Fixed a bug with room transitions where you could buffer input during the fadeout
* Added slashing animations
* Fixed a bug with hazards where player wouldn't stop pogo'ing
* Reduced downslash hitbox (😁)

## Update 22-Apr-2022: v0.2.1
* Added the option to hide the mouse cursor
* Added missing spell interaction to some entities
* Added fps counter

## Update 21-Apr-2022: v0.2.0
* Transitioning rooms now retains player state (health, geo, etc.)

## Update 18-Apr-2022: v0.1.9
* Massively improved the world system
* Added Dirtmouth (incomplete)
* Improved wall climb system

## Update 14-Apr-2022: v0.1.8
* Implemented proper particle system (separate from corresponding entities)

>>>>>>> 6aee207 (v0.3.6)
## Update 25-Mar-2022: v0.1.7
* Reimplemented map loading system for organizing assets better
* Added Lifeblood system
* Added Lifeblood cocoons and Lifeseed

## Update 24-Mar-2022: v0.1.6
* Implemented rendering rank system
* Open chests are now rendered properly
* HUD is now rendered above the game itself
* Fixed a bug with the game freeze system which resulted in twice the desired length
* Added visual effect for enemies getting hit
* Improved enemy knockback
* Improved the visual effect for healing
* Fixed a bug with hazard respawns which resulted in late screen shake
* Geo deposits now shake when hit
* Hitting spikes now shakes screen

## Update 23-Mar-2022: v0.1.5
* Chests now have proper hitbox after being opened
* Implemented item system
* Implemented item chests
* Added screen shake
* Game now freezes for a brief time when getting hit

## Update 22-Mar-2022: v0.1.4
* Fixed a bug with the rendering of healing sprites (Windows specific)
* Vengeful Spirit can now be used with the heal button as well
* Fixed a bug where you would use a spell if you had enough extra soul after healing
* Significantly improved the camera system and fixed many inconsistencies and bugs
* Player now cannot cling to the walls while pogo-ing
* Screen now fades to black at hazard respawns

## Update 20-Mar-2022: v0.1.3
* Fixed a bug with hazards that would not set the player to the ground
* Fixed a bug with hazards that would not reset the camera
* Added transparency option to the rendering engine
* Improved knockback when damage shocked
* Reimplemented attacking system
* Down-slash hitbox was decreased due to the previous change
* Fixed a bug with the reimplemented attack system where knockback was too long
* Added a dark flashing effect when the player is invulnerable (after taking damage)
* Added fully functional healing system
* Refined horizontal slash knockback detection
* Added fully functional Vengeful Spirit spell

## Update 17-Mar-2022: v0.1.2
* Fixed a bug with camera lock system
* Refined camera system
* Completely reimplemented hazard system
* Fixed a bug with hazard respawn system where player would not be grounded afterwards
* Added fully functional stalagmites
* Added a missing geo deposit to King's Pass
* Geo mechanics have been refined
* Implemented solid entity collision detection system
* Solid entities can now be treated as tiles (parts of the map)
* Implemented proper soul mechanics
* Added Geo chests (items coming soon)
* Downslash hitbox size increased to improve pogo-ing
* Added collapsing floors
* Added breakable walls
* Refined King's Pass collision map
* Fixed a bugged hazard respawn point in King's Pass

## Update 16-Mar-2022: v0.1.1
* Up-slashing enemies now stops jumping
* Fixed a bug where you could buffer a super-dash when not permitted to
* Vastly improved the AI of crawling enemies
* Refined general enemy knockback
* Added charging animation to super-dash
* Player now has some momentum after finishing super-dash
* Added post superdash player sprite
* Added camera lag effect
* Player can now look up or down by holding corresponding buttons
* Added King's Pass camera locks

## Update 13-Mar-2022: v0.1.0
(I was busy playing Elden Ring, so expect more frequent updates now that I have finished it)
* Game now works properly in a wide range of framerate options: Tested [36 -> 288], it is recommended to choose a framerate that only differs from 144 by factors of two (e.g. 72)
* Refined flying enemy vertical knockback
* Refined geo physics

## Update 24-Feb-2022: v0.0.9
* Added "Vengefly" enemy with proper combat AI
* Added geo deposits
* Fixed a bug where nail hit knockback would persist through dashes

## Update 23-Feb-2022: v0.0.8
* Improved the implementation of entity movement
* Improved the implementation of spawning entities (spawning on runtime)
* Added geo physics
* Killed enemies now drop geo
* Fixed a bug with dashing where you could jump during it
* Fixed a bug with dashing where if you held jump button during dash you would buffer a jump
* Implemented knockback upon hitting walls

## Update 22-Feb-2022: v0.0.7
* Added "Crawlid" enemy with proper wandering AI
* Implemented **Geo** system
* Improved the implementation of loading worlds (rooms)
* Improved the implementation of player getting hit
* Improved the implementation of entities getting hit by player

## Update 19-Feb-2022: v0.0.6
* Added shadow dash
* Added "Wandering Husk" enemy with proper idle and combat AI
* Hitboxes can now be toggled in "Launcher.java"

## Update 18-Feb-2022: v0.0.5
* Added double jump ability
* Double jump resets upon 'pogo's
* Added super dash ability
* Players can also super dash while clinging onto walls

## Update 17-Feb-2022: v0.0.4
* Added spike objects
* Players can now 'pogo' on spikes
* Spikes cause respawn
* Added hazard respawn areas which change the hazard respawn point
* Slash duration shortened to prevent continuous 'pogo's by spamming attack
* Getting hit by enemies now has its own sprite
* Knockback caused by enemies now depend on which side of them you are at, not which way you are facing
* Added proper spike hazards to "King's Pass"

## Update 16-Feb-2022: v0.0.3
* Added collision detection between entities
* Added ability to attack in all directions
* Added ability to damage and kill entities
* Horizontal slashing sprites now alternate
* Added ability to pogo (downslash and get pushed up)
* Dash resets upon 'pogo's

## Update 14-Feb-2022: v0.0.2
* Refined player movement and dash speed
* Refined dash length
* Added hard-fall event
* Added new sprites for walking, jumping, dashing, falling and hard-falling
* Created "King's Pass" collision map
* Added some HUD elements

## Update 14-Feb-2022: v0.0.1a
* Improved entity grounded state detection
* Fixed a bug with jumping where pressing jump midair after falling off of a platform and holding it would result in a jump upon landing
* Added ability to dash a second time midair after dashing off of platforms

## Update 13-Feb-2022: v0.0.1
* Added map collision detection for entities
* Added gravity
* Added jumping ability (bugged; falling off of a platform and holding jump button midair will result in a jump upon impact)
* Added dashing ability (incomplete; cannot dash twice off platforms)
* Image based map generation (color space has to be grayscale)