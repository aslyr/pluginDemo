/**
 *@author:aslyr
 *@date:create in 21:13 2019/9/23 2019
 */
interface Attackable:View {
    fun isCollison(surrferable: Surrferable):Boolean
    fun notifyAttack(surfer: Surrferable)
    //攻击力
    var power:Int
}