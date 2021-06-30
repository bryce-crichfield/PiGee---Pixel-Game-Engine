package org.apollo
package display.gfx

import java.awt.Image
import scala.collection.mutable.ListBuffer

object SpriteLibrary {

    private val spriteSets = loadSpriteSets("/sprites/units")
    private val images = ImageUtils.loadImages("/sprites/tiles")

    private def loadSpriteSets(path: String): Map[String, SpriteSet] = {
        val folderNames = ImageUtils.getSubdirectories(path)
        val buffer = ListBuffer[(String, SpriteSet)]()
        for(folderName <- folderNames) {
            val spriteSet = new SpriteSet()
            val pathToFolder = s"$path/$folderName"
            val sheetsInFolder = ImageUtils.getImagesInFolder(pathToFolder)
            for(sheetName <- sheetsInFolder) {
                val image = ImageUtils.loadImage(s"$pathToFolder/$sheetName")
                if(image.isSuccess) {
                    spriteSet.addSheet(sheetName.dropRight(4), image.get)
                }
            }
            buffer.addOne(folderName, spriteSet)
        }
        buffer.toMap
    }

    def getSpriteSet(name: String): SpriteSet = {
        spriteSets(name)
    }

    def getImage(name: String): Image = images(name)
}
