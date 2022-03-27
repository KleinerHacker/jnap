module org.pcsoft.jnap {
    requires lombok;
    requires slf4j.api;
    requires jdk.incubator.foreign;

    exports org.pcsoft.framework.jnap;
    exports org.pcsoft.framework.jnap.api;
    exports org.pcsoft.framework.jnap.exceptions;
}