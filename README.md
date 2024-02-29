plugins {
	id 'java'
	id 'org.springframework.boot' version '3.2.2'
	id 'io.spring.dependency-management' version '1.1.4'
}

group = 'hello'
version = '0.0.1-SNAPSHOT'
java {
	sourceCompatibility = '17'
}

//lombok 설정 추가 시작
configurations {
	compileOnly {
		extendsFrom annotationProcessor
	}
}
//lombok 설정 추가 끝




repositories {
	mavenCentral()
}

dependencies {
	implementation 'org.springframework.boot:spring-boot-starter'
	//Provider
	implementation 'jakarta.inject:jakarta.inject-api:2.0.1'
	//web 라이브러리 추가
	implementation 'org.springframework.boot:spring-boot-starter-web'

	//lombok 라이브러리 추가 시작
	compileOnly 'org.projectlombok:lombok'
	annotationProcessor 'org.projectlombok:lombok'
	testCompileOnly 'org.projectlombok:lombok'
	testAnnotationProcessor 'org.projectlombok:lombok'
	//lombok 라이브러리 추가 끝
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

/*
tasks.named('test') {
	useJUnitPlatform()
}
*/
