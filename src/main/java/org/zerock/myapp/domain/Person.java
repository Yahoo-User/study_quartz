package org.zerock.myapp.domain;

import lombok.Builder;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;


@ToString
@Log4j2
//@Slf4j

@Builder
public class Person {
	private String name;
	private Integer age;
	private Double weight;
	private Double height;
	
	// true: female, false: male
	private Boolean gender;
	
	
	
	// 임시 테스트 용도로 선언
	public static void main(String... args) {
//		PersonBuilder builder = Person.builder();
//		Person person = builder.build();

		Person person = 
			Person.builder()	// (1) 건설사를 만들고
			
				.name("Yoseph")
				.age(23)
				.weight(58.0)
				.height(172.5)
				
				.build();		// (2) 건설사에게 Person을 만들라!
		
		log.info("person: {}", person);
	} // main
	
	
	
} // end class




