import org.hsweb.ezorm.rdb.RDBDatabase
import org.hsweb.ezorm.rdb.executor.SqlExecutor

import java.sql.JDBCType

//首次运行，自定义初始化脚本
//内置database和sqlExecutor
SqlExecutor sql = sqlExecutor;
RDBDatabase db = database;
println("=============安装数据库=====================")
db.createOrAlter("s_test")
        .addColumn().name("u_id").comment("ID").varchar(32).primaryKey().commit()
        .addColumn().name("name").comment("名称").varchar(64).notNull().commit()
        .addColumn().name("age").comment("年龄").jdbcType(JDBCType.INTEGER).commit()
        .addColumn().name("create_date").comment("创建日期").datetime().notNull().commit()
        .comment("测试表").commit();
db.getTable("s_test").createInsert().value([u_id:"test",name:"测试",age:10,create_date:new Date()]).exec();