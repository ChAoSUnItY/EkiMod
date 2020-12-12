package com.chaos.eki;

import com.chaos.eki.proxy.ClientProxy;
import com.chaos.eki.proxy.CommonProxy;
import com.chaos.eki.utils.handler.PacketHandler;
import com.chaos.eki.utils.handler.RegistryHandler;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Eki.MOD_ID)
public class Eki {
    public static final String MOD_ID = "eki";
    public static CommonProxy proxy = DistExecutor.runForDist(
            () -> ClientProxy::new,
            () -> CommonProxy::new);
    public static final Logger LOGGER = LogManager.getLogger();

    public Eki() {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        RegistryHandler.register(bus);

        bus.addListener(this::setup);
        bus.addListener(this::enqueueIMC);
        bus.addListener(this::processIMC);
        bus.addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        PacketHandler.register();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(RegistryHandler.BARBED_WIRE.get(), RenderType.getTranslucent());
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
    }

    private void processIMC(final InterModProcessEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
    }

    public static final ItemGroup ekiItemGroup = new ItemGroup("eki") {
        @Override
        public ItemStack createIcon() {
            return RegistryHandler.RETAINING_WALL_MULTI_BLOCK_ITEM.get().getDefaultInstance();
        }
    };
}
