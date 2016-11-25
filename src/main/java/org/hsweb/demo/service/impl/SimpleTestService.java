package org.hsweb.demo.service.impl;

import org.hsweb.demo.bean.test.TestPo;
import org.hsweb.demo.bean.test.TestPo.Property;
import org.hsweb.demo.dao.test.TestDao;
import org.hsweb.demo.service.TestService;
import org.hsweb.web.bean.common.QueryParam;
import org.hsweb.web.service.impl.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * 根据名称查询
     *
     * @see org.hsweb.ezorm.core.dsl.Query
     */
    @Override
    public List<TestPo> selectByName(String name) {
        return createQuery().where(Property.name, name).listNoPaging();
    }

}
