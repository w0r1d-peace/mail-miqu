package com.islet.controller.mail;

import com.islet.controller.AbstractController;
import com.islet.support.elasticsearch.service.IEmailTransClientService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tangJM.
 * @date 2021/11/5
 * @description
 */
@Controller
@RequestMapping("/email")
@Validated
public class EmailController extends AbstractController {

}
