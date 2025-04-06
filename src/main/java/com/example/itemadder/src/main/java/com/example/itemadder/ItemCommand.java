package com.example.itemadder;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class ItemCommand {
    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("giveitems")
                .then(CommandManager.argument("count", IntegerArgumentType.integer(1, 64))
                .executes(context -> {
                    ServerCommandSource source = context.getSource();
                    ServerPlayerEntity player = source.getPlayer();
                    int count = IntegerArgumentType.getInteger(context, "count");

                    if (player != null) {
                        ItemStack diamonds = new ItemStack(Items.DIAMOND, count);
                        boolean added = player.getInventory().insertStack(diamonds);

                        if (added) {
                            source.sendFeedback(Text.literal("Gave you " + count + " diamonds!"), false);
                        } else {
                            source.sendFeedback(Text.literal("Couldn't add items to your inventory."), false);
                        }
                    }
                    return 1; // Command succeeded
                })));
        });
    }
}
