package org.apollo
package display.gfx

import core.StateManager
import entity.{EntityAction, Standing}
import physics.Direction

import java.awt.Image
import java.awt.image.BufferedImage

class AnimationManager(val spriteSet: SpriteSet, var looping: Boolean = true) {

    private val SPRITE_SIZE = StateManager.SPRITE_SIZE

    private var currentAnimationName: String = ""
    private var currentAnimationSheet: BufferedImage = null
    private val updatesPerFrame: Int = 10
    private var currentFrameTime: Int = 0
    private var frameIndex: Int = 0
    private var directionIndex: Int = 0
    playAnimation("light")

    /*  Called through global update
        Determines the index of the frame
     */
    def update(direction: Direction, entityAction: EntityAction): Unit = {
        directionIndex = direction.index + entityAction.startIndex
        currentFrameTime += 1
        if(currentFrameTime >= updatesPerFrame) {
            currentFrameTime = 0
            frameIndex += 1
            if(frameIndex >= entityAction.frameCount) {
                frameIndex = 0
            }
        }
        if(entityAction.equals(Standing)) frameIndex = 0
    }

//    Called by renderer after the frameindex is updated
    def getSprite: Image = {
        currentAnimationSheet.getSubimage(
            frameIndex * SPRITE_SIZE,
            directionIndex * SPRITE_SIZE,
            SPRITE_SIZE,
            SPRITE_SIZE
        )
    }


    def playAnimation(name: String): Unit = {
        if(!name.equals(currentAnimationName)) {
            currentAnimationSheet = spriteSet.getOrGetDefault(name).asInstanceOf[BufferedImage]
            currentAnimationName = name
            frameIndex = 0
        }
    }
}
