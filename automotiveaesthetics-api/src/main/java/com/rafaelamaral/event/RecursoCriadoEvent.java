package com.rafaelamaral.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecursoCriadoEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private HttpServletResponse response;
	
	private Integer codigo;
	
	public RecursoCriadoEvent(Integer codigo , HttpServletResponse response ,Object source) {
		super(source);
		this.codigo = codigo;
		this.response = response;
	}

}
