package be.pxl.olympicgames.api.data.input;

import be.pxl.olympicgames.domain.Discipline;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class CreateRaceCommand {

	@NotNull
	private final Discipline discipline;
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private final LocalDateTime dateTime;

	public CreateRaceCommand(Discipline discipline, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime dateTime) {
		this.discipline = discipline;
		this.dateTime = dateTime;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public Discipline getDiscipline() {
		return discipline;
	}
}
