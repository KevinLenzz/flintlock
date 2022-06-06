package com.example.flintlock.setup.item;

import com.example.flintlock.setup.ModSetup;
import com.example.flintlock.setup.Registration;
import com.example.flintlock.setup.entity.EntityBullet;
import com.example.flintlock.setup.event.Flags;
import com.example.flintlock.setup.network.Messages;
import com.example.flintlock.setup.network.PGM;
import com.example.flintlock.setup.network.PGM2;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.RemotePlayer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
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
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.apache.logging.log4j.core.jmx.Server;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Predicate;

import static com.example.flintlock.setup.Registration.*;
import static com.example.flintlock.setup.event.Flags.reloadflag;
import static com.example.flintlock.setup.event.Flags2.shoot;

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

    public void releaseUsing(ItemStack p_77615_1_, Level level, LivingEntity livingEntity, int p_77615_4_) {
        if (livingEntity instanceof Player) {
            Player player = (Player)livingEntity;
            boolean flag = player.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, p_77615_1_) > 0;
            ItemStack itemstack = this.findAmmo(player);
            int a = this.getUseDuration(p_77615_1_) - p_77615_4_;
            a = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(p_77615_1_, level, player, a, !itemstack.isEmpty() || flag);
//            float f = getPowerForTime(a);
//            ItemBullet arrowitem = (ItemBullet) (itemstack.getItem() instanceof ItemBullet ? itemstack.getItem() : Registration.ITEM_BULLET.get());
//            EntityBullet abstractarrowentity;
//            abstractarrowentity = arrowitem.createArrow(level, itemstack, livingEntity,damageTag);
//            abstractarrowentity = customArrow(abstractarrowentity);
//            abstractarrowentity.shootFromRotation(livingEntity, livingEntity.getXRot(), livingEntity.getYRot(), 0.2F, f * 30.0F, 0.75F);
//            p_77615_1_.hurtAndBreak(1, livingEntity, (p_220009_1_) -> {p_220009_1_.broadcastBreakEvent(livingEntity.getUsedItemHand());});
//            level.addFreshEntity(abstractarrowentity);
//            abstractarrowentity.shootFromRotation(livingEntity, livingEntity.getXRot(), livingEntity.getYRot(), 0.2F, f * 30.0F, 0.75F);
//            p_77615_1_.hurtAndBreak(1, livingEntity, (p_220009_1_) -> {
//                p_220009_1_.broadcastBreakEvent(livingEntity.getUsedItemHand());
//            });
            if(a<0)return;
            if (!itemstack.isEmpty() || flag) {
                float f = getPowerForTime(a);
                if(level.isClientSide()&&Flags.flag&&reloadflag) {
                    if (itemstack.isEmpty()) {
                        itemstack = new ItemStack(Registration.ITEM_BULLET.get());
                    }
                    if (!((double) f < 1D)) {
                        if(number>1){
                            Messages.sendToServer(new PGM2());
                        }
//                        setCharged(p_77615_1_, true);
                        boolean flag1 = player.getAbilities().instabuild || (itemstack.getItem() instanceof ItemBullet && ((ItemBullet) itemstack.getItem()).isInfinite(itemstack, p_77615_1_, player));
//                        System.out.println(11111);
//                        level.addParticle(ParticleTypes.FLAME,
//                                livingEntity.getX() + Math.cos(livingEntity.getViewYRot(0) * Math.PI / 180 + Math.PI / 2) *0.5d,
//                                livingEntity.getY() - Math.sin(livingEntity.getViewXRot(0) * Math.PI / 180) * 0.5d + 1.6d,
//                                livingEntity.getZ() + Math.sin(livingEntity.getViewYRot(0) * Math.PI / 180 + Math.PI / 2) * 0.5d,
//                                0,
//                                0,
//                                0);
//                        for (double j = 0; j < 5; j += 0.5d) {
////                            for (int i = 0; i < 360; i++) {
////                                if (i % 20 == 0) {
////                                    level.addParticle(ParticleTypes.POOF,
////                                            livingEntity.getX() + Math.cos(livingEntity.getViewYRot(0) * Math.PI / 180 + Math.PI / 2) * j,
////                                            livingEntity.getY() - Math.sin(livingEntity.getViewXRot(0) * Math.PI / 180) * j + 1.6d,
////                                            livingEntity.getZ() + Math.sin(livingEntity.getViewYRot(0) * Math.PI / 180 + Math.PI / 2) * j,
////                                            0.15d * Math.cos(livingEntity.getViewYRot(0) * Math.PI / 180) * Math.random() / 2,
////                                            0.15d * Math.random() / 2,
////                                            0.15d * Math.sin(livingEntity.getViewYRot(0) * Math.PI / 180) * Math.random() / 2);
////                                }
////                            }
//                            level.addParticle(ParticleTypes.POOF,
//                                            livingEntity.getX() + Math.cos(livingEntity.getViewYRot(0) * Math.PI / 180 + Math.PI / 2) * j,
//                                            livingEntity.getY() - Math.sin(livingEntity.getViewXRot(0) * Math.PI / 180) * j + 1.6d,
//                                            livingEntity.getZ() + Math.sin(livingEntity.getViewYRot(0) * Math.PI / 180 + Math.PI / 2) * j,
//                                            0.15d * Math.cos(livingEntity.getViewYRot(0) * Math.PI / 180) * Math.random() / 2,
//                                            0.15d * Math.random() / 2,
//                                            0.15d * Math.sin(livingEntity.getViewYRot(0) * Math.PI / 180) * Math.random() / 2);
//                        }
//                        System.out.println(22222);
//                        level.playLocalSound(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), SHOOT.get(), SoundSource.PLAYERS, 1.4F, 1.4F / (livingEntity.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F,false);
                        if(level.isClientSide()) {
                            if (number == 1) {
                                reloadflag = false;
                                number = numberOringin;
                            } else if (number > 1) {
                                number--;
                            }
                        }
                        if (!flag1 && !player.getAbilities().instabuild) {
                            itemstack.shrink(1);
                            if (itemstack.isEmpty()) {
                                player.getInventory().removeItem(itemstack);
                            }
                        }
                        player.awardStat(Stats.ITEM_USED.get(this));
                    }
                }
                if((level instanceof ServerLevel serverWorld)&&shoot){
                    System.out.println(serverWorld.players());
//                    Items.CROSSBOW=player.getItemInHand(player.getUsedItemHand()).getItem();
                    ItemStack theItem=livingEntity.getItemInHand(livingEntity.getUsedItemHand());
                    for(ServerPlayer serverPlayer:serverWorld.players()) {
                        Messages.sendToPlayer(new PGM(theItem),serverPlayer);
                    }
                    serverWorld.sendParticles(ParticleTypes.FLAME,
                            livingEntity.getX() + Math.cos(livingEntity.getViewYRot(0) * Math.PI / 180 + Math.PI / 2) *0.5d,
                            livingEntity.getY() - Math.sin(livingEntity.getViewXRot(0) * Math.PI / 180) * 0.5d + 1.6d,
                            livingEntity.getZ() + Math.sin(livingEntity.getViewYRot(0) * Math.PI / 180 + Math.PI / 2) * 0.5d,
                            1,
                            0,
                            0,
                            0,
                            0);
                    for (double j = 0; j < 5; j += 0.5d) {
                        serverWorld.sendParticles(ParticleTypes.POOF,
                                livingEntity.getX() + Math.cos(livingEntity.getViewYRot(0) * Math.PI / 180 + Math.PI / 2) * j,
                                livingEntity.getY() - Math.sin(livingEntity.getViewXRot(0) * Math.PI / 180) * j + 1.6d,
                                livingEntity.getZ() + Math.sin(livingEntity.getViewYRot(0) * Math.PI / 180 + Math.PI / 2) * j,
                                1,
                                0.15d * Math.cos(livingEntity.getViewYRot(0) * Math.PI / 180) * Math.random() / 2,
                                0.15d * Math.random() / 2,
                                0.15d * Math.sin(livingEntity.getViewYRot(0) * Math.PI / 180) * Math.random() / 2,
                                0);
                    }
                    SoundSource soundsource = livingEntity instanceof Player ? SoundSource.PLAYERS : SoundSource.HOSTILE;
                    level.playSound((Player) null,livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), SHOOT.get(), soundsource, 1.4F, 1.4F / (livingEntity.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    ItemBullet arrowitem = (ItemBullet) (itemstack.getItem() instanceof ItemBullet ? itemstack.getItem() : Registration.ITEM_BULLET.get());
                    EntityBullet abstractarrowentity;
                    abstractarrowentity = arrowitem.createArrow(level, itemstack, livingEntity,damageTag);
                    abstractarrowentity = customArrow(abstractarrowentity);
                    abstractarrowentity.shootFromRotation(livingEntity, livingEntity.getXRot(), livingEntity.getYRot(), 0.2F, f * 30.0F, 0.75F);
                    p_77615_1_.hurtAndBreak(1, livingEntity, (p_220009_1_) -> {p_220009_1_.broadcastBreakEvent(livingEntity.getUsedItemHand());});
                    level.addFreshEntity(abstractarrowentity);
                    abstractarrowentity.shootFromRotation(livingEntity, livingEntity.getXRot(), livingEntity.getYRot(), 0.2F, f * 30.0F, 0.75F);
                    p_77615_1_.hurtAndBreak(1, livingEntity, (p_220009_1_) -> {
                        p_220009_1_.broadcastBreakEvent(livingEntity.getUsedItemHand());
                    });
                    shoot = false;
                }
            }
        }
    }
    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
        SoundSource soundsource = playerIn instanceof Player ? SoundSource.PLAYERS : SoundSource.HOSTILE;
        level.playSound((Player) null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), TRIGGERSOUND.get(), soundsource, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F);
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        CompoundTag compoundtag = playerIn.getMainHandItem().getOrCreateTag();
//        compoundtag.putBoolean("Charged", true);
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
