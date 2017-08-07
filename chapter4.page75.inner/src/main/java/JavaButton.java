import org.jetbrains.annotations.NotNull;

/**
 * @author PaulFrmBrn
 */
public class JavaButton implements View {


    @Override
    public State getCurrentSate() {
        return null;
    }

    @Override
    public void restoreState(State stateKt) {
    }

    // not static by default (nested)
    public class JavaButtonState implements State {

    }

}
