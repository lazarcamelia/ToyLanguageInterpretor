package repo;

import model.ProgramState;
import model.adt.*;
import model.exceptions.LogException;
import model.statements.IStatement;
import model.values.IValue;
import model.values.StringValue;

import java.io.*;


public class Repo implements IRepo {
    private ADTList<ProgramState> programStates;
    private String logFilePath;
    private ProgramState firstProgramState;

    public Repo(ProgramState firstProgramState, String logFilePath) {
        this.logFilePath = logFilePath;
        programStates = new ADTList<>();
        this.firstProgramState = firstProgramState;
        this.addProgram(firstProgramState);
    }

    @Override
    public void addProgram(ProgramState newProgram) {
        programStates.add(newProgram);
    }

    @Override
    public ProgramState getCurrentProgramState() {
        return programStates.pop();
    }

    @Override
    public void logProgramStateExec() {
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            IStack<IStatement> executionStack = firstProgramState.getExecutionStack();
            IMap<String, IValue> symbolTable = firstProgramState.getSymbolTable();
            IList<IValue> output = firstProgramState.getOutputList();
            IMap<StringValue, BufferedReader> fileTable = firstProgramState.getFileTable();
            IHeap<Integer, IValue> heapTable = firstProgramState.getHeapTable();

            writer.println("Execution stack:");
            writer.println(executionStack.toString());
            writer.println("__________________________________________________");

            writer.println("Symbol table:");
            writer.println(symbolTable.toString());
            writer.println("__________________________________________________");

            writer.println("Heap table:");
            writer.println(heapTable.toString());
            writer.println("__________________________________________________");

            writer.println("Output:");
            writer.println(output.toString());
            writer.println("__________________________________________________");

            writer.println("File table:");
            writer.println(fileTable.toString());
            writer.println("__________________________________________________");

            writer.println("\n");

        } catch (IOException e) {
            throw new LogException("The file cannot be open for writing!");
        }

        if (writer != null) {
            writer.close();
        }
    }

    @Override
    public void clearLogFile() throws LogException {
        try {
            PrintWriter printWriter = new PrintWriter(this.logFilePath);
            printWriter.print("");
            printWriter.close();

        } catch (FileNotFoundException e) {
            throw new LogException("The file for storing log was not found!");
        }
    }


}
