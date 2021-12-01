package io.github.fallOut015.milkable.item;

import io.github.fallOut015.milkable.MainMilkable;
import io.github.fallOut015.milkable.fluid.FluidsMilkable;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemsMilkable {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MainMilkable.MODID);



    public static final RegistryObject<Item> INK_BUCKET = ITEMS.register("ink_bucket", () -> new BucketItem(FluidsMilkable.INK, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).tab(ItemGroup.TAB_MISC)));



    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}