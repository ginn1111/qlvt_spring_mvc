package service;

public interface Validation<T> {
    String regexPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
    String regexPhone = "^([0]\\d{2}[- .]?)\\d{3}[- .]?\\d{4}$";
    String regexEmail = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    String validate(T t);
}
