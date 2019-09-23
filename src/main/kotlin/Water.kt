import business.Blockable
import org.itheima.kotlin.game.core.Painter

class Water(override var x: Int, override var y: Int) :View,Blockable{
    override var width=Config.width
    override var height=Config.height;
    override fun draw() {
        Painter.drawImage("img/water.gif",x,y)
    }

}