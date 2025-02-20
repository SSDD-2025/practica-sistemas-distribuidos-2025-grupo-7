package lbj.king.proyecto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SlotMachine {
    @GetMapping("/slots")
    public String slotMachine(Model model) {
        return "slot";
    }
}
