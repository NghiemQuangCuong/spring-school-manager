package edu.cuongnghiem.springschoolmanager.controller.exceptionAndError;

import org.springframework.stereotype.Controller;

/**
 * Created by cuongnghiem on 23/10/2021
 **/
@Controller
public class CustomErrorController {

//    @RequestMapping("/error")
//    public String generalError(HttpServletRequest request, Model model) {
//        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//        Exception exception = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
//        model.addAttribute("message", exception.getMessage());
//        model.addAttribute("stackTrace", exception.getStackTrace());
//        if (status != null) {
//            Integer statusCode = Integer.parseInt(status.toString());
//            if (statusCode == HttpStatus.NOT_FOUND.value())
//                return "/exception/404";
//            if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value())
//                return "/exception/500";
//        }
//        return "/exception/unknown";
//    }
}
