package cinema;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Cinema {
    /** This method creates a 2D char array of Cinema hall.
     * @param numRows numCols
     * @return cinemaArray
     * */
    public static char[][] createCinemaArray(int numRows, int numCols) {
        char[][] cinemaArray = new char[numRows][numCols];
        for(int i = 0; i <= numRows - 1; i++) {
            for(int j = 0; j <= numCols - 1; j++) {
                cinemaArray[i][j] = 'S';
            }
        }
        return cinemaArray;
    }
    /** This method prints the elements in the cinemaArray in the requested format.
     * @param cinemaArray
     * Returns: void
     * */
    public static void printCinemaArray(char[][] cinemaArray) {
        // To print the top row of column numbers
        System.out.println();
        System.out.println("Cinema:");
        for(int i=0; i < 1; ++i){
            System.out.print(" ");
            for(int j = 0; j < cinemaArray[i].length; ++j) {
                System.out.print(" " + (j+1));
            }
        }
        System.out.println();
        // To print the elements and row numbers.
        for(int i = 0; i < cinemaArray.length; ++i) {
            System.out.print(i+1);
            System.out.print(" ");
            for(int j = 0; j < cinemaArray[i].length; ++j) {
                System.out.print(cinemaArray[i][j] + " ");
            }
            System.out.println();
        }
    }

    /** This method calculates the price of the ticket.
     * @return cinemaArray
     * */
    public static int calculateTicketPrice(int numRows, int numSeatsPerRow, int selectedRow) {
        int frontHalfRows;
        int ticketPrice;

        if (numRows * numSeatsPerRow <= 60) {
            //profit = 10 * numRows * numSeatsPerRow;
            ticketPrice = 10;
        } else if (numRows % 2 == 0) {
            frontHalfRows = numRows / 2;
            //profit = frontHalfRows * numSeatsPerRow * 10 + backHalfRows * numSeatsPerRow * 8;
            if (selectedRow <= frontHalfRows) {
                ticketPrice = 10;
            } else {
                ticketPrice = 8;
            }
        } else if (numRows == 9) {
            frontHalfRows = 4;
            //profit = (10 * frontHalfRows * numSeatsPerRow) + (8 * backHalfRows * numSeatsPerRow);
            if (selectedRow <= frontHalfRows) {
                ticketPrice = 10;
            } else {
                ticketPrice = 8;
            }
        } else {
            frontHalfRows = 3;
            //profit = (10 * frontHalfRows * numSeatsPerRow) + (8 * backHalfRows * numSeatsPerRow);
            if (selectedRow <= frontHalfRows) {
                ticketPrice = 10;
            } else {
                ticketPrice = 8;
            }
        }
        return ticketPrice;
    }

    /** This method calculates the total expected income based on seats in the Cinema.
     * @param numSeatsPerRow
     * Returns: int
     * */
    public static int calculateTotalIncome(int numRows, int numSeatsPerRow) {
        int expectedIncome;
        if (numRows * numSeatsPerRow > 60) {
            expectedIncome = numRows / 2 * numSeatsPerRow * 10 + (numRows - (numRows / 2)) * numSeatsPerRow * 8;
        } else {
            expectedIncome = numRows * numSeatsPerRow * 10;
        }
        return expectedIncome;
    }

    /** This method prints the Menu.
     * Returns: void
     * */
    public static void printMenu() {
        System.out.print( "1. Show the seats\n" +
                            "2. Buy a ticket\n" +
                            "3. Statistics\n" +
                            "0. Exit");
    }


    public static void main(String[] args) {
        // Write your code here

        DecimalFormat df = new DecimalFormat("0.00");
        Scanner scanner = new Scanner(System.in);
        // Taking user inputs for total number of rows and seats per row.


        System.out.print("Enter the number of rows: \n");
        int numRows = scanner.nextInt();
        System.out.print("Enter the number of seats in each row: \n");
        int numSeatsPerRow = scanner.nextInt();

        // Creating the Cinema Array.
        char[][] mainCinemaArray = createCinemaArray(numRows, numSeatsPerRow);

        int ticketsSold = 0;
        int currentIncome = 0;
        double soldPercentage;

        while (true) {
            // Printing the menu
            System.out.print("\n");
            printMenu();
            System.out.print("\n");
            int customerSelection = scanner.nextInt();

            while (true) {
                if (customerSelection == 0) {
                    return;
                }
                else if (customerSelection == 1) {
                    printCinemaArray(mainCinemaArray);
                    System.out.println();
                    break;
                }
                else if(customerSelection == 2) {

                    while (true) {
                        System.out.println("Enter a row number: ");
                        int selectedRow = scanner.nextInt();
                        System.out.println("Enter a seat number in that row: ");
                        int selectedSeat = scanner.nextInt();
                        if(selectedRow <= numRows && selectedSeat <= numSeatsPerRow) {
                            if(mainCinemaArray[selectedRow - 1][selectedSeat - 1] == 'B') {
                                System.out.println("That ticket has already been purchased!");
                                System.out.println();
                            }
                            else if (mainCinemaArray[selectedRow - 1][selectedSeat - 1] == 'S') {
                                int ticketPrice = calculateTicketPrice(numRows, numSeatsPerRow, selectedRow);
                                System.out.println();
                                System.out.println("Ticket price: $" + ticketPrice);
                                mainCinemaArray[selectedRow - 1][selectedSeat - 1] = 'B';
                                ticketsSold++;
                                currentIncome += ticketPrice;
                                break;
                            }
                        }  else  {
                            System.out.println("Wrong input!");
                            System.out.println();
                        }
                    }

                }
                else if (customerSelection == 3) {
                    soldPercentage = (double) (ticketsSold * 100) / (numRows * numSeatsPerRow);
                    int totalIncome = calculateTotalIncome(numRows,numSeatsPerRow);
                    System.out.println("Number of purchased tickets: " + ticketsSold);
                    System.out.println("Percentage: " + df.format(soldPercentage) + "%");
                    System.out.println("Current income: $" + currentIncome);
                    System.out.println("Total income: $" + totalIncome);
                    break;

                }
                else {
                    System.out.println("Wrong input!");
                    System.out.println();
                    break;
                }
                break;

            }

        }
    }
}