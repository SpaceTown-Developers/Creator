/*
 * 
 *  PROOF OF CONCEPT
 * MADE TO BE TERRIBLE
 * 
 */

package com.rusketh.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.data.InvalidFormatException;


public class FormatString {
	
	public FormatString(String buffer, Object... input) throws InvalidFormatException {
		append(buffer, input);
	}
	/*========================================================================================================*/
	
	public FormatString append(String buffer, Object... input) throws InvalidFormatException {
		int pos = 0;
		
		for (int i = 0; i <= buffer.length( ); i++) {
			char c = buffer.charAt( i );
			
			if (c == '%') {
				c = buffer.charAt( i ++);
				
				switch (c) {
					case '%': //Escape.
						builder.append('%');
						break;
					case '/':
						builder.append('/');
						break;
					
					case 's':
						if ( !( input[pos] instanceof String ) ) throw new InvalidFormatException("Failed to grab string.");
						builder.append((String) input[pos]);
						pos++;
					case 'q':
						if ( !( input[pos] instanceof String ) ) throw new InvalidFormatException("Failed to grab string.");
						builder.append('"').append((String) input[pos]).append('"');
						pos++;
					
					case 'i':
						if ( !( input[pos] instanceof Integer ) ) throw new InvalidFormatException("Failed to grab integer.");
						builder.append((Integer) input[pos]);
						pos++;
					case 'f':
						if ( !( input[pos] instanceof Float ) ) throw new InvalidFormatException("Failed to grab float.");
						builder.append((Float) input[pos]);
						pos++;
					
					case 'p':
						if ( !( input[pos] instanceof Player ) ) throw new InvalidFormatException("Failed to grab player.");
						builder.append( ((Player) input[pos]).getName( ) );
						pos++;
					case 'P':
						if ( !( input[pos] instanceof Player ) ) throw new InvalidFormatException("Failed to grab player.");
						builder.append( ((Player) input[pos]).getDisplayName( ) );
						pos++;
						
					default:
						builder.append( '%' ).append( c );
				}
				
			} else if (c == '/') {
				c = buffer.charAt( i ++ );
				
				switch (c) {
					case 'r':
						builder.append(ChatColor.RED.toString());
						break;
					case 'R':
						builder.append(ChatColor.DARK_RED.toString());
						break;
					
					case 'y':
						builder.append(ChatColor.YELLOW.toString());
						break;
					case 'Y':
						builder.append(ChatColor.GOLD.toString());
						break;
						
					case 'g':
						builder.append(ChatColor.GREEN.toString());
						break;
					case 'G':
						builder.append(ChatColor.DARK_GREEN.toString());
						break;
						
					case 'c':
						builder.append(ChatColor.AQUA.toString());
						break;
					case 'C':
						builder.append(ChatColor.DARK_AQUA.toString());
						break;
						
					case 'b':
						builder.append(ChatColor.BLUE.toString());
						break;
					case 'B':
						builder.append(ChatColor.DARK_BLUE.toString());
						break;
						
					case 'p':
						builder.append(ChatColor.LIGHT_PURPLE.toString());
						break;
					case 'P':
						builder.append(ChatColor.DARK_PURPLE.toString());
						break;
						
					case 'a':
						builder.append(ChatColor.GRAY.toString());
						break;
					case 'A':
						builder.append(ChatColor.DARK_GRAY.toString());
						break;
						
					case 'w':
						builder.append(ChatColor.WHITE.toString());
						break;
					case 'W':
						builder.append(ChatColor.BLACK.toString());
						break;
						
					case 'm':
						builder.append(ChatColor.MAGIC.toString());
						break;
						
					case 'd':
						builder.append(ChatColor.BOLD.toString());
						break;
					case 's':
						builder.append(ChatColor.STRIKETHROUGH.toString());
						break;
					case 'u':
						builder.append(ChatColor.UNDERLINE.toString());
						break;
					case 'i':
						builder.append(ChatColor.ITALIC.toString());
						break;
					case 'x':
						builder.append(ChatColor.RESET.toString());
						break;
						
					default:
						builder.append( '/' ).append( c );
				}
				
			} else {
				builder.append( c );
			}
		}
		
		return this;
	}
	
	/*========================================================================================================*/
	
	public String toString() {
		return builder.toString( );
	}
	
	/*========================================================================================================*/
	
	StringBuilder builder = new StringBuilder();
}
