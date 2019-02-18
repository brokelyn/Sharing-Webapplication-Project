package de.hhu.propra.sharingplatform.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;


@Controller
public class SharingPlatformErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public String errorHandling(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String errormsg = String.valueOf(request.getAttribute(RequestDispatcher.ERROR_MESSAGE));
        model.addAttribute("errorcode", Integer.valueOf(status.toString()));
        if (errormsg.length() <= 0) {
            errormsg = "No message provided";
        }
        model.addAttribute("errormsg", errormsg);
        return "error";
    }
}
