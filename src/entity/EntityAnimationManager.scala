package org.apollo
package entity

import core.StateManager
import display.gfx.SpriteSet
import display.gfx.spritesheet.LPCSpriteInfo
import physics.North

import java.awt.Image
import java.awt.image.BufferedImage

/** The entity animation manger is responsible for loading the appropriate
 *      animation frame from the current sprite set
 *  Every animated entity will receive it's own animation manager */
class EntityAnimationManager(val spriteSet: SpriteSet, var looping: Boolean = true) {

    private val SPRITE_SIZE = StateManager.SPRITE_SIZE
    private val UPDATES_PER_FRAME: Int = 10

//    private var currentAnimationFileName = defaultAnimation("light")
    private var currentAnimationSheet: BufferedImage = defaultAnimation("light")
    private var currentFrameTime: Int = 0
    private var currentFrameIndex: Int = 0

    private var currentLPCSpriteInfo: LPCSpriteInfo = Standing.lpcSubsheet(North)

    /*  Called through global update
        Determines the index of the frame based on the state of the entity
     */
    def calculateCurrentFrame(entityState: EntityState): Unit = {
        val direction = entityState.direction
        val entityAction = entityState.entityAction
        currentLPCSpriteInfo = entityAction.lpcSubsheet(direction)
        currentFrameTime += 1
        if(currentFrameTime >= UPDATES_PER_FRAME) {
            currentFrameTime = 0
            currentFrameIndex += 1
        }
        if(currentFrameIndex >= currentLPCSpriteInfo.frameCount) {
            currentFrameIndex = 0
        }
    }

    //  Based on the current frame index, will cut out the correct frame from the sprite sheet
    def getSprite: Image = {
        currentAnimationSheet.getSubimage(
            currentFrameIndex * SPRITE_SIZE,
            currentLPCSpriteInfo.yIndex * SPRITE_SIZE,
            SPRITE_SIZE,
            SPRITE_SIZE
        )
    }


    private def defaultAnimation(fileName: String): Image = {
        if(!fileName.equals(currentAnimationFileName)) {
            currentFrameIndex = 0
            spriteSet.getOrGetDefault(fileName)
        }
    }

}
