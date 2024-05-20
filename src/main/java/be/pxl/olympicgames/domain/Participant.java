package be.pxl.olympicgames.domain;

import java.time.LocalTime;

public class Participant {

	// The two following annotation are used to save the time (LocalTime) as a string in the database
	//@Column(columnDefinition = "VARCHAR(12)")
	//@Convert(converter = LocalTimeAttributeConverter.class)
	private LocalTime time;
}
