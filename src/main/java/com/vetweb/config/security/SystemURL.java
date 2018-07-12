package com.vetweb.config.security;

import java.util.Arrays;
import java.util.List;

public class SystemURL {
	
	public static final String CLIENTES = "/clientes/**";
	
	public static final String USUARIOS = "/usuarios/**";

	public static final String PRONTUARIO = "/prontuario/**";
	
	public static final String ANIMAIS = "/animais/**";
	
	public static final String CONFIG = "/config/**";

	public static List<String> all() {
		return Arrays.asList(CLIENTES, USUARIOS, PRONTUARIO, ANIMAIS, CONFIG);
	}

}
