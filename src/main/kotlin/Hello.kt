import cn.hutool.http.*
import org.w3c.dom.ranges.Range
import java.io.File
import java.nio.file.Path
import kotlinx.coroutines.*
fun main() {

   // HttpUtil.downloadFile("http://www.shu800.com/uploads/img/1302401106-5.jpg",System.getProperty("user.dir")+"/1.jpg")

        GlobalScope.launch { // 在后台启动一个新的协程并继续
            delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
            println("World!") // 在延迟后打印输出
        }
        println("Hello,") // 协程已在等待时主线程还在继续


}
