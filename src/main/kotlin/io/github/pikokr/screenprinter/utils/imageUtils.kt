package io.github.pikokr.screenprinter.utils

import net.coobird.thumbnailator.Thumbnails
import java.awt.image.BufferedImage

fun BufferedImage.resize(w: Int, h: Int): BufferedImage {
    return Thumbnails.of(this).forceSize(w, h).asBufferedImage()
}