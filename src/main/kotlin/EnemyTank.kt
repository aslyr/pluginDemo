import business.Blockable
import enums.Directron
import org.itheima.kotlin.game.core.Composer
import org.itheima.kotlin.game.core.Painter
import kotlin.random.Random

/**
 *@author:aslyr
 *@date:create in 21:38 2019/9/24 2019
 */
class EnemyTank(override var x: Int, override var y: Int) :AutoMove,Surrferable,Blockable,AutoShot{
    override fun BoomFx() {
        for (index in 1..32) {
            Painter.drawImage("img/blast_$index.png", x, y)

        }
    }


    override var width: Int=Config.width
    override var height: Int=Config.height
    var badDirection:Directron?=null
    override fun draw() {
        if (blood <= 0) {
            isDestory = true
            return
        }
        when (currentDirectron) {
            Directron.UP -> Painter.drawImage("img/enemy_1_u.gif", x, y)
            Directron.DOWN -> Painter.drawImage("img/enemy_1_d.gif", x, y)
            Directron.LEFT -> Painter.drawImage("img/enemy_1_l.gif", x, y)
            Directron.RIGHT -> Painter.drawImage("img/enemy_1_r.gif", x, y)
        }
    }

    override var isDestory: Boolean=false

    override fun autoMove() {
        if (badDirection==currentDirectron){
           when(Random.nextInt(4)) {
               0->currentDirectron=Directron.UP
               1->currentDirectron=Directron.DOWN
               2->currentDirectron=Directron.LEFT
               3->currentDirectron=Directron.RIGHT
           }
           return
        }
        when (currentDirectron) {
            Directron.UP -> {
                this.y -= speed
            }
            Directron.DOWN -> {
                this.y += speed
            }
            Directron.LEFT -> {
                this.x -= speed
            }
            Directron.RIGHT -> {
                this.x += speed
            }
        }
    }

    override var speed: Int=2
    override var currentDirectron: Directron=Directron.DOWN

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
        if (Math.abs(blCenterX - mlCenterx) < (ml.width + bl.width) / 2 && Math.abs(blCenterY - mlCenterY) < (ml.height + bl.height) / 2 ||x<0||y<0||x>Config.windowWidth-this.width||y>Config.windowHeight-this.height) {

            return true
        } else {
            return false
        }
    }

    override fun notifyCollison(currentDirectron: Directron) {
        badDirection=currentDirectron
    }

    override var blood: Int=3

    override fun notifySurrfer(attack: Attackable) {
        blood -= attack.power
        println("敌方坦克收到攻击")
        Composer.play("sound/hit.wav")
        BoomFx()
    }
    //设置自动射击间隔时间
    private var shortTime=0L
    private var period=800
    override fun autoShot (): Bullets? {
        var currenttime=System.currentTimeMillis()
        if (currenttime-shortTime<period){
            return null
        }

        shortTime=currenttime
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