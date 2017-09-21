import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author PaulFrmBrn
 */
public class SomeJavaClass {

    public void callingKotlinLambda() {
        AppKt.processTheAnswer(arg -> arg + 1); // implementing Java lambda for Function1 Kotlin interface
    }

    public void callingKotlinLambdaWithAnonymousClass() {
        AppKt.processTheAnswer(new Function1<Integer, Integer>() {
            @Override
            public Integer invoke(Integer integer) {
                System.out.println(integer);
                return integer + 1;
            }
        });
    }

    public void usginKotlinExtensionFunction() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("42");
        strings.add("15");
        CollectionsKt.forEach(
                strings, // there should be receiver object passed
                s -> {
                    System.out.println(s);
                    return Unit.INSTANCE; // and instance of Unit class returned explicitly, if Kotlin lambda expects Unit as a result
                }
        );
    }

}
