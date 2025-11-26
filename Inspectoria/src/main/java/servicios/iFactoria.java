package servicios;

import db.ConexionGenericaDB;

public interface iFactoria {
    public ConexionGenericaDB getConexionDB() throws java.lang.ClassNotFoundException, java.lang.InstantiationException, java.lang.IllegalAccessException, java.lang.Exception;
    //aqui podria ir más servicios transversales a la aplicación, como correo, entre otros
}
