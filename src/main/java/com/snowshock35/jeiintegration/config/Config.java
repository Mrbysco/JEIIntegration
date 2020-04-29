/*
 * MIT License
 *
 * Copyright (c) 2017 SnowShock35
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.snowshock35.jeiintegration.config;

import com.snowshock35.jeiintegration.JEIIntegration;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.List;

public class Config {
    static final String CATEGORY_HANDLERS = "Handler Settings";
    static final String CATEGORY_TOOLTIPS = "Tooltip Settings";
    static final String CATEGORY_MISCELLANEOUS = "Miscellaneous Settings";

    private static final String defaultBurnTimeTooltipMode = "disabled";
    private static final String defaultDurabilityTooltipMode = "disabled";
    private static final String defaultFluidRegInfoTooltipMode = "disabled";
    private static final String defaultRegistryNameTooltipMode = "disabled";
    private static final String defaultMaxStackSizeTooltipMode = "disabled";
//    private static final String defaultMetadataTooltipMode = "disabled";
    private static final String defaultNbtTooltipMode = "disabled";
    private static final String defaultOreDictEntriesTooltipMode = "disabled";
    private static final String defaultUnlocalizedNameTooltipMode = "disabled";

    private static final List<String> validOptions = Arrays.asList(new String[] {"disabled", "enabled", "onShift", "onDebug", "onShiftAndDebug"});

    public static class Client {
        public final ConfigValue<String> burnTimeTooltipMode;
        public final ConfigValue<String> durabilityTooltipMode;
//        public final ConfigValue<String> fluidRegInfoTooltipMode;
        public final ConfigValue<String> registryNameTooltipMode;
        public final ConfigValue<String> maxStackSizeTooltipMode;
//        public final ConfigValue<String> metadataTooltipMode;
        public final ConfigValue<String> nbtTooltipMode;
        public final ConfigValue<String> oreDictEntriesTooltipMode;
        public final ConfigValue<String> unlocalizedNameTooltipMode;

        Client(ForgeConfigSpec.Builder builder) {
            builder.comment(CATEGORY_HANDLERS)
                    .comment("Handler Settings")
                    .push("handler_settings");

            builder.pop();

            builder.comment(CATEGORY_TOOLTIPS)
                    .comment("Tooltip Settings")
                    .push("tooltips_settings");

            burnTimeTooltipMode = builder
                    .comment("Configure the state of the burn time tooltip.")
                    .translation("config.jeiintegration.tooltips.burnTimeTooltipMode")
                    .define("burnTimeTooltipMode", defaultBurnTimeTooltipMode, string -> validOptions.contains(string));

            durabilityTooltipMode = builder
                    .comment("Enables or disables showing you items' durability.")
                    .translation("config.jeiintegration.tooltips.durabilityTooltipMode")
                    .define("durabilityTooltipMode", defaultDurabilityTooltipMode, string -> validOptions.contains(string));

//            fluidRegInfoTooltipMode = builder
//                    .comment("Configure the state of the fluid registry information tooltip.")
//                    .translation("config.jeiintegration.tooltips.fluidRegInfoTooltipMode")
//                    .define("fluidRegInfoTooltipMode", defaultFluidRegInfoTooltipMode, string -> validOptions.contains(string));

            registryNameTooltipMode = builder
                    .comment("Configure the state of the registry name tooltip.")
                    .translation("config.jeiintegration.tooltips.registryNameTooltipMode")
                    .define("registryNameTooltipMode", defaultRegistryNameTooltipMode, string -> validOptions.contains(string));

            maxStackSizeTooltipMode = builder
                    .comment("Configure the state of the max stack size tooltip.")
                    .translation("config.jeiintegration.tooltips.maxStackSizeTooltipMode")
                    .define("maxStackSizeTooltipMode", defaultMaxStackSizeTooltipMode, string -> validOptions.contains(string));

            nbtTooltipMode = builder
                    .comment("Configure the state of the nbt tooltip.")
                    .translation("config.jeiintegration.tooltips.nbtTooltipMode")
                    .define("nbtTooltipMode", defaultNbtTooltipMode, string -> validOptions.contains(string));

            oreDictEntriesTooltipMode = builder
                    .comment("Configure the state of the ore dictionary entries tooltip.")
                    .translation("config.jeiintegration.tooltips.oreDictEntriesTooltipMode")
                    .define("oreDictEntriesTooltipMode", defaultOreDictEntriesTooltipMode, string -> validOptions.contains(string));

            unlocalizedNameTooltipMode = builder
                    .comment("Configure the state of the unlocalized name tooltip.")
                    .translation("config.jeiintegration.tooltips.unlocalizedNameTooltipMode")
                    .define("unlocalizedNameTooltipMode", defaultUnlocalizedNameTooltipMode, string -> validOptions.contains(string));

            builder.pop();

            builder.comment(CATEGORY_MISCELLANEOUS)
                    .comment("Miscellaneous Settings")
                    .push("miscellaneous_settings");

            builder.pop();
        }
    }

    public static final ForgeConfigSpec clientSpec;
    public static final Config.Client CLIENT;
    static {
        final Pair<Config.Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Config.Client::new);
        clientSpec = specPair.getRight();
        CLIENT = specPair.getLeft();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {
        JEIIntegration.logger.debug("Loaded JEI Integration config file {}", configEvent.getConfig().getFileName());
    }

    @SubscribeEvent
    public static void onFileChange(final ModConfig.Reloading configEvent) {
        JEIIntegration.logger.debug("JEI Integration config just got changed on the file system!");
    }
}
