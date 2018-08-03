package com.atcs.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

@Component
public class Message {

	@Autowired
	private MessageSource messageSource;

	private MessageSourceAccessor accessor;

	@PostConstruct
	private void init() {
		accessor = new MessageSourceAccessor(messageSource, new Locale("en", "US"));
	}

	public String get(String code) {
		return accessor.getMessage(code);
	}

	public String get(String key, Object... params) {
		try {
			return MessageFormat.format(accessor.getMessage(key), params);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
	
	public interface ResponseMessages {
	    String SAME_AIRCRAFT_EXISTS = "same.aircraft.exists";
	    }

}
