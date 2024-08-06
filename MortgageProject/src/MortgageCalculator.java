import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

public class MortgageCalculator {

	private static final Map<String, Double> PROFESSION_BASE_AMOUNTS = new HashMap<>();

	static {
		PROFESSION_BASE_AMOUNTS.put("Developer", 160000.0);
		PROFESSION_BASE_AMOUNTS.put("Architect", 160000.0);
		PROFESSION_BASE_AMOUNTS.put("Scrum master", 160000.0);
		PROFESSION_BASE_AMOUNTS.put("Tester", 120000.0);
		PROFESSION_BASE_AMOUNTS.put("System Administrator", 120000.0);
		PROFESSION_BASE_AMOUNTS.put("Technical writer", 120000.0);
		PROFESSION_BASE_AMOUNTS.put("Department head", 220000.0);
		PROFESSION_BASE_AMOUNTS.put("Professor", 220000.0);
	}

	public double computeMaxMortgage(int yearOfBirth, int month, int day, double monthlyIncome, boolean married, double monthlyIncomePartner, String profession) {
		int age = calculateAge(yearOfBirth, month, day);
		double totalIncome = calculateTotalIncome(monthlyIncome, married, monthlyIncomePartner);

		if (age < 18) {
			return 0;
		}

		return calculateMortgageAmount(totalIncome, profession);
	}

	private int calculateAge(int year, int month, int day) {
		LocalDate today = LocalDate.now();
		LocalDate birthday = LocalDate.of(year, month, day);
		return Period.between(birthday, today).getYears();
	}

	private double calculateTotalIncome(double monthlyIncome, boolean married, double monthlyIncomePartner) {
		return married ? monthlyIncome + monthlyIncomePartner * 0.94 : monthlyIncome;
	}

	private double calculateMortgageAmount(double totalIncome, String profession) {
		if (!PROFESSION_BASE_AMOUNTS.containsKey(profession)) {
			return 0;
		}

		double baseAmount = PROFESSION_BASE_AMOUNTS.get(profession);
		if (totalIncome >= 5000) {
			return baseAmount + 60000;
		} else if (totalIncome >= 3000) {
			return baseAmount + 20000;
		} else if (totalIncome >= 2000) {
			return baseAmount;
		} else {
			return 0;
		}
	}
}
