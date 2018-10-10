
In the project directory run

mvn clean package

Go to dist/target/dist-all. All the needed jars are will be here.

Now run 

java -javaagent:agent-module-1.0-SNAPSHOT.jar  -jar main-module-1.0-SNAPSHOT.jar


Output will be

Instrumented by MainEntryTransformer
Started..


Even though Entry.java (main-module) has no print statement other than 'Started..' but at the run time we manipulated the main method 
of this class and manipulated bytecode and injected another print statement 'Instrumented by MainEntryTransformer' before that.


Manipulation code is there inside MainEntryTransformer.java under agent-module.