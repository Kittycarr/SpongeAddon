package sponge;

import btw.AddonHandler;
import btw.BTWAddon;
import sponge.blocksSponge.SpongeBlocks;

public class ExampleAddon extends BTWAddon {
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