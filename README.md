# Hollow Night

## A pathetic remake of [Hollow Knight](https://www.hollowknight.com/) by [Team Cherry](https://www.teamcherry.com.au/) made in Java using AWT (Abstract Windowing Toolkit)

## Installation
* You can find the latest compiled version in ***Releases*** tab.

* In case you want to compile it yourself:
```
git clone https://github.com/pogodemon/HollowNight.git
```
```
cd HollowNight
```
```
gradle build
```
Output will be ```./build/libs/HollowNight.jar```. You can run it with:
```
java -jar build/libs/HollowNight.jar
```

## Save Files:
* Windows: ```%USERPROFILE%/hollow_night_savefile.dat```
* Linux: ```$HOME/hollow_knight_savefile.dat```

## Configuration:
* Windows: ```%USERPROFILE%/HollowNight.cfg```
* Linux: ```$HOME/HollowNight.cfg```

# Credits:
* u/**Sumwann** for datamined [assets of Hollow Knight](https://www.reddit.com/r/HollowKnight/comments/cf83u1/all_hollow_knight_sprites_as_of_version_1432/?utm_source=share&utm_medium=web2x&context=3)
* **The Embraced One** for [detailed map information](https://www.hallownest.net/)
* **CodeNMore** for [beginner Swing tutorials](https://youtube.com/playlist?list=PLah6faXAgguMnTBs3JnEJY0shAc18XYQZ)

## Origin of the project:
Back in February 2022 I decided to take on any Java project to improve my programming skills. I chose this as both Hollow Knight and game development were of my interest.
It was originally supposed to be a complete remake with entire Hallownest and Godhome.
I then realised I was way too ambitious and set my goals to be just Forgotten Crossroads, similar to the official demo.
I then abandoned the project about 75% in as it took too much of my time and I felt burnt out.

## Content:
I added a lot of stuff. Missing things include:
* Menu
* False Knight
* NPCs
* Grubs
* Dialogue (lore tablet, npc)
* Room textures
* Audio
* Interiors (Iselda's shop, Ancestral Mound)
* Some hazards and stalagmites in Crossroads

# Limitations:
* It is completely 2D, like a glorified slideshow of PNGs, unlike [Hollow Knight which is actually 3D](https://unity.com/madewith/hollow-knight).
* It doesn't leverage GPU at all, everything is done with CPU.
* It is completely single-threaded, so it will probably run properly only on modern hardware.
* I tried adding audio, but single-threaded nature of the project made the game stutter a lot when any audio started playing, so I scrapped the idea.

I plan to come back to making games just as hobby. I have very good ideas for a mash-up of Sekiro and Hollow Knight, starring Quirrel.
(Idea stolen from [r/HollowKnightMemes](https://www.reddit.com/r/HollowKnightMemes/comments/nw5nbx/what_we_realy_are_awaiting_for/?utm_source=share&utm_medium=web2x&context=3))
