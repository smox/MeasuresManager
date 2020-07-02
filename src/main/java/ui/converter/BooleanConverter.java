package ui.converter;

public abstract class BooleanConverter<T> {
    public abstract boolean toBoolean(T t);
    public abstract T fromBoolean(boolean bool);
}
