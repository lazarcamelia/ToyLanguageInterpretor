package view;

import controller.Controller;

public class RunExample extends Command {
    private Controller controller;

    public RunExample(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() throws RuntimeException {
        try {
            controller.allSteps();
        }catch (RuntimeException ex)  {
            System.out.println(ex.getClass() + ": " + ex.getMessage());
        }
    }
}
