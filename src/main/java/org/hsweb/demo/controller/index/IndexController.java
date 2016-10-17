package org.hsweb.demo.controller.index;

import org.hsweb.web.core.authorize.annotation.Authorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhouhao
 */
@Controller
public class IndexController {
    @RequestMapping(value = {"/", "/index.html"})
    @Authorize
    public String index() {
        return "admin/index";
    }
}
