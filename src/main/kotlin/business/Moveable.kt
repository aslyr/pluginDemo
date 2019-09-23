package business

import enums.Directron

/**
 *@author:aslyr
 *@date:create in 21:07 2019/9/20 2019
 */
interface Moveable {
    var currentDirectron:Directron
    fun iscollison(blockable: Blockable):Boolean
    fun notifyCollison(currentDirectron: Directron)
}