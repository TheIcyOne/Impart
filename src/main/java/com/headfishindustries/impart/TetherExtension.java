package com.headfishindustries.impart;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.headfishindustries.impart.entity.EntityPlayerBody;
import com.headfishindustries.impart.entity.EntityTethering;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;

/** For some reason, one and only one of my capabilities caused awful lag.
 * 	Rather than find the problem and fix it, I'm doing this.
 * 	Maybe I'll do it properly when I don't have 4 days to do it.
 * 
 *  Probably not though.
 *  **/
public class TetherExtension {
	
	public static HashMap<EntityPlayer, EntityTethering> playerToEntity = new HashMap<EntityPlayer, EntityTethering>();
	
	private static HashMap<EntityPlayer, TetherExtension> tethers = new HashMap<EntityPlayer, TetherExtension>();
	
	private static Set<UUID> tetheredPlayers = new HashSet<UUID>();
	
	private EntityPlayer player;
	private Vec3d tetherPos;
	private Vec3d playerPos;
	private EntityPlayerBody body;
	
	private TetherExtension(EntityPlayer p){
		this.player = p;
	}
	
	/** Generates a TetherExtension instance for the specified player.**/
	public static TetherExtension For(EntityPlayer target){
		if (tethers.containsKey(target)){
		return tethers.get(target);	
		}
		return new TetherExtension(target);
	}
	
	/** Returns self because chains are gains. **/
	public TetherExtension tether(){
		tetheredPlayers.add(player.getUniqueID());
		tethers.put(player, this);
		if (!player.world.isRemote){
		this.body = new EntityPlayerBody(player.world, player);
		this.body.setPlayer(player);
		}
		return this;
	}
	/** Returns self because chains are gains. **/
	public TetherExtension setTetherPos(Vec3d in){
		this.tetherPos = in;
		return this;
	}
	
	public Vec3d getTetherPos(){
		return this.tetherPos;
	}
	
	public double getRange(){
		return 2.9f;
	}
	
	public TetherExtension setPlayerPos(Vec3d in){
		this.playerPos = in;
		return this;
	}
	
	public Vec3d getPlayerPos(){
		return this.playerPos;
	}
	
	public EntityPlayerBody getBody(){
		return this.body;
	}
	
	/** Use this to remove a player from the tethered list.**/
	public void clearTether(){
		tetheredPlayers.remove(player.getUniqueID());
		tethers.remove(player);
		this.body = null;
	}
	
	public static boolean hasTether(EntityPlayer p){
		return tetheredPlayers.contains(p.getUniqueID());
	}
	
	/** Used for server stopping and such. **/
	public static void clearAllTethers(){
		tetheredPlayers = new HashSet<UUID>();
	}
	
}
