module org.pcsoft.jnap.test {
    requires jdk.incubator.foreign;
    requires org.pcsoft.jnap;
    requires junit;

    exports org.pcsoft.framework.jnap.test;
    opens org.pcsoft.framework.jnap.test.access;
}