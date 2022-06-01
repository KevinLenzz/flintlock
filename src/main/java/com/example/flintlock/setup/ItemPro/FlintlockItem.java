package com.example.flintlock.setup.ItemPro;

import com.example.flintlock.setup.ModSetup;
import com.example.flintlock.setup.ReflectUtil;
import com.example.flintlock.setup.Registration;
import com.example.flintlock.setup.entity.EntityBullet;
import com.example.flintlock.setup.event.ForgeEventBusEvent;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.lang.reflect.Field;
import java.util.function.Predicate;

import static com.example.flintlock.setup.Registration.*;
import static com.example.flintlock.setup.event.ForgeEventBusEvent.reloadflag;
import static com.example.flintlock.setup.gui.GUIHandler.flag2;

public class FlintlockItem extends CrossbowItem{
    public UseAnim getUseAnimation(ItemStack p_40935_) {
        return UseAnim.CROSSBOW;
    }
    public boolean reload=false;
    public double damageTag;
    public static int duraTag;
    public int number;
    public int numberOringin;
    public FlintlockItem(double damageTag,int duraTag,int number) {
        super(new FlintlockItem.Properties().tab(ModSetup.ITEM_GROUP).stacksTo(1));
        this.damageTag=damageTag;
        this.duraTag=duraTag;
        this.number=number;
        this.numberOringin=number;
    }

    public void onUseTick(Level p_40910_, LivingEntity p_40911_, ItemStack p_40912_, int p_40913_) {
    }

    public FlintlockItem(Item.Properties name) {

        super(name);

    }

