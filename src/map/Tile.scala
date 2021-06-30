package org.apollo
package map

import org.apollo.core.StateManager

import java.awt.Image
import java.awt.image.BufferedImage
import display.gfx.SpriteLibrary

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

