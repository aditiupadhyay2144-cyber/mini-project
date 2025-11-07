import java.util.*;

class Movie {
    private String movieName;
    private int rows, cols;
    private int[][] seats; // 0 = available, 1 = booked

    public Movie(String name, int r, int c) {
        this.movieName = name;
        this.rows = r;
        this.cols = c;
        seats = new int[r][c];
    }

    public String getMovieName() {
        return movieName;
    }

    public void displaySeats() {
        System.out.println("Seat Availability for " + movieName + ":");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (seats[i][j] == 0)
                    System.out.print("[ ] ");
                else
                    System.out.print("[X] ");
            }
            System.out.println();
        }
    }

    public boolean bookSeat(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < cols && seats[row][col] == 0) {
            seats[row][col] = 1; // Mark seat as booked
            return true;
        }
        return false;
    }

    public boolean cancelBooking(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < cols && seats[row][col] == 1) {
            seats[row][col] = 0; // Mark seat as available
            return true;
        }
        return false;
    }
}

class BookingSystem {
    private List<Movie> movies = new ArrayList<>();

    public void addMovie(String movieName, int rows, int cols) {
        movies.add(new Movie(movieName, rows, cols));
    }

    public void displayMovies() {
        System.out.println("Movies available for booking:");
        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i + 1) + ". " + movies.get(i).getMovieName());
        }
    }

    public void bookTicket(int movieIndex, int row, int col) {
        if (movieIndex < 1 || movieIndex > movies.size()) {
            System.out.println("Invalid movie selection.");
            return;
        }

        Movie selectedMovie = movies.get(movieIndex - 1);
        if (selectedMovie.bookSeat(row, col)) {
            System.out.println("Seat booked successfully!");
        } else {
            System.out.println("Seat is already booked or invalid seat.");
        }
    }

    public void cancelTicket(int movieIndex, int row, int col) {
        if (movieIndex < 1 || movieIndex > movies.size()) {
            System.out.println("Invalid movie selection.");
            return;
        }

        Movie selectedMovie = movies.get(movieIndex - 1);
        if (selectedMovie.cancelBooking(row, col)) {
            System.out.println("Booking cancelled successfully!");
        } else {
            System.out.println("Seat is not booked or invalid seat.");
        }
    }

    public void displaySeats(int movieIndex) {
        if (movieIndex < 1 || movieIndex > movies.size()) {
            System.out.println("Invalid movie selection.");
            return;
        }

        movies.get(movieIndex - 1).displaySeats();
    }
}

public class MovieTicketSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookingSystem system = new BookingSystem();

        system.addMovie("Movie A", 5, 5);
        system.addMovie("Movie B", 5, 5);

        while (true) {
            System.out.println("\n1. Display Movies");
            System.out.println("2. Book Ticket");
            System.out.println("3. Cancel Ticket");
            System.out.println("4. Display Seats");
            System.out.println("5. Exit");

            int choice = getValidInt(sc, "Enter your choice: ");

            switch (choice) {
                case 1:
                    system.displayMovies();
                    break;
                case 2:
                    system.displayMovies();
                    int movieIndex = getValidInt(sc, "Select movie: ");
                    int row = getValidInt(sc, "Enter row (0-indexed): ");
                    int col = getValidInt(sc, "Enter column (0-indexed): ");
                    system.bookTicket(movieIndex, row, col);
                    break;
                case 3:
                    system.displayMovies();
                    movieIndex = getValidInt(sc, "Select movie: ");
                    row = getValidInt(sc, "Enter row (0-indexed): ");
                    col = getValidInt(sc, "Enter column (0-indexed): ");
                    system.cancelTicket(movieIndex, row, col);
                    break;
                case 4:
                    system.displayMovies();
                    movieIndex = getValidInt(sc, "Select movie to view seats: ");
                    system.displaySeats(movieIndex);
                    break;
                case 5:
                    System.out.println("Thank you! Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    // Helper method to safely get integer input
    private static int getValidInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                return sc.nextInt();
            } else {
                System.out.println("❌ Invalid input! Please enter a number.");
                sc.next(); // clear invalid token
            }
        }
    }
}
