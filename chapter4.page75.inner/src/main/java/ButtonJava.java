import org.jetbrains.annotations.NotNull;

/**
 * @author PaulFrmBrn
 */
public class ButtonJava implements View {


    @Override
    public State getCurrentSate() {
        return null;
    }

    @Override
    public void restoreState(State stateKt) {
    }

    // not static by default
    public class ButtonState implements State {

    }

}
