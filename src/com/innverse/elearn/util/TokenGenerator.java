package com.innverse.elearn.util;

import java.util.UUID;

public class TokenGenerator {

	    public String generateUniqueKey(){
	    	String id = UUID.randomUUID().toString();
	    	return id;
	    }
	}


