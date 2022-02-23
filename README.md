# Hollow Night

An attempt at remaking "Hollow Knight" from Team Cherry for a project aimed at improving my OOP/Java skills.

To play the game simply clone the repository into your preferred IDE and run Launcher.java. I am using IntelliJ IDEA and cannot guarantee that it will work with other IDEs.
You can change the launch options in the main class. The game currently only works ***properly*** at 1920x1080 resolution, locked at 144FPS as the game physics malfunction otherwise. A fix to this will be issued in the future.

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
