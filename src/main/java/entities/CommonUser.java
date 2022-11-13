package entities;

public class CommonUser extends User{


    public CommonUser(String username, String password) {
        super(username, password);

    }
    @Override
    public boolean passwordIsValid() {
        return password != null && password.length() > 5;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
