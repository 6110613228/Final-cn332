package controller;

/**
 * Context
 */
public class Context {

    private Controller controller;

    public Context(Controller controller) {
        this.controller = controller;
    }

    public Boolean execute() {
        return controller.execute();
    }

    public Boolean execute(Object a, Object b) {
        return controller.execute(a, b);
    }
}