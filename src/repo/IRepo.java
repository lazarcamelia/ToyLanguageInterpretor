package repo;

import model.ProgramState;
import model.exceptions.LogException;

public interface IRepo {
    void addProgram(ProgramState newProgram);
    ProgramState getCurrentProgramState();
    void logProgramStateExec() throws LogException;
    void clearLogFile() throws LogException;
}
