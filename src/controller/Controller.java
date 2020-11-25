package controller;

import model.ProgramState;
import model.adt.IList;
import model.adt.IMap;
import model.adt.IStack;
import model.statements.IStatement;
import model.values.IValue;
import model.values.RefValue;
import repo.IRepo;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    private IRepo repository;
    boolean display;

    public Controller(IRepo repo) {
        this.repository = repo;
        this.display = true;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public void addProgram(ProgramState newProgram) {
        repository.addProgram(newProgram);
    }

    public ProgramState oneStep(ProgramState state) {
        IStack<IStatement> executionStack = state.getExecutionStack();

        if (executionStack.isEmpty()) {
            throw new RuntimeException("The stack is empty!");
        }

        IStatement currentStatement = executionStack.pop();
        return currentStatement.execute(state);
    }

    public void allSteps() {
        ProgramState programState = repository.getCurrentProgramState();
        System.out.println(programState.toString());
        //clear log file
        this.repository.clearLogFile();
        //write programState
        this.repository.logProgramStateExec();

        while (!programState.getExecutionStack().isEmpty()) {
            oneStep(programState);
            // write the program state to file
            this.repository.logProgramStateExec();

            programState.getHeapTable().setContent(garbageCollector(getAddressesFromSymbolTable(programState.getSymbolTable().getContent().values(),
                    programState.getHeapTable().getContent().values()),
                    programState.getHeapTable().getContent()));

            System.out.println("After garbage collector");
            // write the program state to file
            this.repository.logProgramStateExec();

            if (this.display)
                System.out.println(programState.toString());
        }
    }

    public Map<Integer, IValue> garbageCollector(List<Integer> symbolTableAddresses, Map<Integer, IValue> heapTable) {
        return heapTable.entrySet().stream()
                .filter(e -> symbolTableAddresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public List<Integer> getAddressesFromSymbolTable(Collection<IValue> symbolTableValues, Collection<IValue> heapTable) {
        return Stream.concat(
                symbolTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {RefValue v1 = (RefValue)v; return v1.getAddress();}),


                heapTable.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {RefValue v1 = (RefValue)v; return v1.getAddress();})
        ).collect(Collectors.toList());
    }
}
