package unipi.it.umdb;

import unipi.it.umdb.data.RegisteredUser;

public class UmdbUserSession {

    private static UmdbUserSession session = null;
    private RegisteredUser logged = null;
    private String registered = null;
    private boolean deleted = false;

    private UmdbUserSession() {}

    public static UmdbUserSession getInstance() {
        if(session == null) {
            session = new UmdbUserSession();
        }

        return session;
    }

    public void setLoggedUser(String userID) {
        if(session == null) {
            throw new RuntimeException("Session is not active.");
        } else {
            session.logged = new RegisteredUser(userID);
        }
    }

    public RegisteredUser getLoggedUser() {
        if(session == null) {
            throw new RuntimeException("Session is not active.");
        } else {
            return session.logged;
        }
    }

    public void setRegistered(String userID) {
        if(session == null) {
            throw new RuntimeException("Session is not active.");
        } else {
            session.registered = userID;
        }
    }

    public String getRegistered() {
        if(session == null) {
            throw new RuntimeException("Session is not active.");
        } else {
            return session.registered;
        }
    }

    public void setDeleted(boolean newDeleted) {
        if(session == null) {
            throw new RuntimeException("Session is not active.");
        } else {
            session.deleted = newDeleted;
        }
    }

    public boolean getDeleted() {
        if(session == null) {
            throw new RuntimeException("Session is not active.");
        } else {
            return session.deleted;
        }
    }

}
