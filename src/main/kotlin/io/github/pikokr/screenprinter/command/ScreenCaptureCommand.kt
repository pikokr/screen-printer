package io.github.pikokr.screenprinter.command

import io.github.monun.kommand.getValue
import io.github.monun.kommand.kommand
import io.github.pikokr.screenprinter.plugin.ScreenPrinterPlugin
import io.github.pikokr.screenprinter.utils.CaptureThread
import io.github.pikokr.screenprinter.utils.ScreenUtils
import io.github.pikokr.screenprinter.utils.resize
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.MapMeta
import org.bukkit.map.MapCanvas
import org.bukkit.map.MapRenderer
import org.bukkit.map.MapView

class ScreenRenderer(val index: Int) : MapRenderer() {
    override fun render(map: MapView, canvas: MapCanvas, player: Player) {
        CaptureThread.cropped[index]?.let {
            canvas.drawImage(0, 0, it)
        }
    }
}

fun ScreenPrinterPlugin.initCommands() {
    kommand {
        register("sp") {
            then("index" to int()) {
                executes { ctx ->
                    val index: Int by ctx

                    val map = Bukkit.createMap(player.world)

                    map.renderers.forEach { map.removeRenderer(it) }

                    map.addRenderer(ScreenRenderer(index))

                    player.inventory.addItem(ItemStack(Material.FILLED_MAP).apply {
                        itemMeta = (itemMeta as MapMeta).apply {
                            mapView = map
                        }
                    })
                }
            }
        }
    }
}
