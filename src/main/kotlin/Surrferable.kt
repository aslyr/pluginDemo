/**
 *@author:aslyr
 *@date:create in 21:13 2019/9/23 2019
 */
interface Surrferable:View {
    var blood:Int
    abstract fun notifySurrfer(attack: Attackable)
    fun BoomFx()
}