package com.U2W2D5.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.U2W2D5.entity.Employee;
import com.github.javafaker.Faker;

@Configuration
public class EmployeeConfig {
	
	@Bean("User")
	@Scope("prototype")
	public Employee paramsUser(String name, String username, String email, String password) {
		return Employee.builder().name(name).username(username).email(email).build();
	}
	
	@Bean("FakeUser")
	@Scope("prototype")
	public Employee fakeUser() {
		Faker f = Faker.instance(new Locale("it-IT"));
		Employee e = new Employee();
		String rName = f.name().firstName();
		String rSurname = f.name().lastName();
		e.setName(rName + " " + rSurname);
		e.setUsername(rName.toLowerCase() + "." + rSurname.toLowerCase() + ".#");
		e.setEmail(rName.toLowerCase() + "." + rSurname.toLowerCase() + "@doge.com");
		return e;
	}
}
