# JavaRPGEngine
A Java based engine using LWJGL for creating RPG games.

# Compiling and running the game (from Eclipse)
Things are a little more complicated since the move to LibGDX, but ultimately it's worth it. The new build instructions are detailed at the Wiki page located [here](https://github.com/Luigifan/JavaRPGEngine/wiki/Importing-the-Project-Into-Eclipse).

#Our "Roadmap"

So this is a largely incomplete list of "what has to be done".

- "Fixing" player movement 
  - Player movement right now is jerky and accelerates too much (advances too many tiles when holding down movement key)
- Entities
  - Player is not an entity, player is player.
- Correctly implementing the player saves/inventories from Luigifan/C-RPG (classes are written, they just need to implemented PROPERLY
- Entity events (for example, death events, collision events, "chatting" events, etc)
- ~~Drawing text on screen, for god's sake why can't I get this write without it lagging the game? Ffs~~ 
  - [Fixed](https://github.com/Luigifan/JavaRPGEngine/commit/f7f8e19615c2da61f423b9ba15d18cb16a4db9a8)
- Finding a proper direction :point_right:
- Editor needs to be...finished to say the least.
- ~~I need to fix whatever causes LWJGL programs to not run outside of console~~
  - ~~This isn't a HUGE concern though, the OS X app will run the jar through a console command the Linux .desktop will do largely the same. Plus, most people will probably run by Jar on command line anyway. Besides, I can make a lazy C# wrapper for the jar anyway.~~ All fixed since the move to LibGDX master race
- Proper lighting? Idk 
- Recreate Legend of Zelda in the engine
- Sounds need to become a thing
- Menus?
  - Buttons?
  - Other UI elements?
- Credit everyone
- Xbox Gamepad Support
  - not a promise, but definitely would LOVE to see it implemented.
- ~~PSP Port!~~

# Disclaimer

The above list is for my own use, it does not PROMISE features nor it doesn't PROMISE that these will be implemented in the order shown.

# License

I have yet to decide on a license, however for now. You are allowed to clone this, you are allowed to fork, distribute, etc. **As long as credit is given to me and a linkback to this repository is shown**.
