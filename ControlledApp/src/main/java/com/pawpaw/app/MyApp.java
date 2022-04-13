package com.pawpaw.app;

import org.pawpaw.processor.annotations.Controlled;

@Controlled({Controlled.Function.PHONE_CALL, Controlled.Function.WE_CHAT})
public class MyApp {

    @Controlled({Controlled.Function.WE_CHAT})
    public void chat(String message) {
        Comparable<String> send = new Comparable<>() {
            @Override
            @Controlled({Controlled.Function.WE_CHAT})
            public int compareTo(String o) {
                return 0;
            }
        };
        System.out.println(send);
    }

    @Controlled({Controlled.Function.WE_CHAT})
    public void qq(String message) {
        Comparable<String> send = new Comparable<>() {
            @Override
            @Controlled({Controlled.Function.WE_CHAT})
            public int compareTo(String o) {
                return 0;
            }
        };
        System.out.println(send);
    }

    @Controlled({Controlled.Function.SMS, Controlled.Function.SMS, Controlled.Function.WE_CHAT})
    public void call(String message) {

    }


    @Controlled({Controlled.Function.WE_CHAT})
    class SubApp {
        @Controlled({Controlled.Function.SMS})
        class SubSubApp {

        }

    }
}
