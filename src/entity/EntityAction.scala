package org.apollo
package entity

trait EntityAction {
    //From ULPC Animation Sheet
    val startIndex: Int
    val frameCount: Int
}
case object Standing extends EntityAction {
    override val startIndex: Int = 0
    override val frameCount: Int = 1
}
case object Casting extends EntityAction {
    override val startIndex: Int = 0
    override val frameCount: Int = 7
}
case object Thrusting extends EntityAction {
    override val startIndex: Int = 4
    override val frameCount: Int = 8
}
case object Walking extends EntityAction {
    override val startIndex: Int = 8
    override val frameCount: Int = 9
}
case object Slashing extends EntityAction {
    override val startIndex: Int = 12
    override val frameCount: Int = 6
}
case object Drawing extends EntityAction {
    override val startIndex: Int = 16
    override val frameCount: Int = 13
}
case object Prone extends EntityAction {
    override val startIndex: Int = 20
    override val frameCount: Int = 6
}