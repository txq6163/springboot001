package jp.gtf.taotao001.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterForm {
	String userName;
	String email;
	String password;
	String passwordConfirm;
}
