package projects.hospitalSystem;

public class Hospital_queue {
	final int MAX_SPECIALIZATION = 21, MAX_QUEUE = 6;
	String[] names = new String[MAX_QUEUE];
	int[] status = new int[MAX_QUEUE];
	int length;

	Hospital_queue() {
		length = 0;
	}

	void remove_patient() {
		int i = 1;
		while (i < length) { // shifting left
			names[i] = names[i + 1];
			i++;
		}
		length--; // resize
	}

	void print_patient() {
		for (int i = 1; i <= length; ++i) {
			System.out.print(names[i]);
			if (status[i] == 1)
				System.out.println(" urgent ");
			else
				System.out.println(" regular ");
		}
		System.out.println(" ");
	}

	void shift_right() {
		for (int i = length; i > 0; i--) {
			names[i + 1] = names[i];
			status[i + 1] = status[i];
		}
	}

	void add_first(String name) { // urgent
		shift_right(); // shifting right
		names[1] = name; // add it
		length++; // resize the queue
		status[1] = 1; // set status
	}

	void add_last(String name) { // regular
		names[length + 1] = name;
		length++; // resize
	}

	boolean is_full() {
		return (length == MAX_QUEUE - 1);
	}

	boolean is_empty() {
		return (length == 0);
	}

	void get_next_patient() {
		if (is_empty())
			System.out.println("No patient at the moment, Have rest Dr ");
		else {
			System.out.println(names[1] + " Please go with the Dr");
			remove_patient();
		}
		System.out.println();
	}
}
