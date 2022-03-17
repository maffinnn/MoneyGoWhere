package com.cz2006.group3.bean;

/**
 * User object represents individual user entity.
 */
public class UserData{
    /**
     * The unique identifier of a user.
     */
    int id;
    /**
     * The email of a user
     */
    String email;
    /**
     * The password of a user.
     */
    String password;
    /**
     * The username of a user.
     */
    String username;
    /**
     * The contact number of a user.
     */
    int phoneno;

    /**
     * User Constructor.
     */
    public UserData(int id, String email, String password, String username, int phoneno){
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.phoneno = phoneno;
    }

    /**
     * @return the password field of a user.
     */
    public String getPassword() { return this.password; }

    /**
     * @return the user identifier field of a suer.
     */
    public int getUID() { return this.id;}
    /**
     * Method to convert a user object to a JSON String.
     *
     * @return a user JSON String.
     */
    @Override
    public String toString() {
        return "{\"id\":" + id
                + ",\"email\":\"" + email
                + "\",\"password\":\"" + password
                + "\",\"username\":\"" + username
                + "\",\"phoneno\":\"" +phoneno
                + "\"}";
    }

}