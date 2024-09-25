package gitlet;

import javax.swing.text.Style;
import java.io.File;

/**
 * Driver class for Gitlet, a subset of the Git version-control system.
 *
 * @author TODO
 */
public class Main {

    private static final File CWD = Repository.CWD;

    /**
     * Usage: java gitlet.Main ARGS, where ARGS contains
     * <COMMAND> <OPERAND1> <OPERAND2> ...
     */
    public static void main(String[] args) {
        // TODO: what if args is empty?
        Init.setDefault();

        String firstArg = args[0];
        switch (firstArg) {
            case "init":
                // TODO: handle the `init` command
                Repository.setupPersistence();
                break;
            case "add":
                // TODO: handle the `add [filename]` command
                String fileName = args[1];
                Repository.addGitLet(fileName);
                break;
            // TODO: FILL THE REST IN
            case "commit":
                String message = args[1];
                Repository.commitGitLet(message);
                break;
        }
        return;
    }
}
