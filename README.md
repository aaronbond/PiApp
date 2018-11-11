## Possible improvements

### Parellelise estimation algorithm
The code in the pi approximator could very easily fan out it's computation by using co-routines as a threading model and
Splitting the computation into chunks and collating the results into an approximation.

### Better algorithm for estimating pi
I used the Leibniz/Gregory/Madhava algorithm which is very simple to implement but some more complex ones converge on pi
a lot faster.

### Dependency Injection
Given a bit more time I would have properly implemented dependency injection (with something like Dagger 2) instead of
managing dependencies manually. As part of this I would probably remove the service binder from being directly managed
from the main view-model and manage that from an abstraction which is injected in, and inject viewmodels into
activities / fragments etc

### Unit and Screen tests
I wrote a quick unit test around the pi estimator as a demonstration, however it would be nice to write more unit and
Screen tests to ensure the code is robust / testable and refactorable

### UI
Obviously a nicer UI, perhaps using one activity many fragments instead of just a single activity containing all
the UI

### Precision
The code uses Doubles for simplicity (and speed) however it would be more useful to refactor the code to use a 
BigDecimal or similar to allow for calculations at arbitrary precision

### Explicit exceptions in the type system
It is possible that some of the methods in the pi approximator class return an exception, it would be nice to either 
handle them in situ, or make the return type a Try or Either to indicate the method can throw an exception if I wanted
to ensure the consumer handled that case.

### Smaller improvements
There are many other improvements that could be made around the code base

    - PiEstimater could use separate observables for time elapsed and current estimate to reduce cost of making
     Progress objects
    - binding viewmodel to lifecycle of the activity
    - managing services in a more centralised / coherent manner
    - proguard to do tree shaking
    - using just coroutines or rxjava or using a defer monad to allow for changing at will