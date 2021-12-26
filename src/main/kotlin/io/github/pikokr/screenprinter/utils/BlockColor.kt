package io.github.pikokr.screenprinter.utils

import org.bukkit.ChatColor
import org.bukkit.DyeColor
import org.bukkit.Material
import org.bukkit.Tag.WOOL


/**
 * Created by Zombie_Striker on 3/30/2016
 */
object BlockColor {
    // This stores all the Chatcolors and DyeColors for each block
    private val colorList: MutableList<BlockColorData> = ArrayList()

    //This is the instance of this class.
    private val bc: BlockColor = BlockColor

    /**
     * Returns the ChatColor associated with the Type ID.
     *
     * @param id
     * @return ChatColor
     */
    fun getChatColor(id: Int): ChatColor {
        for (bcd in colorList) {
            if (bcd.typeID == id && !bcd.hasData) {
                return bcd.chatColor
            }
        }
        return ChatColor.BLACK
    }

    /**
     * Returns the DyeColor associated with the Type ID.
     *
     * @param id
     * @return DyeColor
     */
    fun getDyeColor(id: Int): DyeColor {
        for (bcd in colorList) {
            if (bcd.typeID == id && !bcd.hasData) {
                return bcd.dyeColor
            }
        }
        return DyeColor.BLACK
    }

    /**
     * Returns the ChatColor associated with the Type.
     *
     * @param mat
     * @return ChatColor
     */
    fun getChatColor(mat: Material): ChatColor {
        for (bcd in colorList) {
            if (bcd.usesMaterials() && bcd.type == mat && !bcd.hasData) {
                return bcd.chatColor
            }
        }
        return ChatColor.BLACK
    }

    /**
     * Returns the DyeColorColor associated with the Type.
     *
     * @param mat
     * @return DyeColor
     */
    fun getDyeColor(mat: Material): DyeColor {
        for (bcd in colorList) {
            if (bcd.usesMaterials() && bcd.type == mat && !bcd.hasData) {
                return bcd.dyeColor
            }
        }
        return DyeColor.BLACK
    }

    /**
     * Returns the ChatColor associated with the Type ID and data.
     *
     * @param id
     * @param data
     * @return ChatColor
     */
    fun getChatColor(id: Int, data: Short): ChatColor {
        for (bcd in colorList) {
            if (bcd.typeID == id && bcd.data == data) {
                return bcd.chatColor
            }
        }
        return ChatColor.BLACK
    }

    /**
     * Returns the DyeColor associated with the Type ID and data.
     * @param id
     * @param data
     * @return DyeColor
     */
    fun getDyeColor(id: Int, data: Short): DyeColor {
        for (bcd in colorList) {
            if (bcd.typeID == id && bcd.data == data) {
                return bcd.dyeColor
            }
        }
        return DyeColor.BLACK
    }

    /**
     * Returns the ChatColor associated with the Type  and data.
     * @param mat
     * @param data
     * @return ChatColor
     */
    fun getChatColor(mat: Material, data: Short): ChatColor {
        for (bcd in colorList) {
            if (bcd.usesMaterials() && bcd.type == mat && bcd.data == data) {
                return bcd.chatColor
            }
        }
        return ChatColor.BLACK
    }

    /**
     * Returns the DyeColorColor associated with the Type and data.
     * @param mat
     * @param data
     * @return
     */
    fun getDyeColor(mat: Material, data: Short): DyeColor {
        for (bcd in colorList) {
            if (bcd.usesMaterials() && bcd.type == mat && bcd.data == data) {
                return bcd.dyeColor
            }
        }
        return DyeColor.BLACK
    }

    /**
     * Gets the Type associated with the DyeColor
     * @param dc
     * @return Material
     */
    fun getMaterialWithColor(dc: DyeColor): Material? {
        for (bdc in colorList) {
            if (bdc.dyeColor == dc && !bdc.hasData) return bdc.type
        }
        return Material.STONE
    }

    /**
     * Gets the Type associated with the ChatColorColor
     * @param cc
     * @return Material
     */
    fun getMaterialWithColor(cc: ChatColor): Material? {
        for (bdc in colorList) {
            if (bdc.chatColor == cc && !bdc.hasData) return bdc.type
        }
        return Material.STONE
    }

