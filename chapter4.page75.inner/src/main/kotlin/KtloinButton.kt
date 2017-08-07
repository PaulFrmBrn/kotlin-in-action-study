/**
 * @author PaulFrmBrn
 */
class KotlinButton : View {
    override fun getCurrentSate(): State {
        return KotlinButtonState()
    }

    override fun restoreState(stateKt: State) {
    }

    class KotlinButtonState : State

    inner class SomeOtherInnerClass : State {
        fun getOuterReference(): KotlinButton = this@KotlinButton
    }
}
