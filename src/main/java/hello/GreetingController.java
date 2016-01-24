package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private int count=0;
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	count++;
    	System.out.println(count);
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping("greeting/login")
    public Greeting login(@RequestParam(value="password", defaultValue="*****") String password, @RequestParam(value="id", defaultValue="Guest") String id) {
    	count++;
    	System.out.println(count);
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, id + password));
    }
}
