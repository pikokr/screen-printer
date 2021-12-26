package io.github.pikokr.screenprinter.command

import io.github.monun.kommand.getValue
import io.github.monun.kommand.kommand
import io.github.pikokr.screenprinter.plugin.ScreenPrinterPlugin
import io.github.pikokr.screenprinter.utils.CaptureThread
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.ItemFrame
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.MapMeta
import org.bukkit.map.MapCanvas
import org.bukkit.map.MapRenderer
import org.bukkit.map.MapView

class ScreenRenderer(private val index: Int) : MapRenderer() {
    override fun render(map: MapView, canvas: MapCanvas, player: Player) {
        CaptureThread.cropped[index]?.let {
            canvas.drawImage(0, 0, it)
        }
    }

    companion object {
        private val maps: Array<MapView?> = Array(249) { null };

        fun get(idx: Int): MapView {
            var i = maps[idx]
            if (i == null) {
                val m = Bukkit.createMap(Bukkit.getWorlds()[0])
                m.renderers.forEach { m.removeRenderer(it) }
                m.addRenderer(ScreenRenderer(idx))
                maps[idx] = m
                i = m
            }
            return i
        }
    }
}

fun ScreenPrinterPlugin.initCommands() {
    kommand {
        register("sp") {
            then("index" to int()) {
                executes { ctx ->
                    val index: Int by ctx

                    player.inventory.addItem(ItemStack(Material.FILLED_MAP).apply {
                        itemMeta = (itemMeta as MapMeta).apply {
                            mapView = ScreenRenderer.get(index)
                        }
                    })
                }
            }
        }

        register("spbuild") {
            executes {
                val loc = player.location
                val world = player.world

                for (i in 0..15) {
                    for (j in 0..8) {
                        world.getBlockAt(loc.blockX + i, loc.blockY + j, loc.blockZ).apply {
                            type = Material.STONE
                            (world.spawnEntity(location.apply {
                                z += 1
                            }, EntityType.ITEM_FRAME) as ItemFrame).apply {
                                setItem(ItemStack(Material.FILLED_MAP).apply {
                                    itemMeta = (itemMeta as MapMeta).apply {
                                        mapView = ScreenRenderer.get((16 * i) + j)
                                    }
                                })
                            }
                        }
                    }
                }
            }
        }
    }
}
