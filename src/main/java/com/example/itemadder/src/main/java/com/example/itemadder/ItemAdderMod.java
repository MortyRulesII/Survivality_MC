package com.example.itemadder;

import net.fabricmc.api.ModInitializer;

public class ItemAdderMod implements ModInitializer {
    @Override
    public void onInitialize() {
        System.out.println("ItemAdderMod initialized!");
        ItemCommand.register(); // Register the custom command
    }
}
