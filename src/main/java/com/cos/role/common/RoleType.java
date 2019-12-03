package com.cos.role.common;

public enum RoleType {
	USER(1), MANAGER(2), ADMIN(3), TESTER(4);

	public final int ID;
	
	private RoleType(int id) {
		this.ID = id;
	}
}
//enum 기본 문법

//RoleType.USER : 해당 문자를 그대로 출력
//RoleType.USER.ordinal : 해당 문자의 순번을 출력
//RoleType.USER.ID : 내가 지정해준 변수로 출력

//for (Day r : RoleType.values()) {
//	System.out.println(r);
//	System.out.println(r.ordinal());
//	System.out.println(r.ID);
//}

