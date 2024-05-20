package be.pxl.olympicgames.servlet;

import be.pxl.olympicgames.domain.Discipline;
import be.pxl.olympicgames.domain.ScoreStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class OlympicGamesServlet {

	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
	private static final Logger LOGGER = LogManager.getLogger(OlympicGamesServlet.class);

	private void printTitle(PrintWriter writer, Discipline discipline, LocalDateTime dateTime) {
		writer.println("<h3>" + discipline + " " + DATE_TIME_FORMATTER.format(dateTime) + "</h3>");
	}

	private void printAthleteWithTime(PrintWriter writer, String name, String country, LocalTime time) {
		writer.println("<tr><td>" + name + "</td><td>" + country + "</td><td>" + TIME_FORMATTER.format(time) + "</td></tr>");
	}

	private void printAthleteWithStatus(PrintWriter writer, String name, String country, ScoreStatus status) {
		writer.println("<tr><td>" + name + "</td><td>" + country + "</td><td>" + status + "</td></tr>");
	}

}
