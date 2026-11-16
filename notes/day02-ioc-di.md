Part A: Maven සහ pom.xml
1. Maven කියන්නේ මොකක්ද?

* Maven කියන්නේ build tool එකක් සහ dependency manager එකක්. Java project එකකට ඕන library (jar files) අතින් download කරන්න ඕනේ නෑ. pom.xml එකේ නම ලිව්වම Maven ඒවා download කරලා project එකට add කරනවා.

* ඔයා මොකක් හරි library එකක් (උදා: MySQL driver) ඕන වුණාම pom.xml එකේ dependency එකක් විදියට ලියනවා. ඒක තමයි Maven වල ප්‍රධාන වැඩේ.

2. pom.xml එකේ කොටස්

* parent        -	Spring Boot වල default settings සහ library versions ඒකෙන් එනවා
* groupId       -	ඔයාගේ company/organization නම (reverse domain: com.example)
* artifactId    -	project එකේ නම
* version	    -   project version එක
* properties	-   Java version වගේ settings
* dependencies	-   project එකට ඕන libraries


Starter dependencies: spring-boot-starter-web එකක් ඇතුලේ Tomcat, Spring MVC, JSON library (Jackson) වගේ ගොඩක් දේවල් එකතු වෙලා තියෙනවා. ඒකයි Boot එක පහසු.

3. Dependency එකක් add කරන හැටි

Dependency එකක් add කරන්න ඕන නම්:

pom.xml එකේ <dependencies> ඇතුලේ දාන්න.
IntelliJ එකේ දකුණු පැත්තේ Maven tab එකේ Reload (🔄) button එක ඔබන්න.

Dependencies හොයාගන්න mvnrepository.com හෝ start.spring.io පාවිච්චි කරන්න.


Part B: IoC සහ Dependency Injection (Spring වල හදවත ❤️)
1. ප්‍රශ්නය මොකක්ද?

සාමාන්‍ය Java වලදී අපි object හදන්නේ new keyword එකෙන්:

public class OrderService {
// OrderService එකම EmailService object එක හදනවා
private EmailService emailService = new EmailService();

    public void placeOrder() {
        emailService.send("Order placed!");
    }
}

මේකේ ප්‍රශ්න:

* OrderService එක EmailService එකට තදින් බැඳිලා (tightly coupled).
* හෙට EmailService වෙනුවට SmsService පාවිච්චි කරන්න ඕන නම් code එක වෙනස් කරන්න ඕනේ.
* Testing කරන්න අමාරුයි.

2. IoC (Inversion of Control)

Inversion of Control කියන්නේ: object හදන වගකීම ඔයාගෙන් අරන් Spring එකට දෙනවා.

* කලින්: ඔයා new කරලා objects හදනවා.
* දැන්: Spring objects හදලා, ඔයාට ඕන තැනට දෙනවා.

Objects හදලා කළමනාකරණය කරන Spring වල කොටසට කියන්නේ IoC Container (ApplicationContext) කියලා.


3. Dependency Injection (DI)

Dependency Injection කියන්නේ IoC එක ක්‍රියාත්මක කරන ක්‍රමය. ඕන object එක class එක ඇතුලෙන් හදන්නේ නැතුව, පිටින් ඇතුල් කරනවා (inject).

උදාහරණය: Restaurant එකක අවන්හලේ කෙනා (OrderService) ඉස්සෙල්ලා කෑම හදන්නේ නෑ, කෝකා (Chef) ලවා හදවගන්නවා. Chef කවුද කියලා management (Spring) තීරණය කරලා දෙනවා.

4. Bean කියන්නේ මොකක්ද?

Bean කියන්නේ Spring IoC Container එක විසින් හදලා කළමනාකරණය කරන object එකක්. Class එකක් උඩ annotation එකක් දැම්මම ඒක Bean එකක් වෙනවා.


ප්‍රධාන annotations:

* Annotation	  -     භාවිතය
* @Component	  -     සාමාන්‍ය Bean එකක්
* @Service	      -     Business logic classes
* @Repository	  -     Database access classes
* @RestController - 	API controllers


@Service, @Repository, @RestController තුනම ඇත්තටම @Component වල විශේෂ වර්ග. අරමුණ පැහැදිලි කරන්න වෙනස් නම් දෙනවා.

5. Code එකෙන් බලමු

Step 1: EmailService (Bean එකක් කරනවා)

package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void send(String message) {
        System.out.println("Email sent: " + message);
    }
}

Step 2: OrderService (EmailService inject කරනවා)

package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final EmailService emailService;

    // Constructor Injection
    public OrderService(EmailService emailService) {
        this.emailService = emailService;
    }

    public String placeOrder() {
        emailService.send("Your order has been placed!");
        return "Order placed successfully";
    }
}

මෙතන new EmailService() කියලා කොහෙවත් නෑ. Spring එකම EmailService object එක හදලා OrderService constructor එකට දෙනවා.

Step 3: OrderController (OrderService inject කරනවා)

package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order")
    public String order() {
        return orderService.placeOrder();
    }
}

Run කරලා http://localhost:8080/order යන්න. Browser එකේ “Order placed successfully” පෙනෙනවා, IntelliJ console එකේ “Email sent: ...” print වෙනවා.

ගලා යන ආකාරය:


// 1. Constructor Injection ✅ (Recommended, company වල පාවිච්චි කරන්නේ මේක)
private final EmailService emailService;
public OrderService(EmailService emailService) {
this.emailService = emailService;
}

// 2. Field Injection ⚠️ (Short නමුත් නරකයි)
@Autowired
private EmailService emailService;

// 3. Setter Injection (අඩුවෙන් පාවිච්චි වෙනවා)
@Autowired
public void setEmailService(EmailService emailService) {
this.emailService = emailService;
}


ඇයි Constructor Injection හොඳ?

* final කරන්න පුළුවන්, ඒ නිසා object එක වෙනස් වෙන්නේ නෑ.
* Testing වලදී ලේසියි.
*  Dependency නැතුව object එක හදන්න බෑ, ඒ නිසා error එක කලින්ම අල්ලගන්න පුළුවන්.

Spring 4.3+ වලදී constructor එක එකක් විතරක් තියෙනවා නම් @Autowired ලියන්න ඕනේ නෑ.


⭐ වැදගත් Points (Interview වලට)
* IoC = object හදන control එක Spring එකට දෙනවා. DI = ඒ objects ඕන තැනට inject කරනවා.
* Bean = Spring Container එකෙන් manage වෙන object එකක්.
* Company වල Constructor Injection පාවිච්චි කරනවා.
* Spring Beans default එකෙන් Singleton. එහෙමයි කියන්නේ app එක පුරාම object එකක් විතරයි හදන්නේ.
* Class එකක් Bean එකක් වෙන්න annotation (@Component, @Service ආදිය) තියෙන්න ඕනේ. new කරලා හදපු object එකට Spring DI වැඩ නෑ.
* Interview ප්‍රශ්නය: “IoC සහ DI වෙනස මොකක්ද?” IoC කියන්නේ සංකල්පය (principle), DI කියන්නේ ඒක ක්‍රියාත්මක කරන ක්‍රමය.