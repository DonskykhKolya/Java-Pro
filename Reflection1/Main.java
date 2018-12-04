import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
	public static void main(String[] args) {

		Class<?> cl = SomeClass.class;

		Method[] methods = cl.getDeclaredMethods();
		try {
            for (Method meth : methods) {
                if (meth.isAnnotationPresent(Test.class)) ;
                Test t = meth.getAnnotation(Test.class);
                int summa = (Integer) meth.invoke(null, t.x(), t.y());
                System.out.println(summa);
            }
        }catch (InvocationTargetException | IllegalAccessException e){
			    e.printStackTrace();
			}
		}
	}	

