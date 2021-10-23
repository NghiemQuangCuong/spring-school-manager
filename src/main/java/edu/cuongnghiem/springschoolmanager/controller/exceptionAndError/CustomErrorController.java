package edu.cuongnghiem.springschoolmanager.controller.exceptionAndError;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.boot.web.servlet.error.ErrorController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by cuongnghiem on 23/10/2021
 **/
@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String generalError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value())
                return "/exception/404";
            if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value())
                return "/exception/500";
        }
        return "/exception/unknown";
    }
}
