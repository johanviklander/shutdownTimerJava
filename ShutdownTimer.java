package shutdownTimer;

/**
 *
 * @author Johan Viklander.
 */
public class ShutdownTimer {

    /**
     * @param args \<duration\> as integer and [type] as "shutdown", "sleep" or
     * "hibernate".
     */
    public static void main(String[] args) {

        Timer timer = new Timer();          // The timer.
        Shutdown shutdown = new Shutdown(); // The shutdown.

        // Valid number of arguments?
        if (args.length < 1 || args.length > 2) {
            System.out.println("Usage: Shutdown <duration> [type]\n");
            return;
        }

        // Process a single argument.
        if (args.length == 1) {
            try {
                timer.setTimer(Integer.parseInt(args[0]));
            } catch (NumberFormatException e) {
                System.out.println("Error: " + e + ".\n");
                System.out.println("Usage: Shutdown <duration> [type]\n");
                return;
            }

            shutdown.setType("shutdown");
        }

        // Process a pair of arguments.
        if (args.length == 2) {
            try {
                timer.setTimer(Integer.parseInt(args[0]));
            } catch (NumberFormatException e) {
                System.out.println("Error: " + e + ".\n");
                System.out.println("Usage: Shutdown <duration> [type]\n");
                return;
            }

            if (shutdown.setType(args[1]) != 0) {
                System.out.println("Error: invalid shutdown type argument.");
                System.out.println("Valid types are: shutdown, restart and "
                        + "hibernate.");
                return;
            }
        }

        // Lets see if this baby flies.
        if (timer.isSet() == true && shutdown.isSet() == true) {
            // Last chance to save that word document.
            if (timer.startTimer() == 0){
                // One shutdown please.
                shutdown.execute();
            }
            // Uh, oh.
            else {
                System.out.println("The timer broke.\n");
            }
        }
    }
}
