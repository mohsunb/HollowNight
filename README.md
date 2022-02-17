# Hollow Night

An attempt at remaking "Hollow Knight" from Team Cherry for a project aimed at improving my OOP/Java skills.

## Update 17-Feb-2022: v0.0.4
* Added spike objects
* Players can now pogo on spikes
* Spikes cause respawn
* Added hazard respawn areas which change the hazard respawn point
* Slash duration shortened to prevent continuous pogos by spamming attack
* Getting hit by enemies now has its own sprite
* Knockback caused by enemies now depend on which side of them you are at, not which way you are facing
* Added proper spike hazards to "King's Pass"

## Update 16-Feb-2022: v0.0.3
* Added collision detection between entities
* Added ability to attack in all directions
* Added ability to damage and kill entities
* Horizontal slashing sprites now alternate
* Added ability to pogo (downslash and get pushed up)

## Update 14-Feb-2022: v0.0.2
* Refined player movement and dash speed
* Refined dash length
* Added hard-fall event
* Added new sprites for walking, jumping, dashing, falling and hard-falling
* Created "King's Pass" collision map
* Added some HUD elements

## Update 14-Feb-2022: v0.0.1a
* Improved entity grounded state detection
* Fixed a bug with jumping where pressing jump mid-air after falling off of a platform and holding it would result in a jump upon landing
* Added ability to dash a second time mid-air after dashing off of platforms

## Update 13-Feb-2022: v0.0.1
* Added map collision detection for entities
* Added gravity
* Added jumping ability (bugged; falling off of a platform and holding jump button mid-air will result in a jump upon impact)
* Added dashing ability (incomplete; cannot dash twice off platforms)
* Image based map generation (colorspace has to be grayscale)
