package space.gootone.controlled.processor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ControlledReportTest {

    @Test
    void test_toJson_emptyList() {
        ControlledReport report = new ControlledReport();
        Assertions.assertEquals("{\"controlledFileMap\":{}}",report.toJson());
    }
}