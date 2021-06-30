package org.apollo
package display.gfx

import java.awt.Image
import scala.collection.mutable


class SpriteSet() {
    private val animationSheets = new mutable.ListMap[String, Image]()

    def this(image: Image) = {
        this()
        addSheet("default", image)
    }

    def addSheet(name: String, animationSheet: Image): Unit = {
        animationSheets.addOne(name -> animationSheet)
    }

    def getOrGetDefault(name: String): Image = {
        if(animationSheets.contains(name)) {
            animationSheets(name)
        }
        else animationSheets(name)
    }

    def getAnimationSheet = animationSheets
}
