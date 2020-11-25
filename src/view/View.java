//package view;
//
//import controller.Controller;
//import model.ProgramState;
//import model.values.IValue;
//import model.adt.*;
//import model.statements.IStatement;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class View {
//    private Controller controller;
//    private ArrayList<IStatement> statements;
//    private Scanner inputScanner;
//
//    public View(Controller controller, ArrayList<IStatement> statements) {
//        this.controller = controller;
//        this.statements = statements;
//        inputScanner = new Scanner(System.in);
//    }
//
//    public void run() {
//        while (true) {
//            try {
//                this.printMenu();
//                System.out.println("Your option is = ");
//                int value = inputScanner.nextInt();
//                inputScanner.nextLine();
//
//                if (value == 0) {
//                    break;
//                } else if (value == 1) {
//                    inputProgram();
//                } else if (value == 2) {
//                    executeCurrentProgram();
//                } else
//                    System.out.println("The option doesn't exist!");
//            }
//            catch (RuntimeException ex) {
//                System.out.println("Exception thrown: " + ex.getMessage());
//            }
//        }
//    }
//
//    private void printMenu() {
//        System.out.println("0. Exit!");
//        System.out.println("1. Input a program");
//        System.out.println("2. Complete execution of a program");
//    }
//
//    private void printAvailablePrograms() {
//        for (int i = 0; i < this.statements.size(); i++) {
//            System.out.println(String.valueOf(i + 1) + ": " +  this.statements.get(i).toString());
//        }
//    }
//
////    private void inputProgram() {
////        this.printAvailablePrograms();
////        System.out.println("Choose the program:");
////        int programOption = inputScanner.nextInt();
////        inputScanner.nextLine();
////
////        if (programOption < 1 || programOption > this.statements.size())
////            throw new RuntimeException("The program chose doesn't exist!");
////        else {
////            IStatement chosenStatement = this.statements.get(programOption - 1);
////            IStack<IStatement> executionStack = new ADTStack<IStatement>();
////            IMap<String, IValue> symbolTable = new ADTMap<String, IValue>();
////            IList<IValue> outputList = new ADTList<IValue>();
////
////            ProgramState programState = new ProgramState(executionStack, symbolTable, outputList, fileTable, chosenStatement);
////            this.controller.addProgram(programState);
////        }
////    }
//
//    private void executeCurrentProgram() throws RuntimeException {
//        this.controller.allSteps();
//    }
//
//}
