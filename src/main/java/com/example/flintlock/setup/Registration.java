package com.example.flintlock.setup;

import com.example.flintlock.Flintlock;
import com.example.flintlock.setup.ItemPro.*;
import com.example.flintlock.setup.entity.EntityBullet;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registration {
    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(bus);
        BLOCKS.register(bus);
        PARTICLE_TYPES.register(bus);
        ENTITY_TYPES.register(bus);
        SOUNDS.register(bus);
    }
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Flintlock.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Flintlock.MODID);
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Flintlock.MODID);
    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Flintlock.MODID);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Flintlock.MODID);
    public static final RegistryObject<SoundEvent> TRIGGER = SOUNDS.register("trigger", () -> new SoundEvent(new ResourceLocation(Flintlock.MODID, "trigger")));
    public static final RegistryObject<SoundEvent> SHOOT = SOUNDS.register("shoot", () ->new SoundEvent(new ResourceLocation(Flintlock.MODID, "shoot")));
    public static final RegistryObject<SimpleParticleType> SMOKE_PARTICLE=PARTICLE_TYPES.register("smoke_particle",()->new SimpleParticleType(true));

    public static final RegistryObject<Item> EAGLE_OF_SUGER = ITEMS.register("eagle_of_suger", ()->new EagleOfSuger());
    public static final RegistryObject<Item> COIN_WITH_FILE = ITEMS.register("coin_with_file",()->new CoinWithFile());
    public static final RegistryObject<Item> DIETY_BUTTER_BREAD = ITEMS.register("dirty_butter_bread",()->new DirtyButterBread());
    public static final RegistryObject<Item> PISTOLITEM = ITEMS.register("pistol",()->new FlintlockItem(6,100,1));
    public static final RegistryObject<Item> FLINT_MUSKETITEM = ITEMS.register("flint_musket",()->new FlintlockItem(6,200,1));
    public static final RegistryObject<Item> OFFICERS_PISTOL = ITEMS.register("officers_pistol",()->new FlintlockItem(6,100,4));
    public static final RegistryObject<Item> SILVER_PLATED_PISTOL = ITEMS.register("silver_plated_pistol",()->new FlintlockItem(6,100,1));
    public static final RegistryObject<Item> AMERICAN_FLINTLOCK = ITEMS.register("american_flintlock",()->new FlintlockItem(6,100,8));
    public static final RegistryObject<Item> FRENCH_CAVALRY_PISTOL = ITEMS.register("french_cavalry_pistol",()->new FlintlockItem(6,100,1));
    public static final RegistryObject<Item> ITEM_BULLET = ITEMS.register("bullet",()->new ItemBullet());
    public static final RegistryObject<EntityType<EntityBullet>> BULLET = ENTITY_TYPES.register("bullet",()->{
        return EntityType.Builder.of((EntityType<EntityBullet> entityType,Level level)->{
            return new EntityBullet(entityType,level);
        }, MobCategory.MISC).sized(1F,1F).build("bullet");
    });
}
