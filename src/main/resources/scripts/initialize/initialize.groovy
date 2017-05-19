import org.hsweb.ezorm.rdb.RDBDatabase
import org.hsweb.ezorm.rdb.executor.SqlExecutor


//首次运行，自定义初始化脚本
//内置database和sqlExecutor
SqlExecutor sql = sqlExecutor;
RDBDatabase db = database;
println("=============安装数据库=====================")
