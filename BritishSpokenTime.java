package com.test;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BritishSpokenTime {

	//Method which takes time in hh:mm and gives output in British spoken format
	public void printBritishSpokenTime(String givenTime) {

		//Array to hold the numbers in corresponding words format
		String numsInWords[] = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
				"eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen",
				"twenty", "twenty one", "twenty two", "twenty three", "twenty four", "twenty five", "twenty six",
				"twenty seven", "twenty eight", "twenty nine", "thirty", "thirty-one", "thirty-two", "thirty-three",
				"thirty-four" };

		if (givenTime == null || givenTime.isEmpty()) {
			System.out.println("Invalid input");
			return;
		}

		//Split the given time to hour and minutes
		String[] parts = givenTime.split(":");
		if (parts.length < 2 && parts.length > 2) {
			System.out.println("Invalid time format");
			return;
		}

		int hour = Integer.parseInt(parts[0]);
		int minutes = Integer.parseInt(parts[1]);;
		
		if(hour < 0 || hour > 12) {
			System.out.println("Hour can only be betwen 1 and 12");
			return;
		}
		
		if(minutes < 0 || minutes > 59) {
			System.out.println("Minutes can only be between 0 and 59");
			return;
		}

		if (minutes == 0) {
			handleZeroMinutes(hour, numsInWords[hour]);
		} else if (minutes == 15) {
			System.out.println("quarter past " + numsInWords[hour]);
		} else if (minutes == 30) {
			System.out.println("half past " + numsInWords[hour]);
		} else if (minutes == 45) {
			System.out.println("quarter to " + numsInWords[(hour % 12) + 1]);
		} else if (minutes <= 30) {
			handleMinutesLessThanThirty(numsInWords, hour, minutes);
		} else if (minutes > 30 && minutes <= 34) {
			System.out.println(numsInWords[hour] + " " + numsInWords[minutes]);
		} else if (minutes > 34) {
			handleWhenMinutesGreaterThanThirtyFive(numsInWords, hour, minutes);
		}

	}

	private void handleWhenMinutesGreaterThanThirtyFive(String[] numsInWords, int hour, int minutes) {
		if(minutes % 5 == 0) {
			System.out.println(numsInWords[60 - minutes] + " to " + numsInWords[(hour % 12) + 1]);
		} else {
			System.out.println(numsInWords[60 - minutes] + " minutes to " + numsInWords[(hour % 12) + 1]);
		}
	}

	private void handleMinutesLessThanThirty(String[] numsInWords, int hour, int minutes) {
		if(hour == 0) {
			hour = 12;
		}
		if(minutes % 5 == 0) {
			System.out.println(numsInWords[minutes] + " past " + numsInWords[hour]);
		} else {
			System.out.println(numsInWords[minutes] + " minutes past " + numsInWords[hour]);
		}
	}

	// Method to handle zero minutes when hour is 0 and 12
	private void handleZeroMinutes(int hour, String hourInWords) {
		if (hour == 0) {
			System.out.println("midnight");
		} else if (hour == 12) {
			System.out.println("noon");
		} else {
			System.out.println(hourInWords + " o'clock");
		}
	}

	public static void main(String[] args) {
		BritishSpokenTime bst = new BritishSpokenTime();
        Scanner scanner = new Scanner(System.in);
        String timePattern = "^(0[0-9]|1[0-2]):([0-5][0-9])$";
        Pattern pattern = Pattern.compile(timePattern);
        String inputTime = "";
        while (true) {
            System.out.print("Enter time in hh:mm format (12-hour format): ");
            inputTime = scanner.nextLine();
            
            Matcher matcher = pattern.matcher(inputTime);
            if (matcher.matches()) {
                break;
            } else {
                System.out.println("Invalid time format. Please enter time in hh:mm format only (12-hour format).");
            }
        }
        
        scanner.close();
        //Print the time in british spoken format
    	bst.printBritishSpokenTime(inputTime);
	}

}
