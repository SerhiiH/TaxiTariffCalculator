class TaxiRide {
	private final int passengers;
	private final int distance;
	private final int duration;
	
	private TaxiRide(int passengers, int distance, int duration) {
		this.passengers = passengers;
		this.distance = distance;
		this.duration = duration;
	}
	
	public static TaxiRide getTaxiRide(int passengers, int distance, int duration) {
		check(passengers, "Passengers");
		check(distance, "Distance");
		check(duration, "Duration");
		return new TaxiRide(passengers, distance, duration);
	}
	private static void check(int value, String valueName) {
		if (value <= 0)
			throw new IllegalArgumentException(valueName + " <= 0");
	}
	public int getPassengersAmount() {
		return passengers;
	}
	public int getDistance() {
		return distance;
	}
	public int getDuration() {
		return duration;
	}
}

interface TaxiTariff {
	long calculatePrice(TaxiRide tr);
}

class StandardTariff implements TaxiTariff {
	@Override
	public long calculatePrice(TaxiRide tr) {
		return (30 + 5 * tr.getDistance() + 2 * tr.getDuration());
	}
}

class FamilyTariff implements TaxiTariff {
	@Override
	public long calculatePrice(TaxiRide tr) {
		return (long)(50 + 20 * tr.getDistance() / tr.getPassengersAmount());
	}
}

public class TaxiTariffCalculator {
	private static long getTariff(TaxiTariff tt, TaxiRide tr) {
		return tt.calculatePrice(tr);
	}
	
	public static void main(String[] args) {
		TaxiRide ride = TaxiRide.getTaxiRide(2, 10, 33);
		
		System.out.println("Standard tariff - " + getTariff(new StandardTariff(), ride));
		System.out.println("Family tariff - " + getTariff(new FamilyTariff(), ride));
	}
}