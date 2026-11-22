Day 4 දී ලියපු StudentController එකේ හැම දේම (data තියාගන්න එක, logic, request handle කරන එක) එකම class එකේ තිබුණා. පොඩි project එකකට ඒක ප්‍රශ්නයක් නෑ. හැබැයි company එකක project එකක් controllers 50ක්, logic ගොඩක් එක්ක තිබුණොත් එහෙම ලියන්න බෑ. අද ඒකට හරි ක්‍රමය ඉගෙන ගන්නවා.

Code එක ස්ථර (layers) වලට බෙදනවා. හැම layer එකකටම එක වගකීමක් විතරයි.

Client (Postman / React / Mobile)
         │  HTTP request
         ▼
┌─────────────────┐
│   Controller    │  ← Request ගන්නවා, Response යවනවා (logic නෑ)
└────────┬────────┘
         ▼
┌─────────────────┐
│     Service     │  ← Business logic (රීති, calculations, තීරණ)
└────────┬────────┘
         ▼
┌─────────────────┐
│   Repository    │  ← Data save/read (Database එක්ක කතා කරනවා)
└────────┬────────┘
         ▼
      Database


Layer             	  Annotation	                  වගකීම
Controller	         @RestController	           URL map කරනවා, request/response handle කරනවා
Service	             @Service	                   Business logic
Repository	         @Repository                	Data access

උදාහරණය: අවන්හලක වේටර් (Controller) ඔයාගේ order එක අරන් එනවා. කෝකා (Service) කෑම හදනවා. ගබඩාව (Repository) අමුද්‍රව්‍ය ගබඩා කරලා දෙනවා. වේටර් කෑම හදන්නේ නෑ, කෝකා ගබඩාවට යන්නේ නෑ.

ඇයි මේක හොඳ?

* පැහැදිලියි: වැරැද්දක් ආවොත් කොහෙද කියලා ඉක්මනට හොයාගන්න පුළුවන්.
* වෙනස් කරන්න ලේසියි: Database එක මාරු කළාම Repository එක විතරක් වෙනස් කරනවා.
* Test කරන්න ලේසියි (Day 27+ දී බලමු).
* Team එකක වැඩ කරන්න ලේසියි: එක කෙනෙක් Controller, තව කෙනෙක් Service.

2. DTO කියන්නේ මොකක්ද?

DTO = Data Transfer Object. Client එකත් එක්ක හුවමාරු වෙන data වලට පාවිච්චි කරන වෙනම class එකක්.

ඇයි Student (model) එකම පාවිච්චි කරන්නේ නැත්තේ? Model/Entity එක database එකේ table එකට සමානයි. ඒකේ client ට පෙන්නන්න ඕන නැති දේවල් තියෙන්න පුළුවන් (password, NIC, internal flags). Model එකම JSON විදියට යැව්වොත් ඒ හැමදේම ලෝකෙට පෙනෙනවා (security ප්‍රශ්නයක්).

Client  ──StudentRequestDto──►  Controller ──► Service ──► Student (model)
Client  ◄──StudentResponseDto──  Controller ◄── Service ◄── Student (model)

3. අද හදන්න යන්නේ

Day 4 එකේ Student API එකම layers වලට බෙදලා හදනවා. අලුත් package එකක් day5 කියලා හදලා, ඒක යටින් sub-packages:

com.mentor.first_spring_app
└── day5
├── controller   → StudentController
├── service      → StudentService
├── repository   → StudentRepository
├── model        → Student
└── dto          → StudentRequestDto, StudentResponseDto

⚠️ වැදගත්: Day 4 එකේ StudentController එකත් /api/students map කරනවා. 
අලුත් එකත් ඒකම map කළොත් Ambiguous mapping error එක එනවා. ඒ නිසා අලුත් එකට /api/v2/students පාවිච්චි කරනවා.
(Day 4 එක ඒ විදියටම තියන්න, delete කරන්න එපා.)

⭐ වැදගත් Points (Interview)
* Layered Architecture: Controller → Service → Repository. හැම layer එකකටම එක වගකීමක්.
* Controller = HTTP handle කරනවා, Service = Business logic, Repository = Data access.
* DTO = Client එක්ක data හුවමාරු කරන class එක. Entity = Database table එකට සමාන class එක.
* ඇයි DTO පාවිච්චි කරන්නේ? Sensitive data සඟවන්න, Client ට අවශ්‍ය දේ විතරක් යවන්න, Entity එක වෙනස් වුණත් API එක වෙනස් නොවෙන්න.
* Service vs @Repository vs @Controller තුනම @Component වර්ග, වගකීම පැහැදිලි කරන්න වෙනස් නම්.
* Interview ප්‍රශ්නය: "Entity සහ DTO වෙනස මොකක්ද?", "Layered architecture ඇයි පාවිච්චි කරන්නේ?"

