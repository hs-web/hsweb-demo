package org.hsweb.demo.service.impl;

import org.hsweb.demo.bean.test.TestPo;
import org.hsweb.demo.bean.test.TestPo.Property;
import org.hsweb.demo.dao.test.TestDao;
import org.hsweb.demo.service.TestService;
import org.hsweb.ezorm.rdb.executor.SqlExecutor;
import org.hsweb.web.core.datasource.DynamicDataSource;
import org.hsweb.web.datasource.dynamic.UseDataSource;
import org.hsweb.web.service.datasource.DataSourceService;
import org.hsweb.web.service.impl.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesJava7;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhouhao
 */
@Service("testService")
public class SimpleTestService extends AbstractServiceImpl<TestPo, String> implements TestService {

    @Autowired
    private TestDao testDao;

    @Override
    protected TestDao getMapper() {
        return testDao;
    }

    @Autowired
    private SqlExecutor sqlExecutor;

    @Autowired
    private DataSourceService dataSourceService;

    @Override
    @Transactional
    public String insert(TestPo data) {
        try {
            DynamicDataSource.use("test");
            super.insert(data);
            createQuery().list();
            DynamicDataSource.useDefault();
            return super.insert(data);
        } finally {
//            throw new RuntimeException();
        }
    }

    /**
     * 根据名称查询
     *
     * @see org.hsweb.ezorm.core.dsl.Query
     */
    @Override
    @Transactional
    @UseDataSource("test")
    public List<TestPo> selectByName(String name) {
        try {
            DynamicDataSource.use("test");
            createQuery().where(Property.name, name).listNoPaging();
            DynamicDataSource.useDefault();
            createQuery().where(Property.name, name).listNoPaging();
            DynamicDataSource.use("test");
            createQuery().where(Property.name, name).listNoPaging();
            DynamicDataSource.useDefault();
        } catch (Exception e) {
        }
        //finally {
        //  throw new RuntimeException();
        //}
        return createQuery().where(Property.name, name).listNoPaging();
    }

}
