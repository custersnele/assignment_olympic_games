package be.pxl.olympicgames.api.data.input;

import be.pxl.olympicgames.domain.ScoreStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public class RegisterScoreCommand {
	@NotNull
	private final ScoreStatus status;
	private final LocalTime time;

	public RegisterScoreCommand(ScoreStatus status, @JsonFormat(pattern = "HH:mm:ss.SSS") LocalTime time) {
		this.status = status;
		this.time = time;
	}

	public ScoreStatus getStatus() {
		return status;
	}

	public LocalTime getTime() {
		return time;
	}
}
