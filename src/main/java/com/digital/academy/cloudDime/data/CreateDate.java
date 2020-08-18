package com.digital.academy.cloudDime.data;

import java.sql.Timestamp;

public class CreateDate {
	
public static Timestamp createDate() {
		
		Timestamp createDate = new Timestamp(System.currentTimeMillis());

		return createDate;
	}

}
