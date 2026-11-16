Part A: @SpringBootApplication ඇතුලේ තියෙන දේවල්

Day 1 දී අපි කිව්වා @SpringBootApplication ඇතුලේ annotation තුනක් තියෙනවා කියලා. දැන් ඒවා බලමු:

Annotation	වැඩේ
@Configuration	   -  මේ class එකේ Bean definitions තියෙනවා කියලා කියනවා
@ComponentScan	   -  @Component, @Service ආදිය තියෙන classes හොයනවා
@EnableAutoConfiguration - 	Classpath එකේ තියෙන libraries බලලා ඒවාට ඕන setup ස්වයංක්‍රීයව කරනවා

@ComponentScan නිසා තමයි ඔයාගේ EmailService එකේ @Service දැම්මම Spring එක ඒක හොයාගත්තේ. ඒක scan කරන්නේ main class එක තියෙන package එකෙන් පටන් අරන් ඒකට යටින් තියෙන හැම package එකක්ම (ඔයාගේ com.mentor.first_spring_app එක).

@EnableAutoConfiguration නිසා තමයි spring-boot-starter-web දාපු ගමන් Tomcat server එක තනියම start වුණේ (ඔයාගේ console එකේ “Tomcat started on port 8080” කියලා පෙනුනා).


Part B: @Bean සහ @Configuration
ඇයි @Bean ඕන?

@Service, @Component දාන්න පුළුවන් ඔයා ලියපු classes වලට විතරයි. හැබැයි වෙන කෙනෙක් ලියපු library classes වලට (උදා: ModelMapper, RestTemplate) ඒවගේ annotations දාන්න බෑ. ඒවා Bean කරගන්න @Bean පාවිච්චි කරනවා.

Code Example

com.mentor.first_spring_app package එක ඇතුලේ AppConfig.java හදන්න:

package com.mentor.first_spring_app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public String appName() {
        return "My First Spring App";
    }
}

දැන් මේ Bean එක HelloController එකට inject කරමු:

package com.mentor.first_spring_app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final String appName;

    public HelloController(String appName) {
        this.appName = appName;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from " + appName;
    }
}


http://localhost:8080/hello යන්න: “Hello from My First Spring App”.

ඉහත එක ඉගෙනීමේ උදාහරණයක් විතරයි. Real projects වල @Bean පාවිච්චි කරන්නේ ModelMapper, PasswordEncoder, RestTemplate වගේ library objects හදන්නයි.

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();   // වෙන API එකකට call කරන්න
    }
}


@Component vs @Bean

@Component /                 @Service           	            @Bean
දාන තැන	                Class එක උඩ	            Method එක උඩ (@Configuration class එකක් ඇතුලේ)
භාවිතය	ඔයා ලියපු classes 	Library classes,            special setup ඕන objects


Part C: Bean Scopes

Bean එකක් කී පාරක් හදනවද කියන එක තීරණය කරන්නේ scope එකෙන්.

Scope	තේරුම
singleton      -   (default)	App එක පුරාම object එකක් විතරයි
prototype	   -   ඉල්ලන හැම වෙලාවකම අලුත් object එකක්
request	       -   HTTP request එකකට එකක් (web apps)
session	User   -   session එකකට එකක් (web apps)

@Service
@Scope("prototype")
public class ReportService {
}

99%ක්ම වෙලාවට singleton තමයි පාවිච්චි වෙන්නේ. ඒ නිසා Service class වල user-specific data fields ලෙස තියාගන්න එපා, මොකද හැම user එකටම object එක එකම නිසා data පටලවෙනවා.

Part D: application.properties

src/main/resources/application.properties ඔයාගේ project එකේ දැනටමත් තියෙනවා (screenshot එකේ පෙනුනා). මෙතනින් app එකේ settings කරනවා.

# Server port
server.port=9090

# App name
spring.application.name=first-spring-app

# Custom property එකක්
app.company=ABC Pvt Ltd
app.version=1.0

Custom properties code එකට ගන්නේ කොහොමද? (@Value)

package com.mentor.first_spring_app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    @Value("${app.company}")
    private String company;

    @Value("${app.version}")
    private String version;

    @GetMapping("/info")
    public String info() {
        return company + " - Version " + version;
    }
}

http://localhost:9090/info (port එක 9090 කළා නම්, නැත්නම් 8080) යන්න: “ABC Pvt Ltd - Version 1.0”.

ඇයි properties file එක පාවිච්චි කරන්නේ? Database password, API keys වගේ දේවල් code එකේ hard-code කරන්නේ නෑ. Settings වෙනස් කරන්න code නැවත compile කරන්න ඕනේ නෑ.

YAML විකල්පය: Company වල application.yml පාවිච්චි කරන එකත් දකින්න ලැබෙනවා. ලියන විදිය වෙනස්, හැබැයි වැඩේ එකමයි. අපි දැනට .properties එකෙන්ම යමු.

⭐ වැදගත් Points

* @SpringBootApplication = @Configuration + @ComponentScan + @EnableAutoConfiguration.
* @ComponentScan එක main class එකේ package එකට යටින් විතරයි scan කරන්නේ.
* ඔයාගේම classes වලට @Component/@Service, library classes වලට @Bean.
* Default scope = singleton.
* Config values application.properties එකේ තියලා @Value("${key}") එකෙන් ගන්නවා.
* Interview ප්‍රශ්න: “@Component සහ @Bean වෙනස?”, “Default bean scope මොකක්ද?”, “@SpringBootApplication ඇතුලේ මොනවද?”
