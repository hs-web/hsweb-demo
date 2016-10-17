package org.hsweb.demo.service.impl;

import org.hsweb.demo.bean.test.TestPo;
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
     * @see QueryParam 还支持丰富的查询条件,如 param.where("name$like","")
     * @see org.hsweb.ezorm.param.TermType
     */
    @Override
    public List<TestPo> selectByName(String name) {
        return testDao.select(QueryParam.build().where("name", name).noPaging());
    }

}
