import business.Moveable
import enums.Directron

/**
 *@author:aslyr
 *@date:create in 16:21 2019/9/23 2019
 */
interface AutoMove:Moveable,View {
    fun autoMove()
    var speed:Int;


}