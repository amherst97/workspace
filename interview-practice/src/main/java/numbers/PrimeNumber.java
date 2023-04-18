package numbers;

public class PrimeNumber {
	
	// using brute force
	public static void findPrimeNumber2(int n) {
		boolean isPrime;
		
		for (int i = 2; i <= n; i++) {
			isPrime = true;
			for (int j = 2; j <= i/2; j++) {
				if (i % j == 0) {
					isPrime = false;
					break;
				}
			}
			
			if (isPrime)
				System.out.println(i);
		}
	}
	
	public static void findPrimeNumber1(int n) {
		boolean isPrime;
		
		for (int i = 2; i <= n; i++) {
			isPrime = true;
			for (int j = 2; j*j <= i; j++) {
				if (i % j == 0) {
					isPrime = false;
					break;
				}
			}
			
			if (isPrime)
				System.out.println(i);
		}
	}
	
	public static void findPrimeNumber(int n) {
		boolean[] primes = new boolean[50000];
		
		// Create a boolean array "prime[0..n]" and
        // initialize all entries it as true. A value in
        // prime[i] will finally be false if i is Not a
        // prime, else true.
		for (int i = 0; i <= n; i++) {
			primes[i] = true;
		}
		
		for (int p = 2; p * p <= n; p++) {
            // If prime[p] is not changed, then it is a
            // prime
            if (primes[p] == true) {
                // Update all multiples of p greater than or
                // equal to the square of it numbers which
                // are multiple of p and are less than p^2
                // are already been marked.
                for (int i = p * p; i <= n; i += p)
                    primes[i] = false;
            }
        }
		
		for (int i = 2; i <= n; i++) {
			if (primes[i]) {
				System.out.println(i);
			}
  		}
	}
	
	public static void main(String[] args) {
		findPrimeNumber(100);
	}
}
