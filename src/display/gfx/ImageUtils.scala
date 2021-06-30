package org.apollo
package display.gfx

import physics.Size

import java.awt.image.BufferedImage
import java.awt.{GraphicsEnvironment, Image}
import java.io.File
import javax.imageio.ImageIO
import scala.collection.mutable.ListBuffer
import scala.util.{Success, Try}

object ImageUtils {

    val ALPHA_OPAQUE = 1
    val ALPHA_BIT_MASKED = 2
    val ALPHA_BLEND = 3

    def loadImage(path: String): Try[Image] = {
        val image = Try(ImageIO.read(new File(path)))
        if(image.isFailure) return image
        val compatibleImage = createCompatibleImage(
            Size(image.get.getWidth(null), image.get.getHeight(null)),
            ALPHA_BIT_MASKED).asInstanceOf[BufferedImage]
        val graphics = compatibleImage.createGraphics()
        graphics.drawImage(image.get, 0, 0, null)
        graphics.dispose()
        Success(compatibleImage.asInstanceOf[Image])
    }

    def loadImages(path: String): Map[String, Image] = {
        val imagesInFolder = getImagesInFolder(path)
        val buffer = ListBuffer[(String, Image)]()
        for(filename <- imagesInFolder) {
            val image = loadImage(filename)
            if(image.isSuccess) {
                buffer.addOne(filename.dropRight(4) -> image.get)
            }
        }
        buffer.toMap
    }

     def getImagesInFolder(path: String): List[String] = {
        val resource = this.getClass.getResource(path)
        val file = new File(resource.getFile)
        file.list((current, name) => new File(current, name).isFile).toList
    }

     def getSubdirectories(path: String): List[String] = {
        val resource = this.getClass.getResource(path)
        val file = new File(resource.getFile)
        file.list((current, name) => new File(current, name).isDirectory).toList
    }

    def createCompatibleImage(size: Size, transparency: Int): Image = {
        val graphicsConfiguration = GraphicsEnvironment
            .getLocalGraphicsEnvironment
            .getDefaultScreenDevice
            .getDefaultConfiguration
        graphicsConfiguration.createCompatibleImage(size.width, size.height, transparency)
    }
}
