package com.pcx.common;

import java.util.UUID;

public class UUIDUntil {
	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
