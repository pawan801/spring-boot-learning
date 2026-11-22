1. REST API කියන්නේ මොකක්ද?
   API කියන්නේ එක software එකක් තවත් software එකක් එක්ක කතා කරන්න තියෙන දොරටුවක්. REST API කියන්නේ HTTP (web එකේ භාෂාව) පාවිච්චි කරලා data හුවමාරු කරන ක්‍රමයක්.

උදාහරණයක්: Mobile app එකක user කෙනෙක්ගේ profile එක පෙන්වන්න ඕන නම්, app එක backend (ඔයා හදන Spring Boot app එක) එකට request එකක් යවනවා. Backend එක JSON data එකක් ආපහු යවනවා

Mobile / React App  ──(HTTP Request)──►  Spring Boot Backend
◄──(JSON Response)──

JSON කියන්නේ data ලියන සරල format එකක්:

{
"id": 1,
"name": "Nimal",
"age": 21,
"course": "Java"
}

2. HTTP Methods (CRUD)

Data වලට කරන්න පුළුවන් මූලික වැඩ 4ක් තියෙනවා (CRUD). හැම එකකටම HTTP method එකක් තියෙනවා:

වැඩේ	                     HTTP Method	   Spring Annotation	  උදාහරණ URL
Create (හදන්න) 	         POST	           @PostMapping	         POST/api/students
Read (කියවන්න)  	         GET	           @GetMapping           GET/api/students
Update (වෙනස් කරන්න)     	 PUT	           @PutMapping	         PUT/api/students/1
Delete (මකන්න)	             DELETE  	       @DeleteMapping	     DELETE/api/students/1

3. HTTP Status Codes
   Server එක response එකත් එක්ක status code එකක් යවනවා. Company වල මේවා නිවැරදිව පාවිච්චි කරන එක ගොඩක් වැදගත්:

Code	                        තේරුම	                            කවදාද
200    OK	                    සාර්ථකයි	                        GET, PUT සාර්ථක වුණාම
201    Created	                අලුතින් හැදුවා	                    POST සාර්ථක වුණාම
204    No Content	            සාර්ථකයි, return කරන්න දෙයක් නෑ	DELETE සාර්ථක වුණාම
400    Bad Request	            Client එකේ request එකේ වැරැද්දක්	වැරදි data එව්වාම
404    Not Found	            හොයපු දේ නෑ                   	ID එකට student කෙනෙක් නැති වුණාම
500    Internal Server Error	Server එකේ වැරැද්දක්             	Code එකේ bug එකක්


5. අලුත් Annotations තේරුම්

Annotation                      	තේරුම්	                                                             උදාහරණය
@RequestMapping("/api/students")	Class එකේ හැම method එකකටම පොදු URL prefix එක 
@PathVariable	                    URL එකේ කොටසක් (/1) ගන්නවා	                                     /api/students/{id}
@RequestParam	                    ? පස්සේ එන query parameter ගන්නවා                 	            ?course=Java
@RequestBody	                    Request එකේ body එකේ ආපු JSON එක Java object එකකට හරවනවා    	POST/PUT
ResponseEntity	                    Response එකත් එක්ක status code එකත් යවන්න                     	ResponseEntity.ok(...)


@PathVariable vs @RequestParam:

GET /api/students/5 → @PathVariable: එක් විශේෂ resource එකක් හඳුනාගන්න (ID)
GET /api/students?course=Java → @RequestParam: filter/search කරන්න

⭐ වැදගත් Points (Interview)

* REST: HTTP methods (GET, POST, PUT, DELETE) පාවිච්චි කරලා resources හසුරුවන architecture style එකක්.
* @RestController = @Controller + @ResponseBody (return කරන object එක JSON විදියට යවනවා).
* PUT vs POST: POST = අලුතින් හදන්න, PUT = තියෙන එකක් update කරන්න.
* @PathVariable vs @RequestParam වෙනස හැමෝම අහනවා.
* Status codes නිවැරදිව යවන එක REST API එකක් professional ද කියන එක පෙන්වනවා (201, 204, 404).
* Interview ප්‍රශ්නය: "PUT සහ PATCH වෙනස?" PUT = සම්පූර්ණ object එකම replace කරනවා, PATCH = කොටසක් විතරක් update කරනවා.