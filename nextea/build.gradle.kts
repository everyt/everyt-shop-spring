plugins {
	java
	id("org.springframework.boot") version "3.1.2"
	id("io.spring.dependency-management") version "1.1.2"
}

group = "com.everyt"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web") // 스프링
	testImplementation("org.springframework.boot:spring-boot-starter-test") // 스프링
    implementation("org.springframework.boot:spring-boot-starter-data-jpa") // JPA
    implementation("org.springframework.boot:spring-boot-starter-security") // 시큐리티
    
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")
    
	compileOnly("org.projectlombok:lombok:1.18.28") // 롬복
    annotationProcessor("org.projectlombok:lombok") // 롬복
    
    runtimeOnly("com.h2database:h2") // H2
    
	implementation("jakarta.persistence:jakarta.persistence-api:3.1.0") // javax: 자카르타
	implementation("jakarta.ws.rs:jakarta.ws.rs-api:3.1.0")
	
	// implementation("mysql:mysql-connector-java:8.0.33")
}