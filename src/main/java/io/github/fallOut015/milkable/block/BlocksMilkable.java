package io.github.fallOut015.milkable.block;

import io.github.fallOut015.milkable.MainMilkable;
import io.github.fallOut015.milkable.fluid.FluidsMilkable;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlocksMilkable {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MainMilkable.MODID);


    public static final RegistryObject<Block> INK = BLOCKS.register("ink", () -> new FlowingFluidBlock(FluidsMilkable.INK, AbstractBlock.Properties.of(Material.WATER).noCollission().strength(100.0F).noDrops()));



    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}