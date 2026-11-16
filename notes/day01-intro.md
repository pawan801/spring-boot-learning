Spring Boot වලින් company එකක කරන වැඩ
REST API හදනවා: Mobile app, React/Angular frontend වලට data දෙන backend එක.
Database එක්ක වැඩ: MySQL/PostgreSQL වල data save, read, update, delete කරනවා (JPA/Hibernate).
Security: Login, JWT token, role-based access (Admin/User).
Microservices: ලොකු system එකක් පොඩි services වලට බෙදලා හදනවා.
Testing, Deployment: Unit tests ලියනවා, Docker වලින් server එකට deploy කරනවා.


Day 1: Spring Boot කියන්නේ මොකක්ද? 

Spring Framework කියන්නේ Java applications හදන්න උදව් වෙන ලොකු framework එකක්. Object හදන එක, objects අතර සම්බන්ධතා, database, web, security වගේ දේවල් පහසු කරනවා.

ප්‍රශ්නේ තිබුණේ, Spring වලට configuration (XML files, setup) ගොඩක් ලියන්න ඕනේ වුණා. ඒකට විසඳුම තමයි Spring Boot.

Spring Boot = Spring + Auto Configuration + Embedded Server

* Auto Configuration: ඔයාට ඕන දේ ඇඟවුවම Boot එකම setup කරනවා.
* Embedded Server: Tomcat server එක application එක ඇතුලෙම තියෙනවා. වෙනම install කරන්න ඕනේ නෑ.
* Starter dependencies: spring-boot-starter-web එකක් දැම්මම web එකට ඕන හැම library එකම එනවා.
* Run කරන්න ලේසියි: main() method එක run කළාම server එක start වෙනවා.

@RestController: මේ class එක web requests handle කරනවා, return කරන දේ response එකක් විදියට යවනවා.
@GetMapping("/hello"): /hello URL එකට GET request එකක් ආවොත් මේ method එක run කරන්න.

⭐ වැදගත් Points
Spring Boot එකෙන් server එක වෙනම install කරන්න ඕනේ නෑ. Embedded Tomcat එක තියෙනවා.
Default port එක 8080. application.properties එකේ server.port=9090 දාලා වෙනස් කරන්න පුළුවන්.
Main class එක තියෙන package එකට යටින් තියෙන classes විතරයි Spring ස්වයංක්‍රීයව හොයාගන්නේ. අනිත් packages වල දැම්මොත් වැඩ කරන්නේ නෑ.
Interview එකකදී හැමෝම අහන්නේ “Spring vs Spring Boot වෙනස මොකක්ද?” කියලා. පිළිතුර: Boot එකෙන් configuration අඩු කරලා, auto-config සහ embedded server දෙනවා.