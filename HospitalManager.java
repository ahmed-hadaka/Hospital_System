package projects.hospitalSystem;

import java.util.Scanner;

public class HospitalManager {
	Scanner sc = new Scanner(System.in);
	final int MAX_SPECIALIZATION = 21;

	Hospital_queue[] queues = new Hospital_queue[MAX_SPECIALIZATION];

	HospitalManager() {
		for (int i = 1; i < MAX_SPECIALIZATION; ++i) {
			queues[i] = new Hospital_queue();
		}
	}

	void print_specializations() {
		for (int i = 1; i < MAX_SPECIALIZATION; ++i) {
			if (!queues[i].is_empty()) {
				System.out.println("There are " + queues[i].length + " patients in specialization " + i);
				queues[i].print_patient();
			}
		}
	}

	int print_menu() { // will handle invalid inputs
		int choice;
		while (true) {
			System.out.println("Enter your choice:  ");
			System.out.println("1) Add new patient  ");
			System.out.println("2) Print all patients  ");
			System.out.println("3) Get next patient  ");
			System.out.println("4) Exit  ");
			choice = sc.nextInt();
			if (choice > 4 || choice < 1) {
				System.out.println("Invalid choice, please try again ");
			} else
				return choice;
		}
	}

	int[] read_request(int specialization, int status) {
		int[] data = new int[2];
		while (true) {
			System.out.println("Enter specialization, status: ");
			data[0] = sc.nextInt();
			data[1] = sc.nextInt();
			if (!(data[0] <= 20 && data[0] >= 1) || (data[1] != 1 && data[1] != 0)) {
				System.out.println("Invalid specialization or status, please try again. ");
				continue;
			} else {
				System.out.println();
				break;
			}
		}
		return data;
	}

	void adding_patient(int specialization, String name, int status) {
		if (status == 1) // urgent
			queues[specialization].add_first(name);
		else
			queues[specialization].add_last(name);
	}

	void go() {
		while (true) {
			int[] data = new int[2];
			int specialization = 0, status = 0;
			String name = "";
			int choice = print_menu();
			if (choice == 1) { // add new patient
				System.out.println("Enter Your name: ");
				name = sc.next();
				data = read_request(specialization, status);
				specialization = data[0];
				status = data[1];
				if (queues[specialization].is_full()) {
					System.out.println("Sorry, this specialization is full\n");
				} else
					adding_patient(specialization, name, status);
			} else if (choice == 2) { // print all patient
				print_specializations();
			} else if (choice == 3) { // get next patient
				System.out.println("Enter specialization: ");
				specialization = sc.nextInt();
				queues[specialization].get_next_patient();
			} else
				break;
		}
	}
}
