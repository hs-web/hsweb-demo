package org.hsweb.demo.bean.test;

import org.hsweb.web.bean.po.GenericPo;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author zhouhao
 */
public class TestPo extends GenericPo<String> {
    @NotNull
    private String name;

    private int age;

    private Date createDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
