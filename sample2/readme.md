https://cloudstudying.kr/

참고 이니셜라이저
start.spring.io
	프로젝트생성 디펜던시 Spring Web, H2 Database, Mustache, Spring Data JPA

artifact 가 프로젝트명

Mustache 뷰템플릿 만드는 엔진
화면template 처리logic 데이터model : mvc

머스태치 처음 생성시 못 읽음 > 플러그인 (인텔리-help-action-plugins-marketplace-mustache-install)
	ext 설치
	{{}} 변수 표기

getbootstrap.com
	starterTemplate를 메인페이지 복붙

정적페이지 내용 변경내용은 빌드만으로도 적용 가능

뷰템플릿 호출시 {{>layouts/header}}

jpa
	entity 자바객체를 규격화
	repository db로 전달
# dto > ctrl > entity > repo > save() > db

@Entity //db가 해당 객체를 인식 가능
@Autowired 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결

#
dto>entity
public Article toEntity() {return new Article(null, title, content);}
repository
public interface ArticleRepository extends CrudRepository<Article, Long> //관리대상 엔티티, 대표값 타입
data save
Article saved = articleRepository.save(article);

# DB 웹 콘솔 접근 허용
spring.h2.console.enabled=true
localhost:8080/h2-console
	jdbc uri 기동시마다 다름

getter, setter, constructor, toString =>
	lombok : @Data, @Slf4j

롬복
build.gradle에 의존성 추가, 빌드 디펜던시, 플러그인 설치, annotaion processing enable 체크
	load gradle changes 실행
#		./gradlew --refresh-dependencies
#		./gradlew build --refresh-dependencies 실행까지 할 때

#lombok ext 설치

@PathVariable
	 url에서 {} 변수 받을 때

null 일 수 있는 건 ex) select 결과
	Optional<타입>
	참고 if 등에 사용 경우 특정 구문필요
		https://www.cloudhadoop.com/java-error-nosuchelementexception/
			if (number2.isPresent()){}
			또는 number1.orElse(0)

mustache에서 모델 data 변수는 {{#변수명}}

failed to execute sql script statement 에러 발생 시
../resources/application.properties
콘솔 설정
  spring.h2.console.enabled=true
기동시 data.sql 실행
  spring.jpa.defer-datasource-initialization=true

인텔리제이 한글 깨짐
 메뉴바 > Help > VM 옵션 수정
다음 설정을 끝부분에 추가
-Dfile.encoding=UTF-8

api test
	jsonplaceholder.typicode.com
	talend api tester

@RestController : json 리턴

@RequestBody
	post 에서 요청 값 가져올 때

ResponseEntity<Article>
	상태코드 보낼 수 있음

ResponseEntity.status(HttpStatus.OK).build();
	body 없을 때

stream 문법에서
Collectors.toList() 는
	~List.add(aaa)

@ManyToOne
	현재클래스 many : 본 변수 one

@JoinColumn(name="연결 대상 컬럼")

extends JpaRepository<대상, Long>
	paging and sorting

쿼리 방법
	@Query
	META-INF > orm.xml
	빌드패턴?

@Autowired에서 자동으로 만들려면 대상이 @Component : ioc컨테이너에 등록 이어야 함.

테스트
자료 수정 생성 테스트는 transactional 처리
@Test
  @Transactional

@Aspect aop클래스 부가기능 클래스
	@Component 같이 필요

@Pointcut 대상 메소드
@Before, @After
@AfterReturning 대상실햏 후, 정상 수행시
@AfterThrowing 대상실행 후, 예외 시
@Around 대상 실행 전후

ObjectMapper
Json <> DTO

String json = objectMapper.writeValueAsString(aaa)
Aaa aaa = objectMapper.readValue(json, aaa.class)

ObjectNode objectNode = objectMapper.createObjectNode();
objectNode.put("name","nameValue");

ArrayNode arrayNode = objectMapper.createArrayNode();
arrayNode.add("value");
objectNode.set("value",arrayNode);

String json = objectNode.toString();

dto > object : mapper
https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application

후속 공부 Spring security, jpa

https://cloudstudying.kr/lectures/485 차례