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
//测试
db.createOrAlter("s_test_form")
        .addColumn().name("u_id").alias("id").comment("ID").jdbcType(JDBCType.VARCHAR).length(32).primaryKey().commit()
        .addColumn().name("sex").alias("sex").comment("性别").jdbcType(JDBCType.DECIMAL).length(4,0).commit()
        .addColumn().name("age").alias("age").comment("年龄").jdbcType(JDBCType.DECIMAL).length(16,0).commit()
        .addColumn().name("name").alias("name").comment("").jdbcType(JDBCType.VARCHAR).length(128).commit()
        .addColumn().name("remark").alias("remark").comment("备注").jdbcType(JDBCType.CLOB).commit()
        .comment("测试").commit();

def module= [u_id: 'test', name: '测试', uri: 'admin/test/list.html', icon: '', parent_id: '-1', remark: '', status: 1, optional: '[{"id":"M","text":"菜单可见","checked":true},{"id":"import","text":"导入excel","checked":true},{"id":"export","text":"导出excel","checked":true},{"id":"R","text":"查询","checked":true},{"id":"C","text":"新增","checked":true},{"id":"U","text":"修改","checked":true},{"id":"D","text":"删除","checked":false}]', sort_index: 1];
db.getTable("s_modules").createInsert().value(module).exec();
