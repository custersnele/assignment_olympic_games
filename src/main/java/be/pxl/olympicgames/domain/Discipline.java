package be.pxl.olympicgames.domain;

public enum Discipline {
	SPRINT_100M(100),
	HORDES_400M(400),
	LONGDISTANCE_10000M(10000);

	private int distance;

	Discipline(int distance) {
		this.distance = distance;
	}

	public int getDistance() {
		return distance;
	}
}
