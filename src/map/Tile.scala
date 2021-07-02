package org.apollo
package map

import core.StateManager
import display.gfx.SpriteLibrary

import java.awt.Image
import java.awt.image.BufferedImage

case class Tile(image: Image, tileIndex: Int, tileName: String) {

    val sprite: Image = generateSprite

    private def generateSprite: Image = {
        image.asInstanceOf[BufferedImage]
            .getSubimage(
                (tileIndex / (image.getWidth(null) / StateManager.SPRITE_SIZE)) * StateManager.SPRITE_SIZE,
                (tileIndex % (image.getWidth(null) / StateManager.SPRITE_SIZE)) * StateManager.SPRITE_SIZE,
                StateManager.SPRITE_SIZE,
                StateManager.SPRITE_SIZE
            )
    }

    def reloadGraphics: Tile = {
        val newImage = SpriteLibrary.getImage(tileName)
        this.copy(image = newImage)
    }

}
object Tile {


}

