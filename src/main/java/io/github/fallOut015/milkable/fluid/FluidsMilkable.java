package io.github.fallOut015.milkable.fluid;

import io.github.fallOut015.milkable.MainMilkable;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FluidsMilkable {
    private static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, MainMilkable.MODID);



    public static final RegistryObject<FlowingFluid> FLOWING_INK = FLUIDS.register("flowing_ink", InkFluid.Flowing::new);
    public static final RegistryObject<FlowingFluid> INK = FLUIDS.register("ink", InkFluid.Source::new);



    public static void register(IEventBus bus) {
        FLUIDS.register(bus);
    }
}
