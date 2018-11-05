package com.lmgroup.groupbusiness.schedule;

import com.lmgroup.groupbusiness.domain.business.BussinessVO;
import com.lmgroup.groupbusiness.service.BussinessService;
import com.lmgroup.groupbusiness.utils.ParamException;
import com.lmgroup.groupbusiness.utils.commonAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class TestTransact extends commonAction {

    @Autowired
    private BussinessService bussinessService;

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/testTransact",method = RequestMethod.POST)
    public void testTransact(HttpServletResponse resp, HttpServletRequest req) throws Exception{
        int id = Integer.parseInt(req.getParameter("id"));
        if (id < 1) {
            throw new ParamException("参数错误");
        }
        BussinessVO bussinessVO = bussinessService.data(id);
        sendResult(resp, bussinessVO);
    }

}
