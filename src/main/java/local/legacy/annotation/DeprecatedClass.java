package local.legacy.annotation;



import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DeprecatedClass {
	Class<?> NewImpl();

}
