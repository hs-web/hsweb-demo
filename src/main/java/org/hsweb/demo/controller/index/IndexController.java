package org.hsweb.demo.controller.index;

import org.hsweb.web.core.authorize.annotation.Authorize;
import org.hsweb.web.core.utils.WebUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhouhao
 */
@Controller
public class IndexController {
    @RequestMapping(value = {"/", "/index.html"})
    @Authorize
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/index");
        view.addObject("absPath", WebUtil.getBasePath(request));
        return view;
    }
}
