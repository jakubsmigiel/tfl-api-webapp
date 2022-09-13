package com.jsmi.tflapp.react;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

// The purpose of this class is to redirect all non-api requests to the React.js front-end and let it manage the routes.

// Attribution:
// The following code was made by Tristan Elliot on StackOverflow
// https://stackoverflow.com/a/71818634

@Controller
public class ReactAppController {

    @RequestMapping(value = { "/", "/{x:[\\w\\-]+}", "/{x:^(?!api$).*$}/*/{y:[\\w\\-]+}","/error"  })
    public String getIndex(HttpServletRequest request) {
        return "/index.html";
    }

}