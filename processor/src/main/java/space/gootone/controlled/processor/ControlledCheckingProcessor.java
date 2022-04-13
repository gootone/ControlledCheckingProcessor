package space.gootone.controlled.processor;


import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ControlledCheckingProcessor extends AbstractProcessor {
    public static final String CONTROLLED_LIST_PARAMETER = "controlled.list";
    public static final String DEFAULT_CONTROLLED_LIST = "controlled.json";
    private static final String INIT_MESSAGE = "Init Controlled checking processor, output controlled list to: %s.";
    private static final String PROCESS_START_MESSAGE = "Start Controlled checking processor.";
    private static final String PROCESS_END_MESSAGE = "End Controlled checking processor.";

    private String controlledListFilename;
    private final ControlledReport report = new ControlledReport();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        // Remove the existing list file if needed, and create the list file.
        controlledListFilename = System.getProperty(CONTROLLED_LIST_PARAMETER);
        if (controlledListFilename == null || controlledListFilename.length() == 0) {
            controlledListFilename = DEFAULT_CONTROLLED_LIST;
        }
        Path controlledListFilePath = Path.of(controlledListFilename);
        try {
            Files.deleteIfExists(controlledListFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Messager messager = processingEnv.getMessager();
        output(messager, String.format(INIT_MESSAGE, controlledListFilename));
        super.init(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // Create a container for the controlled elements, and append to the list file in the end of the method.
        Messager messager = processingEnv.getMessager();
        output(messager, PROCESS_START_MESSAGE);

        for (TypeElement annotation : annotations) {
            for (Element element : roundEnv.getElementsAnnotatedWith(annotation)) {
                var controlled = element.getAnnotation(Controlled.class);
                output(messager, Controlled.class.getCanonicalName() + Arrays.toString(controlled.value()));
                output(messager, getElementFullName(element) + "@" + getOuterClass(element));
                report.addItem(getOuterClass(element).toString(),
                        new ControlledItem(getElementFullName(element), controlled.value()));
            }
        }
        try {
            Files.writeString(Path.of(controlledListFilename), report.toJson());
        } catch (IOException e) {
            e.printStackTrace();
        }
        output(messager, PROCESS_END_MESSAGE);
        return false;
    }

    private static void output(Messager messager, String message) {
        messager.printMessage(Diagnostic.Kind.NOTE, message);
        System.out.println(message);
    }

    private static String getElementFullName(Element element) {
        if (element.getKind() == ElementKind.METHOD) {
            String className = element.getEnclosingElement().toString();
            return className + "." + element;
        } else {
            return element.toString();
        }
    }

    private static Element getOuterClass(Element element) {
        Element enClosing = element.getEnclosingElement();
        if (enClosing.getKind() == ElementKind.PACKAGE) {
            return element;
        } else {
            return getOuterClass(enClosing);
        }
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        // processor supported annotations, equals to @SupportedAnnotationTypes
        Set<String> set = new HashSet<>();
        set.add(Controlled.class.getCanonicalName());
        return set;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
