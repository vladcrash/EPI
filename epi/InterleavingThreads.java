package epi;

public class InterleavingThreads {

	private class Monitor {

		private static final boolean odd = true;
		private static final boolean even = false;
		private boolean turn = odd;

		private synchronized void waitTurn(boolean oldTurn) {
			while (turn != oldTurn) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		private synchronized void toggleTurn() {
			turn = !turn;
			notify();
		}
	}

	private void printNumbers() throws InterruptedException {
		Monitor monitor = new Monitor();

		Thread odd = new Thread(() -> {
			for (int i = 1; i <= 100; i += 2) {
				print(monitor, i, Monitor.odd);
			}
		});

		Thread even = new Thread(() -> {
			for (int i = 2; i <= 100; i += 2) {
				print(monitor, i, Monitor.even);
			}
		});

		odd.start();
		even.start();
	}

	private void print(Monitor monitor, int value, boolean turn) {
		monitor.waitTurn(turn);
		System.out.println(value);
		monitor.toggleTurn();
	}

	public static void main(String[] args) throws InterruptedException {
		InterleavingThreads threads = new InterleavingThreads();
		threads.printNumbers();
	}
}
