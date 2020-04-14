package fly.graphics;

public interface Event<T>
{
	public void invoke(T t);
}
