package shutdownTimer;

import java.io.IOException;

/**
 *
 * @author Johan Viklander.
 */
public class Shutdown {

    private String shutdownType; // shutdown, sleep or hybernate.
    private boolean shutdownTypeSet = false;

    public Shutdown() {
    }

    public Shutdown(String shutdownType) {
        this.setType(shutdownType);
    }

    public int setType(String shutdownType) {
        // Check if it's a valid shutdown type.
        switch (shutdownType.toLowerCase()) {
            case "restart":     break;
            case "shutdown":    break;
            case "hibernate":   break;
            default: System.out.println("Invalid shutdown type.");
            return 1;
        }
        this.shutdownType = shutdownType;
        shutdownTypeSet = true;
        return 0;
    }

    public boolean isSet() {
        return shutdownTypeSet;
    }

    public int execute() {
        if (shutdownTypeSet) {
            System.out.println("Shutting down with " + shutdownType + ".");
            // Use try in case the process cant execute the shutdown command.
            try {
                // Seems my timer isn't even necessary when using windows
                // shutdown command...
                // This is about as portable as a physical music collection.
                switch (shutdownType) {
                    case "restart": {
                        Process p = Runtime.getRuntime().exec("shutdown -r -f");
                        break;
                    }
                    case "shutdown": {
                        Process p = Runtime.getRuntime().exec("shutdown -s -f");
                        break;
                    }
                    case "hibernate": {
                        Process p = Runtime.getRuntime().exec("shutdown -h -f");
                        break;
                    }
                    default: {
                        // This really shouldn't happen!
                        System.out.println("Congratulations somehow you managed"
                                + " to start this method with an invalid"
                                + " shutdown type!");
                        return 1;
                    }
                }
            // Catch any errors.
            } catch (IOException e) {
                System.out.println("Couldn't shutdown computer.\n");
                return 1;
            }
            
            return 0; // About as neglected as the 9 button on a microwave oven.
            
        } else {
            System.out.println("Error: Type of shutdown not set.");
            return 1;
        }
    }
}
