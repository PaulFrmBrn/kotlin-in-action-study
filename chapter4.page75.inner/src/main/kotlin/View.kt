/**
 * @author PaulFrmBrn
 */
interface State : java.io.Serializable

interface View {
    fun getCurrentSate(): State
    fun restoreState(stateKt: State)
}