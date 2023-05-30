package capston.capston.order.viewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/api/payment_inprogess")
    public String paymentInprogress(){
        return "/payment_inprogess.html";
    }

    @GetMapping("/api/payment_completed")
    public String paymentCompleted(){
        return "/payment_completed.html";
    }
}
