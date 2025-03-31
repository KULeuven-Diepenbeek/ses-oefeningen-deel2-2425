package generics.oef07;

import java.util.function.Function;

public class FunctionUtils {


    /* Stel, we nemen als generische parameters
       - A: het parametertype van de samengestelde functie
       - B: het resultaat-type van f1
       - C: het resultaattype van de samengestelde functie
       dan kan (voor maximale flexibiliteit)
       - f1 als parameter-type elke super klasse van A hebben (? super A)
         PECS: f1 is een consument van A's
       - f1 als resultaattype B hebben (veronderstelling hierboven)
       - f2 als parameter-type elke superklasse van B hebben (? super B)
         PECS:  f2 is een consument van B's
       - f2 als resultaat-type elke subklasse van C hebben (? extends C)
         PECS: f2 is een producent van C's
       - de samengestelde functie als parametertype enkel A hebben (veronderstelling)
       - de samengestelde functie als resultaattype enkel C hebben (veronderstelling)

       Je kan ook andere keuzes maken; zie bv. compose_alt hieronder
     */

    public static <A, B, C> Function<A, C> compose(Function<? super A, B> f1, Function<? super B, ? extends C> f2) {
        return (x) -> f2.apply(f1.apply(x));
    }

    /* Stel, we nemen als generische parameters
       - A: het parametertype van de eerste functie f1
       - B: het parameter-type van f2
       - C: het resultaattype van de tweede functie f2
       dan kan (voor maximale flexibiliteit)
       - f1 als parameter-type enkel A hebben (veronderstelling)
       - f1 als resultaattype ? extends B hebben
         PECS: f1 is een producent van B's
       - f2 als parameter-type enkel B hebben (veronderstelling)
       - f2 als resultaat-type enkel C hebben (veronderstelling)
       - de samengestelde functie als parametertype ? super A hebben
         PECS: de samengestelde functie is een consument van A's
       - de samengestelde functie als resultaattype ? extends C hebben
         PECS: de samengestelde functie is een producent van C's
    */
    public static <A, B, C> Function<? super A, ? extends C> compose_alt(Function<A, ? extends B> f1, Function<B, C> f2) {
        return (x) -> f2.apply(f1.apply(x));
    }
}
