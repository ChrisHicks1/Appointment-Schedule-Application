package model;

public class Users {
    private int User_ID;
    private String User_Name;
    private String Password;

    public Users(int User_ID, String User_Name, String Password){
        this.User_ID = User_ID;
        this.User_Name = User_Name;
        this.Password = Password;
    }

    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int User_ID) {
        this.User_ID = User_ID;
    }


    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String User_Name) {
        this.User_Name = User_Name;
    }


    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
}
