package com.vetweb.config.security;

import java.util.Arrays;
import java.util.List;

public class SystemURL {
	
	public static final String CLIENTES = "/clientes/**";
	
	public static final String PRONTUARIO = "/prontuario/**";
	
	public static final String ANIMAIS = "/animais/**";
	
	public static final String CONFIG = "/config/**";
	
	public static final String INTEGRATION = "/integration/mappings";
	
	public static final String AGENDAMENTO = "/agendamento/**";

	public static List<String> all() {
		return Arrays.asList(CLIENTES, PRONTUARIO, ANIMAIS, CONFIG, INTEGRATION, AGENDAMENTO);
	}

}
