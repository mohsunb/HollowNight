## Update 03-June-2022: Major Change 2 (v0.4.8)
***Additions:***
* Save file system
* Default save files
* Parrying system (improper)
* Dashing twice off walls
* Collapsing floor and breakable wall textures
* Dirtmouth entrance door event
* Animations for Vengefly
* Broken geo rocks and breakable walls are saved
* Lifeblood absorption animation
* Multidirectional room transitions
* Complete Forgotten Crossroads map
* Tiktik enemy
* Husk Bully enemy
* Hornhead Husk enemy
* Leaping Husk enemy
* Gruzzer enemy

***Changes:***
* Particle effect of hitting walls and enemies is now displayed properly
* Collected items' name now use the proper font
* Crawlids are affected by gravity after death
* Crawlids no more turn around after hitting a wall during knockback
* Increased wall cling fall speed
* Increased minimum fall height required for hardfalls
* Decreased enemy knockback

***Bugfixes:***
* Fixed soul vessel displaying less soul
* Fixed Vengeful Spirit not resetting fall speed
* Fixed spikes sparking more than necessary
* Fixed a bug where you could buffer attacks during dashes
* Fixed a bug where player could get stuck in double jump animation

## Update 7-May-2022: Major Change 1 (v0.3.6)
***Additions:***
* Implemented proper particle system
* Added Dirtmouth
* Added room transitioning
* Added missing spell interactions to some enemies
* Added option to hide mouse cursor
* Added slashing animations
* Added walking animation
* Added benches
* Added respawn system
* Added death animation
* Added custom window icon
* Added custom mouse cursor
* Added "Perpetua" and "Trajan Pro" custom fonts
* Added regular and shadow dashing animation as well as its particles
* Added double jump animation
* Added visual and particle effect for having low health
* Added Shade enemy
* Added liquid animation to the soul vessel
* Implemented "GameFlags" class for easier and more efficient game state management
* Added particle effect for getting hit while having Lifeblood
* Added ability to mirror images through code
* Added superdash animation
* Added particle effect for hitting walls and enemies
* Added particle effect for collecting geo
* Added particle effect for hitting spikes

***Changes:***
* Massively improved implementation of World system
* Improved wall climb system
* Decreased screen shake
* Reduced horizontal bounce of geo
* Double jump is now slightly delayed to simulate inertia
* Interactable items are now rendered in front of the player
* Generalized the method of player getting hit
* Particle effect of taking damage is now displayed according to facing direction
* Generic breakable walls are now not climbable
* Reduced player slash active hitbox duration

***Bugfixes:***
* Fixed a bug with spikes where player wouldn't stop pogo'ing
* Fixed timing of damage dealt by hazards
* Fixed a bug with the engine where the window wouldn't scale properly if the "Display Scaling" setting in the OS is not 100%
* Fixed a bug with the pogo system where you could pogo twice
* Fixed a broken vertical camera lock in King's Pass

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