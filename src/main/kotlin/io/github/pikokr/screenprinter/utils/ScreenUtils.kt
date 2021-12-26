package io.github.pikokr.screenprinter.utils

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

object ScreenUtils {
    fun capture(): BufferedImage {
        val robot = Robot()

        val rect = Rectangle(Toolkit.getDefaultToolkit().screenSize)

        return robot.createScreenCapture(rect)
    }
}