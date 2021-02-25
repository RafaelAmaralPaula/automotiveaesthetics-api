package com.rafaelamaral.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Builder
@Setter
public class ApiException {

	private final String messageUser;
	private final String messageDeveloper;
	private final HttpStatus httpStatus;
	private final ZonedDateTime timesStamp;

}
