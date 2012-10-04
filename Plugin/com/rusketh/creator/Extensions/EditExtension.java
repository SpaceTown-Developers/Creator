package com.rusketh.creator.Extensions;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rusketh.creator.blocks.RandomBlockArray;
import com.rusketh.creator.commands.CommandInput;
import com.rusketh.creator.commands.CreateCommand;
import com.rusketh.creator.exceptions.CmdException;
import com.rusketh.creator.masks.Mask;
import com.rusketh.creator.masks.MaskBuilder;
import com.rusketh.creator.tasks.RedoTask;
import com.rusketh.creator.tasks.SetTask;
import com.rusketh.creator.tasks.TaskSession;
import com.rusketh.creator.tasks.UndoTask;
import com.rusketh.util.CreatorString;

public class EditExtension extends Extension {
	
	public String name() {
		return "core.Edit";
	}
	
	/*========================================================================================================*/
	
	@CreateCommand( names = { "mask" }, example = "mask <Mask>", desc = "Set the mask of the creator editor.", least = 0, most = 1, console = false, perms = { "creator.editor.mask" } )
	public boolean maskCommand( CommandSender sender, CommandInput input ) {
		Player player = (Player) sender;
		
		Mask mask = new MaskBuilder( input.arg(0) ).getMask();
		plugin.getTaskManager().getSession(player).setMask(mask);
		
		player.sendMessage("Editor mask set.");
		return true;
	}
	
/*========================================================================================================*/
	
	@CreateCommand( names = { "stop" }, example = "stop", desc = "Stop your current edit task.", least = 0, most = 0, console = false, perms = { "creator.editor" } )
	public boolean stopCommand( CommandSender sender, CommandInput input ) {
		Player player = (Player) sender;
		plugin.getTaskManager().getSession(player).stopTask();
		
		player.sendMessage( new CreatorString("%gYour editor task has been stoped (%buse undo to revert).").toString() );
		return true;
	}
	
	/*========================================================================================================*/
	
	@CreateCommand( names = { "pause" }, example = "pause", desc = "Pause your current edit task.", least = 0, most = 0, console = false, perms = { "creator.editor" } )
	public boolean pauseCommand( CommandSender sender, CommandInput input ) {
		Player player = (Player) sender;
		TaskSession session = plugin.getTaskManager().getSession(player);
		
		if ( !session.taskRunning() ) throw new CmdException("%rYou do not have an active task to pause.");
		session.pauseTask();
		
		player.sendMessage( new CreatorString("%gYour editor task has been paused (%buse resume to continue).").toString() );
		return true;
	}
	
/*========================================================================================================*/
	
	@CreateCommand( names = { "resume" }, example = "resume", desc = "Resume your current edit task.", least = 0, most = 0, console = false, perms = { "creator.editor" } )
	public boolean resumeCommand( CommandSender sender, CommandInput input ) {
		Player player = (Player) sender;
		TaskSession session = plugin.getTaskManager().getSession(player);
		
		if ( !session.taskRunning() ) throw new CmdException("%rYou do not have an active task to resume.");
		session.pauseTask();
		
		player.sendMessage( new CreatorString("%gYour editor task has been resumed.").toString() );
		return true;
	}
	
/*========================================================================================================*/
	
	@CreateCommand( names = { "undo" }, example = "undo", desc = "Undo your last edit task.", least = 0, most = 0, console = false, perms = { "creator.editor" } )
	public boolean undoCommand( CommandSender sender, CommandInput input ) {
		Player player = (Player) sender;
		TaskSession session = plugin.getTaskManager().getSession(player);
		if ( !session.undo() ) throw new CmdException("%rPlease wait till your current editor task is finished before using undo.");
		
		int vol = ((UndoTask) session.getTask()).undoCount();
		player.sendMessage( new CreatorString("%Undoing '%b").append( vol ).append("%g' at '%r").append( session.getBlockRate() ).append(" %g' blocks per second.").toString() );
		return true;
	}
	
	/*========================================================================================================*/
	
	@CreateCommand( names = { "redo" }, example = "redo", desc = "Redo your last undo task.", least = 0, most = 0, console = false, perms = { "creator.editor" } )
	public boolean redoCommand( CommandSender sender, CommandInput input ) {
		Player player = (Player) sender;
		TaskSession session = plugin.getTaskManager().getSession(player);
		if ( !session.undo() ) throw new CmdException("%rPlease wait till your current editor task is finished before using redo.");
		
		int vol = ((RedoTask) session.getTask()).redoCount();
		player.sendMessage( new CreatorString("%Redoing '%b").append( vol ).append("%g' at '%r").append( session.getBlockRate() ).append(" %g' blocks per second.").toString() );
		return true;
	}
	
	/*========================================================================================================*/
		
	@CreateCommand( names = { "set" }, example = "set <blocks>", desc = "Set every block inside your selection.", least = 0, most = 1, console = false, perms = { "creator.editor.set" } )
	public boolean setCommand( CommandSender sender, CommandInput input ) {
		Player player = (Player) sender;
		TaskSession session = plugin.getTaskManager().getSession(player);
		if ( !session.getSelection().isValid() ) throw new CmdException("%rPlease make a valid selection first.");
		
		SetTask task = new SetTask( session, player.getWorld(), session.getBlockRate() );
		task.setBlocks( new RandomBlockArray( input.arg(0) ) );
		if ( !session.startTask(task, true) ) throw new CmdException("%rPlease wait till your current editor task is finished.");
		
		int vol = task.getSelection().getVolume();
		player.sendMessage( new CreatorString("%gSetting '%b").append( vol ).append("%g' blocks.").toString() );
		return true;
	}
}
