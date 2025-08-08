package sponge;

import btw.AddonHandler;
import btw.BTWAddon;
import net.minecraft.src.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sponge.blocksSponge.SpongeBlocks;

public class ExampleAddon extends BTWAddon {
    private static final Logger log = LogManager.getLogger(ExampleAddon.class);
    private static ExampleAddon instance;

    public ExampleAddon() {
        super();
    }

    @Override
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");

        SpongeBlocks.initBlocks();
        SpongeRecipes.initRecipes();

    }
}