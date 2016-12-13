package org.hsweb.demo.controller.test;

import org.hsweb.demo.bean.test.TestPo;
import org.hsweb.demo.service.TestService;
import org.hsweb.web.controller.GenericController;
import org.hsweb.web.core.authorize.annotation.Authorize;
import org.hsweb.web.core.authorize.validator.SimpleAuthorizeValidator;
import org.hsweb.web.core.logger.annotation.AccessLogger;
import org.hsweb.web.core.message.ResponseMessage;
import org.hsweb.web.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouhao
 */
@RestController
@RequestMapping("/test")
@Authorize(module = "test")
@AccessLogger("测试")
public class TestController extends GenericController<TestPo, String> {

    @Autowired
    TestService testService;

    @Override
    protected TestService getService() {
        return testService;
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    @Authorize(api = true) //试试oauth2方式调用
    @AccessLogger("按名称查询")
    public ResponseMessage getByName(@PathVariable("name") String name) {
        return ResponseMessage.ok(testService.selectByName(name));
    }
}