    public void releaseUsing(ItemStack p_77615_1_, Level level, LivingEntity p_77615_3_, int p_77615_4_) {
        if (p_77615_3_ instanceof Player) {
            try {
                Class clz = Items.class;
                Field obj = ObfuscationReflectionHelper.findField(Items.class,"CROSSBOW");
                ReflectUtil.setModifierFinal(obj, false);
                obj.set(null, this);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            Player player = (Player)p_77615_3_;
            boolean flag = player.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, p_77615_1_) > 0;
            ItemStack itemstack = this.findAmmo(player);
            int a = this.getUseDuration(p_77615_1_) - p_77615_4_;
            a = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(p_77615_1_, level, player, a, !itemstack.isEmpty() || flag);
            if (a < 0) return;
            if(CrossbowItem.isCharged(p_77615_1_)&&ForgeEventBusEvent.flag&&reloadflag) {
                if (!itemstack.isEmpty() || flag) {
                    if (itemstack.isEmpty()) {
                        itemstack = new ItemStack(Registration.ITEM_BULLET.get());
                    }
                    float f = getPowerForTime(a);
                    if (!((double) f < 1D)) {
                        setCharged(p_77615_1_, true);
                        boolean flag1 = player.getAbilities().instabuild || (itemstack.getItem() instanceof ItemBullet && ((ItemBullet) itemstack.getItem()).isInfinite(itemstack, p_77615_1_, player));
                        level.addParticle(ParticleTypes.FLAME,
                                p_77615_3_.getX() + Math.cos(p_77615_3_.getViewYRot(0) * Math.PI / 180 + Math.PI / 2) *0.5d,
                                p_77615_3_.getY() - Math.sin(p_77615_3_.getViewXRot(0) * Math.PI / 180) * 0.5d + 1.6d,
                                p_77615_3_.getZ() + Math.sin(p_77615_3_.getViewYRot(0) * Math.PI / 180 + Math.PI / 2) * 0.5d,
                                0,
                                0,
                                0);
                        for (double j = 0; j < 5; j += 0.5d) {
//                            for (int i = 0; i < 360; i++) {
//                                if (i % 20 == 0) {
//                                    level.addParticle(ParticleTypes.POOF,
//                                            p_77615_3_.getX() + Math.cos(p_77615_3_.getViewYRot(0) * Math.PI / 180 + Math.PI / 2) * j,
//                                            p_77615_3_.getY() - Math.sin(p_77615_3_.getViewXRot(0) * Math.PI / 180) * j + 1.6d,
//                                            p_77615_3_.getZ() + Math.sin(p_77615_3_.getViewYRot(0) * Math.PI / 180 + Math.PI / 2) * j,
//                                            0.15d * Math.cos(p_77615_3_.getViewYRot(0) * Math.PI / 180) * Math.random() / 2,
//                                            0.15d * Math.random() / 2,
//                                            0.15d * Math.sin(p_77615_3_.getViewYRot(0) * Math.PI / 180) * Math.random() / 2);
//                                }
//                            }
                            level.addParticle(ParticleTypes.POOF,
                                            p_77615_3_.getX() + Math.cos(p_77615_3_.getViewYRot(0) * Math.PI / 180 + Math.PI / 2) * j,
                                            p_77615_3_.getY() - Math.sin(p_77615_3_.getViewXRot(0) * Math.PI / 180) * j + 1.6d,
                                            p_77615_3_.getZ() + Math.sin(p_77615_3_.getViewYRot(0) * Math.PI / 180 + Math.PI / 2) * j,
                                            0.15d * Math.cos(p_77615_3_.getViewYRot(0) * Math.PI / 180) * Math.random() / 2,
                                            0.15d * Math.random() / 2,
                                            0.15d * Math.sin(p_77615_3_.getViewYRot(0) * Math.PI / 180) * Math.random() / 2);
                        }
                        if (!level.isClientSide) {
                            ItemBullet arrowitem = (ItemBullet) (itemstack.getItem() instanceof ItemBullet ? itemstack.getItem() : Registration.ITEM_BULLET.get());
                            EntityBullet abstractarrowentity;
                            abstractarrowentity = arrowitem.createArrow(level, itemstack, player,damageTag);
                            abstractarrowentity = customArrow(abstractarrowentity);
                            abstractarrowentity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.2F, f * 30.0F, 0.75F);
                            abstractarrowentity.playSound(SHOOT.get(), 2.5F, 2.5F);
                            abstractarrowentity.setSecondsOnFire(200);
                            p_77615_1_.hurtAndBreak(1, player, (p_220009_1_) -> {p_220009_1_.broadcastBreakEvent(player.getUsedItemHand());});
                            level.addFreshEntity(abstractarrowentity);
                            abstractarrowentity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.2F, f * 30.0F, 0.75F);
                            p_77615_1_.hurtAndBreak(1, player, (p_220009_1_) -> {
                                p_220009_1_.broadcastBreakEvent(player.getUsedItemHand());
                            });
                        }
                        level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SHOOT.get(), SoundSource.PLAYERS, 1.4F, 1.4F / (player.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                        if (!flag1 && !player.getAbilities().instabuild) {
                            itemstack.shrink(1);
                            if (itemstack.isEmpty()) {
                                player.getInventory().removeItem(itemstack);
                            }
                        }
                        player.awardStat(Stats.ITEM_USED.get(this));
                    }
                    if(number==1) {
                        reloadflag = false;
                        number=numberOringin;
                    }else if(number>1){
                        number--;
                    }
                }
            }
        }
    }
    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
        SoundSource soundsource = playerIn instanceof Player ? SoundSource.PLAYERS : SoundSource.HOSTILE;
        level.playSound((Player) null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), TRIGGER.get(), soundsource, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F);
        CompoundTag compoundtag = playerIn.getMainHandItem().getOrCreateTag();
        compoundtag.putBoolean("Charged", true);
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        boolean flag3 = !this.findAmmo(playerIn).isEmpty();
        InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, level, playerIn, handIn, flag3);
        if (ret != null) return ret;
        if (!playerIn.getAbilities().instabuild && !flag3) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            playerIn.startUsingItem(handIn);
            return InteractionResultHolder.consume(itemstack);
        }
    }
    public static float getPowerForTime(int p_185059_0_) {
        float f = (float)p_185059_0_ / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }
        return f;
    }
    public int getUseDuration(ItemStack p_77626_1_) {
        return 10;
    }
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return ARROW_OR_FIREWORK;
    }
    public  EntityBullet customArrow(EntityBullet arrow) {
        return arrow;
    }
    public int getDefaultProjectileRange() {
        return 11;
    }
    protected ItemStack findAmmo(Player player)
    {
        if (this.isMoSpitter(player.getItemInHand(InteractionHand.OFF_HAND)))
        {
            return player.getItemInHand(InteractionHand.OFF_HAND);
        }
        else if (this.isMoSpitter(player.getItemInHand(InteractionHand.MAIN_HAND)))
        {
            return player.getItemInHand(InteractionHand.MAIN_HAND);
        }
        else
        {
            for (int i = 0; i < player.getInventory().getContainerSize(); ++i)
            {
                ItemStack itemstack = player.getInventory().getItem(i);
                if (this.isMoSpitter(itemstack))
                {
                    return itemstack;
                }
            }
            return ItemStack.EMPTY;
        }
    }
    protected boolean isMoSpitter(ItemStack stack)
    {
        return stack.getItem() instanceof ItemBullet;
    }
}
