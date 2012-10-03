package com.rusketh.creator.Extensions;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rusketh.creator.blocks.RandomBlockArray;
import com.rusketh.creator.commands.CommandInput;
import com.rusketh.creator.commands.CreateCommand;
import com.rusketh.creator.exceptions.CmdException;
import com.rusketh.creator.masks.Mask;
import com.rusketh.creator.masks.MaskBuilder;
import com.rusketh.creator.tasks.SetTask;
import com.rusketh.creator.tasks.TaskSession;
import com.rusketh.util.CreatorString;

public class EditExtension extends Extension {
	
	protected String name = "core.Edit";
	
	/*========================================================================================================*/
	
	private void checkSession(TaskSession session) {
		if ( !session.getSelection().isValid() ) throw new CmdException("%rMake a valid selection first.");
		
		if ( session.taskRunning() ) throw new CmdException("%rPlease wait till your current editor task is finished.");
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
		
		player.sendMessage( "%gYour editor task has been stoped (%buse undo to revert).");
		return true;
	}
	
	/*========================================================================================================*/
		
	@CreateCommand( names = { "set" }, example = "set <blocks>", desc = "Set every block inside your selection.", least = 0, most = 1, console = false, perms = { "creator.editor.set" } )
	public boolean setCommand( CommandSender sender, CommandInput input ) {
		Player player = (Player) sender;
		TaskSession session = plugin.getTaskManager().getSession(player);
		
		checkSession(session);
		
		SetTask task = new SetTask( session, player.getWorld(), session.getBlockRate() );
		task.setMask( session.getMask()) ;
		task.setBlocks( new RandomBlockArray( input.arg(0) ) );
		session.setTask( task );
		
		int vol = task.getSelection().getVolume();
		player.sendMessage( new CreatorString("%gSetting '%b").append( vol ).append("%g' over '%r").append( vol / (session.getBlockRate() * 20) ).append(" seconds%g'.").toString() );
		return true;
	}
}
