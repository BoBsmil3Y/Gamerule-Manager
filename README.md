# Gamerule Manager

## Description
Gamerule Manager is a simple and light plugin to manage your world gamerules. It's completely an only admin plugin with one command and one permission. He has only two listeners trigger : `InventoryClickEvent` & `AsyncPlayerChatEvent`. They're both quickly skip when they're not trigger for the plugin.

## How it work
When executing the main command, a GUI menu will be open with a list of items. You have two types of item : Lever for boolean gamerule, and a paper for integer gamerule. 

 - When you clicked on a Lever, the gamerule will change to the other value (true -> false or false -> true).
 - When you clicked on a Paper, the menu will closed and you have to write in the chat the value you want to put, it has to be an integer between -2 147 483 648 and +2 147 483 647.

## Command
Command to open the GUI menu with all the world gamerules.

    /gmanager
/!\ Its a player only command. You can't execute it from the console !

## Permission

`gamerulemanager.admin` : Permission to execute /gmanager.  

## Tools used

 - **IDE** : [Eclipse](https://www.eclipse.org/)
 - **Documentation** : [Bukkit 1.15.2-R0.1-SNAPSHOT APII](https://hub.spigotmc.org/javadocs/bukkit/overview-summary.html)
   
 - **In-browser Markdown editor** : [StackEdit](https://stackedit.io/app#)
 - **GitHub** : [GitHub](https://github.com/BoBsmil3Y/)
 - **Testing on** : [Spigot 1.15.2](https://www.spigotmc.org/)