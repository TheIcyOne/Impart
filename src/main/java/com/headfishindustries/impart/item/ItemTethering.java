package com.headfishindustries.impart.item;

import com.headfishindustries.impart.Impart;
import com.headfishindustries.impart.LazyMaths;
import com.headfishindustries.impart.TetherExtension;
import com.headfishindustries.impart.entity.EntityPlayerBody;
import com.headfishindustries.impart.entity.EntityTethering;
import com.headfishindustries.impart.projection.ProjectionEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class ItemTethering extends Item{
	
	public ItemTethering(){
		this.maxStackSize = 1;
		this.setRegistryName(Impart.MODID, "item_tethering");
		this.setUnlocalizedName("tether");
		Impart.LOGGER.info(this.getUnlocalizedName());
		this.setCreativeTab(CreativeTabs.TRANSPORTATION);
	}
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn)
    {
		player.setActiveHand(handIn);
		
		if (!TetherExtension.hasTether(player)){
			if (!worldIn.isRemote){
			EntityTethering tether = new EntityTethering(player.getEntityWorld());
			tether.setPosition(player.posX, player.posY - player.getYOffset() + 1, player.posZ);
			Vec3d look = player.getLookVec().scale(0.5);
			if (player.isSneaking())
				look.scale(0.5);
			if (player.isSprinting())
				look.scale(1);
			tether.addVelocity(look.x, look.y, look.z);
			tether.velocityChanged = true;
			
			TetherExtension.playerToEntity.put(player, tether);			
			//woop?
			worldIn.spawnEntity(tether);
			}
			worldIn.playSound(player, player.getPosition(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1, 0.2f);
		}else{
			if (!worldIn.isRemote){
			TetherExtension ex = TetherExtension.For(player);
			player.setPositionAndUpdate(ex.getPlayerPos().x, ex.getPlayerPos().y, ex.getPlayerPos().z);
			EntityTethering tether = TetherExtension.playerToEntity.get(player);
			tether.velocityChanged = true;
			tether.setDead();
			EntityPlayerBody body = ex.getBody();
			body.setDead();
			TetherExtension.playerToEntity.remove(player);
			ex.clearTether();
		}
			worldIn.playSound(player, player.getPosition(), SoundEvents.ENTITY_ENDEREYE_DEATH, SoundCategory.PLAYERS, 1, 1);
		}
		
		return super.onItemRightClick(worldIn, player, handIn);	
    }
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BLOCK;
    }

	@Override
	 public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
    {
		int timeElapsed = this.getMaxItemUseDuration(stack) - timeLeft;
		if (entityLiving instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) entityLiving;
			if (!worldIn.isRemote){	
				if (timeElapsed <= 5 || TetherExtension.playerToEntity.get(player) == null){
					//Let's just avoid accidental uses.
					if (TetherExtension.playerToEntity.get(player) != null){
						TetherExtension.playerToEntity.get(player).setDead();
						TetherExtension.playerToEntity.remove(player);
					}
					return;
				}
				
				
				TetherExtension proj = TetherExtension.For(player);
				proj.tether();
				EntityTethering tether = TetherExtension.playerToEntity.get(player);

				proj.setPlayerPos(player.getPositionVector()).setTetherPos(tether.getPositionVector().add(new Vec3d(0, 1 - player.getYOffset(), 0)));
				
				EntityPlayerBody body = proj.getBody();
				body.setPlayer(player);
				body.setPositionAndRotation(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
				body.setRotationYawHead((float) LazyMaths.angleWithX(tether.getPositionVector().subtract(player.getPositionVector())));
				body.cameraPitch = (float) LazyMaths.angleWithY(tether.getPositionVector().subtract(player.getPositionVector()));
				
				worldIn.spawnEntity(body);

				tether.setVelocity(0, 0, 0);
				tether.velocityChanged = true;

				ProjectionEvent.Transmit e = new ProjectionEvent.Transmit(player);
				}
			
		 }
    }
	
	@Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return Integer.MAX_VALUE;
    }
	
	 @Override
	 public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		 if (oldStack.isEmpty() != newStack.isEmpty()) {
			 return true;
		 }
	     return oldStack.getItem() != newStack.getItem();
	}
	

}
