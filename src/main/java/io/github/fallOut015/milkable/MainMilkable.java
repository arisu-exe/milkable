package io.github.fallOut015.milkable;

import io.github.fallOut015.milkable.block.BlocksMilkable;
import io.github.fallOut015.milkable.fluid.FluidsMilkable;
import io.github.fallOut015.milkable.item.ItemsMilkable;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DrinkHelper;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MainMilkable.MODID)
public class MainMilkable {
    public static final String MODID = "milkable";

    public MainMilkable() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        BlocksMilkable.register(FMLJavaModLoadingContext.get().getModEventBus());
        ItemsMilkable.register(FMLJavaModLoadingContext.get().getModEventBus());
        FluidsMilkable.register(FMLJavaModLoadingContext.get().getModEventBus());

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
    }
    private void doClientStuff(final FMLClientSetupEvent event) {
    }
    private void enqueueIMC(final InterModEnqueueEvent event) {
    }
    private void processIMC(final InterModProcessEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
    }

    @Mod.EventBusSubscriber
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onPlayerInteractWithEntity(final PlayerInteractEvent.EntityInteract event) {
            final Hand hand = event.getHand();
            final PlayerEntity player = event.getPlayer();
            if(event.getItemStack().getItem() == Items.BUCKET && event.getTarget().getType() == EntityType.SQUID) {
                player.swing(hand);
                if(event.getWorld().isClientSide) {
                    player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
                }
                player.setItemInHand(hand, new ItemStack(ItemsMilkable.INK_BUCKET.get()));
                player.getCooldowns().addCooldown(ItemsMilkable.INK_BUCKET.get(), 10);
            }
        }
    }
}
