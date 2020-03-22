# Gamerule Manager

## Description
Gamerule Manager is a simple and light plugin to manage your world gamerules. It's completely an only admin plugin with one command and one permission. He has only two listeners trigger : `InventoryClickEvent` & `AsyncPlayerChatEvent`. They're both quickly skip when they're not trigger for the plugin.

## How it work
When executing the main command, a GUI menu will be open with a list of items. You have two types of item : Tripwire_hook for boolean gamerule, and a paper for integer gamerule. 

 - When you clicked on a Tripwire_hook, the gamerule will change to the other value (true -> false or false -> true).
 - When you clicked on a Paper, the menu will closed and you have to write in the chat the value you want to put, it has to be an integer between -2 147 483 648 and +2 147 483 647.

## Command
Command to open the GUI menu with all the world gamerules.

    /gmanager
/!\ Its a player only command. You can't execute it from the console !

## Permission

`gamerulemanager.admin` : Permission to execute /gmanager.  

    

> README.MD create using [StackEdit](https://stackedit.io/app#).