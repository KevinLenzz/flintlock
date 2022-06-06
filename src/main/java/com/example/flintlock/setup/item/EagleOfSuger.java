package com.example.flintlock.setup.item;

import com.example.flintlock.setup.ModSetup;
import com.example.flintlock.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public class EagleOfSuger extends SwordItem {
    private static Tier iTier=new Tier() {
        @Override
        public int getUses() {
            return 2000;
        }

        @Override
        public float getSpeed() {
            return 10.0F;
        }

        @Override
        public float getAttackDamageBonus() {
            return 4.0F;
        }

        @Override
        public int getLevel() {
            return 3;
        }

        @Override
        public int getEnchantmentValue() {
            return 30;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return null;
        }
    };
    public EagleOfSuger() {
        super(iTier, 3, -2.4F, new Item.Properties().tab(ModSetup.ITEM_GROUP.TAB_COMBAT).tab(ModSetup.ITEM_GROUP).stacksTo(1));
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext){
        if(pContext.getLevel().isClientSide()) {
            BlockPos positionClicked = pContext.getClickedPos();
            spawnFoundParticles(pContext, positionClicked);
        }
        return super.useOn(pContext);
    }
    public void spawnFoundParticles(UseOnContext pContext,BlockPos positionClicked){
        for(int i=0;i<360;i++){
            if(i%20==0){
                pContext.getLevel().addParticle(Registration.SMOKE_PARTICLE.get(),
                        positionClicked.getX()+0.5d,positionClicked.getY()+1,positionClicked.getZ()+0.5d,
                        Math.cos(i)*0.25d,0.15d,Math.sin(i)*0.25d);
            }
        }
    }
    @Override
    public boolean hurtEnemy(ItemStack p_43278_, LivingEntity p_43279_, LivingEntity p_43280_) {
        p_43279_.addEffect(new MobEffectInstance(MobEffects.BLINDNESS,50));
        for(int i=0;i<360;i++){
            if(i%20==0){
//                p_43280_.getLevel().addParticle(Registration.SMOKE_PARTICLE.get(),
//                        p_43279_.getX()+0.5d,p_43279_.getY()+1+5,p_43279_.getZ()+0.5d,
//                        Math.cos(i)*0.25d,0.15d,Math.sin(i)*0.25d);
                ServerLevel serverLevel=(ServerLevel) p_43280_.getLevel();
                serverLevel.sendParticles(ParticleTypes.WAX_OFF,
                        p_43279_.getX()+0.5d,p_43279_.getY()+1,p_43279_.getZ()+0.5d,
                        1,
                        Math.cos(i)*0.25d,0.15d,Math.sin(i)*0.25d,5);
            }
        }
        p_43278_.hurtAndBreak(1, p_43280_, (p_43296_) -> {
            p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        return true;
    }
}
