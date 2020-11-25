package model;

import model.adt.IHeap;
import model.adt.IList;
import model.adt.IMap;
import model.adt.IStack;
import model.statements.IStatement;
import model.values.IValue;
import model.values.StringValue;

import java.io.BufferedReader;

public class ProgramState {
    private IStack<IStatement> executionStack;
    private IMap<String, IValue> symbolTable;
    private IList<IValue> outputList;
    private IMap<StringValue, BufferedReader> fileTable;
    private IHeap<Integer, IValue> heapTable;
    private IStatement originalProgram; //optional field, but good to have

    public ProgramState(IStack<IStatement> stack, IMap<String, IValue> table, IList<IValue> output, IMap<StringValue, BufferedReader> fileTable, IHeap<Integer, IValue> heapTable, IStatement originalPrg) {
        this.executionStack = stack;
        this.symbolTable = table;
        this.outputList = output;
        this.fileTable = fileTable;
        this.originalProgram = originalPrg;
        this.heapTable = heapTable;

        this.executionStack.push(this.originalProgram);
    }

    public IStack<IStatement> getExecutionStack() {
        return executionStack;
    }

    public IMap<String, IValue> getSymbolTable() {
        return symbolTable;
    }

    public IList<IValue> getOutputList() {
        return outputList;
    }

    public IMap<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public IHeap<Integer, IValue> getHeapTable() {
        return heapTable;
    }



    public void setSymbolTable(IMap<String, IValue> newSymbolTable) {
        this.symbolTable = newSymbolTable;
    }

    public void setExecutionStack(IStack<IStatement> newExecutionStack) {
        this.executionStack = newExecutionStack;
    }

    public void setOriginalProgram(IStatement originalProgram) {
        this.originalProgram = originalProgram;
    }

    public void setOutputList(IList<IValue> outputList) {
        this.outputList = outputList;
    }

    public void setHeapTable(IHeap<Integer, IValue> heapTable) {
        this.heapTable = heapTable;
    }


    @Override
    public String toString() {
        return "Stack: " + executionStack.toString() + "\n" + "Symbol Table: " + symbolTable.toString() + "\n" +
                "ADTHeap table: " + heapTable.toString() + "\n" + "Output list: " + outputList.toString()  +  "\n________________________________________________";
    }
}
