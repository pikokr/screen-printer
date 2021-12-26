package io.github.pikokr.screenprinter.utils

import java.awt.image.BufferedImage

class CaptureThread : Thread() {
    companion object {
        var current: BufferedImage? = null

        val cropped: Array<BufferedImage?> = Array(249) { null }

        var shouldStop = false
    }

    override fun run() {
        while (!shouldStop) {
            current = ScreenUtils.capture().resize(1920, 1080)
            for (i in 0..15) {
                for (j in 0..8) {
                    cropped[(16 * i) + j] = current!!.getSubimage(i * 120, j * 120, 120, 120).resize(128, 128)
                }
            }
        }
    }
}