    /**
     * Gets the TypeID associated with the DyeColor
     * @param dc
     * @return int
     */
    fun getIDWithColor(dc: DyeColor): Int {
        for (bdc in colorList) {
            if (bdc.dyeColor == dc && !bdc.hasData) return bdc.typeID
        }
        return 1
    }

    /**
     * Gets the TypeID associated with the ChatColor
     * @param cc
     * @return int
     */
    fun getIDWithColor(cc: ChatColor): Int {
        for (bdc in colorList) {
            if (bdc.chatColor == cc && !bdc.hasData) return bdc.typeID
        }
        return 1
    }

    internal enum class ColorData(var chatColor: ChatColor, var dyeColor: DyeColor) {
        BLACK(ChatColor.BLACK, DyeColor.BLACK), BLUE(
            ChatColor.BLUE,
            DyeColor.BLUE
        ),
        BROWN(ChatColor.DARK_RED, DyeColor.BROWN), CYAN(
            ChatColor.AQUA, DyeColor.CYAN
        ),
        DARK_GRAY(
            ChatColor.DARK_GRAY,
            DyeColor.GRAY
        ),
        DARK_GREEN(ChatColor.DARK_GREEN, DyeColor.GREEN), AQUA(
            ChatColor.BLUE, DyeColor.LIGHT_BLUE
        ),
        GREEN(
            ChatColor.GREEN,
            DyeColor.LIME
        ),
        LIGHT_PURPLE(
            ChatColor.LIGHT_PURPLE,
            DyeColor.MAGENTA
        ),
        ORANGE(ChatColor.GOLD, DyeColor.ORANGE), PINK(
            ChatColor.RED, DyeColor.PINK
        ),
        WHITE(ChatColor.WHITE, DyeColor.WHITE), YELLOW(
            ChatColor.YELLOW, DyeColor.YELLOW
        ),
        RED(
            ChatColor.RED,
            DyeColor.RED
        );

    }

    internal class BlockColorData {
        var usesMaterial: Boolean
        var hasData = false
        var typeID: Int
        var type: Material? = null
        var c: ColorData
        var data: Short = 0

        constructor(id: Int, data: Int, cd: ColorData) {
            typeID = id
            usesMaterial = false
            c = cd
            this.data = data.toShort()
            hasData = true
            colorList.add(this)
        }

        constructor(mat: Material, data: Int, cd: ColorData) {
            type = mat
            typeID = mat.id
            usesMaterial = true
            c = cd
            this.data = data.toShort()
            hasData = true
            colorList.add(this)
        }

        constructor(id: Int, cd: ColorData) {
            typeID = id
            usesMaterial = false
            c = cd
            colorList.add(this)
        }

        constructor(mat: Material, cd: ColorData) {
            type = mat
            typeID = mat.id
            usesMaterial = true
            c = cd
            colorList.add(this)
        }

        fun usesMaterials(): Boolean {
            return usesMaterial
        }

        val chatColor: ChatColor
            get() = c.chatColor
        val dyeColor: DyeColor
            get() = c.dyeColor

        fun hasData(): Boolean {
            return hasData
        }
    }

