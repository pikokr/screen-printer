package io.github.pikokr.screenprinter.plugin

import io.github.pikokr.screenprinter.command.initCommands
import io.github.pikokr.screenprinter.utils.CaptureThread
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Monun
 */
class ScreenPrinterPlugin : JavaPlugin() {
    private var thread: CaptureThread? = null

    override fun onEnable() {
        initCommands()
        thread = CaptureThread().apply {
            start()
        }
    }

    override fun onDisable() {
        CaptureThread.shouldStop = true
    }
}