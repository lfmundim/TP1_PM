package Models;

import java.util.Scanner;

public class MyConsole{
    private static Scanner scanner;
    
    public MyConsole(){
        scanner = new Scanner(System.in);
    }

    public final void printLn(String str){
        System.out.println(str);
    }
	
	public String readLine(){
		String str = scanner.nextLine();
		clearConsole();
		return str;
	}

    public final static void clearConsole()
	{
		System.out.print("\033[2J\033[1;1H");
    }
}