/**
 * @author paul
 */
public class Button {

    private final View view;
    private OnClickListener listener;

    public Button(int viewId) {
        this.view = new View(viewId);
    }

    public void onClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public void click() {
        listener.onClick(view);
    }

}
