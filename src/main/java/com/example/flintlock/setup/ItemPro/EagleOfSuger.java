package com.example.flintlock.setup.ItemPro;

import com.example.flintlock.Flintlock;
import com.example.flintlock.setup.ModSetup;
import com.example.flintlock.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
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
}
