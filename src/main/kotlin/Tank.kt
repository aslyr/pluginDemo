import business.Blockable
import business.Moveable
import enums.Directron
import org.itheima.kotlin.game.core.Painter
import kotlin.text.Typography.bullet

class Tank(override var x: Int, override var y: Int) : View, Moveable,Surrferable {
    override var blood: Int=8

    override fun notifySurrfer(attack: Attackable) {
    }

    override fun BoomFx() {
        for (index in 1..32) {
            Painter.drawImage("img/blast_$index.png", x, y)

        }
    }

    override var isDestory: Boolean=false


    override fun notifyCollison(currentDirectron: Directron) {
        badDirectron = currentDirectron

    }

    override fun iscollison(blockable: Blockable): Boolean {
        val bl = blockable as View;
        val ml = this as View

        var x = ml.x
        var y = ml.y

        when (currentDirectron) {
            Directron.UP -> y -= speed
            Directron.DOWN -> y += speed
            Directron.LEFT -> x -= speed
            Directron.RIGHT -> x += speed
        }

        val blCenterX = bl.x + bl.width / 2
        val blCenterY = bl.y + bl.height / 2
        val mlCenterx = x + ml.width / 2
        val mlCenterY = y + ml.height / 2

        //判断碰撞关键代码，中心点距离判断。
        if (Math.abs(blCenterX - mlCenterx) < (ml.width + bl.width) / 2 && Math.abs(blCenterY - mlCenterY) < (ml.height + bl.height) / 2) {

            return true
        } else {
            return false
        }
    }

    override var width: Int = Config.width

    override var height: Int = Config.width
    override var currentDirectron = Directron.UP
    private var badDirectron: Directron? = null;
    /**
     * 坦克移动速度
     */
    var speed: Int = 32

    override fun draw() {
        if (blood <= 0) {
            isDestory = true
            return
        }
        when (currentDirectron) {
            Directron.UP -> Painter.drawImage("img/tank_u.gif", x, y)
            Directron.DOWN -> Painter.drawImage("img/tank_d.gif", x, y)
            Directron.LEFT -> Painter.drawImage("img/tank_l.gif", x, y)
            Directron.RIGHT -> Painter.drawImage("img/tank_r.gif", x, y)
        }
    }

    /**
     * 坦克移动
     * @param 移动方向
     * @author aslyr
     * @see www.baidu.com
     */
    fun move(directron: Directron) {
        if (badDirectron == directron) {
            return
        }
        //如果当前坦克方向和希望朝向不一致，只转向，不移动。
        if (directron.equals(this.currentDirectron)) {
            this.currentDirectron = directron
            println("开始移动坦克")
            when (currentDirectron) {
                Directron.UP -> y -= speed
                Directron.DOWN -> y += speed
                Directron.LEFT -> x -= speed
                Directron.RIGHT -> x += speed
            }
        } else {
            this.currentDirectron = directron
        }
        if (x < 0) x = 0
        var maxX = Config.windowWidth - width
        if (x > maxX) x = maxX
        if (y < 0) y = 0
        var maxY = Config.windowHeight - height
        if (y > maxY) y = maxY
        badDirectron = null
    }

    fun fire(): Bullets {
        var bullets = Bullets()
        bullets.currentDirectron = currentDirectron
        when (currentDirectron) {
            Directron.UP -> {
                bullets.x = this.x + this.width / 2 - bullets.width / 2
                bullets.y = this.y - bullets.height
            }
            Directron.DOWN -> {
                bullets.x = this.x + this.width / 2 - bullets.width / 2
                bullets.y = this.y + this.height
            }
            Directron.LEFT -> {
                bullets.x = this.x - bullets.height
                bullets.y = this.y + this.height / 2 - bullets.width / 2
            }
            Directron.RIGHT -> {
                bullets.x = this.x + this.width
                bullets.y = this.y + this.height / 2 - bullets.width / 2
            }
        }
        return bullets
    }

}
