package agent;

import java.lang.instrument.Instrumentation;

/**
 * Author: sazal
 * Date: 10/1/18
 */
public class Agent {
    public static void premain(String args, Instrumentation instrumentation) {
        MainEntryTransformer logger = new MainEntryTransformer();
        instrumentation.addTransformer(logger);
    }
}
