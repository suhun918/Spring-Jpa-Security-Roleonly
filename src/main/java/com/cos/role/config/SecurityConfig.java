package com.cos.role.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		// 여기 내부만 완성하면 됩니다.

		http.authorizeRequests().antMatchers("/user/list").permitAll()
				.antMatchers("/user/**").authenticated()
				// or은 둘중 권한 하나만 가져도 접근가능 / and쓰면 두 권한을 다 가져야 접근가능
				// hasRole 안의 것을 MyUserDetails의 계정이 가지고 있는 권한을 리턴받아 쓰는 것
				.antMatchers("/manager/**").access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
				.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/tester/**")
				.access("hasRole('ROLE_TESTER') or hasRole('ROLE_ADMIN')")
				// 이외의 요청은 모두 허가
				.anyRequest().permitAll()
				.and()
				.formLogin()
				.defaultSuccessUrl("/user/test").and().logout()
				.logoutSuccessUrl("/user/test").and()
				// 권한이 없어 오류가 났을때 보낼 페이지
				.exceptionHandling().accessDeniedPage("/accessDenied");

	}

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encodePWD());
	}

}