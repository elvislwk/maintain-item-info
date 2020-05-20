/*==================================================================================
* FSP 2014 Assignment
*
* Features done:
* Basic Requirements:
* 1. Initialisation(done)
* 2. Display menu(done)
* 3. List items(done)
* 4. Display available items(done)
* 5. Enquire average rental of items on loan(done)
* 6. Add new item(done)
* 7. Update status of an item(done)
* 8. Validation(done)
* 
* Additional Requirements:
* 9. Changing the name of an item’s category(done)
* 10. List the items as grouped by categories(done)
* 11. Remove an item(done)
*
* Descriptive Section:
* There are 3 things i need to change
* Firstly, in the init() method, initialise the serial number of the first 3 items as 1, 2, and 3 in ascending order instead of 2222, 1111, and 4444.
* Secondly, in the addItem() method, remove the line 181 to line 194 and add in "serialNos[itemCount] = itemCount + 1"
* so that the serial number will be automatically initialised when a new item is added.
* Lastly, change every System.out.printf part of the programme to
* "if (serialNos[i] > 9);
* System.out.printf("%-4d  %-10s  %-14s  %10s  %13.2f", i+1, categories[i], titles[i], "00"+serialNos[i], rentalCharges[i]);
* else
* System.out.printf(%-4d  %-10s  %-14s  %10s  %13.2f", i+1, categories[i], titles[i], "000"+serialNos[i], rentalCharges[i]);"
* so that single or double or triple digit serial numbers will still display as 4 digit.
=================================================================================*/
import java.util.*;
public class maintainItemInfo
{
   public static void main(String[] args)
   {
      final int MAX_ITEMS = 15; // maximum number of items
      final String[] categoryTypes = {"Manga", "Anime", "Video Game"}; // the 3 types
      String[] categories = new String[MAX_ITEMS]; // array for the categories
      String[] titles = new String[MAX_ITEMS]; // array for the titles
      int[] serialNos = new int[MAX_ITEMS]; // array for the serial numbers
      double[] rentalCharges = new double[MAX_ITEMS]; // array for the rental charges
      boolean[] statuses = new boolean[MAX_ITEMS]; // array to store the statuses
      int itemCount = init(categories, titles, serialNos, rentalCharges, statuses);
      int option;
      do {
         option = displayMenu();
         switch (option) {
            case 1: listItems(categories, titles, serialNos, rentalCharges, statuses, itemCount);
               break;
            case 2: displayAvailableItems(categories, titles, serialNos, rentalCharges, statuses, itemCount);
               break;
            case 3: Average(itemCount, rentalCharges, statuses);
               break;
            case 4: 
               if (itemCount < 15)//if there already exist 15 items
                  itemCount = addItem(categories, titles, serialNos, rentalCharges, statuses, itemCount, categoryTypes);
               else
                  System.out.println("Error: Maximum item added");
               break;
            case 5: updateNewItem(categories, titles, serialNos, rentalCharges, statuses, itemCount);
               break;
            case 6: changingItemCategory(categories, titles, serialNos, rentalCharges, statuses, itemCount, categoryTypes);
               break;
            case 7: listItemByCategory(categories, titles, serialNos, rentalCharges, statuses, itemCount, categoryTypes);
               break;
            case 8:  
               if (itemCount > 0)//if there is already 0 items
                  itemCount = removeItem(categories, titles, serialNos, rentalCharges, statuses, itemCount);
               else 
                  System.out.println("Error: No item detected");
               break;
            case 0: System.out.println("Exiting Application...");
               break;
            default: System.out.println("Error: Invalid input");
         }
      } while (option != 0);
   }
   //The method init() stores all the information of the arrays from item 1 to item 3 and returns the itemCount
   public static int init(String[] categories, String[] titles, int[] serialNos, double[] rentalCharges, boolean[] statuses) {
      categories[0] = "Manga";
      titles[0] = "The Renegade";
      serialNos[0] = 2222;
      rentalCharges[0] = 9.75;
      statuses[0] = false;
   
      categories[1] = "Anime";
      titles[1] = "Flying Pigs";
      serialNos[1] = 1111;
      rentalCharges[1] = 12.40;
      statuses[1] = true;
   
      categories[2] = "Video Game";
      titles[2] = "Mortar ConBet";
      serialNos[2] = 4444;
      rentalCharges[2] = 23.45;
      statuses[2] = false;
      return 3;
   }
   //The method displayMenu() displays the menu and accepts an option chosen by the user and returns the option
   public static int displayMenu() {
      Scanner input = new Scanner(System.in);
      System.out.println("MENU");
      System.out.println("=====");
      System.out.println("[1] List items");
      System.out.println("[2] Display available items");
      System.out.println("[3] Enquire average rental of items on loan");
      System.out.println("[4] Add new item");
      System.out.println("[5] Update status of an item");
      System.out.println("[6] Change category of an item");
      System.out.println("[7] List items as grouped by categories");
      System.out.println("[8] Remove an item");
      System.out.println("[0] Exit");
      System.out.print("Enter your option: ");
      int option = input.nextInt();
      return option;
   }
   //The method listItems() displays the information of the list of items
   public static void listItems(String[] categories, String[] titles, int[] serialNos, double[] rentalCharges, boolean[] statuses, int itemCount) {
      System.out.println("List items");
      System.out.println("==========");
      System.out.printf("%4s  %-10s  %-14s  %10s  %13s  %6s\n", "Item", "Category", "Title", "Serial No.", "Rental Charge", "Status");
      for (int i=0; i<itemCount; i++)
      {
         if (statuses[i] == true)//if the status of the current item is available
            System.out.printf("%-4d  %-10s  %-14s  %10d  %13.2f  Available\n", i+1, categories[i], titles[i], serialNos[i], rentalCharges[i]);
         else
            System.out.printf("%-4d  %-10s  %-14s  %10d  %13.2f  On Loan\n", i+1, categories[i], titles[i], serialNos[i], rentalCharges[i]);
      }
   }
   //The method displayAvailableItems() displays the information of the list of items that are available
   public static void displayAvailableItems(String[] categories, String[] titles, int[] serialNos, double[] rentalCharges, boolean[] statuses, int itemCount) {
      System.out.printf("%4s  %-10s  %-14s  %10s  %13s  %6s\n", "Item", "Category", "Title", "Serial No.", "Rental Charge", "Status");
      int x=1;
      for (int i=0; i<itemCount; i++) {
         if (statuses[i] == true)//if status of the current item is available
            System.out.printf("%-4d  %-10s  %-14s  %10d  %13.2f  Available\n", x++, categories[i], titles[i], serialNos[i], rentalCharges[i]);
      }
   }
   //The method Average() displays the average rental for all the items that are on loan
   public static void Average(int itemCount, double[] rentalCharges, boolean[] statuses) {
      System.out.println("Average rental of items on loan");
      System.out.println("===============================");
      System.out.printf("The average rental for all items on loan is $%.2f.\n", computeAverage(itemCount, rentalCharges, statuses));
   }
   //The method computeAverage() calculates the average rental for all the items on loan and returns the average
   public static double computeAverage(int itemCount, double[] rentalCharges, boolean[] statuses) {
      double sum=0, x=0, average;
      for (int i=0; i<itemCount; i++) {
         if (statuses[i] == false){//if the item status is "On loan"
            sum = sum + rentalCharges[i];
            x++;
         }
      }
      if (x == 0)
         average = 0;
      else
         average = sum/x;
      return average;
   }
   //The method addItem() accepts all the information of an item and stores it and returns the value of the updated itemCount
   public static int addItem(String[] categories, String[] titles, int[] serialNos, double[] rentalCharges, boolean[] statuses, int itemCount, String[] categoryTypes) {
      Scanner input = new Scanner(System.in);
      System.out.println("Add new item");
      System.out.println("============");
      int cate;
      do {
         System.out.print("Category (1-Manga 2-Anime 3-Video Game): ");
         cate = input.nextInt();
         if (cate > 3 || cate < 1)//if the input by user is out of range
            System.out.println("Error: Invalid input");
      } while(cate > 3 || cate < 1);
      categories[itemCount] = categoryTypes[cate-1];
      input.nextLine();
      System.out.print("Title: ");
      String title = input.nextLine();
      titles[itemCount] = title;
      int serial, i;
      do {
         System.out.print("Serial No.: ");
         serial = input.nextInt();
         for (i=0; i<itemCount; i++) {
            if (serial == serialNos[i]) {//if the input by user is the same as the serial number
               System.out.println("Error: Duplicate serial number detected");
               break;//to end the for loop if a duplicate is detected
            }
         }
         if (serial <= 0)//if the input by the user is less than or equals to 0
            System.out.println("Error: Invalid input");
      }while (serial == serialNos[i] || serial <= 0);
      serialNos[itemCount] = serial;
      double rental;
      do {
         System.out.print("Rental Charge : $");
         rental = input.nextDouble();
         if (rental < 0)//if the rental charge is a negative value
            System.out.println("Error: Invalid input");
      } while (rental < 0);
      rentalCharges[itemCount] = rental;
      statuses[itemCount] = true;
      System.out.println("One new item added.\n");
      itemCount++;
      listItems(categories, titles, serialNos, rentalCharges, statuses, itemCount);
      return itemCount;
   }
   //The method updateNewItem() accepts the number of an item and change the status of the item from "Available" to "On loan" or vice versa and stores them
   public static void updateNewItem(String[] categories, String[] titles, int[] serialNos, double[] rentalCharges, boolean[] statuses, int itemCount) {
      Scanner input = new Scanner(System.in);
      System.out.println("Update Status");
      System.out.println("=============");
      listItems(categories, titles, serialNos, rentalCharges, statuses, itemCount);
      int itemno;
      do {
         System.out.print("Enter item no.: ");
         itemno = input.nextInt();
         if (itemno > itemCount || itemno <= 0)//if the item number that the user input is out of range
            System.out.println("You have entered an invalid input...");
      } while (itemno > itemCount || itemno <= 0);
      if (statuses[itemno-1] == true)//if status of item is available
         statuses[itemno-1] = false;
      else
         statuses[itemno-1] = true;
      listItems(categories, titles, serialNos, rentalCharges, statuses, itemCount);
   }
   //The method changingItemCategory() accepts the serial number of an item and allow the user to change the category of the item.
   public static void changingItemCategory(String[] categories, String[] titles, int[] serialNos, double[] rentalCharges, boolean[] statuses, int itemCount, String[] categoryTypes) {
      Scanner input = new Scanner(System.in);
      listItems(categories, titles, serialNos, rentalCharges, statuses, itemCount);
      int serialno, cate, i;
      do {
         System.out.print("Enter item serial no.: ");
         serialno = input.nextInt();
         for (i=0; i<itemCount; i++) {
            if (serialno == serialNos[i]) {//if there is a match for the serial number input by the user and the serial number in the arrays
               do {
                  System.out.print("Enter new category (1-Manga 2-Anime 3-Video Game): ");
                  cate = input.nextInt();
                  if (cate > 3 || cate < 1)//if the input by the user is out of range(range = 1 to 3)
                     System.out.println("Error: Invalid input");
               } while (cate > 3 || cate < 1);
               categories[i] = categoryTypes[cate-1];
               listItems(categories, titles, serialNos, rentalCharges, statuses, itemCount);
               break;
            }
         }
         if (serialno != serialNos[i] || serialno == 0)//if there is no match for the serial number input by the user and the serial number in the arrays
            System.out.println("Error: Invalid input");
      } while (serialno != serialNos[i] || serialno == 0);
   }
   //The method listItemByCategory() displays the information of all the items as grouped by their category from "Manga" to "Anime" to "Video Game"
   public static void listItemByCategory(String[] categories, String[] titles, int[] serialNos, double[] rentalCharges, boolean[] statuses, int itemCount, String[] categoryTypes) { 
      System.out.println("List items as grouped by categories");
      System.out.println("===================================");
      System.out.printf("%-10s  %-14s  %10s  %13s  %6s\n", "Category", "Title", "Serial No.", "Rental Charge", "Status");
      for (int i=0; i<itemCount; i++) {
         if (categories[i] == categoryTypes[0]) {
            if (statuses[i] == true)//if the status of the item is available
               System.out.printf("%-10s  %-14s  %10d  %13.2f  Available\n", categories[i], titles[i], serialNos[i], rentalCharges[i]);
            else
               System.out.printf("%-10s  %-14s  %10d  %13.2f  On Loan\n", categories[i], titles[i], serialNos[i], rentalCharges[i]);
         }
      }
      for (int i=0; i<itemCount; i++) {
         if (categories[i] == categoryTypes[1]) {
            if (statuses[i] == true)//if the status of the item is available
               System.out.printf("%-10s  %-14s  %10d  %13.2f  Available\n", categories[i], titles[i], serialNos[i], rentalCharges[i]);
            else
               System.out.printf("%-10s  %-14s  %10d  %13.2f  On Loan\n", categories[i], titles[i], serialNos[i], rentalCharges[i]);
         }
      }
      for (int i=0; i<itemCount; i++) {
         if (categories[i] == categoryTypes[2]) {
            if (statuses[i] == true)//if the status of the item is available
               System.out.printf("%-10s  %-14s  %10d  %13.2f  Available\n", categories[i], titles[i], serialNos[i], rentalCharges[i]);
            else
               System.out.printf("%-10s  %-14s  %10d  %13.2f  On Loan\n", categories[i], titles[i], serialNos[i], rentalCharges[i]);
         }
      }
   }
   //The method removeItem() accepts the item number of an item and remove the information of the item. It then recalibrates the item number of every item that is affected according to what is being removed.
   public static int removeItem(String[] categories, String[] titles, int[] serialNos, double[] rentalCharges, boolean[] statuses, int itemCount) {
      Scanner input = new Scanner(System.in);
      listItems(categories, titles, serialNos, rentalCharges, statuses, itemCount);
      int itemno;
      do {
         System.out.print("Enter item no.: ");
         itemno = input.nextInt();
         if (itemno > itemCount || itemno < 1)//if input by user is out of range(range = 1 to the number of items)
            System.out.println("Error: Invalid input");
      } while (itemno > itemCount || itemno < 1);
      for (int i=0; i<= itemCount; i++) {
         if (i >= itemno) {//if the item number is bigger than or equals to the input by the user
            categories[i-1] = categories[i];
            titles[i-1] = titles[i];
            serialNos[i-1] = serialNos[i];
            rentalCharges[i-1] = rentalCharges[i];
            statuses[i-1] = statuses[i];
         }
      }
      itemCount--;
      listItems(categories, titles, serialNos, rentalCharges, statuses, itemCount);
      return itemCount;
   }
}