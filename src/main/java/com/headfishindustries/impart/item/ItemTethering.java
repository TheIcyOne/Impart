package com.headfishindustries.impart.item;

import com.headfishindustries.impart.Impart;
import com.headfishindustries.impart.TetherExtension;
import com.headfishindustries.impart.entity.EntityTethering;
import com.headfishindustries.impart.projection.ProjectionEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
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
		if (!worldIn.isRemote){
		if (!TetherExtension.hasTether(player)){
			EntityTethering tether = new EntityTethering(player.getEntityWorld());
			tether.setPosition(player.posX, player.posY - player.getYOffset() + 1, player.posZ);
			Vec3d look = player.getLookVec();
			tether.addVelocity(look.x, look.y, look.z);
			tether.velocityChanged = true;
			
			TetherExtension.playerToEntity.put(player, tether);
			
			//woop?
			worldIn.spawnEntity(tether);
		
		}else{
			TetherExtension ex = TetherExtension.For(player);
			player.setPositionAndUpdate(ex.getPlayerPos().x, ex.getPlayerPos().y, ex.getPlayerPos().z);
			EntityTethering tether = TetherExtension.playerToEntity.get(player);
			tether.velocityChanged = true;
			tether.setDead();
			TetherExtension.playerToEntity.remove(player);	
			ex.clearTether();
		}
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
		if (entityLiving instanceof EntityPlayer){
			if (!worldIn.isRemote){
			EntityPlayer player = (EntityPlayer) entityLiving;
			int timeElapsed = this.getMaxItemUseDuration(stack) - timeLeft;
		
			//Let's just avoid accidental uses.
			if (timeElapsed <= 5){
				if (TetherExtension.playerToEntity.get(player) != null)
				TetherExtension.playerToEntity.get(player).setDead();
				TetherExtension.playerToEntity.remove(player);
				return;
			}
			
			EntityTethering tether = TetherExtension.playerToEntity.get(player);

		
			
				TetherExtension proj = TetherExtension.For(player);
				
				proj.setPlayerPos(player.getPositionVector()).setTetherPos(tether.getPositionVector().add(new Vec3d(0, 1 - player.getYOffset(), 0)));
				proj.tether();
			
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
