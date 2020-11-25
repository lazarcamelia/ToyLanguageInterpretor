
import controller.Controller;
import model.ProgramState;
import model.adt.*;
import model.expressions.*;
import model.types.BoolType;
import model.types.RefType;
import model.values.BoolValue;
import model.types.IntType;
import model.values.IValue;
import model.values.IntValue;
import model.statements.*;
import model.values.StringValue;
import repo.IRepo;
import repo.Repo;
import view.ExitCommand;
import view.RunExample;
import view.TextMenu;

import java.io.BufferedReader;

public class Main {
    public static void testFiles() {
        IStatement example1 = new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));

        IStack<IStatement> executionStack = new ADTStack<>();
        IMap<String, IValue> symbolTable = new ADTMap<>();
        IList<IValue> out = new ADTList<>();
        IMap<StringValue, BufferedReader> fileTable = new ADTMap<>();
        IHeap<Integer, IValue> heap = new ADTHeap<>();
        ProgramState programState = new ProgramState(executionStack, symbolTable, out, fileTable, heap, example1);

        String fileName = "test.in";
        StringValue filenameValue = new StringValue(fileName);
        ValueExpression fileNameExpression = new ValueExpression(filenameValue);
        OpenRFileStastement open = new OpenRFileStastement(fileNameExpression);
        open.execute(programState);

        symbolTable.add("varRead", new IntValue(0));
        ReadFileStatement read = new ReadFileStatement(fileNameExpression, "varRead");
        read.execute(programState);
        System.out.println(symbolTable.lookUp("varRead").toString());
        read.execute(programState);
        System.out.println(symbolTable.lookUp("varRead").toString());

        CloseRFileStatement close = new CloseRFileStatement(fileNameExpression);
        close.execute(programState);
    }

    public static void main(String[] args) {
        //testFiles();

        // int v; v=2; Print(v)
        IStatement example1 = new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));

        IStack<IStatement> executionStack1 = new ADTStack<>();
        IMap<String, IValue> symbolTable1 = new ADTMap<>();
        IList<IValue> out1 = new ADTList<>();
        IMap<StringValue, BufferedReader> fileTable1 = new ADTMap<>();
        IHeap<Integer, IValue> heap1 = new ADTHeap<>();
        ProgramState programState1 = new ProgramState(executionStack1, symbolTable1, out1, fileTable1, heap1, example1);
        IRepo repo1 = new Repo(programState1, "log1.txt");
        Controller controller1 = new Controller(repo1);

        // int a; int b; a=2+3*5; b=a+1; Print(b)
        IStatement example2 =  new CompoundStatement( new VariableDeclarationStatement("a",new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression('+',new ValueExpression(new IntValue(2)),new
                                ArithmeticExpression('*',new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignmentStatement("b",new ArithmeticExpression('+',new VariableExpression("a"), new
                                        ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));

        IStack<IStatement> executionStack2 = new ADTStack<>();
        IMap<String, IValue> symbolTable2 = new ADTMap<>();
        IList<IValue> out2 = new ADTList<>();
        IMap<StringValue, BufferedReader> fileTable2 = new ADTMap<>();
        IHeap<Integer, IValue> heap2 = new ADTHeap<>();
        ProgramState programState2 = new ProgramState(executionStack2, symbolTable2, out2, fileTable2, heap2, example2);
        IRepo repo2 = new Repo(programState2, "log2.txt");
        Controller controller2 = new Controller(repo2);

        // bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)
        IStatement example3 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignmentStatement("v",new ValueExpression(new
                                        IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
                                        VariableExpression("v"))))));

        IStack<IStatement> executionStack3 = new ADTStack<>();
        IMap<String, IValue> symbolTable3 = new ADTMap<>();
        IList<IValue> out3 = new ADTList<>();
        IMap<StringValue, BufferedReader> fileTable3 = new ADTMap<>();
        IHeap<Integer, IValue> heap3 = new ADTHeap<>();
        ProgramState programState3 = new ProgramState(executionStack3, symbolTable3, out3, fileTable3, heap3, example3);
        IRepo repo3 = new Repo(programState3, "log3.txt");
        Controller controller3 = new Controller(repo3);

        // int v; a=2; Print(v) - Wrong expression
        IStatement example4 = new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(new AssignmentStatement("a",new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));

        IStack<IStatement> executionStack4 = new ADTStack<>();
        IMap<String, IValue> symbolTable4 = new ADTMap<>();
        IList<IValue> out4 = new ADTList<>();
        IMap<StringValue, BufferedReader> fileTable4 = new ADTMap<>();
        IHeap<Integer, IValue> heap4 = new ADTHeap<>();
        ProgramState programState4 = new ProgramState(executionStack4, symbolTable4, out4, fileTable4, heap4, example4);
        IRepo repo4 = new Repo(programState4, "log4.txt");
        Controller controller4 = new Controller(repo4);

        IStatement example5 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
                new CompoundStatement(new AssignmentStatement("a",new RelationalExpression(new ValueExpression(new IntValue(4)),
                        new ValueExpression(new IntValue(5)), "<")), new PrintStatement(new VariableExpression("a"))));

        IStack<IStatement> executionStack5 = new ADTStack<>();
        IMap<String, IValue> symbolTable5 = new ADTMap<>();
        IList<IValue> out5 = new ADTList<>();
        IMap<StringValue, BufferedReader> fileTable5 = new ADTMap<>();
        IHeap<Integer, IValue> heap5 = new ADTHeap<>();
        ProgramState programState5 = new ProgramState(executionStack5, symbolTable5, out5, fileTable5, heap5, example5);
        IRepo repo5 = new Repo(programState5, "log5.txt");
        Controller controller5 = new Controller(repo5);

        //A4
        // int v; v=2; Print(v)
        // Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)
        IStatement example6 = new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                new PrintStatement(new VariableExpression("a")))))));

        IStack<IStatement> executionStack6 = new ADTStack<>();
        IMap<String, IValue> symbolTable6 = new ADTMap<>();
        IList<IValue> out6 = new ADTList<>();
        IMap<StringValue, BufferedReader> fileTable6 = new ADTMap<>();
        IHeap<Integer, IValue> heap6 = new ADTHeap<>();
        ProgramState programState6 = new ProgramState(executionStack6, symbolTable6, out6, fileTable4, heap6, example6);
        IRepo repo6 = new Repo(programState6, "log6.txt");
        Controller controller6 = new Controller(repo6);

        // Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a))+5)
        IStatement example7 = new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new readHeapExpression(new VariableExpression("v"))),
                                                new PrintStatement(new ArithmeticExpression('+',
                                                        new readHeapExpression(new readHeapExpression(new VariableExpression("a"))),
                                                        new ValueExpression(new IntValue(5)))))))));

        IStack<IStatement> executionStack7 = new ADTStack<>();
        IMap<String, IValue> symbolTable7 = new ADTMap<>();
        IList<IValue> out7 = new ADTList<IValue>();
        IMap<StringValue, BufferedReader> fileTable7 = new ADTMap<StringValue, BufferedReader>();
        IHeap<Integer, IValue> heap7 = new ADTHeap<Integer, IValue>();
        ProgramState programState7 = new ProgramState(executionStack7, symbolTable7, out7, fileTable7, heap7, example7);
        IRepo repo7 = new Repo(programState7, "log7.txt");
        Controller controller7 = new Controller(repo7);
        //controller5.allSteps();

        // Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);
        IStatement example8 = new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new PrintStatement(new readHeapExpression(new VariableExpression("v"))),
                                new CompoundStatement(new writeHeapStatement("v", new ValueExpression(new IntValue(30))),
                                        new PrintStatement(new ArithmeticExpression('+',
                                                new readHeapExpression(new VariableExpression("v")),
                                                new ValueExpression(new IntValue(5))))))));
        IStack<IStatement> executionStack8 = new ADTStack<IStatement>();
        IMap<String, IValue> symbolTable8 = new ADTMap<String, IValue>();
        IList<IValue> out8 = new ADTList<IValue>();
        IMap<StringValue, BufferedReader> fileTable8 = new ADTMap<StringValue, BufferedReader>();
        IHeap<Integer, IValue> heap8 = new ADTHeap<Integer, IValue>();
        ProgramState programState8 = new ProgramState(executionStack8, symbolTable8, out8, fileTable8, heap8, example8);
        IRepo repo8 = new Repo(programState8, "log8.txt");
        Controller controller8 = new Controller(repo8);
        //controller6.allSteps();

        // Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)));
        IStatement example9 = new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(30))),
                                                new PrintStatement(new readHeapExpression(new readHeapExpression(new VariableExpression("a")))))))));
        IStack<IStatement> executionStack9 = new ADTStack<>();
        IMap<String, IValue> symbolTable9 = new ADTMap<>();
        IList<IValue> out9 = new ADTList<>();
        IMap<StringValue, BufferedReader> fileTable9 = new ADTMap<>();
        IHeap<Integer, IValue> heap9 = new ADTHeap<>();
        ProgramState programState9 = new ProgramState(executionStack9, symbolTable9, out9, fileTable9, heap9, example9);
        IRepo repo9 = new Repo(programState9, "log9.txt");
        Controller controller9 = new Controller(repo9);
        //controller7.allSteps();


        // int v; v=4; (while (v>0) print(v);v=v-1);print(v)
        IStatement example10= new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(4))),
                        new CompoundStatement(new CompoundStatement(new WhileStatement(new RelationalExpression(
                                new VariableExpression("v"), new ValueExpression(new IntValue(0)), ">")),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                        new AssignmentStatement("v", new ArithmeticExpression('-',
                                                new VariableExpression("v"), new ValueExpression(new IntValue(1)))))),
                                new PrintStatement(new VariableExpression("v")))));
        IStack<IStatement> executionStack10 = new ADTStack<IStatement>();
        IMap<String, IValue> symbolTable10 = new ADTMap<String, IValue>();
        IList<IValue> out10= new ADTList<IValue>();
        IMap<StringValue, BufferedReader> fileTable10 = new ADTMap<StringValue, BufferedReader>();
        IHeap<Integer, IValue> heap10 = new ADTHeap<Integer, IValue>();
        ProgramState programState10 = new ProgramState(executionStack10, symbolTable10, out10, fileTable10, heap10, example10);
        IRepo repo10 = new Repo(programState10, "log10.txt");
        Controller controller10 = new Controller(repo10);


        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", example1.toString(), controller1));
        menu.addCommand((new RunExample("2", example2.toString(), controller2)));
        menu.addCommand(new RunExample("3", example3.toString(), controller3));
        menu.addCommand(new RunExample("4", example4.toString(), controller4));
        menu.addCommand(new RunExample("5", example5.toString(), controller5));
        menu.addCommand(new RunExample("6", example6.toString(), controller6));
        menu.addCommand(new RunExample("7", example7.toString(), controller7));
        menu.addCommand(new RunExample("8", example8.toString(), controller8));
        menu.addCommand(new RunExample("9", example9.toString(), controller9));
        menu.addCommand(new RunExample("10", example10.toString(), controller10));
        menu.show();
    }
}