    //Add all of the materials.
    init {
        // Base Materials
        // bc.new BlockColorData(Material,ChatColor,DyeColor);
        BlockColorData(Material.DIRT, ColorData.BROWN)
        BlockColorData(Material.GRASS, ColorData.GREEN)
        BlockColorData(Material.SAND, ColorData.YELLOW)
        BlockColorData(Material.PACKED_ICE, ColorData.AQUA)
        BlockColorData(Material.FROSTED_ICE, ColorData.AQUA)
        BlockColorData(Material.ICE, ColorData.AQUA)
        BlockColorData(Material.LEGACY_MYCEL, ColorData.LIGHT_PURPLE)
        BlockColorData(Material.STONE, 0, ColorData.ORANGE)
        BlockColorData(Material.STONE, 1, ColorData.ORANGE)
        BlockColorData(Material.STONE, 2, ColorData.WHITE)
        BlockColorData(Material.STONE, 3, ColorData.WHITE)
        BlockColorData(Material.BEDROCK, ColorData.DARK_GRAY)
        BlockColorData(Material.FURNACE, ColorData.DARK_GRAY)
        BlockColorData(Material.CAULDRON, ColorData.DARK_GRAY)
        BlockColorData(Material.HOPPER, ColorData.DARK_GRAY)
        BlockColorData(Material.BREWING_STAND, ColorData.DARK_GRAY)
        BlockColorData(Material.ANVIL, ColorData.DARK_GRAY)
        BlockColorData(Material.DIAMOND_BLOCK, ColorData.AQUA)
        BlockColorData(Material.GOLD_BLOCK, ColorData.YELLOW)
        BlockColorData(Material.IRON_BLOCK, ColorData.WHITE)
        BlockColorData(Material.EMERALD_BLOCK, ColorData.GREEN)
        BlockColorData(Material.REDSTONE_BLOCK, ColorData.RED)
        BlockColorData(Material.COAL_BLOCK, ColorData.BLACK)
        BlockColorData(Material.LAPIS_BLOCK, ColorData.BLUE)
        BlockColorData(Material.OBSIDIAN, ColorData.BLACK)
        BlockColorData(Material.WATER, ColorData.BLUE)
        BlockColorData(Material.LAVA, ColorData.ORANGE)
        BlockColorData(Material.LEGACY_WATER_LILY, ColorData.GREEN)
        BlockColorData(Material.NETHER_BRICK, ColorData.BROWN)
        BlockColorData(Material.NETHER_BRICK_STAIRS, ColorData.BROWN)
        BlockColorData(Material.LEGACY_NETHER_STALK, ColorData.RED)
        BlockColorData(Material.NETHERRACK, ColorData.RED)
        BlockColorData(Material.GLOWSTONE, ColorData.YELLOW)
        BlockColorData(Material.SOUL_SAND, ColorData.BROWN)
        BlockColorData(Material.LEGACY_SOIL, ColorData.BROWN)
        BlockColorData(Material.GLASS, ColorData.WHITE)
        BlockColorData(Material.TNT, ColorData.RED)
        BlockColorData(Material.BRICK, ColorData.RED)
        BlockColorData(Material.BRICK_STAIRS, ColorData.RED)
        BlockColorData(Material.CHEST, ColorData.BROWN)
        BlockColorData(Material.TRAPPED_CHEST, ColorData.BROWN)
        BlockColorData(Material.BEACON, ColorData.BLUE)
        BlockColorData(Material.NOTE_BLOCK, ColorData.BROWN)
        BlockColorData(Material.JUKEBOX, ColorData.BROWN)
        BlockColorData(Material.LEGACY_MOB_SPAWNER, ColorData.BLACK)
        BlockColorData(Material.FIRE, ColorData.ORANGE)
        BlockColorData(Material.RED_SANDSTONE, ColorData.RED)
        BlockColorData(Material.RED_SANDSTONE_STAIRS, ColorData.RED)

        //Ender
        BlockColorData(Material.LEGACY_ENDER_STONE, ColorData.YELLOW)
        BlockColorData(Material.LEGACY_ENDER_PORTAL_FRAME, ColorData.WHITE)
        BlockColorData(Material.LEGACY_ENDER_PORTAL, ColorData.LIGHT_PURPLE)
        BlockColorData(Material.ENDER_CHEST, ColorData.LIGHT_PURPLE)
        BlockColorData(Material.END_GATEWAY, ColorData.LIGHT_PURPLE)
        BlockColorData(Material.DRAGON_EGG, ColorData.LIGHT_PURPLE)
        BlockColorData(Material.LEGACY_END_BRICKS, ColorData.LIGHT_PURPLE)
        BlockColorData(Material.PURPUR_BLOCK, ColorData.LIGHT_PURPLE)
        BlockColorData(Material.LEGACY_PURPUR_DOUBLE_SLAB, ColorData.LIGHT_PURPLE)
        BlockColorData(Material.PURPUR_PILLAR, ColorData.LIGHT_PURPLE)
        BlockColorData(Material.PURPUR_SLAB, ColorData.LIGHT_PURPLE)
        BlockColorData(Material.PURPUR_STAIRS, ColorData.LIGHT_PURPLE)
        BlockColorData(Material.LEGACY_END_BRICKS, ColorData.YELLOW)
        BlockColorData(Material.PURPUR_BLOCK, ColorData.LIGHT_PURPLE)
        BlockColorData(Material.PUMPKIN, ColorData.ORANGE)
        BlockColorData(Material.LEGACY_MELON_BLOCK, ColorData.GREEN)
        BlockColorData(Material.LEGACY_CAKE_BLOCK, ColorData.WHITE)
        BlockColorData(Material.CACTUS, ColorData.GREEN)
        BlockColorData(Material.DEAD_BUSH, ColorData.BROWN)
        BlockColorData(Material.LEGACY_SUGAR_CANE_BLOCK, ColorData.GREEN)
        BlockColorData(Material.MELON_STEM, ColorData.BROWN)
        BlockColorData(Material.LEGACY_CROPS, ColorData.YELLOW)
        BlockColorData(Material.CARROT, ColorData.ORANGE)
        BlockColorData(Material.POTATO, ColorData.GREEN)
        BlockColorData(Material.BEETROOT, ColorData.ORANGE)

        //Redstone
        BlockColorData(Material.LEGACY_REDSTONE_LAMP_OFF, ColorData.BROWN)
        BlockColorData(Material.LEGACY_REDSTONE_LAMP_ON, ColorData.ORANGE)
        BlockColorData(Material.LEGACY_REDSTONE_TORCH_OFF, ColorData.BROWN)
        BlockColorData(Material.LEGACY_REDSTONE_TORCH_ON, ColorData.ORANGE)
        BlockColorData(Material.REDSTONE, ColorData.RED)

        // Wood Colors
        BlockColorData(Material.LEGACY_WOOD, 0, ColorData.BROWN)
        BlockColorData(Material.LEGACY_LOG, 0, ColorData.BROWN)
        BlockColorData(Material.LEGACY_WOOD, 1, ColorData.BROWN)
        BlockColorData(Material.LEGACY_LOG, 1, ColorData.BROWN)
        BlockColorData(Material.LEGACY_WOOD, 2, ColorData.YELLOW)
        BlockColorData(Material.LEGACY_LOG, 2, ColorData.WHITE)
        BlockColorData(Material.LEGACY_WOOD, 3, ColorData.RED)
        BlockColorData(Material.LEGACY_LOG, 3, ColorData.BROWN)
        BlockColorData(Material.LEGACY_WOOD, 4, ColorData.RED)
        BlockColorData(Material.LEGACY_WOOD, 5, ColorData.BROWN)
        BlockColorData(Material.LEGACY_LOG, 6, ColorData.BROWN)
        BlockColorData(Material.LEGACY_WOOD_STAIRS, ColorData.BROWN)
        BlockColorData(Material.LEGACY_SPRUCE_WOOD_STAIRS, ColorData.BROWN)
        BlockColorData(Material.LEGACY_BIRCH_WOOD_STAIRS, ColorData.YELLOW)
        BlockColorData(Material.LEGACY_JUNGLE_WOOD_STAIRS, ColorData.RED)
        BlockColorData(Material.ACACIA_STAIRS, ColorData.RED)
        BlockColorData(Material.DARK_OAK_STAIRS, ColorData.BROWN)
        BlockColorData(Material.LEGACY_LEAVES_2, ColorData.GREEN)
        BlockColorData(Material.LEGACY_LEAVES_2, ColorData.GREEN)
        BlockColorData(Material.BOOKSHELF, ColorData.BROWN)
        BlockColorData(Material.LEGACY_ENCHANTMENT_TABLE, ColorData.BLACK)

        // TODO: Add all data for each type of step.
        BlockColorData(Material.LEGACY_STEP, ColorData.BROWN)

        // Bricks:
        BlockColorData(Material.QUARTZ_BLOCK, ColorData.WHITE)
        BlockColorData(Material.QUARTZ_STAIRS, ColorData.WHITE)
        BlockColorData(Material.LEGACY_QUARTZ_ORE, ColorData.RED)

        // Glass
        BlockColorData(
            Material.LEGACY_STAINED_GLASS, DyeColor.BLACK.ordinal,
            ColorData.BLACK
        )
        BlockColorData(
            Material.LEGACY_STAINED_GLASS, DyeColor.BLUE.ordinal,
            ColorData.BLUE
        )
        BlockColorData(
            Material.LEGACY_STAINED_GLASS, DyeColor.BROWN.ordinal,
            ColorData.BROWN
        )
        BlockColorData(
            Material.LEGACY_STAINED_GLASS, DyeColor.CYAN.ordinal,
            ColorData.CYAN
        )
        BlockColorData(
            Material.LEGACY_STAINED_GLASS, DyeColor.GRAY.ordinal,
            ColorData.DARK_GRAY
        )
        BlockColorData(
            Material.LEGACY_STAINED_GLASS, DyeColor.GREEN.ordinal,
            ColorData.DARK_GREEN
        )
        BlockColorData(
            Material.LEGACY_STAINED_GLASS, DyeColor.LIGHT_BLUE.ordinal,
            ColorData.AQUA
        )
        BlockColorData(
            Material.LEGACY_STAINED_GLASS, DyeColor.LIME.ordinal,
            ColorData.GREEN
        )
        BlockColorData(
            Material.LEGACY_STAINED_GLASS, DyeColor.MAGENTA.ordinal,
            ColorData.LIGHT_PURPLE
        )
        BlockColorData(
            Material.LEGACY_STAINED_GLASS, DyeColor.ORANGE.ordinal,
            ColorData.ORANGE
        )
        BlockColorData(
            Material.LEGACY_STAINED_GLASS, DyeColor.RED.ordinal,
            ColorData.RED
        )
        BlockColorData(
            Material.LEGACY_STAINED_GLASS, DyeColor.PINK.ordinal,
            ColorData.PINK
        )
        BlockColorData(
            Material.LEGACY_STAINED_GLASS_PANE, DyeColor.BLACK.ordinal,
            ColorData.BLACK
        )
        BlockColorData(
            Material.LEGACY_STAINED_GLASS_PANE, DyeColor.BLUE.ordinal,
            ColorData.BLUE
        )
        BlockColorData(
            Material.LEGACY_STAINED_GLASS_PANE, DyeColor.BROWN.ordinal,
            ColorData.BROWN
        )
        BlockColorData(
            Material.LEGACY_STAINED_GLASS_PANE, DyeColor.CYAN.ordinal,
            ColorData.CYAN
        )
        BlockColorData(
            Material.LEGACY_STAINED_GLASS_PANE, DyeColor.GRAY.ordinal,
            ColorData.DARK_GRAY
        )
        BlockColorData(
            Material.LEGACY_STAINED_GLASS_PANE, DyeColor.GREEN.ordinal,
            ColorData.DARK_GREEN
        )
        BlockColorData(
            Material.LEGACY_STAINED_GLASS_PANE, DyeColor.LIGHT_BLUE.ordinal,
            ColorData.AQUA
        )
        BlockColorData(
            Material.LEGACY_STAINED_GLASS_PANE, DyeColor.LIME.ordinal,
            ColorData.GREEN
        )
        BlockColorData(
            Material.LEGACY_STAINED_GLASS_PANE, DyeColor.MAGENTA.ordinal,
            ColorData.LIGHT_PURPLE
        )
        BlockColorData(
            Material.LEGACY_STAINED_GLASS_PANE, DyeColor.ORANGE.ordinal,
            ColorData.ORANGE
        )
        BlockColorData(
            Material.LEGACY_STAINED_GLASS_PANE, DyeColor.RED.ordinal,
            ColorData.RED
        )
        BlockColorData(
            Material.LEGACY_STAINED_GLASS_PANE, DyeColor.PINK.ordinal,
            ColorData.PINK
        )

        // Clay
        BlockColorData(Material.LEGACY_HARD_CLAY, ColorData.BROWN)
        BlockColorData(
            Material.LEGACY_STAINED_CLAY, DyeColor.BLACK.ordinal,
            ColorData.BLACK
        )
        BlockColorData(
            Material.LEGACY_STAINED_CLAY, DyeColor.BLUE.ordinal,
            ColorData.BLUE
        )
        BlockColorData(
            Material.LEGACY_STAINED_CLAY, DyeColor.BROWN.ordinal,
            ColorData.BROWN
        )
        BlockColorData(
            Material.LEGACY_STAINED_CLAY, DyeColor.CYAN.ordinal,
            ColorData.CYAN
        )
        BlockColorData(
            Material.LEGACY_STAINED_CLAY, DyeColor.GRAY.ordinal,
            ColorData.DARK_GRAY
        )
        BlockColorData(
            Material.LEGACY_STAINED_CLAY, DyeColor.GREEN.ordinal,
            ColorData.DARK_GREEN
        )
        BlockColorData(
            Material.LEGACY_STAINED_CLAY, DyeColor.LIGHT_BLUE.ordinal,
            ColorData.AQUA
        )
        BlockColorData(
            Material.LEGACY_STAINED_CLAY, DyeColor.LIME.ordinal,
            ColorData.GREEN
        )
        BlockColorData(
            Material.LEGACY_STAINED_CLAY, DyeColor.MAGENTA.ordinal,
            ColorData.LIGHT_PURPLE
        )
        BlockColorData(
            Material.LEGACY_STAINED_CLAY, DyeColor.ORANGE.ordinal,
            ColorData.ORANGE
        )
        BlockColorData(
            Material.LEGACY_STAINED_CLAY, DyeColor.RED.ordinal,
            ColorData.RED
        )
        BlockColorData(
            Material.LEGACY_STAINED_CLAY, DyeColor.PINK.ordinal,
            ColorData.PINK
        )

        // WoolColors
        BlockColorData(
            Material.LEGACY_WOOL, DyeColor.BLACK.ordinal,
            ColorData.BLACK
        )
        BlockColorData(
            Material.LEGACY_WOOL, DyeColor.BLUE.ordinal,
            ColorData.BLUE
        )
        BlockColorData(
            Material.LEGACY_WOOL, DyeColor.BROWN.ordinal,
            ColorData.BROWN
        )
        BlockColorData(
            Material.LEGACY_WOOL, DyeColor.CYAN.ordinal,
            ColorData.CYAN
        )
        BlockColorData(
            Material.LEGACY_WOOL, DyeColor.GRAY.ordinal,
            ColorData.DARK_GRAY
        )
        BlockColorData(
            Material.LEGACY_WOOL, DyeColor.GREEN.ordinal,
            ColorData.DARK_GREEN
        )
        BlockColorData(
            Material.LEGACY_WOOL, DyeColor.LIGHT_BLUE.ordinal,
            ColorData.AQUA
        )
        BlockColorData(
            Material.LEGACY_WOOL, DyeColor.LIME.ordinal,
            ColorData.GREEN
        )
        BlockColorData(
            Material.LEGACY_WOOL, DyeColor.MAGENTA.ordinal,
            ColorData.LIGHT_PURPLE
        )
        BlockColorData(
            Material.LEGACY_WOOL, DyeColor.ORANGE.ordinal,
            ColorData.ORANGE
        )
        BlockColorData(
            Material.LEGACY_WOOL, DyeColor.RED.ordinal,
            ColorData.RED
        )
        BlockColorData(
            Material.LEGACY_WOOL, DyeColor.PINK.ordinal,
            ColorData.PINK
        )

        // Doors
        BlockColorData(Material.ACACIA_DOOR, ColorData.BROWN)
        BlockColorData(Material.DARK_OAK_DOOR, ColorData.BROWN)
        BlockColorData(Material.BIRCH_DOOR, ColorData.BROWN)
        BlockColorData(Material.LEGACY_WOODEN_DOOR, ColorData.BROWN)
        BlockColorData(Material.SPRUCE_DOOR, ColorData.BROWN)
        BlockColorData(Material.LEGACY_IRON_DOOR_BLOCK, ColorData.WHITE)

        //Extras
        BlockColorData(Material.BARRIER, ColorData.WHITE)
        BlockColorData(Material.STRUCTURE_BLOCK, ColorData.LIGHT_PURPLE)
    }
}