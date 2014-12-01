package commons;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Utils {
	public interface Predicate<T> {
		public boolean apply(T input);
	}

	public interface Function2<R,T> {
		public R apply(R a, T b);
	}

	public static <T> Iterable<T> filter(Iterable<? extends T> c, Predicate<? super T> p) {
		List<T> result = new ArrayList<T>();

		for (T e : c) {
			if (p.apply(e)) {
				result.add(e);
			}
		}

		return result;
	}

	/**
	 * 
	 * 
	 * 
	 * @param i
	 * @param f
	 * @param z
	 *            start value
	 * @return the result of inserting {@code f} between consecutive elements of
	 *         this collection, going left to right with the start value
	 *         {@code z} on the left: {@code f(...f(z, x1), x2, ..., xn)} where
	 *         {@code x1, ..., xn} are the elements of this list.
	 */
	public static <R,T> R reduce(Collection<T> c, Function2<R,T> f, R z) {
		for (T e : c) {
			z = f.apply(z, e);
		}
		
		return z;
	}

	/**
	 * Concatenates elements using a {@code sep} as a separator
	 * @param c
	 * @param sep
	 * @return
	 */
	public static String mkString(Collection<String> c, final String sep) {
		if (c.isEmpty()) {
			return "";
		}
		
		String head = c.iterator().next();
		Collection<String> tail = new ArrayList<String>(c);
		tail.remove(head);
		
		return reduce(tail, new Function2<String, String>() {
			public String apply(String a, String b) {
				return a + sep + b;
			}
		}, head);
	}
	
	private Utils() {

	}

}
