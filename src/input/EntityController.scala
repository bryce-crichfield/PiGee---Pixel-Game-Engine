package org.apollo
package input

trait EntityController {

    def isRequestingUp: Boolean
    def isRequestingDown: Boolean
    def isRequestingRight: Boolean
    def isRequestingLeft: Boolean
    def isRequestingAttack: Boolean
    def isRequestingCast: Boolean
    def isRequestingSprint: Boolean
}
