package dev.cephx.vf;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSByRef;
import org.teavm.jso.JSFunctor;
import org.teavm.jso.JSObject;

import java.util.HashMap;
import java.util.Map;

public interface Options extends JSObject {
    @JSBody(script = "return this.options ? Object.entries(this.options) : [];")
    Option[] options();

    default Map<String, String> rawOptions() {
        final Map<String, String> options = new HashMap<>();
        for (final Options.Option option : this.options()) {
            options.put(option.name(), option.value());
        }

        return options;
    }

    // can't use the logical OR, it breaks the TeaVM minifier
    @JSBody(script = "return this.source ? this.source : (() => { return null; });")
    Source source();

    @JSBody(script = "return this.resources || [];")
    String[] resources();

    interface Option extends JSObject {
        @JSBody(script = "return this[0];")
        String name();

        @JSBody(script = "return this[1];")
        String value();
    }

    @JSFunctor
    interface Source extends JSObject {
        @JSByRef
        byte[] get(String name);
    }
}
