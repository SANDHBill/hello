package com.sandh.web;
import com.sandh.domain.Timer;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/timers")
@Controller
@RooWebScaffold(path = "timers", formBackingObject = Timer.class)
public class TimerController {
}
